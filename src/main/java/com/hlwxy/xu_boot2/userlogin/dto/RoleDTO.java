package com.hlwxy.xu_boot2.userlogin.dto;

import com.hlwxy.xu_boot2.userlogin.domain.RoleDo;

/**
 * @author shkstart
 * @create 2019-10-26-23:26
 */
public class RoleDTO extends RoleDo {
    //部门名称
    private String bmName;

    public RoleDTO() {
    }

    public RoleDTO(String bmName) {
        this.bmName = bmName;
    }

    public String getBmName() {
        return bmName;
    }

    public void setBmName(String bmName) {
        this.bmName = bmName;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "bmName='" + bmName + '\'' +
                '}';
    }
}
