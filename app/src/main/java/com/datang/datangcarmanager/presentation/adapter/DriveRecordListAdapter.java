package com.datang.datangcarmanager.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.DriveRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presentation.adapter
 * @class describe
 * @anthor toby QQ:52684056
 * @time 16/11/18 上午10:37
 * @change
 * @chang time
 * @class describe
 */
public class DriveRecordListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    private List<DriveRecord.SegListBean> mDriveRecords;
    private SimpleDateFormat formatter;

    public DriveRecordListAdapter(Context context, List<DriveRecord.SegListBean> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mDriveRecords = datas;
        formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    }


    @Override
    public DriveRecordListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.drive_record_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        DriveRecordListAdapter.ViewHolder holder = (DriveRecordListAdapter.ViewHolder)viewHolder;
        DriveRecord.SegListBean driveRecord = mDriveRecords.get(position);
        holder.mDriveRecordItemStartTime.setText(driveRecord.getStartTime().substring(11, 17));
        holder.mDriveRecordItemEndTime.setText(driveRecord.getEndTime().substring(11, 17));
        holder.mDriveRecordItemPlaceFrom.setText(driveRecord.getStartLocation());
        holder.mDriveRecordItemPlaceTo.setText(driveRecord.getEndLocation());
        holder.mDriveRecordItemDistance.setText("行程: " + driveRecord.getMileAge() + "Km");
        if (position != mDriveRecords.size() - 1) {
            StringBuilder stopTime = new StringBuilder();
            int day, hours, min;
            Date startTime = new Date();
            Date endTime = startTime;
            try {
                startTime = formatter.parse(driveRecord.getEndTime());
                endTime = formatter.parse(mDriveRecords.get(position + 1).getStartTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int time = (int)(endTime.getTime() - startTime.getTime()) / 1000;
            Log.i("Adapter", "time:" + time);
            if ((day = time / 86400) > 0) {
                stopTime.append(day + "天");
                time = time % 86400;
            }
            if ((hours = time / 3600) > 0) {
                stopTime.append(hours + "小时");
                time = time % 3600;
            }
            if ((min = time / 60) > 0) {
                stopTime.append(min + "分钟");
            }
            holder.mDriveRecordItemStopTime.setText("停留: " + stopTime.toString());
        } else {
            holder.mDriveRecordItemStopTime.setText("");
        }
    }


    @Override
    public int getItemCount() {
        return mDriveRecords.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.drive_record_item_start_time)
        TextView mDriveRecordItemStartTime;
        @BindView(R.id.drive_record_item_place_from)
        TextView mDriveRecordItemPlaceFrom;
        @BindView(R.id.drive_record_item_end_time)
        TextView mDriveRecordItemEndTime;
        @BindView(R.id.drive_record_item_place_to)
        TextView mDriveRecordItemPlaceTo;
        @BindView(R.id.drive_record_item_distance)
        TextView mDriveRecordItemDistance;
        @BindView(R.id.drive_record_item_stop_time)
        TextView mDriveRecordItemStopTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
