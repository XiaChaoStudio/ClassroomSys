package com.xc.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.xc.dao.UserDao;
import com.xc.domain.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public User login(User user) {
		String hql = "from User where uid=? and upassword=?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, user.getUid(), user.getUpassword());
		System.out.println(list);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public User checkNum(User user) {
		String hql = "from User where uid=? or uemail=?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, user.getUid(), user.getUemail());
		System.out.println(list);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public User checkEmail(String uid, String uemail) {
		String hql = "from User where uid=? and uemail=?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, uid, uemail);
		System.out.println(list);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public User checkCode(User user) {
		String hql = "from User where activecode=?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, user.getActivecode());
		System.out.println(list);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}

	}

	@Override
	public User query(String uid) {
		User user = this.getHibernateTemplate().get(User.class, uid);
		return user;
	}

	@Override
	public int getCount() {
		String hql = "select count(*) from User";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if (list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<User> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		List<User> users = (List<User>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		for (User user : users) {
			System.out.println(user);
		}
		return users;
	}

	@Override
	public boolean delete(String uid) {
		User user = this.getHibernateTemplate().get(User.class, uid);
		this.getHibernateTemplate().delete(user);
		return true;
	}

	@Override
	public void regist(User user) {
		this.getHibernateTemplate().persist(user);
	}

	@Override
	public void update(User user) {
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().update(user);
	}

}
