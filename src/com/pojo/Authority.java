package com.pojo;
import java.io.Serializable;

public class Authority implements Serializable {

    private   Integer   aid ;
    private   String   aname ;
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
}