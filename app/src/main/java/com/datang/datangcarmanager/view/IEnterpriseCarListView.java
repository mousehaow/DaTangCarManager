package com.datang.datangcarmanager.view;

import com.datang.datangcarmanager.model.DeptartmentList;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.VehicleList;

/**
 * Created by toby on 2017/1/5.
 */

public interface IEnterpriseCarListView {
    void getAllCarInfoSuccess(Responce<VehicleList> responce);
    void getAllDepartmentInfoSuccess(Responce<DeptartmentList> responce);
}
