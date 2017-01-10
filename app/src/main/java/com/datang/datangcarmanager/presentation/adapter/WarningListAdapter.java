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
import com.datang.datangcarmanager.model.AnnualSurveyInfos;
import com.datang.datangcarmanager.model.CarInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presentation.adapter
 * @class describe
 * @anthor toby
 * @time 2016/11/21 下午2:33
 * @change
 * @chang time
 * @class describe
 */
public class WarningListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int MAINTAIN_LIST = 0;
    public static final int ANNUAL_SURVEY_LIST = 1;

    protected Context mContext;
    protected List<AbnormalSituation.AbnormalSituationBean> mWarningInfos = new ArrayList<>();
    protected List<AnnualSurveyInfos.AnnualSurveyBean> mAnnualSurveyInfos = new ArrayList<>();
    protected LayoutInflater mInflater;
    protected int listType;

    public WarningListAdapter(Context context, int type) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        listType = type;
    }

    public void setmWarningInfos(List<AbnormalSituation.AbnormalSituationBean> mWarningInfos) {
        this.mWarningInfos = mWarningInfos;
    }

    public void setmAnnualSurveyInfos(List<AnnualSurveyInfos.AnnualSurveyBean> mAnnualSurveyInfos) {
        this.mAnnualSurveyInfos = mAnnualSurveyInfos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.maintain_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        WarningListAdapter.ViewHolder holder = (WarningListAdapter.ViewHolder)viewHolder;
        switch (listType) {
            case MAINTAIN_LIST:
                holder.mItemWarningType.setText("报警类型: " + mWarningInfos.get(position).getAlarmType());
                holder.mItemPlateNum.setText("车牌号: " + mWarningInfos.get(position).getLpno());
                holder.mItemCarIcon.setImageResource(R.drawable.mercedes);
                break;
            case ANNUAL_SURVEY_LIST:
                holder.mItemCarIcon.setImageResource(R.drawable.mercedes);
                holder.mItemWarningType.setText("提醒类型: " + mAnnualSurveyInfos.get(position).getFeeTypeName());
                holder.mItemPlateNum.setText("车牌号: " + mAnnualSurveyInfos.get(position).getLpno());
                break;
            default:
                break;
        }
    }


    @Override
    public int getItemCount() {
        if (listType == MAINTAIN_LIST) {
            return mWarningInfos.size();
        } else {
            return mAnnualSurveyInfos.size();
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_car_icon)
        ImageView mItemCarIcon;
        @BindView(R.id.item_plate_num)
        TextView mItemPlateNum;
        @BindView(R.id.item_warning_type)
        TextView mItemWarningType;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
