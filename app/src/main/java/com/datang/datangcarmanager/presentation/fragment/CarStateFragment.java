package com.datang.datangcarmanager.presentation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.CarInfo;
import com.datang.datangcarmanager.model.RealTimeDetect;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.Vehicle;
import com.datang.datangcarmanager.presentation.activity.SingleCarInfoActivity;
import com.datang.datangcarmanager.presentation.view.InstrumentImageView;
import com.datang.datangcarmanager.presentation.view.VoltageAndRPMImageView;
import com.datang.datangcarmanager.presenter.CarStatePresenter;
import com.datang.datangcarmanager.view.ICarStateView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CarStateFragment extends Fragment implements ICarStateView{

    @BindView(R.id.instrument_image_view)
    InstrumentImageView mInstrumentImageView;
    @BindView(R.id.instrument_bottom_image_view)
    VoltageAndRPMImageView mInstrumentBottomImageView;
    @BindView(R.id.mileage_tv)
    TextView mMileageTv;
    @BindView(R.id.average_fuel_tv)
    TextView mAverageFuelTv;
    @BindView(R.id.fuel_consumption_tv)
    TextView mFuelConsumptionTv;
    @BindView(R.id.temperature_outside_tv)
    TextView mTemperatureOutsideTv;

    private int nowPage = 0;

    private RealTimeDetect realTimeDetect;

    private Vehicle mCarInfo;

    private CarStatePresenter mPresenter = null;

    public CarStateFragment(Vehicle carInfo) {
        mCarInfo = carInfo;
    }

    private Thread requestThread;


    public static CarStateFragment newInstance(Vehicle carInfo) {
        CarStateFragment fragment = new CarStateFragment(carInfo);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_state, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new CarStatePresenter(this.getContext());
        mInstrumentImageView.setVoltageAndRPMImageView(mInstrumentBottomImageView);
        doRequest();
        return view;
    }

    public void pageChanged(int position) {
        nowPage = position;
        if (position == SingleCarInfoActivity.VIEW_CAR_STATE) {
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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i("StateFragment", "Pause");
        if (requestThread != null) {
            requestThread.interrupt();
            requestThread = null;
        }
        super.onPause();
    }

    private void doRequest() {
        if (mPresenter != null) {
            mPresenter.getCarStateInfo(this, mCarInfo.getObjId());
        }
    }

    @Override
    public void getAllCarInfoSuccess(Responce<RealTimeDetect> responce) {
        realTimeDetect = responce.getDetail();
        Log.i("StateFragment", realTimeDetect.getVehicleSpeed());
        updateViews();
    }

    private void updateViews() {
        int speed = Integer.parseInt(realTimeDetect.getVehicleSpeed());
        int rpm = Integer.parseInt(realTimeDetect.getEngineRpm());
        float voltage = 0f;
        if (!realTimeDetect.getStorageBatteryVoltage().equals("--")) {
            voltage = Float.parseFloat(realTimeDetect.getStorageBatteryVoltage());
        }
        float fuelPressure = 0f;
        if (!realTimeDetect.getFuelPressure().equals("--")) {
            fuelPressure = Float.parseFloat(realTimeDetect.getFuelPressure());
        }
        mInstrumentImageView.setData(speed, voltage, rpm, fuelPressure);
        mMileageTv.setText(realTimeDetect.getVehicleTotalDistance());
        mAverageFuelTv.setText(realTimeDetect.getFuelConsumption());
        mFuelConsumptionTv.setText(realTimeDetect.getTotalFuelConsumption());
        mTemperatureOutsideTv.setText(realTimeDetect.getOutsideAirTemp());
    }
}
