package com.datang.datangcarmanager.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.datang.datangcarmanager.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presentation.adapter
 * @class describe
 * @anthor toby
 * @time 2016/11/21 下午4:06
 * @change
 * @chang time
 * @class describe
 */
public class MailDetailsListAdapter extends RecyclerView.Adapter<MailDetailsListAdapter.ViewHolder> {

    public interface TrackBtnOnClickListener {
        public void buttonClicked();
    }

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<Map<String, String>> mData;
    private TrackBtnOnClickListener listener;

    public MailDetailsListAdapter(Context context, List<Map<String, String>> data) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mData = data;
    }

    public void setListener(TrackBtnOnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.mail_detail_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String key = null;
        for (String set : mData.get(position).keySet()) {
            key = set;
        }
        if (key.equals("轨迹追踪")) {
            holder.mTitle.setText(key);
            holder.mDetails.setVisibility(View.GONE);
            holder.mTrackBtn.setVisibility(View.VISIBLE);
            holder.mTrackBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.buttonClicked();
                    }
                }
            });
        } else {
            holder.mTitle.setText(key);
            holder.mDetails.setVisibility(View.VISIBLE);
            holder.mDetails.setText(mData.get(position).get(key));
            holder.mTrackBtn.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.details)
        TextView mDetails;
        @BindView(R.id.track_btn)
        ImageButton mTrackBtn;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
