package com.datang.datangcarmanager.presenter;

import android.content.Context;
import android.widget.Toast;

import com.datang.datangcarmanager.api.ApiFactory;
import com.datang.datangcarmanager.api.CarApi;
import com.datang.datangcarmanager.model.AbnormalSituation;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.request.AbnormalSituationParams;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.view.IAbnormalSituationView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toby on 2017/1/7.
 */

public class AbnormalSituationPresenter {

    private Context mContext;

    public static final CarApi carApi = ApiFactory.getCarApiSingleton();

    private String searchMessage;

    private int totalRecordCount = 0;

    private int pages = 0;

    private int nowPageNum = 0;

    private boolean isLoadMore = false;

    private IAbnormalSituationView view;

    public AbnormalSituationPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public String getSearchMessage() {
        return searchMessage;
    }

    public void getAbnormalSituationInfo(IAbnormalSituationView iAbnormalSituationView,
                                         String searchMessage) {
        this.searchMessage = searchMessage;
        PostRequest request = new PostRequest();
        request.setCmd("ruleViolationAlarmList");
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

        this.view = iAbnormalSituationView;

        carApi.getAbnormalSituationInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showAbnormalSituationInfo(iAbnormalSituationView, Responce, mContext);
                }, this::loadError);
    }

    private void showAbnormalSituationInfo(IAbnormalSituationView view,
                                           Responce<AbnormalSituation> responce,
                                           Context context) {
        totalRecordCount = responce.getTotalRecordNum();
        pages = responce.getPages();
        nowPageNum = responce.getPageNo();
        if (totalRecordCount - (nowPageNum + 1) * 10 > 0) {
            view.getAbnormalSituationInfoSuccess(responce, true);
        } else {
            view.getAbnormalSituationInfoSuccess(responce, false);
        }
    }
    public void loadMoreAbnormalSituationInfo(IAbnormalSituationView iAbnormalSituationView) {
        PostRequest request = new PostRequest();
        request.setCmd("ruleViolationAlarmList");
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

        this.view = iAbnormalSituationView;

        carApi.getAbnormalSituationInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showloadMoreAbnormalSituationInfo(iAbnormalSituationView, Responce, mContext);
                }, this::loadError);
    }


    private void showloadMoreAbnormalSituationInfo(IAbnormalSituationView view,
                                                   Responce<AbnormalSituation> responce,
                                                   Context context) {
        totalRecordCount = responce.getTotalRecordNum();
        pages = responce.getPages();
        nowPageNum = responce.getPageNo();
        if (totalRecordCount - (nowPageNum + 1) * 10 > 0) {
            view.loadMoreAbnormalSituationInfoSuccess(responce, true);
        } else {
            view.loadMoreAbnormalSituationInfoSuccess(responce, false);
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(mContext, "网络不见了", Toast.LENGTH_SHORT).show();
        view.loadError();
    }
}
