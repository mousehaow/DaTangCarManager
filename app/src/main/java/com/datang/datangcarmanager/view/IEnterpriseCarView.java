package com.datang.datangcarmanager.view;

import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.VehicleList;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.view
 * @class describe
 * @anthor toby
 * @time 2016/12/2 上午8:54
 * @change
 * @chang time
 * @class describe
 */
public interface IEnterpriseCarView {
    void getAllCarInfoSuccess(Responce<VehicleList> responce);
}
