package com.datang.datangcarmanager.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
 * @time 16/11/20 下午1:33
 * @change
 * @chang time
 * @class describe
 */
public class ChartListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<StatisticsAnalyze> mData;

    public ChartListAdapter(Context context, List<StatisticsAnalyze> data) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.chart_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ChartListAdapter.ViewHolder holder = (ChartListAdapter.ViewHolder)viewHolder;
        int width = holder.itemView.getWidth();
        if (position == 0) {
            holder.mChartItemProgressBar.setBackground(
                    mContext.getResources().getDrawable(R.drawable.red_progress_bar_fill_0_1));
            holder.mChartItemProgressBar.setMinimumWidth(width);
        } else {
            holder.mChartItemProgressBar.setBackground(
                    mContext.getResources().getDrawable(R.drawable.red_progress_bar_fill_8_9));
            holder.mChartItemProgressBar.setMinimumWidth(width / (position + 1));
        }
        holder.mChartItemName.setText(mData.get(position).getName());
        holder.mChartItemCount.setText(mData.get(position).getIdlingTime());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chart_item_progress_bar)
        ImageView mChartItemProgressBar;
        @BindView(R.id.chart_item_name)
        TextView mChartItemName;
        @BindView(R.id.chart_item_count)
        TextView mChartItemCount;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
