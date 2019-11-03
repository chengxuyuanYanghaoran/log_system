package com.hlwxy.xu_boot2.system.dto;


import com.hlwxy.xu_boot2.system.domain.PeopleDO;

/**
 * @author shkstart
 * @create 2019-09-29-0:44
 */
public class PeopleDTO extends PeopleDO {
    //公司名称
    private String gsName;
    //部门名称
    private String bmName;

    //职位名称
    private String jobTitle;

    public PeopleDTO(String gsName, String bmName, String jobTitle) {
        this.gsName = gsName;
        this.bmName = bmName;
        this.jobTitle = jobTitle;
    }

    public String getGsName() {
        return gsName;
    }

    public void setGsName(String gsName) {
        this.gsName = gsName;
    }

    public String getBmName() {
        return bmName;
    }

    public void setBmName(String bmName) {
        this.bmName = bmName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "PeopleDTO{" +
                "gsName='" + gsName + '\'' +
                ", bmName='" + bmName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
