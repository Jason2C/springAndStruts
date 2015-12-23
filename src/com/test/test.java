package com.test;

import org.apache.catalina.core.ApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.dao.AttendanceDao;
public class test {
	private ClassPathXmlApplicationContext context;
	@Autowired
	private AttendanceDao attendanceDao;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void main() {
		AttendanceDao dao = (AttendanceDao) context.getBean("AttendanceDao");
		System.out.println(dao.find(1));
		System.out.println(attendanceDao.findAll());
	}
}
