package com.datang.datangcarmanager.presentation.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.amap.api.maps2d.AMapException;
import com.amap.api.maps2d.Projection;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.MarkerOptions;
import com.datang.datangcarmanager.R;

import java.util.ArrayList;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presentation.view
 * @class describe
 * @anthor toby
 * @time 2016/12/2 下午2:19
 * @change
 * @chang time
 * @class describe
 */
public class MarkerCluster {

    private Context mContext;
    private MarkerOptions options;
    private ArrayList<MarkerOptions> includeMarkers;
    private LatLngBounds bounds;// 创建区域

    /**
     *
     * @param context
     * @param firstMarkers
     * @param projection
     * @param gridSize
     * 区域大小参数
     */
    public MarkerCluster(Context context, MarkerOptions firstMarkers,
                         Projection projection, int gridSize) {
        options = new MarkerOptions();
        mContext = context;
        Point point = projection.toScreenLocation(firstMarkers.getPosition());
        Point southwestPoint = new Point(point.x - gridSize, point.y + gridSize);
        Point northeastPoint = new Point(point.x + gridSize, point.y - gridSize);
        try {
            bounds = new LatLngBounds(
                    projection.fromScreenLocation(southwestPoint),
                    projection.fromScreenLocation(northeastPoint));
        } catch (AMapException e) {
            e.printStackTrace();
        }
        options.anchor(0.5f, 0.5f).title(firstMarkers.getTitle())
                .position(firstMarkers.getPosition())
                .icon(firstMarkers.getIcon())
                .snippet(firstMarkers.getSnippet());
        includeMarkers = new ArrayList<MarkerOptions>();
        includeMarkers.add(firstMarkers);
    }


    /**
     * 添加marker
     */
    public void addMarker(MarkerOptions markerOptions) {
        includeMarkers.add(markerOptions);// 添加到列表中
    }

    /**
     * 设置聚合点的中心位置以及图标
     */
    public void setPositionAndIcon() {
        int size = includeMarkers.size();
        double lat = 0.0;
        double lng = 0.0;
        if (size == 1) {
            return;
        } else {
            // 聚合的时候
            //设置marker聚合属性
            for (MarkerOptions op : includeMarkers) {
                lat += op.getPosition().latitude;
                lng += op.getPosition().longitude;
            }
            // 设置marker的位置为中心位置为聚集点的平均位置
            options.position(new LatLng(lat / size, lng / size));
            options.anchor(.5f, 1f);
            options.title("MarkerCluster");
            options.icon(BitmapDescriptorFactory
                    .fromBitmap(getViewBitmap(getView(size))));
        }
    }

    /**
     * marker试图
     */
    public View getView(int num) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.marker_cluster, null);
        /** 数量 */
        TextView txt_num = (TextView) view
                .findViewById(R.id.marker_size);
        txt_num.setText(String.valueOf(num));
        return view;
    }

    /**
     * 把一个view转化成bitmap对象
     */
    public static Bitmap getViewBitmap(View view) {
        Bitmap bitmap = null;
        try {
            if (view != null) {
                view.measure(
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                view.layout(0, 0, view.getMeasuredWidth(),
                        view.getMeasuredHeight());
                view.buildDrawingCache();
                bitmap = view.getDrawingCache();
            }
        } catch (Exception e) {
        }

        return bitmap;
    }

    public LatLngBounds getBounds() {
        return bounds;
    }

    public MarkerOptions getOptions() {
        return options;
    }

    public void setOptions(MarkerOptions options) {
        this.options = options;
    }
}
