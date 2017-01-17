package com.datang.datangcarmanager.model;

import java.util.List;

/**
 * Created by toby on 2017/1/17.
 */

public class RecordTrack {

    private List<PointListBean> pointList;

    public List<PointListBean> getPointList() {
        return pointList;
    }

    public void setPointList(List<PointListBean> pointList) {
        this.pointList = pointList;
    }

    public static class PointListBean {
        /**
         * longitude : 120.642575
         * latitude : 31.280581
         * direction : 287
         * posMethod : 1
         * serviceStatus : 1
         * onlineStatus : 2
         * time : 2016-12-24 18:05:01
         */

        private double longitude;
        private double latitude;
        private int direction;
        private int posMethod;
        private int serviceStatus;
        private int onlineStatus;
        private String time;

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

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getPosMethod() {
            return posMethod;
        }

        public void setPosMethod(int posMethod) {
            this.posMethod = posMethod;
        }

        public int getServiceStatus() {
            return serviceStatus;
        }

        public void setServiceStatus(int serviceStatus) {
            this.serviceStatus = serviceStatus;
        }

        public int getOnlineStatus() {
            return onlineStatus;
        }

        public void setOnlineStatus(int onlineStatus) {
            this.onlineStatus = onlineStatus;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
