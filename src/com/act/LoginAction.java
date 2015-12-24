package com.act;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.UserDao;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.User;

public class LoginAction extends ActionSupport {
	private User user;
	@Autowired
	private UserDao userDao;
	private String msg;

	@Action(value = "login", results = { @Result(location = "index.jsp") })
	public String login() {
		System.out.println("111");
		User user1 = userDao.findByName(user.getUname());
		if (user1 != null) {
			if (user1.getPwd().equals(user.getPwd())) {
				msg = "µÇÂ½³É¹¦";
			}else {
				msg = "µÇÂ½Ê§°Ü";
			}
		}
		return SUCCESS;
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
