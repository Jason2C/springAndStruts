package com.pojo;
import java.io.Serializable;

public class Groups implements Serializable {

    private   Integer   gid ;
    private   String   groupname ;
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}