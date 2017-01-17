package com.datang.datangcarmanager.presentation.activity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.CollapsibleActionView;
import android.util.Log;
import android.view.View;
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
import com.amap.api.maps2d.model.PolylineOptions;
import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.DriveRecord;
import com.datang.datangcarmanager.model.RecordTrack;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.Vehicle;
import com.datang.datangcarmanager.presenter.RecordTrackPresenter;
import com.datang.datangcarmanager.utils.MyToast;
import com.datang.datangcarmanager.utils.ScreenUtils;
import com.datang.datangcarmanager.view.IRecordTrackView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarTrackActivity extends BaseActivity implements IRecordTrackView {

    public static final String TAG = "CarTrackActivity";
    public static final String RECORD_TAG = "CarTrackActivity_RECORD";

    private Vehicle mCarInfo;
    private DriveRecord.SegListBean record;
    private RecordTrackPresenter mPresenter;

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.car_track_activity_map)
    MapView mCarTrackActivityMap;

    private List<LatLng> data = new ArrayList<>();

    private int screenHeight;// 屏幕高度(px)
    private int screenWidth;// 屏幕宽度(px)

    private int currentZoom = 18;

    private LatLng centerLng;

    private AMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_track);
        ButterKnife.bind(this);
        mCarTrackActivityMap.onCreate(savedInstanceState);
        Gson gson = new Gson();
        mCarInfo = gson.fromJson(getIntent().getStringExtra(TAG), Vehicle.class);
        record = gson.fromJson(getIntent().getStringExtra(RECORD_TAG), DriveRecord.SegListBean.class);
        mTitle.setText(mCarInfo.getLpno());
        screenWidth = ScreenUtils.getScreenWidth(this);
        screenHeight = ScreenUtils.getScreenHeight(this);
        mPresenter = new RecordTrackPresenter(this);
        mPresenter.getRecordTrackInfo(this, record.getStartTime(), record.getEndTime(), mCarInfo.getObjId());
        if (mMap == null) {
            mMap = mCarTrackActivityMap.getMap();
        }
        centerLng = new LatLng((record.getStartLat() + record.getEndLat()) / 2,
                (record.getStartLng() + record.getEndLng()) / 2);
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                centerLng,
                currentZoom,
                0,
                0
        ));
        mMap.animateCamera(update, 500, null);
    }

    @OnClick({R.id.back_btn, R.id.back_title_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
            case R.id.back_title_btn:
                finish();
                break;
        }
    }

    @Override
    public void getRecordTrackInfoSuccess(Responce<RecordTrack> responce) {
        data.clear();
        CoordinateConverter converter  = new CoordinateConverter();
        // CoordType.GPS 待转换坐标类型
        converter.from(CoordinateConverter.CoordType.GPS);
        for (RecordTrack.PointListBean bean : responce.getDetail().getPointList()) {
            LatLng latLng = new LatLng(bean.getLatitude(), bean.getLongitude());
            converter.coord(latLng);
            data.add(converter.convert());
        }
        MarkerOptions markerStart = new MarkerOptions();
        markerStart.position(data.get(0));
        markerStart.anchor(.5f, 1f);
        markerStart.title("起点").snippet(record.getStartTime());
        markerStart.icon(BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeResource(getResources(),
                        R.drawable.car_loucs_start)));
        MarkerOptions markerEnd = new MarkerOptions();
        markerEnd.position(data.get(data.size() - 1));
        markerEnd.anchor(.5f, 1f);
        markerEnd.title("终点").snippet(record.getEndTime());
        markerEnd.icon(BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeResource(getResources(),
                        R.drawable.car_loucs_end)));
        mMap.addMarker(markerStart);
        mMap.addMarker(markerEnd);
        mMap.addPolyline(new PolylineOptions()
                .addAll(data).width(8).color(Color.argb(255, 48, 139, 230)));
        boolean start = false;
        boolean end = false;
        while (!start || !end) {
            Projection projection = mMap.getProjection();
            Point point = null;
            point = projection.toScreenLocation(markerStart.getPosition());
            if (point.x < 0 || point.y < 0 || point.x > screenWidth || point.y > screenHeight) {
                start = false;
            } else {
                start = true;
            }
            point = projection.toScreenLocation(markerEnd.getPosition());
            if (point.x < 0 || point.y < 0 || point.x > screenWidth || point.y > screenHeight) {
                end = false;
            } else {
                end = true;
            }
            CameraUpdate newUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                    centerLng,
                    currentZoom--,
                    0,
                    0
            ));
            mMap.moveCamera(newUpdate);
        }
    }

    @Override
    public void loadError() {
        MyToast.showToastShort("数据获取错误!");
    }
}
