package com.hlwxy.xu_boot2.common.utils;

import java.util.List;

public class BatchAuditUtil {
    private List<String> ids;
    private Integer state;

    public BatchAuditUtil() {
    }

    public BatchAuditUtil(List<String> ids, Integer state) {
        this.ids = ids;
        this.state = state;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
