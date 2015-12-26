package com.act;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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

@SuppressWarnings("all")
public class AttendanceAction {

	@Autowired
	private Biz biz;
	private Attendance attendance;
	private List<User> list;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String msg;

	@Action(value = "fillAtt", results = { @Result(location = "/index.jsp", name = "success") })
	public String fillAtt() {

		return "success";
	}

	@Action(value = "gofillAtt", results = { @Result(location = "/fillatt.jsp", name = "success") })
	public String gofillAtt() {
		list = biz.getUserDao().findAll();
		return "success";
	}

	@Action(value = "afillAtt", results = { @Result(location = "findatt", type = "chain", name = "success") })
	public String afillAtt() throws UnsupportedEncodingException, ParseException {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginuser");
		Attendance attendance1 = biz.getAttendanceDao().findByOne(user.getUid(), attendance.getAttendance_time());
		if (attendance1 == null) {
			attendance.setAttendance_day(attendance.getAttendance_time());
			attendance.setAttendance_every(attendance.getAttendance_time());
			attendance.setException_explain(new String(attendance.getException_explain().getBytes("ISO-8859-1"), "utf-8"));
			attendance.setEmpid(user.getUid());
			biz.getAttendanceDao().Insert(attendance);
			msg = "…Í«Î≥…π¶£°£°£°";
		}
		return "success";
	}

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}