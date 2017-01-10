package com.datang.datangcarmanager.presenter;

import android.content.Context;

import com.datang.datangcarmanager.api.ApiFactory;
import com.datang.datangcarmanager.api.CarApi;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.request.CarArchiveParams;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.view.ICarArchiveView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toby on 2017/1/6.
 */

public class CarArchivePresenter {

    private Context mContext;

    public static final CarApi carApi = ApiFactory.getCarApiSingleton();

    public CarArchivePresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getCarArchiveInfo(ICarArchiveView iCarArchiveView, String vehicleId, String lpno) {
        PostRequest request = new PostRequest();
        request.setCmd("vehiclePropertyV1");
        PostRequest.AuthBean authBean = new PostRequest.AuthBean();
        authBean.setPassword("000000");
        authBean.setMapType("google");
        authBean.setAppName("xfinder4company");
        authBean.setUserName("demo@sme");
        request.setAuth(authBean);
        CarArchiveParams params = new CarArchiveParams();
        params.setObjId(vehicleId);
        params.setLpno(lpno);
        request.setParams(params);
        carApi.getCarArchiveInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showInfo(iCarArchiveView, Responce, mContext);
                }, this::loadError);
    }

    private void showInfo(ICarArchiveView view, Responce responce, Context context) {
        view.getCarArchiveInfoSuccess(responce);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        //Toast.makeText(mContext, "网络不见了", Toast.LENGTH_SHORT).show();
    }
}
