package com.datang.datangcarmanager.presenter;

import android.content.Context;
import android.util.Log;

import com.datang.datangcarmanager.api.ApiFactory;
import com.datang.datangcarmanager.api.CarApi;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.request.CarTrackParams;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.view.ICarTrackView;
import com.google.gson.Gson;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toby on 2017/1/6.
 */

public class CarTrackPresenter {

    private Context mContext;

    public static final CarApi carApi = ApiFactory.getCarApiSingleton();

    public CarTrackPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getCarTrackInfo(ICarTrackView iCarTrackView, String vehicleId, String type) {
        PostRequest request = new PostRequest();
        request.setCmd("serviceObjsRealTrack");
        PostRequest.AuthBean authBean = new PostRequest.AuthBean();
        authBean.setPassword("000000");
        authBean.setMapType("google");
        authBean.setAppName("xfinder4company");
        authBean.setUserName("demo@sme");
        request.setAuth(authBean);
        CarTrackParams params = new CarTrackParams();
        params.setParaValue(vehicleId);
        params.setParaType(type);
        request.setParams(params);
        carApi.getCarTrackInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showInfo(iCarTrackView, Responce, mContext);
                }, this::loadError);
    }

    private void showInfo(ICarTrackView view, Responce responce, Context context) {
        view.getCarTrackInfoSuccess(responce);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        //Toast.makeText(mContext, "网络不见了", Toast.LENGTH_SHORT).show();
    }
}
