package com.datang.datangcarmanager.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.StatisticsAnalyze;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presentation.adapter
 * @class describe
 * @anthor toby
 * @time 16/11/19 下午6:28
 * @change
 * @chang time
 * @class describe
 */
public class StatisticsAnalyzeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    private List<StatisticsAnalyze> mStatisticsAnalyzes;

    public StatisticsAnalyzeListAdapter(Context context, List<StatisticsAnalyze> data) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mStatisticsAnalyzes = data;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.statistics_analyze_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        StatisticsAnalyzeListAdapter.ViewHolder holder = (StatisticsAnalyzeListAdapter.ViewHolder)viewHolder;
        StatisticsAnalyze statisticsAnalyze = mStatisticsAnalyzes.get(position);
        holder.mItemName.setText(statisticsAnalyze.getName());
        holder.mItemMailage.setText(String.format("月总里程: %.2f 公里",
                statisticsAnalyze.getMailage()));
        if (statisticsAnalyze.isCar()) {
            if (statisticsAnalyze.isOnLine()) {
                holder.mItemOnlineRatio.setText("是否上线: 上线");
            } else {
                holder.mItemOnlineRatio.setText("是否上线: 离线");
            }
        } else {
            holder.mItemOnlineRatio.setText(String.format("月上线率: %.1f%",
                    statisticsAnalyze.getOnlineRatio()));
        }
        holder.mItemOilConsumption.setText(String.format("月油耗总量: %.2f 升",
                statisticsAnalyze.getOilConsumption()));
        holder.mItemOverLine.setText(String.format("越线次数: %d 次",
                statisticsAnalyze.getOverLine()));
        holder.mItemTotalTime.setText(String.format("累计行车时间: %.2f 小时",
                statisticsAnalyze.getTotalTime()));
        holder.mItemOffTime.setText(String.format("拔出次数: %d 次",
                statisticsAnalyze.getOffTime()));
        holder.mItemIdlingTime.setText(String.format("总怠速时间: %d 分钟",
                statisticsAnalyze.getIdlingTime()));
        holder.mItemRepairTime.setText(String.format("故障维修次数: %d 次",
                statisticsAnalyze.getRepairTime()));
        holder.mItemNotMatchingTime.setText(String.format("未匹配用车次数: %d 次",
                statisticsAnalyze.getNotMatchingTime()));
        holder.mItemViolationTime.setText(String.format("违章次数: %d 次",
                statisticsAnalyze.getViolationTime()));
    }


    @Override
    public int getItemCount() {
        return mStatisticsAnalyzes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_name)
        TextView mItemName;
        @BindView(R.id.item_mailage)
        TextView mItemMailage;
        @BindView(R.id.item_online_ratio)
        TextView mItemOnlineRatio;
        @BindView(R.id.item_oil_consumption)
        TextView mItemOilConsumption;
        @BindView(R.id.item_over_line)
        TextView mItemOverLine;
        @BindView(R.id.item_total_time)
        TextView mItemTotalTime;
        @BindView(R.id.item_off_time)
        TextView mItemOffTime;
        @BindView(R.id.item_idling_time)
        TextView mItemIdlingTime;
        @BindView(R.id.item_repair_time)
        TextView mItemRepairTime;
        @BindView(R.id.item_not_matching_time)
        TextView mItemNotMatchingTime;
        @BindView(R.id.item_violation_time)
        TextView mItemViolationTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
