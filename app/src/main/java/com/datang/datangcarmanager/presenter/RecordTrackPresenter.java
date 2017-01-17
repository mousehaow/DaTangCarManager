package com.datang.datangcarmanager.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.datang.datangcarmanager.api.ApiFactory;
import com.datang.datangcarmanager.api.CarApi;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.request.DriveRecordParams;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.view.IRecordTrackView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toby on 2017/1/17.
 */

public class RecordTrackPresenter {

    private Context mContext;

    private IRecordTrackView iView;

    public static final CarApi carApi = ApiFactory.getCarApiSingleton();

    public RecordTrackPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getRecordTrackInfo(IRecordTrackView iRecordTrackView,
                                   String beginTime,
                                   String endTime,
                                   String objId) {
        iView = iRecordTrackView;
        PostRequest request = new PostRequest();
        request.setCmd("segTrackData");
        PostRequest.AuthBean authBean = new PostRequest.AuthBean();
        authBean.setPassword("000000");
        authBean.setMapType("google");
        authBean.setAppName("xfinder4company");
        authBean.setUserName("demo@sme");
        request.setAuth(authBean);
        DriveRecordParams params = new DriveRecordParams();
        params.setBeginTime(beginTime);
        params.setEndTime(endTime);
        params.setObjId(objId);
        request.setParams(params);
        Log.i("HELLLLLLL", "request");
        carApi.getRecordTrackInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showInfo(iRecordTrackView, Responce, mContext);
                }, this::loadError);
    }

    private void showInfo(IRecordTrackView iRecordTrackView, Responce responce, Context mContext) {
        iRecordTrackView.getRecordTrackInfoSuccess(responce);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        iView.loadError();
    }
}
