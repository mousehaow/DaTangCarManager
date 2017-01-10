package com.datang.datangcarmanager.model.request;

/**
 * Created by toby on 2017/1/7.
 */

public class AbnormalSituationParams extends Params {

    /**
     * deptId : 12120710341890007D106
     * onePageNum : 10
     * processStatus : 0
     * lpnoAlias :
     * pageNo : 0
     */

    private String deptId;
    private int onePageNum;
    private String processStatus;
    private String lpnoAlias;
    private int pageNo;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public int getOnePageNum() {
        return onePageNum;
    }

    public void setOnePageNum(int onePageNum) {
        this.onePageNum = onePageNum;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getLpnoAlias() {
        return lpnoAlias;
    }

    public void setLpnoAlias(String lpnoAlias) {
        this.lpnoAlias = lpnoAlias;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
