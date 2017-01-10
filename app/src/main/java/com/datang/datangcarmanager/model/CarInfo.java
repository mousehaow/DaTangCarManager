package com.datang.datangcarmanager.model;

/**
 * Created by toby on 16/11/14.
 */
public class CarInfo {

    public static final int CAR_STATE_MOVING = 0;
    public static final int CAR_STATE_STOP = 1;
    public static final int CAR_STATE_OFFLINE = 2;

    /* id */
    private int id;
    /* 状态 */
    private int state;
    /* 部门id */
    private int departmentId;
    /* 车名 */
    private String name;
    /* 司机 */
    private String driver;
    /* 车牌号 */
    private String plateNum;
    /* 车辆品牌 */
    private String brandImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(String brandImage) {
        this.brandImage = brandImage;
    }
}
