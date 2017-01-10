package com.datang.datangcarmanager.model;

import java.util.List;

/**
 * Created by toby on 2017/1/9.
 */

public class AnnualSurveyInfos {

    /**
     * dataList : [{"recUid":"16090810272138313","vehicleId":"13071611134677823","pic":"00324116.png","lpno":"苏ACZG05","feeType":"10","feeTypeName":"保养到期","dueTime":""},{"recUid":"16111101445775092","vehicleId":"14061313461596997","pic":"00324114.png","lpno":"苏EDNA03","feeType":"10","feeTypeName":"保养到期","dueTime":""},{"recUid":"16111101445775096","vehicleId":"14061313500697250","pic":"00324114.png","lpno":"苏EDNA07","feeType":"10","feeTypeName":"保养到期","dueTime":""},{"recUid":"16111101445775099","vehicleId":"14061313504297296","pic":"00324114.png","lpno":"苏EDNA08","feeType":"10","feeTypeName":"保养到期","dueTime":""}]
     * hasDoneNum : 0
     */

    private int hasDoneNum;
    private List<AnnualSurveyBean> dataList;

    public int getHasDoneNum() {
        return hasDoneNum;
    }

    public void setHasDoneNum(int hasDoneNum) {
        this.hasDoneNum = hasDoneNum;
    }

    public List<AnnualSurveyBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<AnnualSurveyBean> dataList) {
        this.dataList = dataList;
    }

    public static class AnnualSurveyBean {
        /**
         * recUid : 16090810272138313
         * vehicleId : 13071611134677823
         * pic : 00324116.png
         * lpno : 苏ACZG05
         * feeType : 10
         * feeTypeName : 保养到期
         * dueTime :
         */

        private String recUid;
        private String vehicleId;
        private String pic;
        private String lpno;
        private String feeType;
        private String feeTypeName;
        private String dueTime;

        public String getRecUid() {
            return recUid;
        }

        public void setRecUid(String recUid) {
            this.recUid = recUid;
        }

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getLpno() {
            return lpno;
        }

        public void setLpno(String lpno) {
            this.lpno = lpno;
        }

        public String getFeeType() {
            return feeType;
        }

        public void setFeeType(String feeType) {
            this.feeType = feeType;
        }

        public String getFeeTypeName() {
            return feeTypeName;
        }

        public void setFeeTypeName(String feeTypeName) {
            this.feeTypeName = feeTypeName;
        }

        public String getDueTime() {
            return dueTime;
        }

        public void setDueTime(String dueTime) {
            this.dueTime = dueTime;
        }
    }
}
