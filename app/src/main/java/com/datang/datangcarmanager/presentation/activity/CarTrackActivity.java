package com.datang.datangcarmanager.presentation.activity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.CollapsibleActionView;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.PolylineOptions;
import com.datang.datangcarmanager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarTrackActivity extends BaseActivity {

    public static final String TAG = "CarTrackActivity";

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.car_track_activity_map)
    MapView mCarTrackActivityMap;

    private List<LatLng> data = new ArrayList<>();

    private AMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_track);
        ButterKnife.bind(this);
        mCarTrackActivityMap.onCreate(savedInstanceState);
        if (mMap == null) {
            mMap = mCarTrackActivityMap.getMap();
        }
        LatLng lng = new LatLng(34.341568, 108.940174);
        data.add(lng);
        for (int i = 0; i < 10; i++) {
            LatLng lng1 = null;
            if (i % 2 == 1) {
                lng1 = new LatLng(data.get(i).latitude + 0.00015, data.get(i).longitude);
            } else {
                lng1 = new LatLng(data.get(i).latitude, data.get(i).longitude + 0.00015);
            }
            data.add(lng1);
        }
        MarkerOptions markerStart = new MarkerOptions();
        markerStart.position(data.get(0));
        markerStart.anchor(0.5f, 0.5f);
        markerStart.title("起点").snippet("2016-11-16 12:15:17");
        markerStart.icon(BitmapDescriptorFactory.fromBitmap(
                        BitmapFactory.decodeResource(getResources(),
                        R.drawable.car_loucs_start)));
        MarkerOptions markerEnd = new MarkerOptions();
        markerEnd.position(data.get(data.size() - 1));
        markerEnd.anchor(0.5f, 0.5f);
        markerEnd.title("终点").snippet("2016-11-16 13:15:17");
        markerEnd.icon(BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeResource(getResources(),
                        R.drawable.car_loucs_end)));
        mMap.addMarker(markerStart);
        mMap.addMarker(markerEnd);
        mMap.addPolyline(new PolylineOptions()
            .addAll(data).width(8).color(Color.argb(255, 48, 139, 230)));

        CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                new LatLng(34.341568, 108.940174),
                20,
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
}
