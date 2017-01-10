package com.datang.datangcarmanager.model;

import java.util.List;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.model
 * @class describe
 * @anthor toby QQ:1032006226
 * @time 16/11/18 上午11:03
 * @change
 * @chang time
 * @class describe
 */
public class DriveRecord {


    /**
     * totalMileAge : 39.5Km
     * totalFuelAge : 3.1L
     * averageFuel : 7.8L/100Km
     * fuelCost : 19.9元
     * segList : [{"recUid":"CD098FB2A50A18D6B3F897D1675F906A","startTime":"2016-12-20 07:42:43","endTime":"2016-12-20 07:49:29","startLocation":"江苏省南京市浦口区珠泉路6幢-3号","endLocation":"江苏省南京市浦口区公园北路2号","startLng":118.63225,"startLat":32.06118,"endLng":118.62919,"endLat":32.067226,"mileAge":"1.4","fuel":"0.2"},{"recUid":"496DF29744B9091EAFAAD3D79C928FB0","startTime":"2016-12-20 08:29:28","endTime":"2016-12-20 09:07:55","startLocation":"江苏省南京市浦口区公园北路2号","endLocation":"江苏省南京市浦口区盘汪线","startLng":118.62917,"startLat":32.067303,"endLng":118.69428,"endLat":32.1402,"mileAge":"14.4","fuel":"1.2"},{"recUid":"B73733D59404648DFA333F2A7C4198C8","startTime":"2016-12-20 09:32:03","endTime":"2016-12-20 09:34:25","startLocation":"江苏省南京市浦口区盘汪线","endLocation":"江苏省南京市浦口区盘汪线","startLng":118.69419,"startLat":32.140198,"endLng":118.69474,"endLat":32.14102,"mileAge":"0.5","fuel":"0.0"},{"recUid":"333B48FEED7BF4CCC680E6B26C9DB118","startTime":"2016-12-20 09:55:35","endTime":"2016-12-20 10:02:07","startLocation":"江苏省南京市浦口区盘汪线","endLocation":"江苏省南京市浦口区向阳路","startLng":118.69475,"startLat":32.140892,"endLng":118.69632,"endLat":32.138203,"mileAge":"0.9","fuel":"0.1"},{"recUid":"3C1CA5D8DD942CE56047D64A2A348B2C","startTime":"2016-12-20 10:55:54","endTime":"2016-12-20 11:01:22","startLocation":"江苏省南京市浦口区盘汪线","endLocation":"江苏省南京市浦口区泰西路23号","startLng":118.69457,"startLat":32.13992,"endLng":118.704384,"endLat":32.143337,"mileAge":"3.2","fuel":"0.2"},{"recUid":"C3C385D1FF3767434B399278E09FAA4A","startTime":"2016-12-20 11:04:29","endTime":"2016-12-20 11:10:03","startLocation":"江苏省南京市浦口区泰西路23号","endLocation":"江苏省南京市浦口区向阳路","startLng":118.70438,"startLat":32.14333,"endLng":118.69365,"endLat":32.14118,"mileAge":"2.0","fuel":"0.2"},{"recUid":"EED302AE909B0C2A79FCFC8A618E718C","startTime":"2016-12-20 11:17:35","endTime":"2016-12-20 11:19:04","startLocation":"江苏省南京市浦口区向阳路","endLocation":"江苏省南京市浦口区向阳路","startLng":118.69371,"startLat":32.14117,"endLng":118.6925,"endLat":32.141327,"mileAge":"0.1","fuel":"0.0"},{"recUid":"3F1D973E2C84F076FE630CAE250C3C07","startTime":"2016-12-20 11:34:59","endTime":"2016-12-20 11:37:26","startLocation":"江苏省南京市浦口区盘汪线","endLocation":"江苏省南京市浦口区浦六线","startLng":118.692535,"startLat":32.141117,"endLng":118.696754,"endLat":32.13758,"mileAge":"0.7","fuel":"0.1"},{"recUid":"73F3E2DCF84B1341AE2D9F48726D2591","startTime":"2016-12-20 11:47:12","endTime":"2016-12-20 11:49:02","startLocation":"江苏省南京市浦口区泰西路","endLocation":"江苏省南京市浦口区盘汪线","startLng":118.69668,"startLat":32.13768,"endLng":118.6946,"endLat":32.13997,"mileAge":"0.5","fuel":"0.0"},{"recUid":"E99F2FC268D5C18FF3209CE997E00916","startTime":"2016-12-20 13:04:12","endTime":"2016-12-20 13:05:57","startLocation":"江苏省南京市浦口区盘汪线","endLocation":"江苏省南京市浦口区盘汪线","startLng":118.6946,"startLat":32.139935,"endLng":118.69489,"endLat":32.140877,"mileAge":"0.4","fuel":"0.0"},{"recUid":"57D793CB8641BB145F152FF3FC33EBF7","startTime":"2016-12-20 13:49:07","endTime":"2016-12-20 13:50:30","startLocation":"江苏省南京市浦口区盘汪线","endLocation":"江苏省南京市浦口区向阳路","startLng":118.694916,"startLat":32.14092,"endLng":118.69254,"endLat":32.14151,"mileAge":"0.3","fuel":"0.0"},{"recUid":"82D728441DC33D097912F50ACBF2029B","startTime":"2016-12-20 14:24:48","endTime":"2016-12-20 14:27:08","startLocation":"江苏省南京市浦口区向阳路","endLocation":"江苏省南京市浦口区向阳路","startLng":118.69262,"startLat":32.14134,"endLng":118.69627,"endLat":32.138336,"mileAge":"0.6","fuel":"0.1"},{"recUid":"8E8BB20C87CEE9700D1A9E52B5A9D298","startTime":"2016-12-20 14:49:31","endTime":"2016-12-20 14:51:32","startLocation":"江苏省南京市浦口区浦六线","endLocation":"江苏省南京市浦口区向阳路","startLng":118.6969,"startLat":32.137615,"endLng":118.692535,"endLat":32.14122,"mileAge":"0.6","fuel":"0.1"},{"recUid":"FEEA3534FBD813BA3D40815988F7F404","startTime":"2016-12-20 15:11:06","endTime":"2016-12-20 15:13:07","startLocation":"江苏省南京市浦口区向阳路","endLocation":"江苏省南京市浦口区盘汪线","startLng":118.69257,"startLat":32.14128,"endLng":118.69439,"endLat":32.14019,"mileAge":"0.4","fuel":"0.0"},{"recUid":"30E9C76E1EF316BF3091B25B4DEBB847","startTime":"2016-12-20 16:25:22","endTime":"2016-12-20 16:26:43","startLocation":"江苏省南京市浦口区盘汪线","endLocation":"江苏省南京市浦口区向阳路","startLng":118.69436,"startLat":32.140255,"endLng":118.69635,"endLat":32.138374,"mileAge":"0.2","fuel":"0.0"},{"recUid":"A5AD68CA35773FAB60797420E8153B29","startTime":"2016-12-20 16:40:06","endTime":"2016-12-20 16:41:43","startLocation":"江苏省南京市浦口区向阳路","endLocation":"江苏省南京市浦口区盘汪线","startLng":118.69649,"startLat":32.13841,"endLng":118.69452,"endLat":32.139908,"mileAge":"0.2","fuel":"0.0"},{"recUid":"29345BA1AA6B140EF80775986EA79919","startTime":"2016-12-20 16:47:08","endTime":"2016-12-20 16:48:47","startLocation":"江苏省南京市浦口区盘汪线","endLocation":"江苏省南京市浦口区盘汪线","startLng":118.69448,"startLat":32.139935,"endLng":118.6948,"endLat":32.140972,"mileAge":"0.4","fuel":"0.0"},{"recUid":"F57385956384AFC793C49F23D99EBF8C","startTime":"2016-12-20 16:57:00","endTime":"2016-12-20 17:15:39","startLocation":"江苏省南京市浦口区盘汪线","endLocation":"江苏省南京市浦口区公园北路2号","startLng":118.69448,"startLat":32.140934,"endLng":118.62921,"endLat":32.067226,"mileAge":"12.9","fuel":"0.8"}]
     */

