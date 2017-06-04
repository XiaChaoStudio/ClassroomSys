package com.xc.action;

import java.security.GeneralSecurityException;
import java.sql.Date;
import java.util.List;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xc.domain.Classroom;
import com.xc.domain.Classroom_use;
import com.xc.domain.User;
import com.xc.service.BorrowService;
import com.xc.util.MailUtils;

public class BorrowAction extends ActionSupport implements ModelDriven<Classroom_use> {
	private Classroom_use classroom_use = new Classroom_use();

	private BorrowService borrowService;
	private int confirm;
	private int condition;
	private String content;

	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setBorrowService(BorrowService borrowService) {
		this.borrowService = borrowService;
	}

	@Override
	public Classroom_use getModel() {
		return classroom_use;
	}

	public String cancel() {
		boolean result = borrowService.cancel(classroom_use.getRecordid());
		if (result) {
			if (classroom_use.getIslonguse() == 0) {
				return "cancel_success0";
			} else {
				return "cancel_success1";
			}
		}
		return "cancel_fail";
	}

	public String agree() {
		classroom_use = borrowService.getById(classroom_use.getRecordid());
		User user = borrowService.getUser(classroom_use.getUid());
		if (classroom_use.getStatus() == 0) {
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
				mimeMessage.setSubject("教室预约系统通知邮件");
				if (classroom_use.getIslonguse() == 0) {
					mimeMessage.setContent("<h1>您好，" + user.getUid() + "!</h1><h2>您短期借用会议室" + classroom_use.getCname()
							+ "的申请已被同意，请去查看，并按时使用该会议室</h2>", "text/html;charset=utf-8");

				} else {
					mimeMessage.setContent("<h1>您好，" + user.getUid() + "!</h1><h2>您长期借用教室" + classroom_use.getCname()
							+ "的申请已被同意，请去查看，并按时使用该教室</h2>", "text/html;charset=utf-8");

				}
				MailUtils.sendMail(session, mimeMessage);
			} catch (Exception e) {
				e.printStackTrace();
				this.addActionError("发送邮件失败");
				return "sendemail_fail";

			}
		}
		borrowService.agree(classroom_use.getRecordid());
		if (classroom_use.getIslonguse() == 0) {
			return "agree_success0";
		} else {
			return "agree_success1";
		}
	}

	public String borrow() {
		if (ActionContext.getContext().getSession().get("date") != null) {
			ActionContext.getContext().getSession().remove("date");
		}
		if (ActionContext.getContext().getSession().get("week") != null) {
			ActionContext.getContext().getSession().remove("week");
		}
		if (ActionContext.getContext().getSession().get("timeid") != null) {
			ActionContext.getContext().getSession().remove("timeid");
		}
		classroom_use.setUid((String) ActionContext.getContext().getSession().get("LoginUserId"));
		Classroom classroom = borrowService.getRoom(classroom_use.getCname());
		if (classroom.getKind() == 2 || classroom_use.getIslonguse() == 1) {
			classroom_use.setStatus(0);
		} else {
			classroom_use.setStatus(1);
		}
		boolean result = borrowService.borrow(classroom_use);
		if (result) {
			if (classroom.getKind() == 1) {
				return "borrow_success1";
			} else {
				return "borrow_success2";
			}
		} else {
			return "borrow_fail";
		}
	}

	public String find() {
		List<Classroom_use> classroom_uses = null;
		if (content.length() == 0) {
			content = null;
		}
		if (condition == 1) {
			classroom_uses = borrowService.listById(content, classroom_use.getIslonguse(), confirm);
		} else {
			classroom_uses = borrowService.listByName(content, classroom_use.getIslonguse(), confirm);
		}

		if (classroom_uses.size() > 0) {
			ActionContext.getContext().getValueStack().set("Use_Record", classroom_uses);
			return "find_success";
		} else {
			return "find_null";
		}
	}

	public String invalid() {
		List<Classroom_use> classroom_uses = borrowService.listAll(null, classroom_use.getIslonguse());
		Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 3);
		for (int i = 0; i < classroom_uses.size(); i++) {
			if (classroom_uses.get(i).getDate().before(date)) {
				borrowService.cancel(classroom_uses.get(i).getRecordid());
			}
		}
		for (Classroom_use classroom_use : classroom_uses) {
			System.out.println(classroom_use);
		}
		return "invalid_success";
	}

	public String listAll() {
		String uid = null;
		if ((Integer) ActionContext.getContext().getSession().get("LoginUserIdentify") != 3) {
			uid = (String) ActionContext.getContext().getSession().get("LoginUserId");
		}
		List<Classroom_use> classroom_uses = borrowService.listAll(uid, classroom_use.getIslonguse());
		for (Classroom_use classroom_use : classroom_uses) {
			System.out.println(classroom_use);
		}
		ActionContext.getContext().getValueStack().set("Use_Record", classroom_uses);
		return "list_success";

	}

}
