package com.datang.datangcarmanager.presentation.fragment;


import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.CarInfo;
import com.datang.datangcarmanager.model.CarTrack;
import com.datang.datangcarmanager.model.DriveRecord;
import com.datang.datangcarmanager.model.ParkingRecordList;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.Vehicle;
import com.datang.datangcarmanager.presentation.activity.CarTrackActivity;
import com.datang.datangcarmanager.presentation.activity.EnterpriseCarListActivity;
import com.datang.datangcarmanager.presentation.activity.SingleCarInfoActivity;
import com.datang.datangcarmanager.presentation.activity.SingleCarMapActivity;
import com.datang.datangcarmanager.presentation.adapter.DriveRecordListAdapter;
import com.datang.datangcarmanager.presentation.view.DefultItemDecoration;
import com.datang.datangcarmanager.presenter.DriveRecordPresenter;
import com.datang.datangcarmanager.utils.MyToast;
import com.datang.datangcarmanager.view.IDriveRecordView;
import com.google.gson.Gson;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class DriveRecordFragment extends Fragment implements TimePickerDialog.OnTimeSetListener, IDriveRecordView, GeocodeSearch.OnGeocodeSearchListener {

    public static final String DIALOG_TAG = "CALDROID_DIALOG_DriveRecordFragment";

    Vehicle mCarInfo;

    @BindView(R.id.drive_record_left_daypick_btn)
    TextView mDriveRecordLeftDaypickBtn;
    @BindView(R.id.drive_record_date_show_tv)
    TextView mDriveRecordDateShowTv;
    @BindView(R.id.drive_record_date_picker_btn)
    ImageButton mDriveRecordDatePickerBtn;
    @BindView(R.id.drive_record_right_daypick_btn)
    TextView mDriveRecordRightDaypickBtn;
    @BindView(R.id.drive_record_low_time)
    TextView mDriveRecordLowTime;
    @BindView(R.id.drive_record_height_time)
    TextView mDriveRecordHeightTime;
    @BindView(R.id.drive_record_reset_btn)
    TextView mDriveRecordResetBtn;
    @BindView(R.id.drive_record_day_oil_consumption)
    TextView mDriveRecordDayOilConsumption;
    @BindView(R.id.drive_record_mileage)
    TextView mDriveRecordMileage;
    @BindView(R.id.drive_record_average_oil_consumption)
    TextView mDriveRecordAverageOilConsumption;
    @BindView(R.id.drive_record_recycler_view)
    RecyclerView mDriveRecordRecyclerView;
    @BindView(R.id.drive_record_recycler_view_frame)
    PtrClassicFrameLayout mDriveRecordRecyclerViewFrame;

    private DriveRecordListAdapter mRecordListAdapter;
    private RecyclerAdapterWithHF mAdapterWithHF;

    private List<DriveRecord.SegListBean> mDriveRecords = new ArrayList<>();

    Context mContext;

    private Date selectedDate;
    private Date selectedTime;

    private CaldroidFragment caldroidFragment;
    private Calendar calendar;
    private Bundle state;
    public static SimpleDateFormat formatter;
    public static SimpleDateFormat timeFormatter;

    private boolean isLeftTimePicker;

    private SweetAlertDialog mDialog;

    private DriveRecordPresenter mPresenter = null;
    private int nowPage;

    private GeocodeSearch geocodeSearch;
    private int geocodeSearchNum = 0;

    public DriveRecordFragment(Context context, Vehicle carInfo) {
        mCarInfo = carInfo;
        mContext = context;
    }

    public static DriveRecordFragment newInstance(Context context, Vehicle carInfo) {
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        timeFormatter = new SimpleDateFormat("hh:mm");
        DriveRecordFragment fragment = new DriveRecordFragment(context, carInfo);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drive_record, container, false);
        ButterKnife.bind(this, view);
        state = savedInstanceState;
        mPresenter = new DriveRecordPresenter(this.getContext());
        initDatePickView();
        initRecycleView();
        return view;
    }

    public void pageChanged(int position) {
        nowPage = position;
        Log.i("Parking", "" + nowPage);
        if (position == SingleCarInfoActivity.VIEW_DRIVE_RECORD) {
            if (mDriveRecords.size() == 0 && mPresenter != null) {
                driveRecordDataRequest();
                mDriveRecordRecyclerViewFrame.autoRefresh(true);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        caldroidFragment.onSaveInstanceState(outState);
    }
    @Override
    public void onResume() {
        super.onResume();
        caldroidFragment.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        caldroidFragment.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initDatePickView() {
        calendar = new GregorianCalendar();
        selectedDate = new Date();
        mDriveRecordDateShowTv.setText(formatter.format(selectedDate));
        geocodeSearch = new GeocodeSearch(this.getActivity());
        geocodeSearch.setOnGeocodeSearchListener(this);
        matchPickBtn();
        if (caldroidFragment == null) {
            caldroidFragment = new CaldroidFragment();
            caldroidFragment.setCaldroidListener(getListener());
            if (state == null) {
                state = new Bundle();
            }
            caldroidFragment.setSelectedDate(selectedDate);
            caldroidFragment.setMaxDate(selectedDate);
        }
    }

    private void initRecycleView() {
        mRecordListAdapter = new DriveRecordListAdapter(mContext, mDriveRecords);
        mAdapterWithHF = new RecyclerAdapterWithHF(mRecordListAdapter);
        mAdapterWithHF.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                Gson gson = new Gson();
                String car = gson.toJson(mCarInfo);
                String record = gson.toJson(mDriveRecords.get(position));
                Intent intent = new Intent(getActivity(), CarTrackActivity.class);
                intent.putExtra(CarTrackActivity.TAG, car);
                intent.putExtra(CarTrackActivity.RECORD_TAG, record);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, android.R.anim.fade_out);
            }
        });
        mDriveRecordRecyclerView.setLayoutManager(
                new LinearLayoutManager(mContext, OrientationHelper.VERTICAL, false));
        mDriveRecordRecyclerView.addItemDecoration(new DefultItemDecoration(mContext,
                LinearLayoutManager.VERTICAL));
        mDriveRecordRecyclerView.setAdapter(mAdapterWithHF);
        mDriveRecordRecyclerViewFrame.setLoadMoreEnable(false);
        mDriveRecordRecyclerViewFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mDriveRecordRecyclerViewFrame.autoRefresh(true);
            }
        }, 500);
        mDriveRecordRecyclerViewFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                driveRecordDataRequest();
            }
        });
        mDriveRecordRecyclerViewFrame.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                mDriveRecordRecyclerViewFrame.loadMoreComplete(true);
            }
        });
    }

    private void driveRecordDataRequest() {
        if (nowPage == SingleCarInfoActivity.VIEW_DRIVE_RECORD) {
            String date = formatter.format(selectedDate);
            String beginTime = date + " " + mDriveRecordLowTime.getText().toString() + ":00";
            String endTime = date + " " + mDriveRecordHeightTime.getText().toString() + ":59";
            mPresenter.getDriveRecords(this, mCarInfo.getObjId(), beginTime, endTime);
            mDriveRecordRecyclerViewFrame.autoRefresh(true);
            if (mDialog == null || !mDialog.isShowing()) {
                mDialog = new SweetAlertDialog(this.getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                mDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                mDialog.setTitleText("正在加载中...");
                mDialog.setCancelable(false);
                mDialog.show();
            }
        }
    }


    @OnClick({R.id.drive_record_left_daypick_btn,
              R.id.drive_record_date_picker_btn,
              R.id.drive_record_right_daypick_btn,
              R.id.drive_record_low_time,
              R.id.drive_record_height_time,
              R.id.drive_record_reset_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drive_record_left_daypick_btn:
                calendar.setTime(selectedDate);
                calendar.add(calendar.DATE, -1);
                selectedDate = calendar.getTime();
                caldroidFragment.clearSelectedDates();
                caldroidFragment.setSelectedDate(selectedDate);
                state.putInt(caldroidFragment.MONTH, selectedDate.getMonth());
                state.putInt(caldroidFragment.YEAR, selectedDate.getYear());
                mDriveRecordDateShowTv.setText(formatter.format(selectedDate));
                matchPickBtn();
                break;
            case R.id.drive_record_right_daypick_btn:
                calendar.setTime(selectedDate);
                calendar.add(calendar.DATE, 1);
                selectedDate = calendar.getTime();
                caldroidFragment.clearSelectedDates();
                caldroidFragment.setSelectedDate(selectedDate);
                state.putInt(caldroidFragment.MONTH, selectedDate.getMonth());
                state.putInt(caldroidFragment.YEAR, selectedDate.getYear());
                mDriveRecordDateShowTv.setText(formatter.format(selectedDate));
                matchPickBtn();
                break;
            case R.id.drive_record_date_picker_btn:
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
                break;
            case R.id.drive_record_low_time:
                isLeftTimePicker = true;
                try {
                    selectedTime = timeFormatter.parse(mDriveRecordLowTime.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.i("showTime", selectedTime.getHours() + ":" + selectedTime.getMinutes());
                new TimePickerDialog(mContext, this, selectedTime.getHours(),
                        selectedTime.getMinutes(), true).show();
                break;
            case R.id.drive_record_height_time:
                isLeftTimePicker = false;
                try {
                    selectedTime = timeFormatter.parse(mDriveRecordHeightTime.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.i("showTime", selectedTime.getHours() + ":" + selectedTime.getMinutes());
                new TimePickerDialog(mContext, this, selectedTime.getHours(),
                        selectedTime.getMinutes(), true).show();
                break;
            case R.id.drive_record_reset_btn:
                mDriveRecordLowTime.setText("00:00");
                mDriveRecordHeightTime.setText("23:59");
                break;
        }
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
                mDriveRecordDateShowTv.setText(formatter.format(selectedDate));
                matchPickBtn();
            }

            @Override
            public void onChangeMonth(int month, int year) {
                super.onChangeMonth(month, year);
            }
        };
    }

    private void matchPickBtn() {
        driveRecordDataRequest();
        calendar.setTime(selectedDate);
        calendar.add(calendar.DATE, -1);
        mDriveRecordLeftDaypickBtn.setText(calendar.getTime().getDate() + "日");
        calendar.add(calendar.DATE, 2);
        mDriveRecordRightDaypickBtn.setText(calendar.getTime().getDate() + "日");
        if (formatter.format(selectedDate).equals(formatter.format(new Date()))) {
            mDriveRecordLeftDaypickBtn.setTextColor(mContext.getResources().getColor(R.color.black));
            mDriveRecordLeftDaypickBtn.setClickable(true);
            mDriveRecordRightDaypickBtn.setTextColor(mContext.getResources().getColor(R.color.grey_light));
            mDriveRecordRightDaypickBtn.setClickable(false);
            return;
        }
        if (selectedDate.after(new Date())) {
            mDriveRecordLeftDaypickBtn.setTextColor(mContext.getResources().getColor(R.color.grey_light));
            mDriveRecordLeftDaypickBtn.setClickable(false);
            mDriveRecordRightDaypickBtn.setTextColor(mContext.getResources().getColor(R.color.grey_light));
            mDriveRecordRightDaypickBtn.setClickable(false);
        } else {
            mDriveRecordLeftDaypickBtn.setTextColor(mContext.getResources().getColor(R.color.black));
            mDriveRecordLeftDaypickBtn.setClickable(true);
            mDriveRecordRightDaypickBtn.setTextColor(mContext.getResources().getColor(R.color.black));
            mDriveRecordRightDaypickBtn.setClickable(true);
        }
    }

    /**
     * Called when the user is done setting a new time and the dialog has
     * closed.
     *
     * @param view      the view associated with this listener
     * @param hourOfDay the hour that was set
     * @param minute    the minute that was set
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (isLeftTimePicker) {
            String right = mDriveRecordHeightTime.getText().toString();
            char[] rightCharArray = right.toCharArray();
            for (int i = 0; i < rightCharArray.length; i++) {
                if (i == 4) {
                    rightCharArray[i] = '0';
                    break;
                }
                if (i >= 2) {
                    rightCharArray[i] = rightCharArray[i + 1];
                }
            }
            right = new String(rightCharArray);
            int low = hourOfDay * 1000 + minute * 10;
            int height = (new Integer(right)).intValue();
            if (low >= height) {
                Toast.makeText(mContext, "时间设置有误，请重新设置。",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            mDriveRecordLowTime.setText(String.format("%02d:%02d",
                    new Integer(hourOfDay),
                    new Integer(minute)));
        } else {
            String left = mDriveRecordLowTime.getText().toString();
            char[] leftCharArray = left.toCharArray();
            for (int i = 0; i < leftCharArray.length; i++) {
                if (i == 4) {
                    leftCharArray[i] = '0';
                    break;
                }
                if (i >= 2) {
                    leftCharArray[i] = leftCharArray[i + 1];
                }
            }
            left = new String(leftCharArray);
            int low = (new Integer(left)).intValue();
            int height = hourOfDay * 1000 + minute * 10;
            if (low >= height) {
                Toast.makeText(mContext, "时间设置有误，请重新设置。",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            mDriveRecordHeightTime.setText(String.format("%02d:%02d",
                    new Integer(hourOfDay),
                    new Integer(minute)));
        }
        mDriveRecordRecyclerViewFrame.autoRefresh(true);
    }


    @Override
    public void getDriveRecordsSuccess(Responce<DriveRecord> responce) {
        mDriveRecords.clear();
        if (responce.getDetail().getSegList().size() == 0) {
            MyToast.showToastShort("此时间段内没有行驶记录。");
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mRecordListAdapter.notifyDataSetChanged();
            mDriveRecordRecyclerViewFrame.refreshComplete();
        } else {
            mDriveRecords.addAll(responce.getDetail().getSegList());
            Collections.reverse(mDriveRecords);
            geocodeSearchNum = 0;
            RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(mDriveRecords.get(0).getStartLat(),
                    mDriveRecords.get(0).getStartLng()),
                    20, GeocodeSearch.AMAP);
            geocodeSearch.getFromLocationAsyn(query);
            mDriveRecordDayOilConsumption.setText(responce.getDetail().getFuelCost());
            mDriveRecordMileage.setText(responce.getDetail().getTotalMileAge());
            mDriveRecordAverageOilConsumption.setText(responce.getDetail().getAverageFuel());
            geocodeSearchNum = 0;
        }
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        if (i == 1000) {
            RegeocodeAddress address = regeocodeResult.getRegeocodeAddress();
            if (geocodeSearchNum >= mDriveRecords.size() * 2) {
                return;
            } else {
                if (geocodeSearchNum % 2 == 0) {
                    String location = address.getProvince() + address.getCity() + address.getDistrict() +
                            address.getTownship() + address.getNeighborhood();
                    mDriveRecords.get(geocodeSearchNum / 2).setStartLocation(location);
                } else {
                    String location = address.getProvince() + address.getCity() + address.getDistrict() +
                            address.getTownship() + address.getNeighborhood();
                    mDriveRecords.get(geocodeSearchNum / 2).setEndLocation(location);
                }
            }
            geocodeSearchNum++;
            if (geocodeSearchNum == mDriveRecords.size() * 2) {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                mRecordListAdapter.notifyDataSetChanged();
                mDriveRecordRecyclerViewFrame.refreshComplete();
            } else {
                if (geocodeSearchNum % 2 == 1) {
                    RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(mDriveRecords.get(geocodeSearchNum / 2).getEndLat(),
                            mDriveRecords.get(geocodeSearchNum / 2).getEndLng()),
                            20, GeocodeSearch.AMAP);
                    geocodeSearch.getFromLocationAsyn(query);
                    Log.i("TIME", "end time " + mDriveRecords.get(geocodeSearchNum / 2).getEndTime());
                } else {
                    RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(mDriveRecords.get(geocodeSearchNum / 2).getStartLat(),
                            mDriveRecords.get(geocodeSearchNum / 2).getStartLng()),
                            20, GeocodeSearch.AMAP);
                    geocodeSearch.getFromLocationAsyn(query);
                    Log.i("TIME", "start time " + mDriveRecords.get(geocodeSearchNum / 2).getStartTime());
                }
            }
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
}
