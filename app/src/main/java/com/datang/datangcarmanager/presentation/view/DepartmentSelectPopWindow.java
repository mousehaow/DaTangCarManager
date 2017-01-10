package com.datang.datangcarmanager.presentation.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.Department;
import com.datang.datangcarmanager.model.Node;
import com.datang.datangcarmanager.presentation.adapter.AbstractTreeListAdapter.OnItemSelectListener;
import com.datang.datangcarmanager.presentation.adapter.DepartmentTreeListAdapter;

import java.util.List;

/**
 * Created by toby on 16/11/13.
 */
public class DepartmentSelectPopWindow extends PopupWindow {

    private Context mContext;
    private ListView mListView;
    private DepartmentTreeListAdapter mAdapter;
    private OnItemSelectListener mItemSelectListener;
    private List<Department> mDepartmentList;

    public DepartmentSelectPopWindow(Context context, List<Department> datas) {
        super(context);

        mContext = context;
        mDepartmentList = datas;
        init();
    }

    public void setItemListener(OnItemSelectListener listener){
        mItemSelectListener = listener;
    }

    public void setmDepartmentList(List<Department> mDepartmentList) {
        this.mDepartmentList = mDepartmentList;
        mAdapter.setData(this.mDepartmentList);
        mAdapter.notifyDataSetChanged();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.department_select_pop_window, null);
        setContentView(view);
        setWidth(LayoutParams.WRAP_CONTENT);
        setHeight(LayoutParams.WRAP_CONTENT);

        setFocusable(true);

        mListView = (ListView) view.findViewById(R.id.tree_list_view);
        try {
            mAdapter = new DepartmentTreeListAdapter(mContext, mDepartmentList, 0);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mListView.setAdapter(mAdapter);
        mAdapter.setOnItemSelectListener(new OnItemSelectListener() {
            @Override
            public void onItemClick(int id) {
                dismiss();
                if (mItemSelectListener != null){
                    mItemSelectListener.onItemClick(id);
                }
            }
        });
    }
}
