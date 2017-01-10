package com.datang.datangcarmanager.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.utils.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presentation.adapter
 * @class describe
 * @anthor toby
 * @time 2016/11/26 上午11:01
 * @change
 * @chang time
 * @class describe
 */
public class SettingsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int WEIBO_SHARE = 0;
    public static final int WECHAT_F_SHARE = 1;
    public static final int WECHAT_SHARE = 2;
    public static final int QQSPACE_SHARE = 3;

    public interface OnSettingsListItemClickedListener {
        public void itemClicked(int position);
        public void shareBtnClicked(int type);
    }

    public enum ITEM_TYPE {
        COMMON_ITEM,
        SHARE_ITEM
    }

    private Context mContext;
    private LayoutInflater mInflater;
    private String[] mTitles;
    private OnSettingsListItemClickedListener mListener;

    public SettingsListAdapter(Context context, String[] titles) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.mTitles = titles;
    }

    public void setListener(OnSettingsListItemClickedListener listener) {
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.COMMON_ITEM.ordinal()) {
            View view = mInflater.inflate(R.layout.settings_common_item, parent, false);
            CommonViewHolder viewHolder = new CommonViewHolder(view);
            return viewHolder;
        } else {
            View view = mInflater.inflate(R.layout.setting_share_item, parent, false);
            ShareViewHolder viewHolder = new ShareViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mTitles[position].equals("推荐分享")) {
            return ITEM_TYPE.SHARE_ITEM.ordinal();
        } else {
            return ITEM_TYPE.COMMON_ITEM.ordinal();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof CommonViewHolder) {
            CommonViewHolder holder = (CommonViewHolder)viewHolder;
            holder.mSettingNameTv.setText(mTitles[position]);
            if (position == 0 || position == 2) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)holder.itemView.getLayoutParams();
                layoutParams.setMargins(DensityUtil.dip2px(mContext, 8f),
                        DensityUtil.dip2px(mContext, 20f),
                        DensityUtil.dip2px(mContext, 8f), 0);
                holder.itemView.setLayoutParams(layoutParams);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.itemClicked(position);
                }
            });
        } else if (viewHolder instanceof ShareViewHolder) {
            ShareViewHolder holder = (ShareViewHolder)viewHolder;
            holder.mWeiboShareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.shareBtnClicked(WEIBO_SHARE);
                }
            });
            holder.mWechatFShareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.shareBtnClicked(WECHAT_F_SHARE);
                }
            });
            holder.mWechatShareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.shareBtnClicked(WECHAT_SHARE);
                }
            });
            holder.mQqspaceShartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.shareBtnClicked(QQSPACE_SHARE);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    static class CommonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.setting_name_tv)
        TextView mSettingNameTv;

        CommonViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ShareViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.weibo_share_btn)
        ImageButton mWeiboShareBtn;
        @BindView(R.id.wechat_f_share_btn)
        ImageButton mWechatFShareBtn;
        @BindView(R.id.wechat_share_btn)
        ImageButton mWechatShareBtn;
        @BindView(R.id.qqspace_shart_btn)
        ImageButton mQqspaceShartBtn;

        ShareViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
