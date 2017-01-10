package com.datang.datangcarmanager.presentation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.utils.DensityUtil;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presentation.view
 * @class describe
 * @anthor toby QQ:1032006226
 * @time 16/11/16 上午8:24
 * @change
 * @chang time
 * @class describe
 */
public class VoltageAndRPMImageView extends ImageView{

    private static final float DEFAULT_TEXT_SIZE = 16f;

    private Context mContext;

    private Paint paint;

    private float width;
    private float height;

    /* 当前电压 */
    private float currentVoltage = 0f;
    /* 当前转速 */
    private float currentRPM = 0f;

    public VoltageAndRPMImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        invalidate();
    }

    public void setData(float voltage, float rpm) {
        currentVoltage = voltage;
        currentRPM = rpm;
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        width = this.getWidth();
        height = this.getHeight();
        paint.setColor(mContext.getResources().getColor(R.color.instrument_text_defult_color));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(DensityUtil.sp2px(mContext, DEFAULT_TEXT_SIZE));
        canvas.drawText("电瓶电压", width * 0.15f, height * 0.68f, paint);
        canvas.drawText("发动机转速", width * 0.514f, height * 0.68f, paint);
        paint.setColor(mContext.getResources().getColor(R.color.black));
        /* 添加判断条件动态显示数据 */
        if (currentVoltage != 0) {
            canvas.drawText((float)(Math.round(currentVoltage * 100)) / 100 + " V", width * 0.15f, height * 0.33f, paint);
        } else {
            canvas.drawText("-- V", width * 0.15f, height * 0.33f, paint);
        }
        if ((int)currentRPM != 0) {
            canvas.drawText((int)currentRPM + " rpm", width * 0.514f, height * 0.33f, paint);
        } else {
            canvas.drawText("0 rpm", width * 0.514f, height * 0.33f, paint);
        }

    }

}
