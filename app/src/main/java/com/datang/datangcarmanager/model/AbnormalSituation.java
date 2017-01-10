package com.datang.datangcarmanager.model;

import java.util.List;

/**
 * Created by toby on 2017/1/7.
 */

public class AbnormalSituation {

    private int hasDoneNum;
    private List<AbnormalSituationBean> dataList;

    public int getHasDoneNum() {
        return hasDoneNum;
    }

    public void setHasDoneNum(int hasDoneNum) {
        this.hasDoneNum = hasDoneNum;
    }

    public List<AbnormalSituationBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<AbnormalSituationBean> dataList) {
        this.dataList = dataList;
    }

    public static class AbnormalSituationBean {
        /**
         * alarmCostId : 16122418180231653
         * vehicleId : 14061313500697250
         * lpno : 苏EDNA07
         * alias : 迪ES973Q
         * deptId : 12120710341890007D106
         * deptName : 服务部
         * driver :
         * alarmTime : 2016-12-24 18:11:20
         * alarmTypeId : 7
         * alarmType : 车辆未匹配报警
         * description :
         * processStatus : 0
         * personInCharge :
         * processTime :
         * pic : 00324114.png
         * cost :
         */

        private String alarmCostId;
        private String vehicleId;
        private String lpno;
        private String alias;
        private String deptId;
        private String deptName;
        private String driver;
        private String alarmTime;
        private String alarmTypeId;
        private String alarmType;
        private String description;
        private String processStatus;
        private String personInCharge;
        private String processTime;
        private String pic;
        private String cost;

        public String getAlarmCostId() {
            return alarmCostId;
        }

        public void setAlarmCostId(String alarmCostId) {
            this.alarmCostId = alarmCostId;
        }

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getLpno() {
            return lpno;
        }

        public void setLpno(String lpno) {
            this.lpno = lpno;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

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

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getAlarmTime() {
            return alarmTime;
        }

        public void setAlarmTime(String alarmTime) {
            this.alarmTime = alarmTime;
        }

        public String getAlarmTypeId() {
            return alarmTypeId;
        }

        public void setAlarmTypeId(String alarmTypeId) {
            this.alarmTypeId = alarmTypeId;
        }

        public String getAlarmType() {
            return alarmType;
        }

        public void setAlarmType(String alarmType) {
            this.alarmType = alarmType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getProcessStatus() {
            return processStatus;
        }

        public void setProcessStatus(String processStatus) {
            this.processStatus = processStatus;
        }

        public String getPersonInCharge() {
            return personInCharge;
        }

        public void setPersonInCharge(String personInCharge) {
            this.personInCharge = personInCharge;
        }

        public String getProcessTime() {
            return processTime;
        }

        public void setProcessTime(String processTime) {
            this.processTime = processTime;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }
    }
}
