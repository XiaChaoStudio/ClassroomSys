package com.xc.service.impl;

import java.sql.Date;
import java.util.List;

import com.xc.dao.ClassroomDao;
import com.xc.domain.Classroom;
import com.xc.domain.Classroom_use;
import com.xc.domain.PageBean;
import com.xc.domain.Timeinfo;
import com.xc.service.ClassroomService;

public class ClassroomServiceImpl implements ClassroomService {
	private ClassroomDao classroomDao;

	public void setClassroomDao(ClassroomDao classroomDao) {
		this.classroomDao = classroomDao;
	}

	@Override
	public PageBean<Classroom> findByPage(Integer currPage, int kind) {
		PageBean<Classroom> pageBean = new PageBean<Classroom>();
		pageBean.setCurrPage(currPage);

		int pageSize = 3;
		pageBean.setPageSize(pageSize);

		int totalCount = classroomDao.getCount(kind);
		pageBean.setTotalCount(totalCount);

		double temp = totalCount;
		Double totalPage = Math.ceil(temp / pageSize);
		pageBean.setTotalPage(totalPage.intValue());

		int begin = (currPage - 1) * pageSize;
		List<Classroom> classrooms = classroomDao.findByPage(begin, pageSize, kind);
		pageBean.setList(classrooms);
		return pageBean;
	}

	@Override
	public void add(Classroom classroom) {
		classroomDao.add(classroom);

	}

	@Override
	public List<Timeinfo> query() {
		return classroomDao.query();
	}

	@Override
	public void delete(Classroom classroom) {
		classroomDao.delete(classroom);
	}

	@Override
	public List<Classroom_use> findUse(Date date, int timeid) {
		return classroomDao.findUse(date, timeid);
	}

	@Override
	public List<Classroom> findByName(String content, int kind) {
		return classroomDao.findByName(content, kind);
	}

	@Override
	public List<Classroom> findBySize(int size, int kind) {
		return classroomDao.findBySize(size, kind);
	}

	@Override
	public List<Classroom> findBySite(String content, int kind) {
		return classroomDao.findBySite(content, kind);
	}

	@Override
	public List<Classroom_use> findUseByWeek(String week, int timeid) {
		return classroomDao.findUseByWeek(week, timeid);
	}

	@Override
	public Classroom getByName(String cname) {
		return classroomDao.getByName(cname);
	}

	@Override
	public void alter(Classroom classroom) {
		classroomDao.alter(classroom);
	}

}
