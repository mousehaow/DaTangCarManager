package com.datang.datangcarmanager.presenter;

import android.content.Context;
import android.widget.Toast;

import com.datang.datangcarmanager.api.ApiFactory;
import com.datang.datangcarmanager.api.CarApi;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.Vehicle;
import com.datang.datangcarmanager.model.VehicleList;
import com.datang.datangcarmanager.model.request.EnterpriseCarParams;
import com.datang.datangcarmanager.model.request.PostRequest;
import com.datang.datangcarmanager.view.IEnterpriseCarView;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presenter
 * @class describe
 * @anthor toby
 * @time 2016/12/2 上午9:41
 * @change
 * @chang time
 * @class describe
 */
public class EnterpriseCarPresenter {

    private Context mContext;

    public static final CarApi carApi = ApiFactory.getCarApiSingleton();

    public EnterpriseCarPresenter(Context context) {
        mContext = context;
    }

    public void getAllEnterpriseCar(IEnterpriseCarView iEnterpriseCarView) {
        PostRequest request = new PostRequest();
        request.setCmd("corpDeptVehicleListV2");
        PostRequest.AuthBean authBean = new PostRequest.AuthBean();
        authBean.setPassword("000000");
        authBean.setMapType("google");
        authBean.setAppName("xfinder4company");
        authBean.setUserName("demo@sme");
        request.setAuth(authBean);
        EnterpriseCarParams paramsBean = new EnterpriseCarParams();
        paramsBean.setIsBind("1");
        paramsBean.setVehicleIds(new ArrayList<>());
        paramsBean.setHiddenNoContractVec("1");
        paramsBean.setDeptIds(new ArrayList<>());
        paramsBean.setCorpId("12120710341890007");
        request.setParams(paramsBean);
        carApi.getEquipmentCars(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Responce -> {
                    showInfo(iEnterpriseCarView, Responce, mContext);
                }, this::loadError);
    }

    private void showInfo(IEnterpriseCarView view, Responce<VehicleList> responce, Context context) {
        view.getAllCarInfoSuccess(responce);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        //Toast.makeText(mContext, "网络不见了", Toast.LENGTH_SHORT).show();
    }
}
