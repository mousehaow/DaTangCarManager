package com.datang.datangcarmanager.model;

import java.util.List;

/**
 * Created by toby on 16/11/14.
 */
public class CarInfo {


    /**
     * result : 0
     * pages : 1
     * pageNo : 0
     * resultNote : Success
     * totalRecordNum : 1
     * cmd : serviceObjsRealTrack
     * detail : {"objList":[{"serviceTypeDesc":"","gid":"","lastLongitude":"","onlineStatus":1,"latitude":"41.773736","lastPosSpeed":"","posSpeed":"0.0","updateStatusTime":"2016-11-13 10:04:45","deviceId":"103880000032","deviceStatus":1,"gidStatus":0,"idName":"","driverTelephone":"","ispublic":"0","objType":1,"invisibleStatus":"0","longitude":"123.412389","posTime":"2016-11-13 10:04:45","corpId":"","lastPosTime":"","lastPosDirection":"","hasExtStatus":1,"hasAlarmMsg":0,"lpno":"00002","objId":"00002","posDirection":"13","driverName":"","lastLatitude":"","posMethod":1}]}
     */

    private int result;
    private int pages;
    private int pageNo;
    private String resultNote;
    private int totalRecordNum;
    private String cmd;
    private DetailBean detail;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getResultNote() {
        return resultNote;
    }

    public void setResultNote(String resultNote) {
        this.resultNote = resultNote;
    }

    public int getTotalRecordNum() {
        return totalRecordNum;
    }

    public void setTotalRecordNum(int totalRecordNum) {
        this.totalRecordNum = totalRecordNum;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        private List<ObjListBean> objList;

        public List<ObjListBean> getObjList() {
            return objList;
        }

        public void setObjList(List<ObjListBean> objList) {
            this.objList = objList;
        }

        public static class ObjListBean {
            /**
             * serviceTypeDesc :
             * gid :
             * lastLongitude :
             * onlineStatus : 1
             * latitude : 41.773736
             * lastPosSpeed :
             * posSpeed : 0.0
             * updateStatusTime : 2016-11-13 10:04:45
             * deviceId : 103880000032
             * deviceStatus : 1
             * gidStatus : 0
             * idName :
             * driverTelephone :
             * ispublic : 0
             * objType : 1
             * invisibleStatus : 0
             * longitude : 123.412389
             * posTime : 2016-11-13 10:04:45
             * corpId :
             * lastPosTime :
             * lastPosDirection :
             * hasExtStatus : 1
             * hasAlarmMsg : 0
             * lpno : 00002
             * objId : 00002
             * posDirection : 13
             * driverName :
             * lastLatitude :
             * posMethod : 1
             */

            private String serviceTypeDesc;
            private String gid;
            private String lastLongitude;
            private int onlineStatus;
            private String latitude;
            private String lastPosSpeed;
            private String posSpeed;
            private String updateStatusTime;
            private String deviceId;
            private int deviceStatus;
            private int gidStatus;
            private String idName;
            private String driverTelephone;
            private String ispublic;
            private int objType;
            private String invisibleStatus;
            private String longitude;
            private String posTime;
            private String corpId;
            private String lastPosTime;
            private String lastPosDirection;
            private int hasExtStatus;
            private int hasAlarmMsg;
            private String lpno;
            private String objId;
            private String posDirection;
            private String driverName;
            private String lastLatitude;
            private int posMethod;

            public String getServiceTypeDesc() {
                return serviceTypeDesc;
            }

            public void setServiceTypeDesc(String serviceTypeDesc) {
                this.serviceTypeDesc = serviceTypeDesc;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getLastLongitude() {
                return lastLongitude;
            }

            public void setLastLongitude(String lastLongitude) {
                this.lastLongitude = lastLongitude;
            }

            public int getOnlineStatus() {
                return onlineStatus;
            }

            public void setOnlineStatus(int onlineStatus) {
                this.onlineStatus = onlineStatus;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLastPosSpeed() {
                return lastPosSpeed;
            }

            public void setLastPosSpeed(String lastPosSpeed) {
                this.lastPosSpeed = lastPosSpeed;
            }

            public String getPosSpeed() {
                return posSpeed;
            }

            public void setPosSpeed(String posSpeed) {
                this.posSpeed = posSpeed;
            }

            public String getUpdateStatusTime() {
                return updateStatusTime;
            }

            public void setUpdateStatusTime(String updateStatusTime) {
                this.updateStatusTime = updateStatusTime;
            }

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public int getDeviceStatus() {
                return deviceStatus;
            }

            public void setDeviceStatus(int deviceStatus) {
                this.deviceStatus = deviceStatus;
            }

            public int getGidStatus() {
                return gidStatus;
            }

            public void setGidStatus(int gidStatus) {
                this.gidStatus = gidStatus;
            }

            public String getIdName() {
                return idName;
            }

            public void setIdName(String idName) {
                this.idName = idName;
            }

            public String getDriverTelephone() {
                return driverTelephone;
            }

            public void setDriverTelephone(String driverTelephone) {
                this.driverTelephone = driverTelephone;
            }

            public String getIspublic() {
                return ispublic;
            }

            public void setIspublic(String ispublic) {
                this.ispublic = ispublic;
            }

            public int getObjType() {
                return objType;
            }

            public void setObjType(int objType) {
                this.objType = objType;
            }

            public String getInvisibleStatus() {
                return invisibleStatus;
            }

            public void setInvisibleStatus(String invisibleStatus) {
                this.invisibleStatus = invisibleStatus;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getPosTime() {
                return posTime;
            }

            public void setPosTime(String posTime) {
                this.posTime = posTime;
            }

            public String getCorpId() {
                return corpId;
            }

            public void setCorpId(String corpId) {
                this.corpId = corpId;
            }

            public String getLastPosTime() {
                return lastPosTime;
            }

            public void setLastPosTime(String lastPosTime) {
                this.lastPosTime = lastPosTime;
            }

            public String getLastPosDirection() {
                return lastPosDirection;
            }

            public void setLastPosDirection(String lastPosDirection) {
                this.lastPosDirection = lastPosDirection;
            }

            public int getHasExtStatus() {
                return hasExtStatus;
            }

            public void setHasExtStatus(int hasExtStatus) {
                this.hasExtStatus = hasExtStatus;
            }

            public int getHasAlarmMsg() {
                return hasAlarmMsg;
            }

            public void setHasAlarmMsg(int hasAlarmMsg) {
                this.hasAlarmMsg = hasAlarmMsg;
            }

            public String getLpno() {
                return lpno;
            }

            public void setLpno(String lpno) {
                this.lpno = lpno;
            }

            public String getObjId() {
                return objId;
            }

            public void setObjId(String objId) {
                this.objId = objId;
            }

            public String getPosDirection() {
                return posDirection;
            }

            public void setPosDirection(String posDirection) {
                this.posDirection = posDirection;
            }

            public String getDriverName() {
                return driverName;
            }

            public void setDriverName(String driverName) {
                this.driverName = driverName;
            }

            public String getLastLatitude() {
                return lastLatitude;
            }

            public void setLastLatitude(String lastLatitude) {
                this.lastLatitude = lastLatitude;
            }

            public int getPosMethod() {
                return posMethod;
            }

            public void setPosMethod(int posMethod) {
                this.posMethod = posMethod;
            }
        }
    }
}
