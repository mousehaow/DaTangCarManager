package com.datang.datangcarmanager.presenter;

import android.content.Context;

import com.datang.datangcarmanager.api.ApiFactory;
import com.datang.datangcarmanager.api.CarApi;
import com.datang.datangcarmanager.model.AlarmCostDetails;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.request.AlarmCostParams;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.view.IAlarmCostDetailsView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toby on 2017/1/7.
 */

public class AlarmCostDetailsPresenter {

    private Context mContext;

    public static final CarApi carApi = ApiFactory.getCarApiSingleton();

    private IAlarmCostDetailsView view;

    public AlarmCostDetailsPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getAlarmCostDetailsInfo(IAlarmCostDetailsView iAlarmCostDetailsView, String costId) {
        view = iAlarmCostDetailsView;
        PostRequest request = new PostRequest();
        request.setCmd("alarmCostInfo");
        PostRequest.AuthBean authBean = new PostRequest.AuthBean();
        authBean.setPassword("000000");
        authBean.setMapType("google");
        authBean.setAppName("xfinder4company");
        authBean.setUserName("demo@sme");
        request.setAuth(authBean);
        AlarmCostParams params = new AlarmCostParams();
        params.setCorpId("12120710341890007");
        params.setDeptId("");
        params.setRecUid(costId);
        request.setParams(params);

        carApi.getAlarmCostDetailsInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showAlarmCostDetailsInfo(iAlarmCostDetailsView, Responce, mContext);
                }, this::loadError);
    }

    private void showAlarmCostDetailsInfo(IAlarmCostDetailsView iAlarmCostDetailsView,
                                          Responce<AlarmCostDetails> responce,
                                          Context mContext) {
        iAlarmCostDetailsView.getAlarmCostDetailsInfoSuccess(responce);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        view.loadError();
    }
}
