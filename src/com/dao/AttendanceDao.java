package com.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.pojo.Attendance;

@SuppressWarnings("all")
public class AttendanceDao extends BaseDao {
	public Attendance findByOne(int kid, String day) {
		String sql = super.getSelectAll() + " where uid = ? and attendance_day = ?";
		return (Attendance) super.findOne(sql, new Object[] { kid, day });
	}

	public int insert(Attendance attendance) {
		return super.Insert(attendance);
	}
}
