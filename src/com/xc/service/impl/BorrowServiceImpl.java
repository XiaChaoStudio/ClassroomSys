package com.xc.service.impl;

import java.util.List;

import com.xc.dao.BorrowDao;
import com.xc.domain.Classroom;
import com.xc.domain.Classroom_use;
import com.xc.domain.User;
import com.xc.service.BorrowService;

public class BorrowServiceImpl implements BorrowService {
	private BorrowDao borrowDao;

	public void setBorrowDao(BorrowDao borrowDao) {
		this.borrowDao = borrowDao;
	}

	@Override
	public boolean borrow(Classroom_use classroom_use) {
		return borrowDao.borrow(classroom_use);
	}

	@Override
	public boolean cancel(int recordid) {
		return borrowDao.cancel(recordid);
	}

	@Override
	public List<Classroom_use> listAll(String uid, int islonguse) {
		return borrowDao.listAll(uid, islonguse);
	}

	@Override
	public Classroom getRoom(String cname) {
		return borrowDao.getRoom(cname);
	}

	@Override
	public void agree(int recordid) {
		borrowDao.agree(recordid);
	}

	@Override
	public Classroom_use getById(int recordid) {
		return borrowDao.getById(recordid);
	}

	@Override
	public User getUser(String uid) {
		return borrowDao.getUser(uid);
	}

	@Override
	public List<Classroom_use> listById(String uid, int islonguse, int status) {
		return borrowDao.listById(uid, islonguse, status);
	}

	@Override
	public List<Classroom_use> listByName(String cname, int islonguse, int status) {
		return borrowDao.listByName(cname, islonguse, status);
	}

}
