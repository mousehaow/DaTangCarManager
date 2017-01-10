package com.datang.datangcarmanager.model;

import java.util.List;

/**
 * Created by toby on 2017/1/9.
 */

public class StatisticsAnalyzeInfo {

    /**
     * deptDetail : [{"deptId":"12120710341890007D106","deptName":"服务部","mile":"2223.64","fuel":"213.41","accumulativeDriveTime":"99.45","idlespeedCount":"6","idlespeedTime":"0","unbindDriveCount":"207","speedingCount":"0","efenceCount":"0","poweroffCount":"0","repairMaintCount":"0","alarmCount":"0","hKMFuel":"9.60","onlinePercent":"100%"},{"deptId":"12120710341890007D107","deptName":"办公室","mile":"2284.60","fuel":"183.70","accumulativeDriveTime":"94.60","idlespeedCount":"26","idlespeedTime":"47611","unbindDriveCount":"61","speedingCount":"0","efenceCount":"51","poweroffCount":"0","repairMaintCount":"0","alarmCount":"0","hKMFuel":"8.04","onlinePercent":"100%"},{"deptId":"12120710341890007D117","deptName":"维修一部","mile":"2022.03","fuel":"150.34","accumulativeDriveTime":"80.29","idlespeedCount":"2","idlespeedTime":"25083","unbindDriveCount":"0","speedingCount":"0","efenceCount":"35","poweroffCount":"0","repairMaintCount":"0","alarmCount":"0","hKMFuel":"7.44","onlinePercent":"100%"},{"deptId":"12120710341890007D118","deptName":"维修二部","mile":"575.46","fuel":"65.48","accumulativeDriveTime":"34.99","idlespeedCount":"3","idlespeedTime":"0","unbindDriveCount":"124","speedingCount":"0","efenceCount":"0","poweroffCount":"0","repairMaintCount":"0","alarmCount":"0","hKMFuel":"11.38","onlinePercent":"100%"},{"deptId":"14052310485750156","deptName":"技术部","mile":"5714.82","fuel":"486.42","accumulativeDriveTime":"198.96","idlespeedCount":"9","idlespeedTime":"0","unbindDriveCount":"686","speedingCount":"1","efenceCount":"1","poweroffCount":"0","repairMaintCount":"0","alarmCount":"0","hKMFuel":"8.51","onlinePercent":"100%"},{"deptId":"14081809191739137","deptName":"产品部","mile":"1557.26","fuel":"139.46","accumulativeDriveTime":"64.57","idlespeedCount":"5","idlespeedTime":"0","unbindDriveCount":"109","speedingCount":"0","efenceCount":"0","poweroffCount":"0","repairMaintCount":"0","alarmCount":"0","hKMFuel":"8.96","onlinePercent":"100%"},{"deptId":"14111809492622508","deptName":"市场部","mile":"1270.74","fuel":"113.20","accumulativeDriveTime":"62.56","idlespeedCount":"8","idlespeedTime":"0","unbindDriveCount":"0","speedingCount":"0","efenceCount":"0","poweroffCount":"0","repairMaintCount":"0","alarmCount":"0","hKMFuel":"8.91","onlinePercent":"100%"}]
     * vehicleDetail : [{"vehicleId":"15012710553123739","vehiclePlateNo":"苏EDNA02","deptId":"14111809492622508","mile":"746.21","fuel":"64.44","accumulativeDriveTime":"32.11","idlespeedCount":"1","idlespeedTime":"17","unbindDriveCount":"0","speedingCount":"0","efenceCount":"0","poweroffCount":"0","repairMaintCount":"0","alarmCount":"0","hKMFuel":"8.64","isOnline":1},{"vehicleId":"13071611113377647","vehiclePlateNo":"苏ACZG02","deptId":"14111809492622508","mile":"354.50","fuel":"28.29","accumulativeDriveTime":"14.26","idlespeedCount":"0","idlespeedTime":"0","unbindDriveCount":"0","speedingCount":"0","efenceCount":"21","poweroffCount":"0","repairMaintCount":"0","alarmCount":"0","hKMFuel":"7.98","isOnline":1},{"vehicleId":"13071611123377757","vehiclePlateNo":"苏ACZG04","deptId":"14111809492622508","mile":"1390.71","fuel":"161.72","accumulativeDriveTime":"72.43","idlespeedCount":"7","idlespeedTime":"14587","unbindDriveCount":"0","speedingCount":"0","efenceCount":"0","poweroffCount":"0","repairMaintCount":"0","alarmCount":"0","hKMFuel":"11.63","isOnline":1},{"vehicleId":"14061313492897203","vehiclePlateNo":"苏EDNA06","deptId":"14111809492622508","mile":"2512.86","fuel":"246.20","accumulativeDriveTime":"110.61","idlespeedCount":"21","idlespeedTime":"0","unbindDriveCount":"392","speedingCount":"0","efenceCount":"0","poweroffCount":"0","repairMaintCount":"0","alarmCount":"0","hKMFuel":"9.80","isOnline":1},{"vehicleId":"14061313515997416","vehiclePlateNo":"苏EDNA10","deptId":"14111809492622508","mile":"1316.62","fuel":"125.77","accumulativeDriveTime":"50.30","idlespeedCount":"6","idlespeedTime":"0","unbindDriveCount":"164","speedingCount":"0","efenceCount":"0","poweroffCount":"0","repairMaintCount":"0","alarmCount":"0","hKMFuel":"9.55","isOnline":1}]
     * corpId : 12120710341890007
     * corpName : 江苏迪纳数字科技股份有限公司
     * mile : 21969.44
     * fuel : 1978.42
     * accumulativeDriveTime : 915.11
     * idlespeedCount : 94
     * idlespeedTime : 87298
     * unbindDriveCount : 1743
     * speedingCount : 1
     * efenceCount : 108
     * poweroffCount : 0
     * repairMaintCount : 0
     * alarmCount : 0
     * hKMFuel : 9.01
     * onlinePercent : 100%
     */

