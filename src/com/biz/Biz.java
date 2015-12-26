package com.biz;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.AttendanceDao;
import com.dao.AuthorityDao;
import com.dao.GroupsDao;
import com.dao.UserDao;

@Component
public class Biz {
	@Autowired
	private AttendanceDao attendanceDao;
	@Autowired
	private AuthorityDao authorityDao;
	@Autowired
	private GroupsDao groupDao;
	@Autowired
	private UserDao userDao;

	public AttendanceDao getAttendanceDao() {
		return attendanceDao;
	}

	public AuthorityDao getAuthorityDao() {
		return authorityDao;
	}

	public GroupsDao getGroupDao() {
		return groupDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}


}
