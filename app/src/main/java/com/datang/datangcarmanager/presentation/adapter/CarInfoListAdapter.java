package com.datang.datangcarmanager.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.CarInfo;
import com.datang.datangcarmanager.model.Vehicle;

import java.util.List;

/**
 * Created by toby on 16/11/14.
 */
public class CarInfoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    protected Context mContext;
    protected List<Vehicle> mCarInfos;
    protected LayoutInflater mInflater;

    public CarInfoListAdapter(Context context, List<Vehicle> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mCarInfos = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.car_list_item, parent, false);
        CarInfoListAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        CarInfoListAdapter.ViewHolder holder = (CarInfoListAdapter.ViewHolder)viewHolder;
        holder.carIcon.setImageResource(R.drawable.mercedes);
        holder.plateNum.setText(mCarInfos.get(position).getLpno());
        holder.carName.setText(mCarInfos.get(position).getIdName());
        holder.itemView.setTag(Integer.valueOf(position));
        switch (mCarInfos.get(position).getOnlineStatus()) {
            case Vehicle.CAR_STATE_MOVING:
                holder.carState.setText("状态:运动");
                holder.carState.setTextColor(mContext.getResources().getColor(R.color.green));
                break;
            case Vehicle.CAR_STATE_STOP:
                holder.carState.setText("状态:静止");
                holder.carState.setTextColor(mContext.getResources().getColor(R.color.light_blue));
                break;
            case Vehicle.CAR_STATE_OFFLINE:
                holder.carState.setText("状态:离线");
                holder.carState.setTextColor(mContext.getResources().getColor(R.color.grey_dark));
                break;
            default:
                break;
        }
    }



    @Override
    public int getItemCount() {
        return mCarInfos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView carIcon;
        TextView plateNum;
        TextView carName;
        TextView carState;

        public ViewHolder(View itemView) {
            super(itemView);
            carIcon = (ImageView) itemView.findViewById(R.id.car_icon);
            plateNum = (TextView) itemView.findViewById(R.id.plate_num_tv);
            carName = (TextView) itemView.findViewById(R.id.car_name_tv);
            carState = (TextView) itemView.findViewById(R.id.car_state_tv);
        }
    }

}
