package com.datang.datangcarmanager.model.request;

/**
 * Created by toby on 2017/1/7.
 */

public class SearchCarParams extends Params {

    /**
     * deptId :
     * idNameOrLpno : 1
     * onePageNum : 10
     * isBind : 0
     * pageNo : 0
     */

    private String deptId;
    private String idNameOrLpno;
    private int onePageNum;
    private int isBind;
    private int pageNo;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getIdNameOrLpno() {
        return idNameOrLpno;
    }

    public void setIdNameOrLpno(String idNameOrLpno) {
        this.idNameOrLpno = idNameOrLpno;
    }

    public int getOnePageNum() {
        return onePageNum;
    }

    public void setOnePageNum(int onePageNum) {
        this.onePageNum = onePageNum;
    }

    public int getIsBind() {
        return isBind;
    }

    public void setIsBind(int isBind) {
        this.isBind = isBind;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
