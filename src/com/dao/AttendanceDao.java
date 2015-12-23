package com.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class AttendanceDao extends BaseDao {

	public String find(int id) {
		return (String) super.getJdbcTemplate().queryForObject("select attendance_day from t_Attendance where kid = ?", new Object[] { id }, String.class);
	}

	@Override
	public List findAll() {
		return super.findAll();
	}

}
