package com.datang.datangcarmanager.model;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.model
 * @class describe
 * @anthor toby
 * @time 16/11/21 上午9:15
 * @change
 * @chang time
 * @class describe
 */
public class Vehicle {

    public static final int CAR_STATE_OFFLINE = 0;
    public static final int CAR_STATE_STOP = 1;
    public static final int CAR_STATE_MOVING = 2;

    private String objId;
    private String idName;
    private String deptId;
    private String deptName;
    private String productName;
    private String color;
    private String lpno;
    private String picture;
    private int ispublic;
    private int offlineDuration;
    private int hasAlarmMsg;
    private int isOBD;
    private String bindDevice;
    private int onlineStatus;
    private int posMethod;
    private double longitude;
    private double latitude;
    private int direction;
    private double speed;
    private String posTime;
    private String driverId;
    private String driverName;
    private String driverTelephone;
    private String corpId;
    private String hasDriver;
    private String hasServiceContract;

    private String userName;
    private String province;
    private String city;
    private String corpName;
    private String brandName;
    private String displacement;
    private String serviceTypeId;
    private String serviceType;
    private String hasExtStatus;
    private String offlineTime;
    private int objType;
    private String curUser;
    private String isOwner;
    private String serviceTypeDesc;
    private String vehicleTerminalNo;

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLpno() {
        return lpno;
    }

    public void setLpno(String lpno) {
        this.lpno = lpno;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getIspublic() {
        return ispublic;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    public int getOfflineDuration() {
        return offlineDuration;
    }

    public void setOfflineDuration(int offlineDuration) {
        this.offlineDuration = offlineDuration;
    }

    public int getHasAlarmMsg() {
        return hasAlarmMsg;
    }

    public void setHasAlarmMsg(int hasAlarmMsg) {
        this.hasAlarmMsg = hasAlarmMsg;
    }

    public int getIsOBD() {
        return isOBD;
    }

    public void setIsOBD(int isOBD) {
        this.isOBD = isOBD;
    }

    public String getBindDevice() {
        return bindDevice;
    }

    public void setBindDevice(String bindDevice) {
        this.bindDevice = bindDevice;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getPosTime() {
        return posTime;
    }

    public void setPosTime(String posTime) {
        this.posTime = posTime;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
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

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getHasDriver() {
        return hasDriver;
    }

    public void setHasDriver(String hasDriver) {
        this.hasDriver = hasDriver;
    }

    public String getHasServiceContract() {
        return hasServiceContract;
    }

    public void setHasServiceContract(String hasServiceContract) {
        this.hasServiceContract = hasServiceContract;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getHasExtStatus() {
        return hasExtStatus;
    }

    public void setHasExtStatus(String hasExtStatus) {
        this.hasExtStatus = hasExtStatus;
    }

    public String getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(String offlineTime) {
        this.offlineTime = offlineTime;
    }

    public int getObjType() {
        return objType;
    }

    public void setObjType(int objType) {
        this.objType = objType;
    }

    public String getCurUser() {
        return curUser;
    }

    public void setCurUser(String curUser) {
        this.curUser = curUser;
    }

    public String getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(String isOwner) {
        this.isOwner = isOwner;
    }

    public String getServiceTypeDesc() {
        return serviceTypeDesc;
    }

    public void setServiceTypeDesc(String serviceTypeDesc) {
        this.serviceTypeDesc = serviceTypeDesc;
    }

    public String getVehicleTerminalNo() {
        return vehicleTerminalNo;
    }

    public void setVehicleTerminalNo(String vehicleTerminalNo) {
        this.vehicleTerminalNo = vehicleTerminalNo;
    }
}
