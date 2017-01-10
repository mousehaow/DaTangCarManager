package com.datang.datangcarmanager.model.request;

import com.datang.datangcarmanager.model.request.Params;

import java.util.List;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.model
 * @class describe
 * @anthor toby
 * @time 2016/12/2 上午10:14
 * @change
 * @chang time
 * @class describe
 */
public class EnterpriseCarParams extends Params {

    /**
     * isBind : 1
     * vehicleIds : []
     * hiddenNoContractVec : 1
     * deptIds : []
     * corpId : 12120710341890007
     */

    private String isBind;
    private String hiddenNoContractVec;
    private List<?> vehicleIds;
    private List<?> deptIds;

    public String getIsBind() {
        return isBind;
    }

    public void setIsBind(String isBind) {
        this.isBind = isBind;
    }

    public String getHiddenNoContractVec() {
        return hiddenNoContractVec;
    }

    public void setHiddenNoContractVec(String hiddenNoContractVec) {
        this.hiddenNoContractVec = hiddenNoContractVec;
    }

    public List<?> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<?> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public List<?> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<?> deptIds) {
        this.deptIds = deptIds;
    }
}
