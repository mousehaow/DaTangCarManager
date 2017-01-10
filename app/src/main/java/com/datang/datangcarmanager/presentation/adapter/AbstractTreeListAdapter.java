package com.datang.datangcarmanager.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.datang.datangcarmanager.model.Node;
import com.datang.datangcarmanager.utils.TreeHelper;
import java.util.List;

/**
 * Created by toby on 16/11/13.
 */
public abstract  class AbstractTreeListAdapter<T> extends BaseAdapter {
    public static interface OnItemSelectListener{
        public void onItemClick(int id);
    };

    protected Context mContext;
    protected List<Node> mAllNodes;
    protected List<Node> mVisableNodes;
    protected LayoutInflater mInflater;


    public AbstractTreeListAdapter(Context context, List<T> datas, int defaultExpandLevel) throws IllegalAccessException {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mAllNodes = TreeHelper.getSortedNodes(datas,defaultExpandLevel);
        mVisableNodes = TreeHelper.filterVisableNodes(mAllNodes);
    }

    public void setmAllNodes(List<T> mAllNodes) {
        try {
            this.mAllNodes = TreeHelper.getSortedNodes(mAllNodes,0);
            mVisableNodes = TreeHelper.filterVisableNodes(this.mAllNodes);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void expandOrCollapse(int id) {

        for (Node n : mVisableNodes) {
            if (n.getId() == id) {
                if (n.isLeaf())
                    return;
                n.setExpand(!n.isExpand());
                mVisableNodes = TreeHelper.filterVisableNodes(mAllNodes);
                notifyDataSetChanged();
            }
        }
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mVisableNodes.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mVisableNodes.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = mVisableNodes.get(position);
        convertView = getConvertView(node, position, convertView, parent);
        convertView.setPadding(node.getLevle() * 30, 3, 3, 3);
        return convertView;
    }

    public abstract View getConvertView(Node node, int position, View convertView, ViewGroup parent);
}
