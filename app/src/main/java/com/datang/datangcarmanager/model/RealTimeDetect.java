package com.datang.datangcarmanager.model;

/**
 * Created by toby on 2017/1/5.
 */

public class RealTimeDetect {

    /**
     * posTime : 2016-12-23 17:41:59
     * fuelConsumption : 7.7
     * totalFuelConsumption : 9190.9
     * vehicleTotalDistance : 119176.5
     * fuelPressure : --
     * storageBatteryVoltage : --
     * vehicleSpeed : 0
     * engineRpm : 0
     * outsideAirTemp : --
     */

    private String posTime;
    private String fuelConsumption;
    private String totalFuelConsumption;
    private String vehicleTotalDistance;
    private String fuelPressure;
    private String storageBatteryVoltage;
    private String vehicleSpeed;
    private String engineRpm;
    private String outsideAirTemp;

    public String getPosTime() {
        return posTime;
    }

    public void setPosTime(String posTime) {
        this.posTime = posTime;
    }

    public String getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(String fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getTotalFuelConsumption() {
        return totalFuelConsumption;
    }

    public void setTotalFuelConsumption(String totalFuelConsumption) {
        this.totalFuelConsumption = totalFuelConsumption;
    }

    public String getVehicleTotalDistance() {
        return vehicleTotalDistance;
    }

    public void setVehicleTotalDistance(String vehicleTotalDistance) {
        this.vehicleTotalDistance = vehicleTotalDistance;
    }

    public String getFuelPressure() {
        return fuelPressure;
    }

    public void setFuelPressure(String fuelPressure) {
        this.fuelPressure = fuelPressure;
    }

    public String getStorageBatteryVoltage() {
        return storageBatteryVoltage;
    }

    public void setStorageBatteryVoltage(String storageBatteryVoltage) {
        this.storageBatteryVoltage = storageBatteryVoltage;
    }

    public String getVehicleSpeed() {
        return vehicleSpeed;
    }

    public void setVehicleSpeed(String vehicleSpeed) {
        this.vehicleSpeed = vehicleSpeed;
    }

    public String getEngineRpm() {
        return engineRpm;
    }

    public void setEngineRpm(String engineRpm) {
        this.engineRpm = engineRpm;
    }

    public String getOutsideAirTemp() {
        return outsideAirTemp;
    }

    public void setOutsideAirTemp(String outsideAirTemp) {
        this.outsideAirTemp = outsideAirTemp;
    }
}
