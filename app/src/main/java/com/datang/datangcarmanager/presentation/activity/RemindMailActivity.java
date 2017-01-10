package com.datang.datangcarmanager.presentation.activity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.presentation.fragment.AbnormalSituationFragment;
import com.datang.datangcarmanager.presentation.fragment.AnnualSurveyFragment;
import com.datang.datangcarmanager.presentation.fragment.MaintainFragment;
import com.datang.datangcarmanager.presentation.view.APSTSViewPager;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RemindMailActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    public static final String TAG = "RemindMailActivity";

    public static final int VIEW_ABNORMAIL_SITUATION = 0;
    public static final int VIEW_MAINTAIN = 1;
    public static final int VIEW_ANNUAL_SURVEY = 2;

    private static final int VIEW_SIZE = 3;

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.remind_mail_search_view)
    SearchView mRemindMailSearchView;
    @BindView(R.id.mail_tabs)
    AdvancedPagerSlidingTabStrip mMailTabs;
    @BindView(R.id.view_pager_mail)
    APSTSViewPager mViewPagerMail;

    private Context mContext;

    private String searchInfo = "";

    private AbnormalSituationFragment abnormalSituationFragment;
    private MaintainFragment maintainFragment;
    private AnnualSurveyFragment annualSurveyFragment;

    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_mail);
        ButterKnife.bind(this);
        mContext = this;
        initTab();
        initSearchView();
    }

    private void initSearchView() {
        mRemindMailSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchInfo = query;
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                if (abnormalSituationFragment != null) {
                    abnormalSituationFragment.searchInfoChanged(currentPage, searchInfo);
                }
                if (maintainFragment != null) {
                    maintainFragment.searchInfoChanged(currentPage, searchInfo);
                }
                if (annualSurveyFragment != null) {
                    annualSurveyFragment.searchInfoChanged(currentPage, searchInfo);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initTab() {
        mViewPagerMail.setOffscreenPageLimit(VIEW_SIZE);
        MailFragmentAdapter adapter = new MailFragmentAdapter(getSupportFragmentManager());
        mViewPagerMail.setAdapter(new MailFragmentAdapter(getSupportFragmentManager()));
        adapter.notifyDataSetChanged();
        mMailTabs.setViewPager(mViewPagerMail);
        mViewPagerMail.addOnPageChangeListener(this);
        mViewPagerMail.setCurrentItem(0);

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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPage = position;
        if (abnormalSituationFragment != null) {
            abnormalSituationFragment.pageChanged(position);
        }
        if (maintainFragment != null) {
            maintainFragment.pageChanged(position);
        }
        if (annualSurveyFragment != null) {
            annualSurveyFragment.pageChanged(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class MailFragmentAdapter extends FragmentStatePagerAdapter implements AdvancedPagerSlidingTabStrip.IconTabProvider {
        public MailFragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            if(position >= 0 && position < VIEW_SIZE){
                switch (position){
                    case VIEW_ABNORMAIL_SITUATION:
                        if (abnormalSituationFragment == null) {
                            abnormalSituationFragment = AbnormalSituationFragment.newInstance(mContext, searchInfo);
                            abnormalSituationFragment.pageChanged(currentPage);
                        }
                        return abnormalSituationFragment;
                    case VIEW_MAINTAIN:
                        if (maintainFragment == null) {
                            maintainFragment = MaintainFragment.newInstance(mContext, searchInfo);
                            maintainFragment.pageChanged(currentPage);
                        }
                        return maintainFragment;
                    case VIEW_ANNUAL_SURVEY:
                        if (annualSurveyFragment == null) {
                            annualSurveyFragment = AnnualSurveyFragment.newInstance(mContext, searchInfo);
                            annualSurveyFragment.pageChanged(currentPage);
                        }
                        return annualSurveyFragment;
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
                    case VIEW_ABNORMAIL_SITUATION:
                        return "异常用车处理";
                    case VIEW_MAINTAIN:
                        return "故障维修处理";
                    case VIEW_ANNUAL_SURVEY:
                        return "保险年检处理";
                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public Integer getPageIcon(int position) {
            if(position >= 0 && position < VIEW_SIZE){
                switch (position){
                    case VIEW_ABNORMAIL_SITUATION:
                        return R.drawable.car_manager_one_common;
                    case VIEW_MAINTAIN:
                        return R.drawable.car_manager_two_common;
                    case VIEW_ANNUAL_SURVEY:
                        return R.drawable.car_manager_three_common;
                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public Integer getPageSelectIcon(int position) {
            if(position >= 0 && position < VIEW_SIZE){
                switch (position){
                    case VIEW_ABNORMAIL_SITUATION:
                        return R.drawable.car_manager_one_choose;
                    case VIEW_MAINTAIN:
                        return R.drawable.car_manager_two_choose;
                    case VIEW_ANNUAL_SURVEY:
                        return R.drawable.car_manager_three_choose;
                    default:
                        break;
                }
            }
            return null;
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
