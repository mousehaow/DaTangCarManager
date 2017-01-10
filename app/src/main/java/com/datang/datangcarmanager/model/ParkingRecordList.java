package com.datang.datangcarmanager.model;

import java.util.List;

/**
 * Created by toby on 2017/1/6.
 */

public class ParkingRecordList {

    private List<ParkingRecordBean> dataList;

    public List<ParkingRecordBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<ParkingRecordBean> dataList) {
        this.dataList = dataList;
    }

    public static class ParkingRecordBean {
        /**
         * endLocation : 江苏省扬州市江都区江都区庄台路16号
         * latitude : 32.426768
         * vehicleId : 14061314131098090
         * beginTime : 2017-01-05 09:24:34
         * endTime : 2017-01-05 09:33:50
         * hour : 9分钟
         * longitude : 119.545378
         */

        private String endLocation;
        private double latitude;
        private String vehicleId;
        private String beginTime;
        private String endTime;
        private String hour;
        private double longitude;

        public String getEndLocation() {
            return endLocation;
        }

        public void setEndLocation(String endLocation) {
            this.endLocation = endLocation;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

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

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
