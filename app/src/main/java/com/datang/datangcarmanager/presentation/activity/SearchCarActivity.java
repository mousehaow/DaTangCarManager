package com.datang.datangcarmanager.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.PtrHandler;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.CarInfo;
import com.datang.datangcarmanager.model.Responce;
import com.datang.datangcarmanager.model.SearchCarInfo;
import com.datang.datangcarmanager.model.Vehicle;
import com.datang.datangcarmanager.presentation.adapter.CarInfoListAdapter;
import com.datang.datangcarmanager.presentation.fragment.CarTrackFragment;
import com.datang.datangcarmanager.presentation.view.DefultItemDecoration;
import com.datang.datangcarmanager.presenter.SearchCarPresenter;
import com.datang.datangcarmanager.utils.MyToast;
import com.datang.datangcarmanager.view.ISearchCarView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SearchCarActivity extends BaseActivity implements ISearchCarView {

    public static final String TAG = "SearchCarActivity";

    @BindView(R.id.back_btn)
    ImageButton mBackBtn;
    @BindView(R.id.back_title_btn)
    TextView mBackTitleBtn;
    @BindView(R.id.search_car_recycler_view)
    RecyclerView mSearchCarRecyclerView;
    @BindView(R.id.car_search_view)
    SearchView mCarSearchView;
    @BindView(R.id.search_car_recycler_view_frame)
    PtrClassicFrameLayout mSearchCarRecyclerViewFrame;

    private List<Vehicle> mCarInfos = new ArrayList<Vehicle>();
    private CarInfoListAdapter mDataAdapter;
    private RecyclerAdapterWithHF mAdapterWithHF;
    Handler handler = new Handler();

    private SearchCarPresenter mPresenter;

    private boolean loadMore;

    SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_car);
        ButterKnife.bind(this);
        mPresenter = new SearchCarPresenter(this);
        mCarSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(TAG, "Submit Search");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                doSearchRequest();
                mSearchCarRecyclerViewFrame.autoRefresh(true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mDataAdapter = new CarInfoListAdapter(this, mCarInfos);
        mAdapterWithHF = new RecyclerAdapterWithHF(mDataAdapter);
        mSearchCarRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                OrientationHelper.VERTICAL, false));
        mSearchCarRecyclerView.addItemDecoration(new DefultItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        mSearchCarRecyclerView.setAdapter(mAdapterWithHF);
        mSearchCarRecyclerViewFrame.setLoadMoreEnable(false);
        mSearchCarRecyclerViewFrame.autoRefresh(true);
        mSearchCarRecyclerViewFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doSearchRequest();
                    }
                },1500);
            }
        });
        mSearchCarRecyclerViewFrame.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doLoadMoreRequest();
                    }
                }, 1500);
            }
        });
        mAdapterWithHF.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                Gson gson = new Gson();
                String car = gson.toJson(mCarInfos.get(position));
                Intent intent = new Intent(SearchCarActivity.this, SingleCarMapActivity.class);
                intent.putExtra(SingleCarMapActivity.TAG, car);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, android.R.anim.fade_out);
            }
        });
    }

    private void doLoadMoreRequest() {
        mPresenter.getLoadMoreInfo(this);
    }

    private void doSearchRequest() {
        if (!pDialog.isShowing()) {
            pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("正在加载中...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        mPresenter.getSearchCarInfo(this, mCarSearchView.getQuery().toString());
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
    public void getSearchCarInfoSuccess(Responce<SearchCarInfo> responce, boolean loadMore) {
        mCarInfos.clear();
        pDialog.dismiss();
        this.loadMore = loadMore;
        mCarInfos.addAll(responce.getDetail().getObjList());
        mAdapterWithHF.notifyDataSetChanged();
        mSearchCarRecyclerViewFrame.refreshComplete();
        mSearchCarRecyclerViewFrame.setLoadMoreEnable(loadMore);
        if (mCarInfos.size() == 0) {
            MyToast.showToastShort("没有找到您要查找的数据。");
        }
    }

    @Override
    public void loadMoreSearchCarInfoSuccess(Responce<SearchCarInfo> responce, boolean loadMore) {
        this.loadMore = loadMore;
        mCarInfos.addAll(responce.getDetail().getObjList());
        mAdapterWithHF.notifyDataSetChanged();
        mSearchCarRecyclerViewFrame.loadMoreComplete(loadMore);
    }

    @Override
    public void loadError() {
        if (mSearchCarRecyclerViewFrame.isRefreshing()) {
            pDialog.dismiss();
            mSearchCarRecyclerViewFrame.refreshComplete();
        } else if (mSearchCarRecyclerViewFrame.isLoadingMore()) {
            mSearchCarRecyclerViewFrame.loadMoreComplete(loadMore);
        }
    }
}
