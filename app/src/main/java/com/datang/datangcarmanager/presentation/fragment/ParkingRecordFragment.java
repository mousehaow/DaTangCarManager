package com.datang.datangcarmanager.presentation.fragment;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapException;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.Projection;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.CarInfo;
import com.datang.datangcarmanager.model.ParkingRecordList;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.Vehicle;
import com.datang.datangcarmanager.presentation.activity.EnterpriseCarActivity;
import com.datang.datangcarmanager.presentation.activity.SingleCarInfoActivity;
import com.datang.datangcarmanager.presentation.view.MarkerCluster;
import com.datang.datangcarmanager.presenter.ParkingRecordPresenter;
import com.datang.datangcarmanager.utils.MyToast;
import com.datang.datangcarmanager.utils.ScreenUtils;
import com.datang.datangcarmanager.view.IParkingRecordView;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ParkingRecordFragment extends Fragment implements IParkingRecordView, AMap.OnCameraChangeListener {

    public static final String DIALOG_TAG = "CALDROID_DIALOG_ParkingRecordFragment";

    Vehicle mCarInfo;
    Context mContext;

    @BindView(R.id.parking_record_map)
    MapView mParkingRecordMap;
    @BindView(R.id.left_day_pick_btn)
    TextView mLeftDayPickBtn;
    @BindView(R.id.date_show_tv)
    TextView mDateShowTv;
    @BindView(R.id.right_day_pick_btn)
    TextView mRightDayPickBtn;

    private AMap aMap;
    private View mapLayout;

    private int nowPage = 0;

    private Date selectedDate;

    private CaldroidFragment caldroidFragment;
    private Calendar calendar;
    public static Bundle state = null;

    public static SimpleDateFormat formatter;

    private ParkingRecordPresenter mPresenter;

    private List<ParkingRecordList.ParkingRecordBean> records = new ArrayList<>();

    private float currentZoom = 18f;

    private boolean isLoadMarkers = false;

    private int screenHeight;// 屏幕高度(px)
    private int screenWidth;// 屏幕宽度(px)

    private SweetAlertDialog mDialog;

    /**
     * 所有的marker
     */
    private ArrayList<MarkerOptions> markerOptionsListall = new ArrayList<MarkerOptions>();
    /**
     * 应该显示的marker
     */
    private ArrayList<MarkerOptions> markerOptionsListShouldShow = new ArrayList<MarkerOptions>();
    /**
     * 视野内的marker
     */
    private ArrayList<MarkerOptions> markerOptionsListInView = new ArrayList<MarkerOptions>();

    public ParkingRecordFragment(Context context, Vehicle carInfo) {
        mCarInfo = carInfo;
        mContext = context;
    }

    public static ParkingRecordFragment newInstance(Context context, Vehicle carInfo) {
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParkingRecordFragment fragment = new ParkingRecordFragment(context, carInfo);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mapLayout = inflater.inflate(R.layout.fragment_parking_record, container, false);
        ButterKnife.bind(this, mapLayout);
        mParkingRecordMap.onCreate(savedInstanceState);
        state = savedInstanceState;
        screenWidth = ScreenUtils.getScreenWidth(this.getActivity());
        screenHeight = ScreenUtils.getScreenHeight(this.getActivity());
        if (aMap == null) {
            aMap = mParkingRecordMap.getMap();
        }
        mPresenter = new ParkingRecordPresenter(this.getContext());
        calendar = new GregorianCalendar();
        selectedDate = new Date();
        if (caldroidFragment == null) {
            caldroidFragment = new CaldroidFragment();
            caldroidFragment.setCaldroidListener(getListener());
            if (state == null) {
                state = new Bundle();
            }
            caldroidFragment.setSelectedDate(selectedDate);
            caldroidFragment.setMaxDate(selectedDate);
        }
        mDateShowTv.setText(formatter.format(selectedDate));
        matchPickBtn();
        return mapLayout;
    }

    public void pageChanged(int position) {
        nowPage = position;
        if (position == SingleCarInfoActivity.VIEW_CAR_ARCHIVE) {
            if (records.size() == 0) {
                doRequest();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        caldroidFragment.onResume();
        mParkingRecordMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        caldroidFragment.onPause();
        mParkingRecordMap.onPause();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        caldroidFragment.onSaveInstanceState(outState);
        mParkingRecordMap.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mParkingRecordMap.onDestroy();
    }

    @OnClick(R.id.date_picker_show_btn)
    public void onClick() {
        if (state != null) {
            caldroidFragment.restoreDialogStatesFromKey(
                    ((FragmentActivity) mContext).getSupportFragmentManager(), state,
                    "DIALOG_CALDROID_SAVED_STATE", DIALOG_TAG);
            Bundle args = caldroidFragment.getArguments();
            if (args == null) {
                args = new Bundle();
                caldroidFragment.setArguments(args);
            }
        } else {
            // Setup arguments
            Bundle bundle = new Bundle();
            // Setup dialogTitle
            caldroidFragment.setArguments(bundle);
        }
        caldroidFragment.show(((FragmentActivity) mContext).getSupportFragmentManager(),
                    DIALOG_TAG);

    }

    protected CaldroidListener getListener() {
        return new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                caldroidFragment.dismiss();
                selectedDate = date;
                caldroidFragment.clearSelectedDates();
                caldroidFragment.setSelectedDate(selectedDate);
                state.putInt(caldroidFragment.MONTH, selectedDate.getMonth());
                state.putInt(caldroidFragment.YEAR, selectedDate.getYear());
                mDateShowTv.setText(formatter.format(selectedDate));
                matchPickBtn();
            }

            @Override
            public void onChangeMonth(int month, int year) {
                super.onChangeMonth(month, year);
            }
        };
    }

    private void matchPickBtn() {
        aMap.clear();
        doRequest();
        calendar.setTime(selectedDate);
        calendar.add(calendar.DATE, -1);
        mLeftDayPickBtn.setText(calendar.getTime().getDate() + "日");
        calendar.add(calendar.DATE, 2);
        mRightDayPickBtn.setText(calendar.getTime().getDate() + "日");
        if (formatter.format(selectedDate).equals(formatter.format(new Date()))){
            mLeftDayPickBtn.setTextColor(mContext.getResources().getColor(R.color.black));
            mLeftDayPickBtn.setClickable(true);
            mRightDayPickBtn.setTextColor(mContext.getResources().getColor(R.color.grey_light));
            mRightDayPickBtn.setClickable(false);
            return;
        }
        if (selectedDate.after(new Date())){
            mLeftDayPickBtn.setTextColor(mContext.getResources().getColor(R.color.grey_light));
            mLeftDayPickBtn.setClickable(false);
            mRightDayPickBtn.setTextColor(mContext.getResources().getColor(R.color.grey_light));
            mRightDayPickBtn.setClickable(false);
        } else {
            mLeftDayPickBtn.setTextColor(mContext.getResources().getColor(R.color.black));
            mLeftDayPickBtn.setClickable(true);
            mRightDayPickBtn.setTextColor(mContext.getResources().getColor(R.color.black));
            mRightDayPickBtn.setClickable(true);
        }
    }

    private void doRequest() {
        mPresenter.getParkingRecordsInfo(this,
                mCarInfo.getObjId(),
                formatter.format(selectedDate),
                mCarInfo.getLpno());
        if (nowPage == SingleCarInfoActivity.VIEW_PARKING_RECORD) {
            if (mDialog == null || !mDialog.isShowing()) {
                mDialog = new SweetAlertDialog(this.getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                mDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                mDialog.setTitleText("正在加载中...");
                mDialog.setCancelable(false);
                mDialog.show();
            }
        }
    }

    @OnClick({R.id.left_day_pick_btn, R.id.right_day_pick_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_day_pick_btn:
                calendar.setTime(selectedDate);
                calendar.add(calendar.DATE, -1);
                selectedDate = calendar.getTime();
                caldroidFragment.clearSelectedDates();
                caldroidFragment.setSelectedDate(selectedDate);
                state.putInt(caldroidFragment.MONTH, selectedDate.getMonth());
                state.putInt(caldroidFragment.YEAR, selectedDate.getYear());
                mDateShowTv.setText(formatter.format(selectedDate));
                matchPickBtn();
                break;
            case R.id.right_day_pick_btn:
                calendar.setTime(selectedDate);
                calendar.add(calendar.DATE, 1);
                selectedDate = calendar.getTime();
                caldroidFragment.clearSelectedDates();
                caldroidFragment.setSelectedDate(selectedDate);
                state.putInt(caldroidFragment.MONTH, selectedDate.getMonth());
                state.putInt(caldroidFragment.YEAR, selectedDate.getYear());
                mDateShowTv.setText(formatter.format(selectedDate));
                matchPickBtn();
                break;
        }
    }

    @Override
    public void getParkingRecordsInfoSuccess(Responce<ParkingRecordList> responce) {
        records = responce.getDetail().getDataList();
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        if (records.size() != 0) {
            initData();
        } else {
            aMap.clear();
            MyToast.showToastShort("此时间段内没有停车记录。");
        }
    }

    private void initData() {
        isLoadMarkers = true;
        for(ParkingRecordList.ParkingRecordBean record : records) {
            LatLng lng = new LatLng(record.getLatitude(), record.getLongitude());
            MarkerOptions options = new MarkerOptions();
            options.position(lng)
                    .anchor(.5f, .5f)
                    .title(record.getEndLocation())
                    .snippet(record.getBeginTime() + " ~ " + record.getEndTime())
                    .icon(BitmapDescriptorFactory
                    .fromBitmap(BitmapFactory
                            .decodeResource(getResources(),
                                    R.drawable.car_status_static)));
            markerOptionsListall.add(options);
        }
        selectShouldShowMarkers();
    }

    private void selectShouldShowMarkers() {
        markerOptionsListShouldShow.clear();
        markerOptionsListShouldShow.addAll(markerOptionsListall);
        if (markerOptionsListShouldShow.size() > 0) {
             /* 确定中心点 */
            double minLatitude = markerOptionsListShouldShow.get(0).getPosition().latitude;
            double maxLatitude = markerOptionsListShouldShow.get(0).getPosition().latitude;
            double minLongitude = markerOptionsListShouldShow.get(0).getPosition().longitude;
            double maxLongitude = markerOptionsListShouldShow.get(0).getPosition().longitude;
            for (MarkerOptions option : markerOptionsListShouldShow) {
                if (minLatitude > option.getPosition().latitude) {
                    minLatitude = option.getPosition().latitude;
                }
                if (maxLatitude < option.getPosition().latitude) {
                    maxLatitude = option.getPosition().latitude;
                }
                if (minLongitude > option.getPosition().longitude) {
                    minLongitude = option.getPosition().longitude;
                }
                if (maxLongitude < option.getPosition().longitude) {
                    maxLongitude = option.getPosition().longitude;
                }
            }
            double centerLatitude = (maxLatitude + minLatitude) / 2;
            double centerLongitude = (maxLongitude + minLongitude) / 2;
//            currentZoom = 19f;
//            CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(
//                    new LatLng(centerLatitude, centerLongitude),
//                    currentZoom,
//                    0,
//                    0
//            ));
            LatLngBounds bounds = null;
            try {
                bounds = new LatLngBounds(new LatLng(minLatitude, minLongitude),
                        new LatLng(maxLatitude, maxLongitude));
            } catch (AMapException e) {
                e.printStackTrace();
            }
            if (bounds != null) {
                CameraUpdate uuu = CameraUpdateFactory.newLatLngBounds(bounds,
                        ScreenUtils.getScreenWidth(this.getActivity()),
                        ScreenUtils.getScreenHeight(this.getActivity()), 24);
                aMap.moveCamera(uuu);
            }
            markerOptionsListInView.addAll(markerOptionsListShouldShow);
//            while (markerOptionsListShouldShow.size() != markerOptionsListInView.size()) {
//                Projection projection = aMap.getProjection();
//                Point point = null;
//                markerOptionsListInView.clear();
//                for (MarkerOptions options : markerOptionsListShouldShow) {
//                    point = projection.toScreenLocation(options.getPosition());
//                    if (point.x < 0 || point.y < 0 || point.x > screenWidth || point.y > screenHeight) {
//
//                    } else {
//                        markerOptionsListInView.add(options);
//                    }
//                }
//                if (markerOptionsListall.size() != markerOptionsListInView.size()) {
//                    CameraUpdate newUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(
//                            new LatLng(centerLatitude, centerLongitude),
//                            currentZoom--,
//                            0,
//                            0
//                    ));
//                    aMap.moveCamera(newUpdate);
//                }
//            }
            showMarks();
        } else {
            aMap.clear();
        }
        isLoadMarkers = false;
    }

    private void showMarks() {
        /* 开始刷新 */
        Projection projection = aMap.getProjection();
        Point point = null;
        markerOptionsListInView.clear();
        /* 获取在当前视野内的marker */
        for (MarkerOptions options : markerOptionsListShouldShow) {
            point = projection.toScreenLocation(options.getPosition());
            if (point.x < 0 || point.y < 0 || point.x > screenWidth || point.y > screenHeight) {

            } else {
                markerOptionsListInView.add(options);
            }
        }
        aMap.clear();
        // 重新添加 marker
        for (MarkerOptions options : markerOptionsListInView) {
            Marker marker = aMap.addMarker(options);
        }
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        if (currentZoom != cameraPosition.zoom ) {
            currentZoom = cameraPosition.zoom;
            if (!isLoadMarkers) {
                showMarks();
            }
        }
    }
}
