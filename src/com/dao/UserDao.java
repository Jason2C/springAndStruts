package com.dao;

import com.pojo.User;


public class UserDao extends BaseDao {
	
	public User findByName(String uname){
		String sql=super.getSelectAll()+" where uname = ? ";
		return (User) super.findOne(sql, uname);
	}
	
	
}
