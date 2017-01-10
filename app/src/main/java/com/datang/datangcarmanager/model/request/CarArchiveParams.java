package com.datang.datangcarmanager.model.request;

/**
 * Created by toby on 2017/1/6.
 */

public class CarArchiveParams extends Params {

    /**
     * objId : 13071611134677823
     * lpno : 苏ACZG05
     */

    private String objId;
    private String lpno;

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getLpno() {
        return lpno;
    }

    public void setLpno(String lpno) {
        this.lpno = lpno;
    }
}
