package com.datang.datangcarmanager.model;

import android.widget.TextView;

import com.datang.datangcarmanager.R;

import butterknife.BindView;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.model
 * @class describe
 * @anthor toby
 * @time 16/11/19 下午6:31
 * @change
 * @chang time
 * @class describe
 */
public class StatisticsAnalyze {

    /* 名称 */
    private String name;
    /* 总里程 */
    private float mailage;
    /* 上线率 */
    private float onlineRatio;
    /* 耗油量 */
    private float oilConsumption;
    /* 越线次数 */
    private int overLine;
    /* 总行驶时间 */
    private float totalTime;
    /* 拔出次数 */
    private int offTime;
    /* 怠速总时间 */
    private int IdlingTime;
    /* 维修次数 */
    private int repairTime;
    /* 未匹配用车次数 */
    private int notMatchingTime;
    /* 违章次数 */
    private int violationTime;
    /* 是否上线 */
    private boolean onLine;
    /* 是否是车辆数据或是部门数据 */
    private boolean isCar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMailage() {
        return mailage;
    }

    public void setMailage(float mailage) {
        this.mailage = mailage;
    }

    public float getOnlineRatio() {
        return onlineRatio;
    }

    public void setOnlineRatio(float onlineRatio) {
        this.onlineRatio = onlineRatio;
    }

    public float getOilConsumption() {
        return oilConsumption;
    }

    public void setOilConsumption(float oilConsumption) {
        this.oilConsumption = oilConsumption;
    }

    public int getOverLine() {
        return overLine;
    }

    public void setOverLine(int overLine) {
        this.overLine = overLine;
    }

    public float getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }

    public int getOffTime() {
        return offTime;
    }

    public void setOffTime(int offTime) {
        this.offTime = offTime;
    }

    public int getIdlingTime() {
        return IdlingTime;
    }

    public void setIdlingTime(int idlingTime) {
        IdlingTime = idlingTime;
    }

    public int getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(int repairTime) {
        this.repairTime = repairTime;
    }

    public int getNotMatchingTime() {
        return notMatchingTime;
    }

    public void setNotMatchingTime(int notMatchingTime) {
        this.notMatchingTime = notMatchingTime;
    }

    public int getViolationTime() {
        return violationTime;
    }

    public void setViolationTime(int violationTime) {
        this.violationTime = violationTime;
    }

    public boolean isOnLine() {
        return onLine;
    }

    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }

    public boolean isCar() {
        return isCar;
    }

    public void setCar(boolean car) {
        isCar = car;
    }
}
