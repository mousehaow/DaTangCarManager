package com.datang.datangcarmanager.presenter;

import android.content.Context;

import com.datang.datangcarmanager.api.ApiFactory;
import com.datang.datangcarmanager.api.CarApi;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.request.DriveRecordParams;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.view.IDriveRecordView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toby on 2017/1/5.
 */

public class DriveRecordPresenter {

    private Context mContext;

    public static final CarApi carApi = ApiFactory.getCarApiSingleton();

    public DriveRecordPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getDriveRecords(IDriveRecordView iDriveRecordView,
                                          String vehicleId,
                                          String beginTime,
                                          String endTime) {
        PostRequest request = new PostRequest();
        request.setCmd("trackSegmentListWithTime");
        PostRequest.AuthBean authBean = new PostRequest.AuthBean();
        authBean.setPassword("000000");
        authBean.setMapType("google");
        authBean.setAppName("xfinder4company");
        authBean.setUserName("demo@sme");
        request.setAuth(authBean);
        DriveRecordParams params = new DriveRecordParams();
        params.setObjId(vehicleId);
        params.setBeginTime(beginTime);
        params.setEndTime(endTime);
        request.setParams(params);
        carApi.getDriveRecordsInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showInfo(iDriveRecordView, Responce, mContext);
                }, this::loadError);

    }

    private void showInfo(IDriveRecordView view, Responce responce, Context context) {
        view.getDriveRecordsSuccess(responce);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        //Toast.makeText(mContext, "网络不见了", Toast.LENGTH_SHORT).show();
    }
}
