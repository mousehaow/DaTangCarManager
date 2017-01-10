package com.datang.datangcarmanager.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.AbnormalSituation;
import com.datang.datangcarmanager.model.CarInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presentation.adapter
 * @class describe
 * @anthor toby
 * @time 2016/11/21 下午2:18
 * @change
 * @chang time
 * @class describe
 */
public class AbnormalSituationListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected List<AbnormalSituation.AbnormalSituationBean> mCarInfos;
    protected LayoutInflater mInflater;

    public AbnormalSituationListAdapter(Context context,
                                        List<AbnormalSituation.AbnormalSituationBean> carInfos) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mCarInfos = carInfos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.abnormal_situation_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        AbnormalSituationListAdapter.ViewHolder holder = (AbnormalSituationListAdapter.ViewHolder)viewHolder;
        holder.mItemDriver.setText("驾驶员: " + mCarInfos.get(position).getDriver());
        holder.mItemPlateNum.setText("车牌号: " + mCarInfos.get(position).getLpno());
        holder.mItemWarningType.setText("违规类型: " + mCarInfos.get(position).getAlarmType());
    }


    @Override
    public int getItemCount() {
        return mCarInfos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_car_icon)
        ImageView mItemCarIcon;
        @BindView(R.id.item_plate_num)
        TextView mItemPlateNum;
        @BindView(R.id.item_warning_type)
        TextView mItemWarningType;
        @BindView(R.id.item_driver)
        TextView mItemDriver;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
