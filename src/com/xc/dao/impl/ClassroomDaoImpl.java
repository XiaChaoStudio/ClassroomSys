package com.xc.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.xc.dao.ClassroomDao;
import com.xc.domain.Classroom;
import com.xc.domain.Classroom_use;
import com.xc.domain.Timeinfo;

public class ClassroomDaoImpl extends HibernateDaoSupport implements ClassroomDao {

	@Override
	public List<Classroom> findByPage(int begin, int pageSize, int kind) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Classroom.class);
		criteria.add(Restrictions.or(Restrictions.eq("kind", kind)));
		List<Classroom> classrooms = (List<Classroom>) this.getHibernateTemplate().findByCriteria(criteria, begin,
				pageSize);

		return classrooms;
	}

	@Override
	public int getCount(int kind) {
		String hql = "select count(*) from Classroom where kind=?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, kind);
		if (list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public void add(Classroom classroom) {
		this.getHibernateTemplate().persist(classroom);
	}

	@Override
	public List<Timeinfo> query() {
		List<Timeinfo> list = this.getHibernateTemplate().loadAll(Timeinfo.class);
		return list;
	}

	@Override
	public void delete(Classroom classroom) {
		this.getHibernateTemplate().delete(classroom);
	}

	@Override
	public List<Classroom_use> findUse(Date date, int timeid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Classroom_use.class);
		criteria.add(Restrictions.and(Restrictions.eq("date", date), Restrictions.eq("timeid", timeid)));
		List<Classroom_use> classroom_uses = (List<Classroom_use>) this.getHibernateTemplate().findByCriteria(criteria);
		return classroom_uses;
	}

	@Override
	public List<Classroom_use> findUseByWeek(String week, int timeid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Classroom_use.class);
		criteria.add(Restrictions.and(Restrictions.eq("week", week), Restrictions.eq("timeid", timeid)));
		List<Classroom_use> classroom_uses = (List<Classroom_use>) this.getHibernateTemplate().findByCriteria(criteria);
		return classroom_uses;
	}

	@Override
	public List<Classroom> findByName(String content, int kind) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Classroom.class);
		criteria.add(Restrictions.and(Restrictions.eq("cname", content), Restrictions.eq("kind", kind)));
		List<Classroom> classrooms = (List<Classroom>) this.getHibernateTemplate().findByCriteria(criteria);
		return classrooms;
	}

	@Override
	public List<Classroom> findBySize(int size, int kind) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Classroom.class);
		criteria.add(Restrictions.and(Restrictions.ge("size", size), Restrictions.eq("kind", kind)));
		@SuppressWarnings("unchecked")
		List<Classroom> classrooms = (List<Classroom>) this.getHibernateTemplate().findByCriteria(criteria);

		return classrooms;
	}

	@Override
	public List<Classroom> findBySite(String content, int kind) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Classroom.class);
		criteria.add(Restrictions.and(Restrictions.eq("kind", kind)));
		criteria.add(Restrictions.like("cname", content, MatchMode.START));
		List<Classroom> classrooms = (List<Classroom>) this.getHibernateTemplate().findByCriteria(criteria);

		return classrooms;
	}

	@Override
	public Classroom getByName(String cname) {
		return this.getHibernateTemplate().get(Classroom.class, cname);
	}

	@Override
	public void alter(Classroom classroom) {
		this.getHibernateTemplate().update(classroom);
	}

}
