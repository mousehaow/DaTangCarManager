package com.datang.datangcarmanager.presentation.activity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.widget.ImageButton;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.CarInfo;
import com.datang.datangcarmanager.model.Vehicle;
import com.datang.datangcarmanager.presentation.fragment.CarArchiveFragment;
import com.datang.datangcarmanager.presentation.fragment.CarStateFragment;
import com.datang.datangcarmanager.presentation.fragment.CarTrackFragment;
import com.datang.datangcarmanager.presentation.fragment.DriveRecordFragment;
import com.datang.datangcarmanager.presentation.fragment.ParkingRecordFragment;
import com.datang.datangcarmanager.presentation.view.APSTSViewPager;
import com.google.gson.Gson;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleCarInfoActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    public static final String TAG = "SingleCarInfoActivity";
    public static final String CAR_TAG = "carTag";

    public static final int VIEW_CAR_STATE = 0;
    public static final int VIEW_DRIVE_RECORD = 1;
    public static final int VIEW_PARKING_RECORD = 2;
    public static final int VIEW_CAR_TRACK = 3;
    public static final int VIEW_CAR_ARCHIVE = 4;

    private static final int VIEW_SIZE = 5;

    private int firstShowItem;

    public Context mContext;
    public Vehicle mVehicle;

    @BindView(R.id.back_btn)
    ImageButton mBackBtn;
    @BindView(R.id.back_title_btn)
    TextView mBackTitleBtn;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.tabs)
    AdvancedPagerSlidingTabStrip mTabs;
    @BindView(R.id.vp_main)
    APSTSViewPager mVpMain;

    private CarStateFragment mCarStateFragment;
    private DriveRecordFragment mDriveRecordFragment;
    private ParkingRecordFragment mParkingRecordFragment;
    private CarTrackFragment mCarTrackFragment;
    private CarArchiveFragment mCarArchiveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_car_info);
        ButterKnife.bind(this);
        mContext = this;
        Gson gson = new Gson();
        firstShowItem = getIntent().getIntExtra(TAG, 0);
        mVehicle = gson.fromJson(getIntent().getStringExtra(CAR_TAG), Vehicle.class);
        mTitle.setText(mVehicle.getLpno());
        initTab();
    }

    /**
     * @author toby
     * @time 16/11/15  下午12:07
     * @describe 初始化底部的TabBar
     */
    private void initTab() {
        mVpMain.setOffscreenPageLimit(VIEW_SIZE);
        mVpMain.setNoFocus(true);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        mVpMain.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        adapter.notifyDataSetChanged();
        mTabs.setViewPager(mVpMain);
        mTabs.setOnPageChangeListener(this);
        mVpMain.setCurrentItem(firstShowItem);
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mCarStateFragment != null) {
            mCarStateFragment.pageChanged(position);
        }
        if (mDriveRecordFragment != null) {
            mDriveRecordFragment.pageChanged(position);
        }
        if (mCarTrackFragment != null) {
            mCarTrackFragment.pageChanged(position);
        }
        if (mParkingRecordFragment != null) {
            mParkingRecordFragment.pageChanged(position);
        }
        if (mCarArchiveFragment != null) {
            mCarArchiveFragment.pageChanged(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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

    public class FragmentAdapter extends FragmentStatePagerAdapter implements AdvancedPagerSlidingTabStrip.IconTabProvider {

        public FragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if (position >= 0 && position < VIEW_SIZE) {
                switch (position) {
                    case VIEW_CAR_STATE:
                        if (mCarStateFragment == null) {
                            mCarStateFragment = CarStateFragment.newInstance(mVehicle);
                            mCarStateFragment.pageChanged(firstShowItem);
                        }
                        return mCarStateFragment;
                    case VIEW_DRIVE_RECORD:
                        if (mDriveRecordFragment == null) {
                            mDriveRecordFragment = DriveRecordFragment.newInstance(mContext, mVehicle);
                            mDriveRecordFragment.pageChanged(firstShowItem);
                        }
                        return mDriveRecordFragment;
                    case VIEW_PARKING_RECORD:
                        if (mParkingRecordFragment == null) {
                            mParkingRecordFragment = ParkingRecordFragment.newInstance(mContext, mVehicle);
                            mParkingRecordFragment.pageChanged(firstShowItem);
                        }
                        return mParkingRecordFragment;
                    case VIEW_CAR_TRACK:
                        if (mCarTrackFragment == null) {
                            mCarTrackFragment = CarTrackFragment.newInstance(mContext, mVehicle);
                            mCarTrackFragment.pageChanged(firstShowItem);
                        }
                        return mCarTrackFragment;
                    case VIEW_CAR_ARCHIVE:
                        if (mCarArchiveFragment == null) {
                            mCarArchiveFragment = CarArchiveFragment.newInstance(mVehicle);
                            mCarArchiveFragment.pageChanged(firstShowItem);
                        }
                        return mCarArchiveFragment;
                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position >= 0 && position < VIEW_SIZE){
                switch (position){
                    case VIEW_CAR_STATE:
                        return "车辆状况";
                    case VIEW_DRIVE_RECORD:
                        return "行车记录";
                    case VIEW_PARKING_RECORD:
                        return "停车分布";
                    case VIEW_CAR_TRACK:
                        return "车辆跟踪";
                    case VIEW_CAR_ARCHIVE:
                        return "车辆档案";
                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public Integer getPageIcon(int position) {
            if (position >= 0 && position < VIEW_SIZE) {
                switch (position) {
                    case VIEW_CAR_STATE:
                        return R.drawable.trace_condition_common;
                    case VIEW_DRIVE_RECORD:
                        return R.drawable.trace_record_common;
                    case VIEW_PARKING_RECORD:
                        return R.drawable.trace_parking_common;
                    case VIEW_CAR_TRACK:
                        return R.drawable.trace_tracing_common;
                    case VIEW_CAR_ARCHIVE:
                        return R.drawable.trace_car_record_common;
                    default:
                        break;
                }
            }
            return 0;
        }

        @Override
        public Integer getPageSelectIcon(int position) {
            if (position >= 0 && position < VIEW_SIZE) {
                switch (position) {
                    case VIEW_CAR_STATE:
                        return R.drawable.trace_condition_pressed;
                    case VIEW_DRIVE_RECORD:
                        return R.drawable.trace_record_pressed;
                    case VIEW_PARKING_RECORD:
                        return R.drawable.trace_parking_pressed;
                    case VIEW_CAR_TRACK:
                        return R.drawable.trace_tracing_pressed;
                    case VIEW_CAR_ARCHIVE:
                        return R.drawable.trace_car_record_pressed;
                    default:
                        break;
                }
            }
            return 0;
        }

        @Override
        public Rect getPageIconBounds(int position) {
            return null;
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return VIEW_SIZE;
        }
    }
}
