package com.datang.datangcarmanager.presenter;

import android.content.Context;
import android.widget.Toast;

import com.datang.datangcarmanager.api.ApiFactory;
import com.datang.datangcarmanager.api.CarApi;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.SearchCarInfo;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.model.request.SearchCarParams;
import com.datang.datangcarmanager.view.ISearchCarView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toby on 2017/1/7.
 */

public class SearchCarPresenter {

    private Context mContext;

    public static final CarApi carApi = ApiFactory.getCarApiSingleton();

    private String searchMessage;

    private int totalRecordCount = 0;

    private int pages = 0;

    private int nowPageNum = 0;

    private boolean isLoadMore = false;

    private ISearchCarView view;

    public SearchCarPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getSearchCarInfo(ISearchCarView iSearchCarView, String searchMessage) {
        this.searchMessage = searchMessage;
        PostRequest request = new PostRequest();
        request.setCmd("searchVehicle");
        PostRequest.AuthBean authBean = new PostRequest.AuthBean();
        authBean.setPassword("000000");
        authBean.setMapType("google");
        authBean.setAppName("xfinder4company");
        authBean.setUserName("demo@sme");
        request.setAuth(authBean);
        SearchCarParams params = new SearchCarParams();
        params.setCorpId("12120710341890007");
        params.setDeptId("");
        params.setIdNameOrLpno(searchMessage);
        params.setOnePageNum(10);
        params.setIsBind(0);
        params.setPageNo(0);
        request.setParams(params);

        this.view = view;

        carApi.getSearchCarInfo(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showSearchCarInfo(iSearchCarView, Responce, mContext);
                }, this::loadError);
    }

    private void showSearchCarInfo(ISearchCarView view, Responce<SearchCarInfo> responce, Context context) {
        totalRecordCount = responce.getTotalRecordNum();
        pages = responce.getPages();
        nowPageNum = responce.getPageNo();
        if (totalRecordCount - (nowPageNum + 1) * 10 > 0) {
            view.getSearchCarInfoSuccess(responce, true);
        } else {
            view.getSearchCarInfoSuccess(responce, false);
        }
    }

    public void  getLoadMoreInfo(ISearchCarView iSearchCarView) {
        if (!isLoadMore) {
            isLoadMore = true;
            PostRequest request = new PostRequest();
            request.setCmd("searchVehicle");
            PostRequest.AuthBean authBean = new PostRequest.AuthBean();
            authBean.setPassword("000000");
            authBean.setMapType("google");
            authBean.setAppName("xfinder4company");
            authBean.setUserName("demo@sme");
            request.setAuth(authBean);
            SearchCarParams params = new SearchCarParams();
            params.setCorpId("12120710341890007");
            params.setDeptId("");
            params.setIdNameOrLpno(searchMessage);
            params.setOnePageNum(10);
            params.setIsBind(0);
            params.setPageNo(nowPageNum + 1);
            request.setParams(params);

            this.view = view;

            carApi.getSearchCarInfo(request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(Responce -> {
                        showLoadMoreInfo(iSearchCarView, Responce, mContext);
                    }, this::loadError);
        }
    }

    private void showLoadMoreInfo(ISearchCarView view, Responce<SearchCarInfo> responce, Context context) {
        totalRecordCount = responce.getTotalRecordNum();
        pages = responce.getPages();
        nowPageNum = responce.getPageNo();
        if (totalRecordCount - (nowPageNum + 1) * 10 > 0) {
            view.loadMoreSearchCarInfoSuccess(responce, true);
        } else {
            view.loadMoreSearchCarInfoSuccess(responce, false);
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(mContext, "网络不见了", Toast.LENGTH_SHORT).show();
        view.loadError();
    }
}
