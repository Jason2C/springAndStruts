package com.act;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.IBiz.IBiz;
import com.dao.AttendanceDao;
import com.dao.UserDao;
import com.pojo.Attendance;
import com.pojo.User;

public class LoginAction {
	@Autowired
	private IBiz biz;
	private User user;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AttendanceDao attendanceDao;
	private String msg;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Action(value = "login", results = { @Result(location = "/index.jsp", name = "success") })
	public String login() {
	System.out.println(biz.getUserDao().findByName(user.getUname()));
		User user1 = userDao.findByName(user.getUname());
		if (user1 != null) {
			if (user1.getPwd().equals(user.getPwd())) {
				msg = "µÇÂ½³É¹¦";
				if (attendanceDao.findByOne(user1.getUid(), sdf.format(new Date())) == null) {
					Attendance attendance = new Attendance();
					attendance.setUid(user1.getUid());
					attendance.setAttendance_day(new Date());
					attendance.setAttendance_every(new Date());
					attendanceDao.insert(attendance);
				}
			} else {
				msg = "µÇÂ½Ê§°Ü";
			}
		} else {
			msg = "µÇÂ½Ê§°Ü";
		}
		return "success";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
