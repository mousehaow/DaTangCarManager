package com.datang.datangcarmanager.model.request;

/**
 * Created by toby on 2017/1/5.
 */

public class DriveRecordParams extends Params {
    /**
     * beginTime : 2016-12-20 00:00:00
     * endTime : 2016-12-20 23:59:59
     * objId : 13071611134677823
     */

    private String beginTime;
    private String endTime;
    private String objId;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }
}