    private String corpId;
    private String corpName;
    private String mile;
    private String fuel;
    private String accumulativeDriveTime;
    private String idlespeedCount;
    private String idlespeedTime;
    private String unbindDriveCount;
    private String speedingCount;
    private String efenceCount;
    private String poweroffCount;
    private String repairMaintCount;
    private String alarmCount;
    private String hKMFuel;
    private String onlinePercent;
    private List<DeptDetailBean> deptDetail;
    private List<VehicleDetailBean> vehicleDetail;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getMile() {
        return mile;
    }

    public void setMile(String mile) {
        this.mile = mile;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getAccumulativeDriveTime() {
        return accumulativeDriveTime;
    }

    public void setAccumulativeDriveTime(String accumulativeDriveTime) {
        this.accumulativeDriveTime = accumulativeDriveTime;
    }

    public String getIdlespeedCount() {
        return idlespeedCount;
    }

    public void setIdlespeedCount(String idlespeedCount) {
        this.idlespeedCount = idlespeedCount;
    }

    public String getIdlespeedTime() {
        return idlespeedTime;
    }

    public void setIdlespeedTime(String idlespeedTime) {
        this.idlespeedTime = idlespeedTime;
    }

    public String getUnbindDriveCount() {
        return unbindDriveCount;
    }

    public void setUnbindDriveCount(String unbindDriveCount) {
        this.unbindDriveCount = unbindDriveCount;
    }

    public String getSpeedingCount() {
        return speedingCount;
    }

    public void setSpeedingCount(String speedingCount) {
        this.speedingCount = speedingCount;
    }

    public String getEfenceCount() {
        return efenceCount;
    }

    public void setEfenceCount(String efenceCount) {
        this.efenceCount = efenceCount;
    }

    public String getPoweroffCount() {
        return poweroffCount;
    }

    public void setPoweroffCount(String poweroffCount) {
        this.poweroffCount = poweroffCount;
    }

    public String getRepairMaintCount() {
        return repairMaintCount;
    }

    public void setRepairMaintCount(String repairMaintCount) {
        this.repairMaintCount = repairMaintCount;
    }

    public String getAlarmCount() {
        return alarmCount;
    }

    public void setAlarmCount(String alarmCount) {
        this.alarmCount = alarmCount;
    }

    public String getHKMFuel() {
        return hKMFuel;
    }

    public void setHKMFuel(String hKMFuel) {
        this.hKMFuel = hKMFuel;
    }

    public String getOnlinePercent() {
        return onlinePercent;
    }

    public void setOnlinePercent(String onlinePercent) {
        this.onlinePercent = onlinePercent;
    }

    public List<DeptDetailBean> getDeptDetail() {
        return deptDetail;
    }

    public void setDeptDetail(List<DeptDetailBean> deptDetail) {
        this.deptDetail = deptDetail;
    }

    public List<VehicleDetailBean> getVehicleDetail() {
        return vehicleDetail;
    }

    public void setVehicleDetail(List<VehicleDetailBean> vehicleDetail) {
        this.vehicleDetail = vehicleDetail;
    }

    public static class DeptDetailBean {
        /**
         * deptId : 12120710341890007D106
         * deptName : 服务部
         * mile : 2223.64
         * fuel : 213.41
         * accumulativeDriveTime : 99.45
         * idlespeedCount : 6
         * idlespeedTime : 0
         * unbindDriveCount : 207
         * speedingCount : 0
         * efenceCount : 0
         * poweroffCount : 0
         * repairMaintCount : 0
         * alarmCount : 0
         * hKMFuel : 9.60
         * onlinePercent : 100%
         */

        private String deptId;
        private String deptName;
        private String mile;
        private String fuel;
        private String accumulativeDriveTime;
        private String idlespeedCount;
        private String idlespeedTime;
        private String unbindDriveCount;
        private String speedingCount;
        private String efenceCount;
        private String poweroffCount;
        private String repairMaintCount;
        private String alarmCount;
        private String hKMFuel;
        private String onlinePercent;

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getMile() {
            return mile;
        }

        public void setMile(String mile) {
            this.mile = mile;
        }

        public String getFuel() {
            return fuel;
        }

        public void setFuel(String fuel) {
            this.fuel = fuel;
        }

        public String getAccumulativeDriveTime() {
            return accumulativeDriveTime;
        }

        public void setAccumulativeDriveTime(String accumulativeDriveTime) {
            this.accumulativeDriveTime = accumulativeDriveTime;
        }

        public String getIdlespeedCount() {
            return idlespeedCount;
        }

        public void setIdlespeedCount(String idlespeedCount) {
            this.idlespeedCount = idlespeedCount;
        }

        public String getIdlespeedTime() {
            return idlespeedTime;
        }

        public void setIdlespeedTime(String idlespeedTime) {
            this.idlespeedTime = idlespeedTime;
        }

        public String getUnbindDriveCount() {
            return unbindDriveCount;
        }

        public void setUnbindDriveCount(String unbindDriveCount) {
            this.unbindDriveCount = unbindDriveCount;
        }

        public String getSpeedingCount() {
            return speedingCount;
        }

        public void setSpeedingCount(String speedingCount) {
            this.speedingCount = speedingCount;
        }

        public String getEfenceCount() {
            return efenceCount;
        }

        public void setEfenceCount(String efenceCount) {
            this.efenceCount = efenceCount;
        }

        public String getPoweroffCount() {
            return poweroffCount;
        }

        public void setPoweroffCount(String poweroffCount) {
            this.poweroffCount = poweroffCount;
        }

        public String getRepairMaintCount() {
            return repairMaintCount;
        }

        public void setRepairMaintCount(String repairMaintCount) {
            this.repairMaintCount = repairMaintCount;
        }

        public String getAlarmCount() {
            return alarmCount;
        }

        public void setAlarmCount(String alarmCount) {
            this.alarmCount = alarmCount;
        }

        public String getHKMFuel() {
            return hKMFuel;
        }

        public void setHKMFuel(String hKMFuel) {
            this.hKMFuel = hKMFuel;
        }

        public String getOnlinePercent() {
            return onlinePercent;
        }

        public void setOnlinePercent(String onlinePercent) {
            this.onlinePercent = onlinePercent;
        }
    }

    public static class VehicleDetailBean {
        /**
         * vehicleId : 15012710553123739
         * vehiclePlateNo : 苏EDNA02
         * deptId : 14111809492622508
         * mile : 746.21
         * fuel : 64.44
         * accumulativeDriveTime : 32.11
         * idlespeedCount : 1
         * idlespeedTime : 17
         * unbindDriveCount : 0
         * speedingCount : 0
         * efenceCount : 0
         * poweroffCount : 0
         * repairMaintCount : 0
         * alarmCount : 0
         * hKMFuel : 8.64
         * isOnline : 1
         */

        private String vehicleId;
        private String vehiclePlateNo;
        private String deptId;
        private String mile;
        private String fuel;
        private String accumulativeDriveTime;
        private String idlespeedCount;
        private String idlespeedTime;
        private String unbindDriveCount;
        private String speedingCount;
        private String efenceCount;
        private String poweroffCount;
        private String repairMaintCount;
        private String alarmCount;
        private String hKMFuel;
        private int isOnline;

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getVehiclePlateNo() {
            return vehiclePlateNo;
        }

        public void setVehiclePlateNo(String vehiclePlateNo) {
            this.vehiclePlateNo = vehiclePlateNo;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getMile() {
            return mile;
        }

        public void setMile(String mile) {
            this.mile = mile;
        }

        public String getFuel() {
            return fuel;
        }

        public void setFuel(String fuel) {
            this.fuel = fuel;
        }

        public String getAccumulativeDriveTime() {
            return accumulativeDriveTime;
        }

        public void setAccumulativeDriveTime(String accumulativeDriveTime) {
            this.accumulativeDriveTime = accumulativeDriveTime;
        }

        public String getIdlespeedCount() {
            return idlespeedCount;
        }

        public void setIdlespeedCount(String idlespeedCount) {
            this.idlespeedCount = idlespeedCount;
        }

        public String getIdlespeedTime() {
            return idlespeedTime;
        }

        public void setIdlespeedTime(String idlespeedTime) {
            this.idlespeedTime = idlespeedTime;
        }

        public String getUnbindDriveCount() {
            return unbindDriveCount;
        }

        public void setUnbindDriveCount(String unbindDriveCount) {
            this.unbindDriveCount = unbindDriveCount;
        }

        public String getSpeedingCount() {
            return speedingCount;
        }

        public void setSpeedingCount(String speedingCount) {
            this.speedingCount = speedingCount;
        }

        public String getEfenceCount() {
            return efenceCount;
        }

        public void setEfenceCount(String efenceCount) {
            this.efenceCount = efenceCount;
        }

        public String getPoweroffCount() {
            return poweroffCount;
        }

        public void setPoweroffCount(String poweroffCount) {
            this.poweroffCount = poweroffCount;
        }

        public String getRepairMaintCount() {
            return repairMaintCount;
        }

        public void setRepairMaintCount(String repairMaintCount) {
            this.repairMaintCount = repairMaintCount;
        }

        public String getAlarmCount() {
            return alarmCount;
        }

        public void setAlarmCount(String alarmCount) {
            this.alarmCount = alarmCount;
        }

        public String getHKMFuel() {
            return hKMFuel;
        }

        public void setHKMFuel(String hKMFuel) {
            this.hKMFuel = hKMFuel;
        }

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }
    }
}
