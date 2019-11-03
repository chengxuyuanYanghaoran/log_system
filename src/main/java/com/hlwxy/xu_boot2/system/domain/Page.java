package com.hlwxy.xu_boot2.system.domain;

public class Page {
    private Integer pageSize;//每页显示多少条
    private Integer page;//当前页数
    private Integer pp;//总记录数
    private Integer pc;//开始检索位置

    public Integer getPc() {
        return pc;
    }

    public void setPc(Integer pc) {
        this.pc = pc;
    }

    public Page() {
    }

    public Page(Integer pageSize, Integer page, Integer pp) {
        this.pageSize = pageSize;
        this.page = page;
        this.pp = pp;
    }

    public Integer getPp() {
        return pp;
    }

    public void setPp(Integer pp) {
        this.pp = pp;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
