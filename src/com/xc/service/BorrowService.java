package com.xc.service;

import java.util.List;

import com.xc.domain.Classroom;
import com.xc.domain.Classroom_use;
import com.xc.domain.User;

public interface BorrowService {

	boolean borrow(Classroom_use classroom_use);

	boolean cancel(int recordid);

	List<Classroom_use> listAll(String uid, int islonguse);

	Classroom getRoom(String cname);

	void agree(int recordid);

	Classroom_use getById(int recordid);

	User getUser(String uid);

	List<Classroom_use> listById(String uid, int islonguse, int status);

	List<Classroom_use> listByName(String cname, int islonguse, int status);

}
