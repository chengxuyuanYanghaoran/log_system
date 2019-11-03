package com.hlwxy.xu_boot2.system.domain;

/**
 * 初始化实体
 * @author shkstart
 * @create 2019-09-21-17:14
 */
public class DataDO {
    private String del;

    public DataDO(String del) {
        this.del = del;
    }

    public DataDO() {
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
