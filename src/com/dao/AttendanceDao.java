package com.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.pojo.Attendance;

@SuppressWarnings("all")
public class AttendanceDao extends BaseDao {
	public Attendance findByOne(int empid, Date day) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sql = super.getSelectAll() + " where empid = ? and attendance_day = ?";
		return (Attendance) super.findOne(sql, new Object[] { empid, sdf.format(day) });
	}

	public int insert(Attendance attendance) {
		return super.Insert(attendance);
	}

	public List<Attendance> findAll() {
		return super.findAll();
	}
}
