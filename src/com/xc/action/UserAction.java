package com.xc.action;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xc.domain.PageBean;
import com.xc.domain.User;
import com.xc.listener.SessionListener;
import com.xc.service.UserService;
import com.xc.util.MD5Utils;
import com.xc.util.MailUtils;
import com.xc.util.UUIDUtils;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user = new User();
	private UserService userService;
	private Integer currPage = 1;
	private String password_old;
	private String password_new;
	private String altercode;

	public String getAltercode() {
		return altercode;
	}

	public void setAltercode(String altercode) {
		this.altercode = altercode;
	}

	public String getPassword_old() {
		return password_old;
	}

	public void setPassword_old(String password_old) {
		this.password_old = password_old;
	}

	public String getPassword_new() {
		return password_new;
	}

	public void setPassword_new(String password_new) {
		this.password_new = password_new;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getModel() {
		return user;
	}

	public String listAll() {
		PageBean<User> pageBean = userService.findByPage(currPage);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "list_success";
	}

	public String query() {
		String uid = (String) ActionContext.getContext().getSession().get("LoginUserId");
		user = userService.query(uid);
		ActionContext.getContext().getValueStack().set("user", user);
		return "query_success";
	}

	public String delete() {
		boolean result = userService.delete(user.getUid());
		if (result)
			return "delete_success";
		return "delete_fail";
	}

	public String logout() {
		SessionListener.removeSession(ServletActionContext.getRequest().getSession());
		if (ActionContext.getContext().getSession().get("LoginUserId") != null) {
			ActionContext.getContext().getSession().remove("LoginUserId");
			ActionContext.getContext().getSession().remove("LoginUserIdentify");
		}
		return "logout_success";
	}

	public String login() throws Exception {

		user.setUpassword(MD5Utils.md5Encode(user.getUpassword()));
		User result = userService.login(user);
		if (result == null) {
			this.addActionError("学号或者密码错误");
			return "input";
		}
		if (result.getStatus() == 0) {
			this.addActionError("邮件未激活，请前往邮箱激活");
			return "input";
		} else {
			boolean isEnter = SessionListener.isAlreadyEnter(ServletActionContext.getRequest().getSession(),
					user.getUid());
			if (isEnter) {
				this.addActionError("您的账号已在其他设备登录");
				return "input";
			}
			ActionContext.getContext().getSession().put("LoginUserId", result.getUid());
			ActionContext.getContext().getSession().put("LoginUserIdentify", result.getIdentify());
			return "login_success";
		}
	}

	public String regist() throws Exception {
		user.setUpassword(MD5Utils.md5Encode(user.getUpassword()));
		User result = userService.checkNum(user);
		if (result != null) {
			this.addActionError("学号或者邮箱重复");
			return "regist_fail";
		} else {
			user.setStatus(0);
			user.setActivecode(UUIDUtils.getUUID(10));
			user.setUpdatetime(System.currentTimeMillis());
			Session session = null;
			try {
				session = MailUtils.getSession();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MimeMessage mimeMessage = new MimeMessage(session);
			try {
				mimeMessage.setFrom(new InternetAddress("379299677@qq.com"));
				mimeMessage.setRecipients(MimeMessage.RecipientType.TO, user.getUemail());
				mimeMessage.setSubject("教室预约系统激活邮件");
				mimeMessage.setContent(
						"<h1>欢迎您注册XiaChaoStudio下的产品，您的用户名为" + user.getUid() + ", 请妥善保管!</h1><h2>请点下面链接进行激活:</h2>"
								+ "<h2><a href='http://www.xiachaostudio.cn:8080/ClassroomSys/user/user_active?activecode="
								+ user.getActivecode()
								+ "'>http://www.xiachaostudio.cn:8080/ClassroomSys/user/user_active?activecode="
								+ user.getActivecode() + "</a></h2>",
						"text/html;charset=utf-8");
				MailUtils.sendMail(session, mimeMessage);
				// mimeMessage.setContent(
				// "<h1>欢迎您注册XiaChaoStudio下的产品，您的用户名为" + user.getUid() + ",
				// 请妥善保管!</h1><h2>请点下面链接进行激活:</h2>"
				// + "<h2><a
				// href='http://www.xiachaostudio.cn.cn/ClassroomSys/user/user_active?activecode="
				// + user.getActivecode()
				// +
				// "'>http://www.xiachaostudio.cn.cn/ClassroomSys/user/user_active?activecode="
				// + user.getActivecode() + "</a></h2>",
				// "text/html;charset=utf-8");
				// MailUtils.sendMail(session, mimeMessage);
			} catch (Exception e) {
				e.printStackTrace();

				this.addActionError("发送邮件失败");
				return "regist_fail";

			}
			userService.regist(user);
		}
		return "regist_success";
	}

	public String send() throws IOException {
		User result = userService.checkEmail(user.getUid(), user.getUemail());
		HttpServletResponse response = ServletActionContext.getResponse();
		String altercode = UUIDUtils.getUUID(10);
		response.setContentType("text/html;charset=utf-8");
		if (result == null) {
			this.addActionError("学号或者邮箱错误");
			return "send_fail";
		} else {
			Session session = null;
			try {
				session = MailUtils.getSession();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MimeMessage mimeMessage = new MimeMessage(session);
			try {
				mimeMessage.setFrom(new InternetAddress("379299677@qq.com"));
				mimeMessage.setRecipients(MimeMessage.RecipientType.TO, user.getUemail());
				mimeMessage.setSubject("教室预约系统更改密码邮件");
				mimeMessage.setContent("<h1>您好，" + result.getUid() + ", 您正在更改密码!</h1><h2>请点下面链接进行密码更改:</h2>"
						+ "<h2><a href='http://www.xiachaostudio.cn:8080/ClassroomSys/user/user_confirm?altercode="
						+ altercode + "'>http://www.xiachaostudio.cn:8080/ClassroomSys/user/user_confirm?altercode="
						+ altercode + "</a></h2>", "text/html;charset=utf-8");
				MailUtils.sendMail(session, mimeMessage);
			} catch (Exception e) {
				e.printStackTrace();
				this.addActionError("发送邮件失败");
				return "send_fail";
			}

			result.setStatus(2);
			result.setActivecode(altercode);
			userService.update(result);

		}
		response.getWriter().write("<h3>邮件已经发送，请前往邮箱更改密码</h3>");
		return Action.NONE;
	}

	public String confirm() throws IOException {
		user.setActivecode(altercode);
		User result = userService.checkCode(user);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if (result.getStatus() == 2) {
			if (result == null) {
				response.getWriter().write(
						"更改码无效，请重新确认，去<a href='http://www.xiachaostudio.cn:8080/ClassroomSys/users/Users_forget.jsp'>确认信息</a>");
				return Action.NONE;
			} else {
				ActionContext.getContext().getValueStack().set("user", result);
				return "confirm_success";
			}
		} else {
			response.getWriter().write(
					"已修改密码，去<a href='http://www.xiachaostudio.cn:8080/ClassroomSys/users/Users_login.jsp'>登录</a>");
			return Action.NONE;
		}

	}

	public String active() throws IOException {
		User result = userService.checkCode(user);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if (result == null) {
			response.getWriter().write(
					"激活码无效，请重新注册，去<a href='http://www.xiachaostudio.cn:8080/ClassroomSys/users/Users_regist.jsp'>注册</a>");
		} else if (System.currentTimeMillis() - result.getUpdatetime() > 1000 * 60 * 60 * 24) {
			response.getWriter().write(
					"激活码超时，请重新注册，去<a href='http://www.xiachaostudio.cn:8080/ClassroomSys/users/Users_regist.jsp'>注册</a>");
		} else {
			result.setStatus(1);
			userService.update(result);
			response.getWriter().write("邮件激活成功，去<a href='http://www.xiachaostudio.cn:8080/ClassroomSys/'>登录</a>");
		}
		return Action.NONE;
	}

	public String alterpass() throws Exception {
		String uid = (String) ActionContext.getContext().getSession().get("LoginUserId");
		user = userService.query(uid);
		password_old = MD5Utils.md5Encode(password_old);
		password_new = MD5Utils.md5Encode(password_new);
		if (password_old.equals(user.getUpassword())) {
			user.setUpassword(password_new);
			userService.update(user);
			return "alterpass_success";
		} else {
			this.addActionError("原密码错误");
			return "alterpass_fail";
		}
	}

	public String queryInfo() {
		user = userService.query(user.getUid());
		ActionContext.getContext().getValueStack().set("user", user);
		return "queryInfo_success";
	}

	public String alterInfo() {
		User user_old = userService.query(user.getUid());

		// BeanUtils.copyProperties(user_old, user);
		user_old.setUemail(user.getUemail());
		user_old.setDeptname(user.getDeptname());
		user_old.setIdentify(user.getIdentify());
		userService.update(user_old);
		return "alterInfo_success";
	}

	public String update() throws Exception {
		user.setUpassword(MD5Utils.md5Encode(user.getUpassword()));
		User user_old = userService.query(user.getUid());
		user_old.setUpassword(user.getUpassword());
		user_old.setStatus(1);
		userService.update(user_old);
		ActionContext.getContext().getSession().put("LoginUserId", user_old.getUid());
		ActionContext.getContext().getSession().put("LoginUserIdentify", user_old.getIdentify());
		return "update_success";
	}
}
