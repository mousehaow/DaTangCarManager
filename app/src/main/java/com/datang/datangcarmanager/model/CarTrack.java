package com.datang.datangcarmanager.model;

import java.util.List;

/**
 * Created by toby on 2017/1/6.
 */

public class CarTrack {

    private List<TrackBean> objList;

    public List<TrackBean> getObjList() {
        return objList;
    }

    public void setObjList(List<TrackBean> objList) {
        this.objList = objList;
    }

    public static class TrackBean {
        /**
         * objType : 1
         * gidStatus : 0
         * corpId : 12120710341890007
         * ispublic : 0
         * invisibleStatus : 0
         * hasExtStatus : 1
         * deviceId : YY2012521722
         * objId : 13071611134677823
         * lpno : 苏ACZG05
         * serviceTypeDesc : 分享你的车源、你的车况、你的路线、你的驾情、互换用车…，让分享惊喜不断
         * idName : 服务用车1
         * onlineStatus : 1
         * posMethod : 1
         * posTime : 2016-12-24 18:53:06
         * longitude : 118.634316
         * latitude : 32.065019
         * posSpeed : 0
         * posDirection : 0
         * deviceStatus : 1
         * gid : c703e482ab446cbe947ba7a4a7aee8d5
         * updateStatusTime : 2016-12-24 18:53:06
         * hasAlarmMsg : 1
         * driverName : 沙师傅
         * driverTelephone : 13800000000
         * lastPosTime : 2016-12-24 18:51:46
         * lastLongitude : 118.634321
         * lastLatitude : 32.06502
         * lastPosSpeed : 0
         * lastPosDirection : 0
         */

        private int objType;
        private int gidStatus;
        private String corpId;
        private String ispublic;
        private String invisibleStatus;
        private int hasExtStatus;
        private String deviceId;
        private String objId;
        private String lpno;
        private String serviceTypeDesc;
        private String idName;
        private int onlineStatus;
        private int posMethod;
        private String posTime;
        private double longitude;
        private double latitude;
        private double posSpeed;
        private int posDirection;
        private int deviceStatus;
        private String gid;
        private String updateStatusTime;
        private int hasAlarmMsg;
        private String driverName;
        private String driverTelephone;
        private String lastPosTime;
//        private double lastLongitude;
//        private double lastLatitude;
//        private double lastPosSpeed;
//        private int lastPosDirection;


        private String lastLatitude;
        private String lastLongitude;
        private String lastPosSpeed;
        private String lastPosDirection;
//
//        private String posDirection;
//        private String latitude;
//        private String longitude;
//        private String posSpeed;



        public int getObjType() {
            return objType;
        }

        public void setObjType(int objType) {
            this.objType = objType;
        }

        public int getGidStatus() {
            return gidStatus;
        }

        public void setGidStatus(int gidStatus) {
            this.gidStatus = gidStatus;
        }

        public String getCorpId() {
            return corpId;
        }

        public void setCorpId(String corpId) {
            this.corpId = corpId;
        }

        public String getIspublic() {
            return ispublic;
        }

        public void setIspublic(String ispublic) {
            this.ispublic = ispublic;
        }

        public String getInvisibleStatus() {
            return invisibleStatus;
        }

        public void setInvisibleStatus(String invisibleStatus) {
            this.invisibleStatus = invisibleStatus;
        }

        public int getHasExtStatus() {
            return hasExtStatus;
        }

        public void setHasExtStatus(int hasExtStatus) {
            this.hasExtStatus = hasExtStatus;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

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

        public String getServiceTypeDesc() {
            return serviceTypeDesc;
        }

        public void setServiceTypeDesc(String serviceTypeDesc) {
            this.serviceTypeDesc = serviceTypeDesc;
        }

        public String getIdName() {
            return idName;
        }

        public void setIdName(String idName) {
            this.idName = idName;
        }

        public int getOnlineStatus() {
            return onlineStatus;
        }

        public void setOnlineStatus(int onlineStatus) {
            this.onlineStatus = onlineStatus;
        }

        public int getPosMethod() {
            return posMethod;
        }

        public void setPosMethod(int posMethod) {
            this.posMethod = posMethod;
        }

        public String getPosTime() {
            return posTime;
        }

        public void setPosTime(String posTime) {
            this.posTime = posTime;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getPosSpeed() {
            return posSpeed;
        }

        public void setPosSpeed(double posSpeed) {
            this.posSpeed = posSpeed;
        }

        public int getPosDirection() {
            return posDirection;
        }

        public void setPosDirection(int posDirection) {
            this.posDirection = posDirection;
        }

        public int getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(int deviceStatus) {
            this.deviceStatus = deviceStatus;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getUpdateStatusTime() {
            return updateStatusTime;
        }

        public void setUpdateStatusTime(String updateStatusTime) {
            this.updateStatusTime = updateStatusTime;
        }

        public int getHasAlarmMsg() {
            return hasAlarmMsg;
        }

        public void setHasAlarmMsg(int hasAlarmMsg) {
            this.hasAlarmMsg = hasAlarmMsg;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getDriverTelephone() {
            return driverTelephone;
        }

        public void setDriverTelephone(String driverTelephone) {
            this.driverTelephone = driverTelephone;
        }

        public String getLastPosTime() {
            return lastPosTime;
        }

        public void setLastPosTime(String lastPosTime) {
            this.lastPosTime = lastPosTime;
        }



    }
}
