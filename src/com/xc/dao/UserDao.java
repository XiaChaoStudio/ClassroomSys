package com.xc.dao;

import java.util.List;

import com.xc.domain.User;

public interface UserDao {

	User login(User user);

	User query(String uid);

	int getCount();

	List<User> findByPage(int begin, int pageSize);

	boolean delete(String uid);

	User checkNum(User user);

	void regist(User user);

	User checkCode(User user);

	void update(User user);

	User checkEmail(String uid, String uemail);

}
