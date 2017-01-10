package com.datang.datangcarmanager.presentation.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.CarArchive;
import com.datang.datangcarmanager.model.CarInfo;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.Vehicle;
import com.datang.datangcarmanager.presentation.activity.SingleCarInfoActivity;
import com.datang.datangcarmanager.presenter.CarArchivePresenter;
import com.datang.datangcarmanager.view.ICarArchiveView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class CarArchiveFragment extends Fragment implements ICarArchiveView {

    @BindView(R.id.car_archive_plate_num)
    TextView mCarArchivePlateNum;
    @BindView(R.id.car_archive_car_name)
    TextView mCarArchiveCarName;
    @BindView(R.id.car_archive_car_brand)
    TextView mCarArchiveCarBrand;
    @BindView(R.id.car_archive_department)
    TextView mCarArchiveDepartment;
    @BindView(R.id.car_archive_current_mileage)
    TextView mCarArchiveCurrentMileage;
    @BindView(R.id.car_archive_maintenance_interval)
    TextView mCarArchiveMaintenanceInterval;
    @BindView(R.id.car_archive_insurance_date)
    TextView mCarArchiveInsuranceDate;
    @BindView(R.id.car_archive_inspection_date)
    TextView mCarArchiveInspectionDate;

    private Vehicle mCarInfo;

    private int nowPage;

    private CarArchivePresenter mPresenter;
    private CarArchive mArchive;

    private SweetAlertDialog mDialog;

    public CarArchiveFragment(Vehicle carInfo) {
        mCarInfo = carInfo;
    }


    public static CarArchiveFragment newInstance(Vehicle carInfo) {
        CarArchiveFragment fragment = new CarArchiveFragment(carInfo);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_archive, container, false);
        ButterKnife.bind(this, view);
        mCarArchiveCarName.setText("");
        mCarArchiveCarBrand.setText("");
        mCarArchiveDepartment.setText("");
        mCarArchivePlateNum.setText("");
        mCarArchiveCurrentMileage.setText("");
        mCarArchiveMaintenanceInterval.setText("");
        mCarArchiveInsuranceDate.setText("");
        mCarArchiveInspectionDate.setText("");
        mPresenter = new CarArchivePresenter(this.getActivity());
        mPresenter.getCarArchiveInfo(this, mCarInfo.getObjId(), mCarInfo.getLpno());
        if (nowPage == SingleCarInfoActivity.VIEW_CAR_ARCHIVE) {
            mDialog = new SweetAlertDialog(this.getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            mDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            mDialog.setTitleText("正在加载中...");
            mDialog.setCancelable(false);
            mDialog.show();
        }
        return view;
    }

    private void configViews() {
        mCarArchiveCarName.setText(mArchive.getIdName());
        mCarArchiveCarBrand.setText(mArchive.getBrandName() + " " + mArchive.getProductName());
        mCarArchiveDepartment.setText(mArchive.getDeptName());
        mCarArchivePlateNum.setText(mArchive.getLpno());
        mCarArchiveCurrentMileage.setText(String.valueOf(mArchive.getCurrentMiles()));
        mCarArchiveMaintenanceInterval.setText(mArchive.getMaintainMile());
        mCarArchiveInsuranceDate.setText(mArchive.getInsuraceDueTime());
        mCarArchiveInspectionDate.setText(mArchive.getVehicleAuditTime());
    }

    public void pageChanged(int position) {
        nowPage = position;
        if (position == SingleCarInfoActivity.VIEW_CAR_ARCHIVE) {
            if (mArchive == null) {
                mPresenter.getCarArchiveInfo(this, mCarInfo.getObjId(), mCarInfo.getLpno());
                if (mDialog == null || !mDialog.isShowing()) {
                    mDialog = new SweetAlertDialog(this.getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                    mDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    mDialog.setTitleText("正在加载中...");
                    mDialog.setCancelable(false);
                    mDialog.show();
                }
            }
        }
    }

    @Override
    public void getCarArchiveInfoSuccess(Responce<CarArchive> responce) {
        Log.i("Archive", "Get Success!!!!");
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mArchive = responce.getDetail();
        configViews();
    }

}
