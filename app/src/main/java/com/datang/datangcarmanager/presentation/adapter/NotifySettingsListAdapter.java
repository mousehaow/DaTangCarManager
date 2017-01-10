package com.datang.datangcarmanager.presentation.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.presentation.activity.NotifySettingActivity;
import com.datang.datangcarmanager.utils.DensityUtil;
import com.kyleduo.switchbutton.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presentation.adapter
 * @class describe
 * @anthor toby
 * @time 2016/11/26 下午5:09
 * @change
 * @chang time
 * @class describe
 */
public class NotifySettingsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnSwichChangeStateListener{
        public void checkedChanged(int position, boolean isChecked);
    }

    private Context mContext;
    private LayoutInflater mInflater;
    private String[] settingsName;
    private OnSwichChangeStateListener mListener;

    public NotifySettingsListAdapter(Context context, String[] settingsName) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.settingsName = settingsName;
    }

    public void setListener(OnSwichChangeStateListener listener) {
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.notify_setting_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder)viewHolder;
        if (position == 0) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)holder.itemView.getLayoutParams();
            layoutParams.setMargins(DensityUtil.dip2px(mContext, 8f),
                    DensityUtil.dip2px(mContext, 10f),
                    DensityUtil.dip2px(mContext, 8f), 0);
            holder.itemView.setLayoutParams(layoutParams);
        }
        holder.mSettingNameTv.setText(settingsName[position]);
        SharedPreferences settings = mContext.getSharedPreferences(NotifySettingActivity.NOTIFY_SETTINGS, 0);
        boolean state = settings.getBoolean(settingsName[position], true);
        holder.mSettingSwitch.setChecked(state);
        holder.mSettingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mListener.checkedChanged(position, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return settingsName.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.setting_name_tv)
        TextView mSettingNameTv;
        @BindView(R.id.setting_switch)
        SwitchButton mSettingSwitch;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
