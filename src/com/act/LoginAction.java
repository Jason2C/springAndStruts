package com.act;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.biz.Biz;
import com.pojo.Attendance;
import com.pojo.User;

public class LoginAction {
	@Autowired
	private Biz biz;
	private User user;
	private String msg;
	private List<Attendance> alist;
	private int alistlg;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Action(value = "login", results = { @Result(location = "/loginPage.jsp", name = "err"), @Result(location = "findatt",type="chain", name = "success") })
	public String login() {
		User user1 = biz.getUserDao().findByName(user.getUname());
		if (user1 != null) {
			if (user1.getPwd().equals(user.getPwd())) {
				ServletActionContext.getRequest().getSession().setAttribute("loginuser", user1);
				msg = "µÇÂ½³É¹¦";
				Attendance attendance1 = biz.getAttendanceDao().findByOne(user1.getUid(), new Date());
				if (attendance1 == null) {
					Attendance attendance = new Attendance();
					attendance.setEmpid(user1.getUid());
					attendance.setAttendance_day(new Date());
					attendance.setAttendance_every(new Date());
					biz.getAttendanceDao().insert(attendance);

				}
				
				return "success";
			} else {
				msg = "µÇÂ½Ê§°Ü";
			}
		} else {
			msg = "µÇÂ½Ê§°Ü";
		}
		return "err";
	}

	@Action(value = "findatt", results = { @Result(location = "/index.jsp", name = "success") })
	public String findatt() {
		alist = biz.getAttendanceDao().findAll();
		alistlg = alist.size();
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

	public List<Attendance> getAlist() {
		return alist;
	}

	public void setAlist(List<Attendance> alist) {
		this.alist = alist;
	}

	public int getAlistlg() {
		return alistlg;
	}

	public void setAlistlg(int alistlg) {
		this.alistlg = alistlg;
	}

}
