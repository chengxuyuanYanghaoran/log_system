package com.hlwxy.xu_boot2.userlogin.domain;

public class UserRoleDO {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private Integer userId;
    //
    private Integer roleId;


    public UserRoleDO() {
    }

    public UserRoleDO(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    /**
     * 获取：
     */
    public Integer getUserId() {
        return userId;
    }
    /**
     * 设置：
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    /**
     * 获取：
     */
    public Integer getRoleId() {
        return roleId;
    }

    @Override
    public String toString() {
        return "UserRoleDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
