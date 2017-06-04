package com.xc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.xc.dao.BorrowDao;
import com.xc.domain.Classroom;
import com.xc.domain.Classroom_use;
import com.xc.domain.User;

public class BorrowDaoImpl extends HibernateDaoSupport implements BorrowDao {

	@Override
	public boolean borrow(Classroom_use classroom_use) {
		this.getHibernateTemplate().persist(classroom_use);
		return true;
	}

	@Override
	public List<Classroom_use> listAll(String uid, int islonguse) {
		String hql = null;
		List<Classroom_use> classroom_uses = null;
		if (uid == null) {
			hql = "select use from Classroom_use use where use.islonguse=?";
			classroom_uses = (List<Classroom_use>) this.getHibernateTemplate().find(hql, islonguse);
		} else {
			hql = "select use from Classroom_use use where use.uid=? and use.islonguse=?";
			classroom_uses = (List<Classroom_use>) this.getHibernateTemplate().find(hql, uid, islonguse);
		}

		return classroom_uses;
	}

	@Override
	public boolean cancel(int recordid) {
		Classroom_use classroom_use = this.getHibernateTemplate().get(Classroom_use.class, recordid);
		this.getHibernateTemplate().delete(classroom_use);
		return true;

	}

	@Override
	public Classroom getRoom(String cname) {
		return this.getHibernateTemplate().get(Classroom.class, cname);
	}

	@Override
	public void agree(int recordid) {
		Classroom_use classroom_use = this.getHibernateTemplate().get(Classroom_use.class, recordid);
		if (classroom_use.getStatus() == 0)
			classroom_use.setStatus(1);
		this.getHibernateTemplate().persist(classroom_use);
	}

	@Override
	public Classroom_use getById(int recordid) {
		return this.getHibernateTemplate().get(Classroom_use.class, recordid);
	}

	@Override
	public User getUser(String uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}

	@Override
	public List<Classroom_use> listById(String uid, int islonguse, int status) {
		String hql = null;
		List<Classroom_use> classroom_uses = null;
		if (uid != null) {
			hql = "select use from Classroom_use use where use.uid=? and use.islonguse=? and use.status=?";
			classroom_uses = (List<Classroom_use>) this.getHibernateTemplate().find(hql, uid, islonguse, status);
		} else {
			hql = "select use from Classroom_use use where use.islonguse=? and use.status=?";
			classroom_uses = (List<Classroom_use>) this.getHibernateTemplate().find(hql, islonguse, status);
		}
		return classroom_uses;
	}

	@Override
	public List<Classroom_use> listByName(String cname, int islonguse, int status) {
		String hql = null;
		List<Classroom_use> classroom_uses = null;
		if (cname != null) {
			hql = "select use from Classroom_use use where use.cname=? and use.islonguse=? and use.status=?";
			classroom_uses = (List<Classroom_use>) this.getHibernateTemplate().find(hql, cname, islonguse, status);
		} else {
			hql = "select use from Classroom_use use where  use.islonguse=? and use.status=?";
			classroom_uses = (List<Classroom_use>) this.getHibernateTemplate().find(hql, islonguse, status);
		}
		return classroom_uses;
	}

}
