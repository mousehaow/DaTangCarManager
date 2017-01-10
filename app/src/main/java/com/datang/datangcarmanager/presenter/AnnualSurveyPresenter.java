package com.datang.datangcarmanager.presenter;

import android.content.Context;
import android.widget.Toast;

import com.datang.datangcarmanager.api.ApiFactory;
import com.datang.datangcarmanager.api.CarApi;
import com.datang.datangcarmanager.model.AnnualSurveyInfos;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.request.AbnormalSituationParams;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.view.IAnnualSurveyView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toby on 2017/1/9.
 */

public class AnnualSurveyPresenter {

    private Context mContext;

    public static final CarApi carApi = ApiFactory.getCarApiSingleton();

    private String searchMessage;

    private int totalRecordCount = 0;

    private int pages = 0;

    private int nowPageNum = 0;

    private IAnnualSurveyView view;

    public AnnualSurveyPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public String getSearchMessage() {
        return searchMessage;
    }

    public void getAnnualSurveyInfo(IAnnualSurveyView iAnnualSurveyView,
                                          String searchMessage) {
        this.searchMessage = searchMessage;
        PostRequest request = new PostRequest();
        request.setCmd("insuranceAuditAlarmListForApp");
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

        this.view = iAnnualSurveyView;

        carApi.getAnnualSurveyInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showAnnualSurveyInfo(iAnnualSurveyView, Responce, mContext);
                }, this::loadError);
    }

    private void showAnnualSurveyInfo(IAnnualSurveyView iAnnualSurveyView,
                                            Responce<AnnualSurveyInfos> responce,
                                            Context mContext) {
        totalRecordCount = responce.getTotalRecordNum();
        pages = responce.getPages();
        nowPageNum = responce.getPageNo();
        if (totalRecordCount - (nowPageNum + 1) * 10 > 0) {
            iAnnualSurveyView.getAnnualSurveyInfosSuccess(responce, true);
        } else {
            iAnnualSurveyView.getAnnualSurveyInfosSuccess(responce, false);
        }
    }

    public void loadMoreAnnualSurveyInfo(IAnnualSurveyView iAnnualSurveyView) {
        PostRequest request = new PostRequest();
        request.setCmd("insuranceAuditAlarmListForApp");
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

        view = iAnnualSurveyView;

        carApi.getAnnualSurveyInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showloadMoreAnnualSurveyInfo(iAnnualSurveyView, Responce, mContext);
                }, this::loadError);
    }

    private void showloadMoreAnnualSurveyInfo(IAnnualSurveyView iAnnualSurveyView,
                                                    Responce<AnnualSurveyInfos> responce,
                                                    Context mContext) {
        totalRecordCount = responce.getTotalRecordNum();
        pages = responce.getPages();
        nowPageNum = responce.getPageNo();
        if (totalRecordCount - (nowPageNum + 1) * 10 > 0) {
            iAnnualSurveyView.loadMoreAnnualSurveyInfosSuccess(responce, true);
        } else {
            iAnnualSurveyView.loadMoreAnnualSurveyInfosSuccess(responce, false);
        }
    }


    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(mContext, "网络不见了", Toast.LENGTH_SHORT).show();
        view.loadError();
    }
}
