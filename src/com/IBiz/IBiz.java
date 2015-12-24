package com.IBiz;

import com.dao.AttendanceDao;
import com.dao.AuthorityDao;
import com.dao.GroupDao;
import com.dao.UserDao;

public interface IBiz {
	public AttendanceDao getAttendanceDao();

	public AuthorityDao getAuthorityDao();

	public GroupDao getGroupDao();

	public UserDao getUserDao();
}
