package com.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.IBiz.IBiz;
import com.dao.AttendanceDao;
import com.dao.AuthorityDao;
import com.dao.GroupDao;
import com.dao.UserDao;

@Component
public class Biz implements IBiz {
	@Autowired
	private AttendanceDao attendanceDao;
	@Autowired
	private AuthorityDao authorityDao;
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private UserDao userDao;

	@Override
	public AttendanceDao getAttendanceDao() {
		return attendanceDao;
	}

	@Override
	public AuthorityDao getAuthorityDao() {
		return authorityDao;
	}

	@Override
	public GroupDao getGroupDao() {
		return groupDao;
	}

	@Override
	public UserDao getUserDao() {
		return userDao;
	}

}