    private String totalMileAge;
    private String totalFuelAge;
    private String averageFuel;
    private String fuelCost;
    private List<SegListBean> segList;

    public String getTotalMileAge() {
        return totalMileAge;
    }

    public void setTotalMileAge(String totalMileAge) {
        this.totalMileAge = totalMileAge;
    }

    public String getTotalFuelAge() {
        return totalFuelAge;
    }

    public void setTotalFuelAge(String totalFuelAge) {
        this.totalFuelAge = totalFuelAge;
    }

    public String getAverageFuel() {
        return averageFuel;
    }

    public void setAverageFuel(String averageFuel) {
        this.averageFuel = averageFuel;
    }

    public String getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(String fuelCost) {
        this.fuelCost = fuelCost;
    }

    public List<SegListBean> getSegList() {
        return segList;
    }

    public void setSegList(List<SegListBean> segList) {
        this.segList = segList;
    }

    public static class SegListBean {
        /**
         * recUid : CD098FB2A50A18D6B3F897D1675F906A
         * startTime : 2016-12-20 07:42:43
         * endTime : 2016-12-20 07:49:29
         * startLocation : 江苏省南京市浦口区珠泉路6幢-3号
         * endLocation : 江苏省南京市浦口区公园北路2号
         * startLng : 118.63225
         * startLat : 32.06118
         * endLng : 118.62919
         * endLat : 32.067226
         * mileAge : 1.4
         * fuel : 0.2
         */

        private String recUid;
        private String startTime;
        private String endTime;
        private String startLocation;
        private String endLocation;
        private double startLng;
        private double startLat;
        private double endLng;
        private double endLat;
        private String mileAge;
        private String fuel;

        public String getRecUid() {
            return recUid;
        }

        public void setRecUid(String recUid) {
            this.recUid = recUid;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStartLocation() {
            return startLocation;
        }

        public void setStartLocation(String startLocation) {
            this.startLocation = startLocation;
        }

        public String getEndLocation() {
            return endLocation;
        }

        public void setEndLocation(String endLocation) {
            this.endLocation = endLocation;
        }

        public double getStartLng() {
            return startLng;
        }

        public void setStartLng(double startLng) {
            this.startLng = startLng;
        }

        public double getStartLat() {
            return startLat;
        }

        public void setStartLat(double startLat) {
            this.startLat = startLat;
        }

        public double getEndLng() {
            return endLng;
        }

        public void setEndLng(double endLng) {
            this.endLng = endLng;
        }

        public double getEndLat() {
            return endLat;
        }

        public void setEndLat(double endLat) {
            this.endLat = endLat;
        }

        public String getMileAge() {
            return mileAge;
        }

        public void setMileAge(String mileAge) {
            this.mileAge = mileAge;
        }

        public String getFuel() {
            return fuel;
        }

        public void setFuel(String fuel) {
            this.fuel = fuel;
        }
    }
}
