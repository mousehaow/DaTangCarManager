package com.datang.datangcarmanager.presentation.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.Projection;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.Vehicle;
import com.datang.datangcarmanager.model.VehicleList;
import com.datang.datangcarmanager.presentation.view.MarkerCluster;
import com.datang.datangcarmanager.presenter.EnterpriseCarPresenter;
import com.datang.datangcarmanager.utils.ScreenUtils;
import com.datang.datangcarmanager.view.IEnterpriseCarView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class EnterpriseCarActivity extends BaseActivity implements IEnterpriseCarView, AMap.InfoWindowAdapter,
        AMap.OnMapClickListener, AMap.OnMarkerClickListener, View.OnClickListener, AMap.OnCameraChangeListener {

    public static final String TAG = "EnterpriseCarActivity";

    @BindView(R.id.back_btn)
    ImageButton mBackBtn;
    @BindView(R.id.back_title_btn)
    TextView mBackTitleBtn;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.show_in_list_btn)
    TextView mShowInListBtn;
    @BindView(R.id.online_move_checkbox)
    CheckBox mOnlineMoveCheckbox;
    @BindView(R.id.online_stop_checkbox)
    CheckBox mOnlineStopCheckbox;
    @BindView(R.id.offline_checkbox)
    CheckBox mOfflineCheckbox;
    @BindView(R.id.enterprise_map)
    MapView mEnterpriseMap;

    private EnterpriseCarPresenter mPresenter;

    private AMap mMap;

    private Marker currentMarker;

    private List<Vehicle> carList;

    private float currentZoom = 14f;

    private boolean isLoadMarkers = false;

    private boolean showOnlineMove = true;

    private boolean showOnlineStop = true;

    private boolean showOffline = true;

    SweetAlertDialog pDialog;

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

    private int screenHeight;// 屏幕高度(px)
    private int screenWidth;// 屏幕宽度(px)

    ImageView mInfoWindowCarIcon;
    TextView mInfoWindowCarPlateNum;
    TextView mInfoWindowCarDriver;
    TextView mInfoWindowCarState;
    ImageButton mInfoWindowParkingRecordImgbtn;
    TextView mInfoWindowParkingRecordBtn;
    ImageButton mInfoWindowCarTrackImgbtn;
    TextView mInfoWindowCarTrackBtn;
    ImageButton mInfoWindowDriveRecordImgbtn;
    TextView mInfoWindowDriveRecordBtn;
    ImageButton mInfoWindowCarArchiveImgbtn;
    TextView mInfoWindowCarArchiveBtn;
    ImageButton mInfoWindowCarStateImgbtn;
    TextView mInfoWindowCarStateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_car);
        ButterKnife.bind(this);
        mBackBtn.setOnClickListener(this);
        mBackTitleBtn.setOnClickListener(this);
        mShowInListBtn.setOnClickListener(this);
        screenWidth = ScreenUtils.getScreenWidth(this);
        screenHeight = ScreenUtils.getScreenHeight(this);
        mEnterpriseMap.onCreate(savedInstanceState);
        if (mMap == null) {
            mMap = mEnterpriseMap.getMap();
            mMap.setInfoWindowAdapter(this);
            mMap.setOnMarkerClickListener(this);
            mMap.setOnMapClickListener(this);
            mMap.setOnCameraChangeListener(this);
        }
        mOnlineMoveCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showOnlineMove = isChecked;
                selectShouldShowMarkers();
                Log.i(TAG, "check changed");
            }
        });
        mOnlineStopCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showOnlineStop = isChecked;
                selectShouldShowMarkers();
            }
        });
        mOfflineCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showOffline = isChecked;
                selectShouldShowMarkers();
            }
        });
        mPresenter = new EnterpriseCarPresenter(this);
        mPresenter.getAllEnterpriseCar(this);
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("正在加载中...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEnterpriseMap.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mEnterpriseMap.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mEnterpriseMap.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mEnterpriseMap.onSaveInstanceState(outState);
    }


    @Override
    public void getAllCarInfoSuccess(Responce<VehicleList> responce) {
        carList = responce.getDetail().getVehicleList();
        CoordinateConverter converter  = new CoordinateConverter();
        converter.from(CoordinateConverter.CoordType.GPS);
        for (Vehicle vehicle : carList) {
            LatLng latLng = new LatLng(vehicle.getLatitude(), vehicle.getLongitude());
            converter.coord(latLng);
            LatLng newLatLng = converter.convert();
            vehicle.setLatitude(newLatLng.latitude);
            vehicle.setLongitude(newLatLng.longitude);
        }
        pDialog.dismissWithAnimation();
        initData();
    }

    private void initData() {
        isLoadMarkers = true;
        for(Vehicle car : carList) {
            LatLng lng = new LatLng(car.getLatitude(), car.getLongitude());
            MarkerOptions options = new MarkerOptions();
            options.position(lng)
                    .anchor(.5f, .5f)
                    .title(car.getObjId())
                    .snippet(String.valueOf(car.getOnlineStatus()));
            markerOptionsListall.add(options);
        }
        selectShouldShowMarkers();
    }

    private void selectShouldShowMarkers() {
        markerOptionsListShouldShow.clear();
        for (MarkerOptions option : markerOptionsListall) {
            switch (Integer.parseInt(option.getSnippet())) {
                case Vehicle.CAR_STATE_MOVING:
                    if (showOnlineMove)
                        markerOptionsListShouldShow.add(option);
                    break;
                case Vehicle.CAR_STATE_STOP:
                    if (showOnlineStop)
                        markerOptionsListShouldShow.add(option);
                    break;
                case Vehicle.CAR_STATE_OFFLINE:
                    if (showOffline)
                        markerOptionsListShouldShow.add(option);
                    break;
                default:
                    break;
            }
        }
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
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                    new LatLng(centerLatitude, centerLongitude),
                    currentZoom,
                    0,
                    0
            ));
            mMap.moveCamera(update);

            while (markerOptionsListShouldShow.size() != markerOptionsListInView.size()) {
                Projection projection = mMap.getProjection();
                Point point = null;
                markerOptionsListInView.clear();
                for (MarkerOptions options : markerOptionsListShouldShow) {
                    point = projection.toScreenLocation(options.getPosition());
                    if (point.x < 0 || point.y < 0 || point.x > screenWidth || point.y > screenHeight) {

                    } else {
                        markerOptionsListInView.add(options);
                    }
                }
                if (markerOptionsListall.size() != markerOptionsListInView.size()) {
                    CameraUpdate newUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                            new LatLng(centerLatitude, centerLongitude),
                            currentZoom--,
                            0,
                            0
                    ));
                    mMap.moveCamera(newUpdate);
                }
            }
            showMarks();
        } else {
            mMap.clear();
        }
        isLoadMarkers = false;
    }

    private void showMarks() {

        /* 开始刷新 */
        Projection projection = mMap.getProjection();
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

        // 自定义的聚合类MarkerCluster
        ArrayList<MarkerCluster> clustersMarker = new ArrayList<MarkerCluster>();
        for (MarkerOptions options : markerOptionsListInView) {
            if (clustersMarker.size() == 0) {
                // 添加一个新的自定义marker
                clustersMarker.add(new MarkerCluster(
                        EnterpriseCarActivity.this, options, projection, 100));// 100=相距多少才聚合
            } else {
                boolean isIn = false;
                for (MarkerCluster cluster : clustersMarker) {
                    // 判断当前的marker是否在前面marker的聚合范围内 并且每个marker只会聚合一次。
                    if (cluster.getBounds().contains(options.getPosition())) {
                        cluster.addMarker(options);
                        isIn = true;
                        break;
                    }
                }
                // 如果没在任何范围内，自己单独形成一个自定义marker。在和后面的marker进行比较
                if (!isIn) {
                    clustersMarker.add(new MarkerCluster(
                            EnterpriseCarActivity.this, options, projection, 100));// 80=相距多少才聚合
                }
            }
        }

        // 设置聚合点的位置和icon
        for (MarkerCluster clusterq : clustersMarker) {
            clusterq.setPositionAndIcon();
        }
        mMap.clear();
        // 重新添加 marker
        for (MarkerCluster cluster : clustersMarker) {
            Marker marker = mMap.addMarker(cluster.getOptions());
            if (!marker.getTitle().equals("MarkerCluster")) {
                for(Vehicle car : carList) {
                    if (marker.getTitle().equals(car.getObjId())) {
                        marker.setRotateAngle(car.getDirection());
                        marker.setObject(car);
                        if (car.getOnlineStatus() == 1) {
                            marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                    .decodeResource(getResources(),
                                            R.drawable.car_status_static)));
                        } else if (car.getOnlineStatus() == 2) {
                            marker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                    .decodeResource(getResources(),
                                            R.drawable.car_status_move)));
                        }
                    }
                }
            }
        }
    }


    @Override
    public View getInfoWindow(Marker marker) {
        if (!marker.getTitle().equals("MarkerCluster")) {
            View infoWindow = getLayoutInflater().inflate(R.layout.info_window, null);
            renderWindow(infoWindow, marker);
            return infoWindow;
        }
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        return null;
    }

    @Override
    public void onMapClick(LatLng lng) {
        if (currentMarker != null) {
            currentMarker.hideInfoWindow();
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (!marker.getTitle().equals("MarkerCluster")) {
            currentMarker = marker;
            marker.showInfoWindow();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                    marker.getPosition(),
                    mMap.getCameraPosition().zoom,
                    0,
                    0
            ));
            mMap.animateCamera(update, 300, null);
        } else {
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                    marker.getPosition(),
                    mMap.getCameraPosition().zoom + 1,
                    0,
                    0
            ));
            mMap.animateCamera(update);
        }

        return true;
    }

    /**
     * 渲染以及为infoWindow绑定点击事件
     *
     * @param window
     * @param marker
     */
    private void renderWindow(View window, Marker marker) {
        Vehicle car = (Vehicle) marker.getObject();
        mInfoWindowCarIcon = (ImageView) window.findViewById(R.id.info_window_car_icon);
        mInfoWindowCarPlateNum = (TextView) window.findViewById(R.id.info_window_car_plate_num);
        mInfoWindowCarDriver = (TextView) window.findViewById(R.id.info_window_car_driver);
        mInfoWindowCarState = (TextView) window.findViewById(R.id.info_window_car_state);
        mInfoWindowParkingRecordImgbtn = (ImageButton) window.findViewById(R.id.info_window_parking_record_imgbtn);
        mInfoWindowParkingRecordBtn = (TextView) window.findViewById(R.id.info_window_parking_record_btn);
        mInfoWindowCarTrackImgbtn = (ImageButton) window.findViewById(R.id.info_window_car_track_imgbtn);
        mInfoWindowCarTrackBtn = (TextView) window.findViewById(R.id.info_window_car_track_btn);
        mInfoWindowDriveRecordImgbtn = (ImageButton) window.findViewById(R.id.info_window_drive_record_imgbtn);
        mInfoWindowDriveRecordBtn = (TextView) window.findViewById(R.id.info_window_drive_record_btn);
        mInfoWindowCarArchiveImgbtn = (ImageButton) window.findViewById(R.id.info_window_car_archive_imgbtn);
        mInfoWindowCarArchiveBtn = (TextView) window.findViewById(R.id.info_window_car_archive_btn);
        mInfoWindowCarStateImgbtn = (ImageButton) window.findViewById(R.id.info_window_car_state_imgbtn);
        mInfoWindowCarStateBtn = (TextView) window.findViewById(R.id.info_window_car_state_btn);
        /* 绑定点击事件 */
        mInfoWindowParkingRecordImgbtn.setOnClickListener(this);
        mInfoWindowParkingRecordBtn.setOnClickListener(this);

        mInfoWindowCarTrackImgbtn.setOnClickListener(this);
        mInfoWindowCarTrackBtn.setOnClickListener(this);

        mInfoWindowDriveRecordImgbtn.setOnClickListener(this);
        mInfoWindowDriveRecordBtn.setOnClickListener(this);

        mInfoWindowCarArchiveImgbtn.setOnClickListener(this);
        mInfoWindowCarArchiveBtn.setOnClickListener(this);

        mInfoWindowCarStateImgbtn.setOnClickListener(this);
        mInfoWindowCarStateBtn.setOnClickListener(this);

        /* 设置车辆品牌 */
        //mInfoWindowCarIcon.setText(carInfo.getPlateNum());
        /* 设置车牌号码 */
        mInfoWindowCarPlateNum.setText(car.getLpno());
        /* 设置车辆司机 */
        mInfoWindowCarDriver.setText(car.getDriverName());
        /* 设置车辆状态 */
        switch (car.getOnlineStatus()) {
            case Vehicle.CAR_STATE_MOVING:
                mInfoWindowCarState.setText("运动");
                mInfoWindowCarState.setTextColor(getResources().getColor(R.color.green));
                break;
            case Vehicle.CAR_STATE_STOP:
                mInfoWindowCarState.setText("静止");
                mInfoWindowCarState.setTextColor(getResources().getColor(R.color.light_blue));
                break;
            case Vehicle.CAR_STATE_OFFLINE:
                mInfoWindowCarState.setText("离线");
                mInfoWindowCarState.setTextColor(getResources().getColor(R.color.grey_dark));
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        int clickNum;
        Gson gson = null;
        String car = null;
        if (currentMarker != null) {
            gson = new Gson();
            car = gson.toJson(currentMarker.getObject());
        }
        Log.i(TAG, "click");
        Intent intent = new Intent(EnterpriseCarActivity.this, SingleCarInfoActivity.class);
        switch (view.getId()) {
            case R.id.back_btn:
            case R.id.back_title_btn:
                finish();
                overridePendingTransition(R.anim.custom_fade_in, R.anim.out_to_right);
                break;
            case R.id.show_in_list_btn:
                Intent listIntent = new Intent(EnterpriseCarActivity.this, EnterpriseCarListActivity.class);
                startActivity(listIntent);
                overridePendingTransition(R.anim.in_from_right, android.R.anim.fade_out);
                break;
            case R.id.info_window_parking_record_imgbtn:
            case R.id.info_window_parking_record_btn:
                clickNum = 2;
                Log.i(TAG, "parking");
                intent.putExtra(SingleCarInfoActivity.TAG, clickNum);
                intent.putExtra(SingleCarInfoActivity.CAR_TAG, car);
                startActivity(intent);
                break;
            case R.id.info_window_car_track_imgbtn:
            case R.id.info_window_car_track_btn:
                clickNum = 3;
                Log.i(TAG, "track");
                intent.putExtra(SingleCarInfoActivity.TAG, clickNum);
                intent.putExtra(SingleCarInfoActivity.CAR_TAG, car);
                startActivity(intent);
                break;
            case R.id.info_window_drive_record_imgbtn:
            case R.id.info_window_drive_record_btn:
                clickNum = 1;
                Log.i(TAG, "drive");
                intent.putExtra(SingleCarInfoActivity.TAG, clickNum);
                intent.putExtra(SingleCarInfoActivity.CAR_TAG, car);
                startActivity(intent);
                break;
            case R.id.info_window_car_archive_imgbtn:
            case R.id.info_window_car_archive_btn:
                clickNum = 4;
                Log.i(TAG, "archive");
                intent.putExtra(SingleCarInfoActivity.TAG, clickNum);
                intent.putExtra(SingleCarInfoActivity.CAR_TAG, car);
                startActivity(intent);
                break;
            case R.id.info_window_car_state_imgbtn:
            case R.id.info_window_car_state_btn:
                clickNum = 0;
                Log.i(TAG, "state");
                intent.putExtra(SingleCarInfoActivity.TAG, clickNum);
                intent.putExtra(SingleCarInfoActivity.CAR_TAG, car);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onCameraChange(CameraPosition position) {


    }

    @Override
    public void onCameraChangeFinish(CameraPosition position) {
        Log.i(TAG, "finsh change");
        if (currentZoom != position.zoom ) {
            currentZoom = position.zoom;
            showMarks();
        }
    }
}
