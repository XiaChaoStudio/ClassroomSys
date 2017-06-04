package com.xc.service;

import com.xc.domain.PageBean;
import com.xc.domain.User;

public interface UserService {

	User login(User user);

	User query(String uid);

	PageBean<User> findByPage(Integer currPage);

	boolean delete(String uid);

	User checkNum(User user);

	void regist(User user);

	User checkCode(User user);

	void update(User user);

	User checkEmail(String uid, String uemail);

}
