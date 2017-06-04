package com.xc.dao;

import java.sql.Date;
import java.util.List;

import com.xc.domain.Classroom;
import com.xc.domain.Classroom_use;
import com.xc.domain.Timeinfo;

public interface ClassroomDao {

	List<Classroom> findByPage(int begin, int pageSize, int kind);

	int getCount(int kind);

	void add(Classroom classroom);

	List<Timeinfo> query();

	void delete(Classroom classroom);

	List<Classroom_use> findUse(Date date, int timeid);

	List<Classroom> findByName(String content, int kind);

	List<Classroom> findBySize(int size, int kind);

	List<Classroom> findBySite(String content, int kind);

	List<Classroom_use> findUseByWeek(String week, int timeid);

	Classroom getByName(String cname);

	void alter(Classroom classroom);

}
