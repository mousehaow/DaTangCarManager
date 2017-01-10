package com.datang.datangcarmanager.presenter;

import android.content.Context;
import android.widget.Toast;

import com.datang.datangcarmanager.api.ApiFactory;
import com.datang.datangcarmanager.api.CarApi;
import com.datang.datangcarmanager.model.AbnormalSituation;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.request.AbnormalSituationParams;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.view.ITroubleRepairAlarmView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toby on 2017/1/7.
 */

public class TroubleRepairAlarmPresenter {

    private Context mContext;

    public static final CarApi carApi = ApiFactory.getCarApiSingleton();

    private String searchMessage;

    private int totalRecordCount = 0;

    private int pages = 0;

    private int nowPageNum = 0;

    private boolean isLoadMore = false;

    private ITroubleRepairAlarmView view;

    public TroubleRepairAlarmPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public String getSearchMessage() {
        return searchMessage;
    }

    public void getTroubleRepairAlarmInfo(ITroubleRepairAlarmView iTroubleRepairAlarmView,
                                          String searchMessage) {
        this.searchMessage = searchMessage;
        PostRequest request = new PostRequest();
        request.setCmd("troubleRepairAlarmList");
        PostRequest.AuthBean authBean = new PostRequest.AuthBean();
        authBean.setPassword("000000");
        authBean.setMapType("google");
        authBean.setAppName("xfinder4company");
        authBean.setUserName("demo@sme");
        request.setAuth(authBean);
        AbnormalSituationParams params = new AbnormalSituationParams();
        params.setCorpId("12120710341890007");
        params.setDeptId("");
        params.setPageNo(0);
        params.setOnePageNum(10);
        params.setProcessStatus("0");
        params.setLpnoAlias(searchMessage);
        request.setParams(params);

        this.view = iTroubleRepairAlarmView;

        carApi.getAbnormalSituationInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showTroubleRepairAlarmInfo(iTroubleRepairAlarmView, Responce, mContext);
                }, this::loadError);
    }

    private void showTroubleRepairAlarmInfo(ITroubleRepairAlarmView iTroubleRepairAlarmView,
                                            Responce<AbnormalSituation> responce,
                                            Context mContext) {
        totalRecordCount = responce.getTotalRecordNum();
        pages = responce.getPages();
        nowPageNum = responce.getPageNo();
        if (totalRecordCount - (nowPageNum + 1) * 10 > 0) {
            iTroubleRepairAlarmView.getTroubleRepairAlarmSuccess(responce, true);
        } else {
            iTroubleRepairAlarmView.getTroubleRepairAlarmSuccess(responce, false);
        }
    }

    public void loadMoreTroubleRepairAlarmInfo(ITroubleRepairAlarmView iTroubleRepairAlarmView) {
        PostRequest request = new PostRequest();
        request.setCmd("troubleRepairAlarmList");
        PostRequest.AuthBean authBean = new PostRequest.AuthBean();
        authBean.setPassword("000000");
        authBean.setMapType("google");
        authBean.setAppName("xfinder4company");
        authBean.setUserName("demo@sme");
        request.setAuth(authBean);
        AbnormalSituationParams params = new AbnormalSituationParams();
        params.setCorpId("12120710341890007");
        params.setDeptId("");
        params.setPageNo(nowPageNum + 1);
        params.setOnePageNum(10);
        params.setProcessStatus("0");
        params.setLpnoAlias(searchMessage);
        request.setParams(params);

        view = iTroubleRepairAlarmView;

        carApi.getAbnormalSituationInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showloadMoreTroubleRepairAlarmInfo(iTroubleRepairAlarmView, Responce, mContext);
                }, this::loadError);
    }

    private void showloadMoreTroubleRepairAlarmInfo(ITroubleRepairAlarmView iTroubleRepairAlarmView,
                                                    Responce<AbnormalSituation> responce,
                                                    Context mContext) {
        totalRecordCount = responce.getTotalRecordNum();
        pages = responce.getPages();
        nowPageNum = responce.getPageNo();
        if (totalRecordCount - (nowPageNum + 1) * 10 > 0) {
            iTroubleRepairAlarmView.loadMoreTroubleRepairAlarmInfoSuccess(responce, true);
        } else {
            iTroubleRepairAlarmView.loadMoreTroubleRepairAlarmInfoSuccess(responce, false);
        }
    }


    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(mContext, "网络不见了", Toast.LENGTH_SHORT).show();
        view.loadError();
    }
}
