package com.datang.datangcarmanager.presentation.fragment;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.CarInfo;
import com.datang.datangcarmanager.model.CarTrack;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.Vehicle;
import com.datang.datangcarmanager.presentation.activity.SingleCarInfoActivity;
import com.datang.datangcarmanager.presenter.CarStatePresenter;
import com.datang.datangcarmanager.presenter.CarTrackPresenter;
import com.datang.datangcarmanager.view.ICarTrackView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CarTrackFragment extends Fragment implements ICarTrackView {

    Vehicle mCarInfo;
    Context mContext;

    @BindView(R.id.car_track_plate_num_tv)
    TextView mCarTrackPlateNumTv;
    @BindView(R.id.car_track_car_name_tv)
    TextView mCarTrackCarNameTv;
    @BindView(R.id.car_track_current_state_tv)
    TextView mCarTrackCurrentStateTv;
    @BindView(R.id.car_track_current_speed_tv)
    TextView mCarTrackCurrentSpeedTv;
    @BindView(R.id.car_track_report_time_tv)
    TextView mCarTrackReportTimeTv;
    @BindView(R.id.car_track_map)
    MapView mCarTrackMap;

    private AMap aMap;
    private View mapLayout;
    private int nowPage;
    private CarTrackPresenter mPresenter;

    private CarTrack.TrackBean trackBean;

    private Thread requestThread;

    public CarTrackFragment(Context context, Vehicle carInfo) {
        mContext = context;
        mCarInfo = carInfo;
    }

    public static CarTrackFragment newInstance(Context context, Vehicle carInfo) {
        return new CarTrackFragment(context, carInfo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mapLayout = inflater.inflate(R.layout.fragment_car_track, container, false);
        ButterKnife.bind(this, mapLayout);
        mPresenter = new CarTrackPresenter(this.getActivity());
        mCarTrackMap.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mCarTrackMap.getMap();
        }
        mPresenter.getCarTrackInfo(this, mCarInfo.getObjId(), "1");
        return mapLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCarTrackMap.onResume();
    }

    @Override
    public void onPause() {
        if (requestThread != null) {
            requestThread.interrupt();
            requestThread = null;
        }
        mCarTrackMap.onPause();
        super.onPause();
    }

    public void pageChanged(int position) {
        if (position == SingleCarInfoActivity.VIEW_CAR_TRACK) {
            Log.i("StateFragment", "Resume");
            requestThread = new Thread() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            doRequest();
                            Thread.sleep(5000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            requestThread.start();
        } else {
            if (requestThread != null) {
                Log.i("StateFragment", "Pause");
                requestThread.interrupt();
                requestThread = null;
            }
        }
    }

    private void doRequest() {
        if (mPresenter != null) {
            mPresenter.getCarTrackInfo(this, mCarInfo.getObjId(), "1");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mCarTrackMap.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCarTrackMap.onDestroy();
    }

    @Override
    public void getCarTrackInfoSuccess(Responce<CarTrack> responce) {
        if (responce.getDetail().getObjList().size() > 0) {
            if (trackBean != null) {
                if (trackBean.getPosTime().equals(responce.getDetail().getObjList().get(0).getPosTime())) {
                    return;
                }
            }
            trackBean = responce.getDetail().getObjList().get(0);
            aMap.clear();
            LatLng lng = new LatLng(trackBean.getLastLatitude(), trackBean.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(lng);
            markerOptions.anchor(0.5f, 0.5f);
            if (trackBean.getOnlineStatus() == Vehicle.CAR_STATE_MOVING) {
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.drawable.car)));
                mCarTrackCurrentStateTv.setText("运动");
            } else if (trackBean.getOnlineStatus() == Vehicle.CAR_STATE_STOP) {
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.drawable.car_status_static)));
                mCarTrackCurrentStateTv.setText("静止");
            } else {
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.drawable.car_status_off_line)));
                mCarTrackCurrentStateTv.setText("离线");
            }
            Marker marker = aMap.addMarker(markerOptions);
            marker.setRotateAngle(trackBean.getPosDirection());
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                    lng,
                    20,
                    0,
                    0
            ));
            aMap.animateCamera(update, 1500, null);
            mCarTrackPlateNumTv.setText(trackBean.getLpno());
            mCarTrackCarNameTv.setText(trackBean.getIdName());
            mCarTrackCurrentSpeedTv.setText(trackBean.getPosSpeed() + " Km/h");
            mCarTrackReportTimeTv.setText(trackBean.getPosTime());
        } else {
            aMap.clear();
            mCarTrackCurrentStateTv.setText("--");
            mCarTrackPlateNumTv.setText("--");
            mCarTrackCarNameTv.setText("--");
            mCarTrackCurrentSpeedTv.setText("--" + " Km/h");
            mCarTrackReportTimeTv.setText("--");
            return;
        }

    }
}
