package com.pojo;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private   Integer   uid ;
    private   String   uname ;
    private   String   rname ;
    private   String   pwd ;
    private   String   position ;
    private   String   mobile ;
    private   String   email ;
    private   Date   create_time ;
    private   String   usr_flag ;
    private   Integer   groupid ;
    private   Integer   ayid ;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getUsr_flag() {
		return usr_flag;
	}
	public void setUsr_flag(String usr_flag) {
		this.usr_flag = usr_flag;
	}
	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	public Integer getAyid() {
		return ayid;
	}
	public void setAyid(Integer ayid) {
		this.ayid = ayid;
	}
}