package com.xc.service.impl;

import java.util.List;

import com.xc.dao.UserDao;
import com.xc.domain.PageBean;
import com.xc.domain.User;
import com.xc.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public User query(String uid) {
		return userDao.query(uid);
	}

	@Override
	public PageBean<User> findByPage(Integer currPage) {
		PageBean<User> pageBean = new PageBean<User>();
		pageBean.setCurrPage(currPage);

		int pageSize = 3;
		pageBean.setPageSize(pageSize);

		int totalCount = userDao.getCount();
		pageBean.setTotalCount(totalCount);

		double temp = totalCount;
		Double totalPage = Math.ceil(temp / pageSize);
		pageBean.setTotalPage(totalPage.intValue());

		int begin = (currPage - 1) * pageSize;
		List<User> users = userDao.findByPage(begin, pageSize);
		pageBean.setList(users);
		return pageBean;
	}

	@Override
	public boolean delete(String uid) {
		return userDao.delete(uid);
	}

	@Override
	public User checkNum(User user) {
		return userDao.checkNum(user);
	}

	@Override
	public void regist(User user) {
		userDao.regist(user);
	}

	@Override
	public User checkCode(User user) {
		return userDao.checkCode(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public User checkEmail(String uid, String uemail) {
		return userDao.checkEmail(uid, uemail);
	}

}
