package com.hlwxy.xu_boot2.userlogin.domain;

import com.hlwxy.xu_boot2.system.domain.PeopleDO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author
 * @create 2019-09-29-20:29
 * 角色表
 */
public class RoleDo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String name;

    private List<Long> menuIds;

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }

    private Set<MenuDO> menus = new HashSet<>();

    private Set<PeopleDO> users = new HashSet<>();

    public Set<PeopleDO> getUsers() {
        return users;
    }

    public void setUsers(Set<PeopleDO> users) {
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MenuDO> getMenus() {
        return menus;
    }

    public void setMenus(Set<MenuDO> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "RoleDo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", menus=" + menus +
                ", users=" + users +
                '}';
    }


}
