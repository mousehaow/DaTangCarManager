package com.datang.datangcarmanager.model.request;

/**
 * Created by toby on 2017/1/6.
 */

public class ParkingRecordParams extends Params {

    /**
     * vehicleId : 14061314131098090
     * deptId :
     * date : 2017-01-05
     * lpno : 苏KDNA15
     * corpId : 12120710341890007
     */

    private String deptId;
    private String date;
    private String lpno;


    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLpno() {
        return lpno;
    }

    public void setLpno(String lpno) {
        this.lpno = lpno;
    }
}
