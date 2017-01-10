package com.datang.datangcarmanager.presenter;

import android.content.Context;
import android.widget.Toast;

import com.datang.datangcarmanager.api.ApiFactory;
import com.datang.datangcarmanager.api.CarApi;
import com.datang.datangcarmanager.model.DeptartmentList;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.VehicleList;
import com.datang.datangcarmanager.model.request.EnterpriseCarParams;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.view.IEnterpriseCarListView;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toby on 2017/1/5.
 */

public class EnterpriseCarListPresenter {

    private Context mContext;

    public static final CarApi carApi = ApiFactory.getCarApiSingleton();

    public EnterpriseCarListPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getAllEnterpriseCar(IEnterpriseCarListView iEnterpriseCarListView) {
        PostRequest request = new PostRequest();
        request.setCmd("corpDeptVehicleListV2");
        PostRequest.AuthBean authBean = new PostRequest.AuthBean();
        authBean.setPassword("000000");
        authBean.setMapType("google");
        authBean.setAppName("xfinder4company");
        authBean.setUserName("demo@sme");
        request.setAuth(authBean);
        EnterpriseCarParams paramsBean = new EnterpriseCarParams();
        paramsBean.setIsBind("1");
        paramsBean.setVehicleIds(new ArrayList<>());
        paramsBean.setHiddenNoContractVec("1");
        paramsBean.setDeptIds(new ArrayList<>());
        paramsBean.setCorpId("12120710341890007");
        request.setParams(paramsBean);
        carApi.getEquipmentCars(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showCarListInfo(iEnterpriseCarListView, Responce, mContext);
                }, this::loadError);
    }

    private void showCarListInfo(IEnterpriseCarListView view, Responce<VehicleList> responce, Context context) {
        view.getAllCarInfoSuccess(responce);
    }

    public void getAllDepartmentInfo(IEnterpriseCarListView iEnterpriseCarListView) {
        PostRequest request = new PostRequest();
        request.setCmd("corpDept");
        PostRequest.AuthBean authBean = new PostRequest.AuthBean();
        authBean.setPassword("000000");
        authBean.setMapType("google");
        authBean.setAppName("xfinder4company");
        authBean.setUserName("demo@sme");
        request.setAuth(authBean);
        EnterpriseCarParams paramsBean = new EnterpriseCarParams();
        paramsBean.setIsBind("1");
        paramsBean.setCorpId("12120710341890007");
        request.setParams(paramsBean);
        carApi.getDepartmentsInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showDepartmentInfo(iEnterpriseCarListView, Responce, mContext);
                }, this::loadError);
    }

    private void showDepartmentInfo(IEnterpriseCarListView view, Responce departmentList, Context context) {
        view.getAllDepartmentInfoSuccess(departmentList);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(mContext, "网络错误，稍后再试~", Toast.LENGTH_SHORT).show();
    }
}
