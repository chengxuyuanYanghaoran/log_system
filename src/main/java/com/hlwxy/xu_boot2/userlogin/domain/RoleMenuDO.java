package com.hlwxy.xu_boot2.userlogin.domain;

public class RoleMenuDO {
	//id
	private String id;
	//
	private String roleId;
	//
	private String pid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "RoleMenuDO{" +
				"id='" + id + '\'' +
				", roleId='" + roleId + '\'' +
				", pid='" + pid + '\'' +
				'}';
	}
}
