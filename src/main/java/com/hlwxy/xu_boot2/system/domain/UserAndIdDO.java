package com.hlwxy.xu_boot2.system.domain;




import java.io.Serializable;


/**
 * @email 1992lcg@163.com
 * @date 2019-09-26 22:35:53
 */
public class UserAndIdDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private PeopleDO userDO;
	private Integer id;

	public UserAndIdDO() {
	}

	public UserAndIdDO(PeopleDO userDO, Integer id) {
		this.userDO = userDO;
		this.id = id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public PeopleDO getUserDO() {
		return userDO;
	}

	public void setUserDO(PeopleDO userDO) {
		this.userDO = userDO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
