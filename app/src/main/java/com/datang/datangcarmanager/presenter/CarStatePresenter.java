package com.datang.datangcarmanager.presenter;

import android.content.Context;

import com.datang.datangcarmanager.api.ApiFactory;
import com.datang.datangcarmanager.api.CarApi;
import com.datang.datangcarmanager.model.RealTimeDetect;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.VehicleList;
import com.datang.datangcarmanager.model.request.Params;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.view.ICarStateView;
import com.datang.datangcarmanager.view.IEnterpriseCarView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toby on 2017/1/5.
 */

public class CarStatePresenter {

    private Context mContext;

    public static final CarApi carApi = ApiFactory.getCarApiSingleton();

    public CarStatePresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getCarStateInfo(ICarStateView iCarStateView, String vehicleId) {
        PostRequest request = new PostRequest();
        request.setCmd("realTimeDetectV1");
        PostRequest.AuthBean authBean = new PostRequest.AuthBean();
        authBean.setPassword("000000");
        authBean.setMapType("google");
        authBean.setAppName("xfinder4company");
        authBean.setUserName("demo@sme");
        request.setAuth(authBean);
        Params params = new Params();
        params.setVehicleId(vehicleId);
        request.setParams(params);
        carApi.getCarStateInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showInfo(iCarStateView, Responce, mContext);
                }, this::loadError);
    }

    private void showInfo(ICarStateView view, Responce responce, Context context) {
        view.getAllCarInfoSuccess(responce);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        //Toast.makeText(mContext, "网络不见了", Toast.LENGTH_SHORT).show();
    }
}
