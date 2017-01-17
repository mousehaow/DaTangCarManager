package com.datang.datangcarmanager.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.datang.datangcarmanager.api.ApiFactory;
import com.datang.datangcarmanager.api.CarApi;
import com.datang.datangcarmanager.model.ParkingRecordList;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.request.DriveRecordParams;
import com.datang.datangcarmanager.model.request.ParkingRecordParams;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.view.IParkingRecordView;
import com.google.gson.Gson;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toby on 2017/1/6.
 */

public class ParkingRecordPresenter {

    private Context mContext;

    public static final CarApi carApi = ApiFactory.getCarApiSingleton();

    public ParkingRecordPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getParkingRecordsInfo(IParkingRecordView iParkingRecordView,
                                                  String vehicleId,
                                                  String date,
                                                  String lpno) {
        PostRequest request = new PostRequest();
        request.setCmd("historyDockedLocation");
        PostRequest.AuthBean authBean = new PostRequest.AuthBean();
        authBean.setPassword("000000");
        authBean.setMapType("google");
        authBean.setAppName("xfinder4company");
        authBean.setUserName("demo@sme");
        request.setAuth(authBean);
        ParkingRecordParams params = new ParkingRecordParams();
        params.setCorpId("12120710341890007");
        params.setDeptId("");
        params.setVehicleId(vehicleId);
        params.setDate(date);
        params.setLpno(lpno);
        request.setParams(params);
        carApi.getParkingRecordsInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showInfo(iParkingRecordView, Responce, mContext);
                }, this::loadError);
    }

    private void showInfo(IParkingRecordView view, Responce<ParkingRecordList> responce, Context context) {
        Gson gson = new Gson();
        view.getParkingRecordsInfoSuccess(responce);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(mContext, "网络不见了", Toast.LENGTH_SHORT).show();
    }
}
