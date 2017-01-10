package com.datang.datangcarmanager.presentation.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.Node;

import java.util.List;

/**
 * Created by toby on 16/11/13.
 */
public class DepartmentTreeListAdapter extends AbstractTreeListAdapter {

    OnItemSelectListener mOnItemSelectListener;

    public DepartmentTreeListAdapter(Context context, List datas, int defaultExpandLevel) throws IllegalAccessException {
        super(context, datas, defaultExpandLevel);
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        mOnItemSelectListener = onItemSelectListener;
    }

    public void setData(List data) {
        super.setmAllNodes(data);
    }

    @Override
    public View getConvertView(final Node node, final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.department_list_item, parent, false);
            holder = new ViewHolder();
            holder.mImageButton = (ImageButton) convertView.findViewById(R.id.tree_list_item_icon);
            holder.departmentLabel = (TextView) convertView.findViewById(R.id.department_name_tv);
            holder.mImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = v.getTag().toString();
                    expandOrCollapse(Integer.parseInt(id));
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (node.getIcon() == -1) {
            holder.mImageButton.setVisibility(View.INVISIBLE);
        } else {
            holder.mImageButton.setVisibility(View.VISIBLE);
            holder.mImageButton.setImageResource(node.getIcon());
        }
        holder.mImageButton.setTag(String.valueOf(node.getId()));
        holder.departmentLabel.setText(node.getName());
        convertView.setId(node.getId());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemSelectListener.onItemClick(v.getId());
            }
        });
        return convertView;
    }

    private class ViewHolder {
        ImageButton mImageButton;
        TextView departmentLabel;
    }
}
