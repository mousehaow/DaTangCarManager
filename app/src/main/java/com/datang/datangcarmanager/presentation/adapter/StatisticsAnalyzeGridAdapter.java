package com.datang.datangcarmanager.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.StatisticsAnalyze;
import com.datang.datangcarmanager.presentation.view.BadgeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presentation.adapter
 * @class describe
 * @anthor toby
 * @time 16/11/20 上午9:51
 * @change
 * @chang time
 * @class describe
 */
public class StatisticsAnalyzeGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public interface OnGridItemClickedListener {
        void itemClicked(int position);
    }

    protected Context mContext;
    protected LayoutInflater mInflater;
    private StatisticsAnalyze mStatisticsAnalyze;
    private OnGridItemClickedListener mListener;

    public StatisticsAnalyzeGridAdapter(Context context, StatisticsAnalyze data) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mStatisticsAnalyze = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.statistics_analyze_more_grid_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void setListener(OnGridItemClickedListener listener) {
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        StatisticsAnalyzeGridAdapter.ViewHolder holder = (StatisticsAnalyzeGridAdapter.ViewHolder)viewHolder;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.itemClicked(position);
            }
        });
        switch (position) {
            /* 里程 */
            case 0:
                holder.mGridItemIcon.setImageResource(R.drawable.chart_mile_icon);
                holder.mGridItemTitle.setText("里程");
                holder.mGridItemBg.setBackgroundResource(R.color.grey_light);
                break;
            /* 用车时间 */
            case 1:
                holder.mGridItemIcon.setImageResource(R.drawable.chart_time_icon);
                holder.mGridItemTitle.setText("用车时间");
                holder.mGridItemBg.setBackgroundResource(R.color.white);
                break;
            /* 耗油量 */
            case 2:
                holder.mGridItemIcon.setImageResource(R.drawable.chart_oil_icon);
                holder.mGridItemTitle.setText("耗油量");
                holder.mGridItemBg.setBackgroundResource(R.color.white);
                break;
            /* 机车拆除 */
            case 3:
                holder.mGridItemIcon.setImageResource(R.drawable.car_demolition);
                holder.mGridItemTitle.setText("机车拆除");
                holder.mGridItemBg.setBackgroundResource(R.color.grey_light);
                break;
            /* 怠速时长 */
            case 4:
                holder.mGridItemIcon.setImageResource(R.drawable.holiday_car);
                holder.mGridItemTitle.setText("怠速时长");
                holder.mGridItemBg.setBackgroundResource(R.color.grey_light);
                break;
            /* 越界 */
            case 5:
                holder.mGridItemIcon.setImageResource(R.drawable.transboundary);
                holder.mGridItemTitle.setText("越界");
                holder.mGridItemBg.setBackgroundResource(R.color.white);
                break;
            /* 未匹配用车 */
            case 6:
                holder.mGridItemIcon.setImageResource(R.drawable.mismatch);
                holder.mGridItemTitle.setText("未匹配用车");
                holder.mGridItemBg.setBackgroundResource(R.color.white);
                break;
            /* 违章 */
            case 7:
                holder.mGridItemIcon.setImageResource(R.drawable.violate_regulations);
                holder.mGridItemTitle.setText("违章");
                holder.mGridItemBg.setBackgroundResource(R.color.grey_light);
                break;
            /* 超速 */
            case 8:
                holder.mGridItemIcon.setImageResource(R.drawable.speeding);
                holder.mGridItemTitle.setText("超速");
                holder.mGridItemBg.setBackgroundResource(R.color.grey_light);
                BadgeView badge = new BadgeView(mContext, holder.mGridItemRight);
                badge.setBadgePosition(BadgeView.POSITION_CENTER);
                badge.setText("1");
                badge.show();
                break;
            default:
                break;
        }
    }


    @Override
    public int getItemCount() {
        return 9;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.grid_item_icon)
        ImageView mGridItemIcon;
        @BindView(R.id.grid_item_title)
        TextView mGridItemTitle;
        @BindView(R.id.grid_item_right)
        RelativeLayout mGridItemRight;
        @BindView(R.id.grid_item_bg)
        RelativeLayout mGridItemBg;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
