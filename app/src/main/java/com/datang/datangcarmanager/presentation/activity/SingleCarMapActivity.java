package com.datang.datangcarmanager.presentation.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.CarInfo;
import com.datang.datangcarmanager.model.Vehicle;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleCarMapActivity extends BaseActivity implements AMap.InfoWindowAdapter,
        AMap.OnMapClickListener, AMap.OnMarkerClickListener, View.OnClickListener {

    public static final String TAG = "SingleCarMapActivity";

    @BindView(R.id.back_btn)
    ImageButton mBackBtn;
    @BindView(R.id.back_title_btn)
    TextView mBackTitleBtn;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.single_car_map)
    MapView mSingleCarMap;


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

    public Vehicle mVehicle;

    private AMap mMap;

    private Marker currentMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_car_map);
        ButterKnife.bind(this);
        Gson gson = new Gson();
        mVehicle = gson.fromJson(getIntent().getStringExtra(TAG), Vehicle.class);
        mBackTitleBtn.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
        mTitle.setText(mVehicle.getLpno());
        mBackTitleBtn.setText("返回");

        mSingleCarMap.onCreate(savedInstanceState);
        if (mMap == null) {
            mMap = mSingleCarMap.getMap();
        }
        setUpMarker();
    }

    private void setUpMarker() {
        LatLng lng = new LatLng(mVehicle.getLatitude(), mVehicle.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(lng);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.title("Car");
        switch (mVehicle.getOnlineStatus()) {
            case Vehicle.CAR_STATE_MOVING:
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.drawable.car)));
                break;
            case Vehicle.CAR_STATE_STOP:
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.drawable.car_status_static)));
                break;
            case Vehicle.CAR_STATE_OFFLINE:
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.drawable.car_status_off_line)));
                break;
            default:
                break;
        }
        Marker marker = mMap.addMarker(markerOptions);
        marker.setObject(mVehicle);
        marker.setRotateAngle(mVehicle.getDirection());
        mMap.setInfoWindowAdapter(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this);
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                lng,
                18,
                0,
                0
        ));
        mMap.animateCamera(update, 1500, null);
        marker.showInfoWindow();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSingleCarMap.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSingleCarMap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSingleCarMap.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mSingleCarMap.onSaveInstanceState(outState);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View infoWindow = getLayoutInflater().inflate(R.layout.info_window, null);
        renderWindow(infoWindow, marker);
        return infoWindow;
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
        currentMarker = marker;
        marker.showInfoWindow();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                marker.getPosition(),
                mMap.getCameraPosition().zoom,
                0,
                0
        ));
        mMap.animateCamera(update, 200, null);
        return true;
    }

    @Override
    public void onClick(View view) {
        int clickNum;
        Gson gson = new Gson();
        String car = gson.toJson(mVehicle);
        Intent intent = new Intent(SingleCarMapActivity.this, SingleCarInfoActivity.class);
        intent.putExtra(SingleCarInfoActivity.CAR_TAG, car);
        switch (view.getId()) {
            case R.id.back_btn:
            case R.id.back_title_btn:
                finish();
                overridePendingTransition(R.anim.custom_fade_in, R.anim.out_to_right);
                break;
            case R.id.info_window_parking_record_imgbtn:
            case R.id.info_window_parking_record_btn:
                clickNum = 2;
                Log.i(TAG, "parking");
                intent.putExtra(SingleCarInfoActivity.TAG, clickNum);
                startActivity(intent);
                break;
            case R.id.info_window_car_track_imgbtn:
            case R.id.info_window_car_track_btn:
                clickNum = 3;
                Log.i(TAG, "track");
                intent.putExtra(SingleCarInfoActivity.TAG, clickNum);
                startActivity(intent);
                break;
            case R.id.info_window_drive_record_imgbtn:
            case R.id.info_window_drive_record_btn:
                clickNum = 1;
                Log.i(TAG, "drive");
                intent.putExtra(SingleCarInfoActivity.TAG, clickNum);
                startActivity(intent);
                break;
            case R.id.info_window_car_archive_imgbtn:
            case R.id.info_window_car_archive_btn:
                clickNum = 4;
                Log.i(TAG, "archive");
                intent.putExtra(SingleCarInfoActivity.TAG, clickNum);
                startActivity(intent);
                break;
            case R.id.info_window_car_state_imgbtn:
            case R.id.info_window_car_state_btn:
                clickNum = 0;
                Log.i(TAG, "state");
                intent.putExtra(SingleCarInfoActivity.TAG, clickNum);
                startActivity(intent);
                break;
        }
    }
}
