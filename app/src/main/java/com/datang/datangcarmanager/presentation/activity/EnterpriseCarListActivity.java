package com.datang.datangcarmanager.presentation.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.model.LatLng;
import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.CarInfo;
import com.datang.datangcarmanager.model.Department;
import com.datang.datangcarmanager.model.DeptartmentList;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.Vehicle;
import com.datang.datangcarmanager.model.VehicleList;
import com.datang.datangcarmanager.presentation.adapter.AbstractTreeListAdapter;
import com.datang.datangcarmanager.presentation.adapter.CarInfoListAdapter;
import com.datang.datangcarmanager.presentation.view.DefultItemDecoration;
import com.datang.datangcarmanager.presentation.view.DepartmentSelectPopWindow;
import com.datang.datangcarmanager.presenter.EnterpriseCarListPresenter;
import com.datang.datangcarmanager.presenter.EnterpriseCarPresenter;
import com.datang.datangcarmanager.view.IEnterpriseCarListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class EnterpriseCarListActivity extends BaseActivity implements IEnterpriseCarListView {

    public static final String TAG = "CarListActivity";

    @BindView(R.id.back_btn)
    ImageButton mBackBtn;
    @BindView(R.id.back_title_btn)
    TextView mBackTitleBtn;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.department_select_btn)
    TextView mDepartmentSelectBtn;
    @BindView(R.id.enterprise_car_recycler_view)
    RecyclerView mEnterpriseCarRecyclerView;
    @BindView(R.id.enterprise_car_recycler_view_frame)
    PtrClassicFrameLayout mEnterpriseCarRecyclerViewFrame;

    private DepartmentSelectPopWindow mSelectPopWindow;

    private Activity context;

    SweetAlertDialog pDialog;

    private List<Department> departmentList = new ArrayList<>();
    private List<Vehicle> allDepartmentCarList = new ArrayList<>();
    private List<Vehicle> showDepartmentCarList = new ArrayList<>();
    private CarInfoListAdapter mCarInfoListAdapter;
    private RecyclerAdapterWithHF mAdapterWithHF;

    private EnterpriseCarListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_car_list);
        ButterKnife.bind(this);
        context = this;
        mPresenter = new EnterpriseCarListPresenter(this);
        mPresenter.getAllDepartmentInfo(this);

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("正在加载中...");
        pDialog.setCancelable(false);
        pDialog.show();

        Department showAll = new Department();
        showAll.setId(-1);
        showAll.setDepartmentame("显示全部");
        showAll.setpId(0);
        departmentList.add(showAll);

        mCarInfoListAdapter = new CarInfoListAdapter(this, showDepartmentCarList);
        mAdapterWithHF = new RecyclerAdapterWithHF(mCarInfoListAdapter);
        mEnterpriseCarRecyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        mEnterpriseCarRecyclerView.setAdapter(mAdapterWithHF);
        mEnterpriseCarRecyclerView.addItemDecoration(new DefultItemDecoration(this, LinearLayoutManager.VERTICAL));
        mAdapterWithHF.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                Log.i(TAG, "点击了车辆" + showDepartmentCarList.get(position).getIdName());
                Gson gson = new Gson();
                String car = gson.toJson(showDepartmentCarList.get(position));
                Intent intent = new Intent(EnterpriseCarListActivity.this, SingleCarMapActivity.class);
                intent.putExtra(SingleCarMapActivity.TAG, car);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, android.R.anim.fade_out);
            }
        });
        mEnterpriseCarRecyclerViewFrame.setLoadMoreEnable(false);
        mEnterpriseCarRecyclerViewFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnterpriseCarRecyclerViewFrame.autoRefresh(true);
            }
        }, 500);
        mEnterpriseCarRecyclerViewFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPresenter.getAllEnterpriseCar((IEnterpriseCarListView) context);
            }
        });
        mEnterpriseCarRecyclerViewFrame.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                mEnterpriseCarRecyclerViewFrame.loadMoreComplete(false);
            }
        });
        mSelectPopWindow = new DepartmentSelectPopWindow(this, departmentList);
        mSelectPopWindow.setItemListener(new AbstractTreeListAdapter.OnItemSelectListener() {
            @Override
            public void onItemClick(int id) {
                if (id == -1) {
                    mDepartmentSelectBtn.setText("显示全部");
                    showDepartmentCarList.removeAll(showDepartmentCarList);
                    showDepartmentCarList.addAll(allDepartmentCarList);
                    mAdapterWithHF.notifyDataSetChanged();

                } else {
                    for (Department department : departmentList) {
                        if (department.getId() == id) {
                            mDepartmentSelectBtn.setText(department.getDepartmentame());
                            showDepartmentCarList.removeAll(showDepartmentCarList);
                            showDepartmentCarList.addAll(getShowListById(id));
                            mAdapterWithHF.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }


    private List<Vehicle> getShowListById(int id) {
        ArrayList<Integer> all = new ArrayList();
        getAllId(id, departmentList, all);
        ArrayList<Vehicle> show = new ArrayList();
        for (Vehicle carInfo1 : allDepartmentCarList) {
            for (Integer num : all) {
                if (carInfo1.getDeptId().equals(num.toString())) {
                    show.add(carInfo1);
                }
            }
        }
        return show;
    }

    @OnClick({R.id.back_btn, R.id.back_title_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
            case R.id.back_title_btn:
                finish();
                overridePendingTransition(R.anim.custom_fade_in, R.anim.out_to_right);
                break;
        }
    }

    @OnClick(R.id.department_select_btn)
    public void onClick() {
        mSelectPopWindow.setWidth(this.getWindowManager().getDefaultDisplay().getWidth());
        mSelectPopWindow.setHeight(this.getWindowManager().getDefaultDisplay().getHeight() / 2);
        mSelectPopWindow.showAsDropDown(mDepartmentSelectBtn, 20, 20);
    }

    private void getAllId(int id, List<Department> departments, List<Integer> result) {
        result.add(Integer.valueOf(id));
        for (Department department : departments) {
            if (department.getpId() == id) {
                getAllId(department.getId(), departments, result);
            }
        }
    }

    @Override
    public void getAllCarInfoSuccess(Responce<VehicleList> responce) {
        pDialog.dismiss();
        allDepartmentCarList.clear();
        allDepartmentCarList.addAll(responce.getDetail().getVehicleList());
        CoordinateConverter converter  = new CoordinateConverter();
        converter.from(CoordinateConverter.CoordType.GPS);
        for (Vehicle vehicle : allDepartmentCarList) {
            LatLng latLng = new LatLng(vehicle.getLatitude(), vehicle.getLongitude());
            converter.coord(latLng);
            LatLng newLatLng = converter.convert();
            vehicle.setLatitude(newLatLng.latitude);
            vehicle.setLongitude(newLatLng.longitude);
        }
        showDepartmentCarList.clear();
        showDepartmentCarList.addAll(allDepartmentCarList);

        mEnterpriseCarRecyclerViewFrame.refreshComplete();
    }

    @Override
    public void getAllDepartmentInfoSuccess(Responce<DeptartmentList> responce) {
        departmentList.addAll(responce.getDetail().getDeptList());
        mSelectPopWindow.setmDepartmentList(departmentList);
    }
}
