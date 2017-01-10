package com.datang.datangcarmanager.presentation.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.utils.DensityUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.presentation.view
 * @class describe
 * @anthor toby QQ:1032006226
 * @time 16/11/15 下午2:34
 * @change
 * @chang time
 * @class describe
 */
public class InstrumentImageView extends ImageView {

    private static final float DEFAULT_TEXT_SIZE = 16f;
    private static final float BIG_TEXT_SIZE = 22f;

    private Context mContext;

    private Paint paint;

    private VoltageAndRPMImageView mVoltageAndRPMImageView;

    /* 视图宽 */
    private float width;
    /* 视图高 */
    private float height;
    /* 速度仪表盘圆心 */
    private float centerX;
    private float centerY;
    /* 左边仪表盘圆心 */
    private float centerXLeft;
    private float centerYLeft;
    /* 右边仪表盘圆心 */
    private float centerXRight;
    private float centerYRight;
    /* 仪表盘半径 */
    private float radius;
    /* 仪表盘宽 */
    private int ringWidth;
    /* 速度表指针角度 */
    private float speedAngle = 0f;
    /* 电压表指针角度 */
    private float voltageAngle = 0f;
    /* 转速表指针角度 */
    private float rpmAngle = 0f;

    /* 当前速度 */
    private float currentSpeed = 0f;
    /* 当前电压 */
    private float currentVoltage = 0f;
    /* 当前转速 */
    private float currentRPM = 0f;
    /* 当前油耗 */
    private float currentOilConsumption = 0f;

    /* 目标速度 */
    private float targetSpeed;
    /* 目标电压 */
    private float targetVoltage;
    /* 目标转速 */
    private float targetRPM;

    private float stepSpeed;
    private float stepVoltage;
    private float stepRPM;

    private Timer timer;
    private TimerTask task;
    private TimerTask backTask;
    private int timeCount;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                timeCount++;
                if ((targetSpeed - currentSpeed) >= 1 || (targetSpeed - currentSpeed) <= -1) {
                    currentSpeed += stepSpeed;
                }
                if ((targetVoltage - currentVoltage) >= 1 || (targetVoltage - currentVoltage) <= -1){
                    currentVoltage += stepVoltage;
                }
                if ((targetRPM - currentSpeed) >= 1 || (targetRPM - currentSpeed) <= -1) {
                    currentRPM += stepRPM;
                }
                if (timeCount == 17) {
                    currentSpeed = targetSpeed;
                    currentVoltage = targetVoltage;
                    currentRPM = targetRPM;
                    reflashData();
                    stopTimer();
                    startTimer();
                    if (!(currentSpeed == 0 && currentVoltage == 0 && currentRPM == 0)){
                        timer.schedule(backTask, 10000);
                    } else {

                    }
                } else {
                    reflashData();
                }
            }
            if (msg.what == 2) {
                setData(0, 0, 0, 0);
            }
        }
    };

    private Bitmap largeIndicator;
    private Bitmap smallIndicator;

    public InstrumentImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);

        largeIndicator = BitmapFactory.decodeResource(getResources(), R.drawable.indicator_large);
        smallIndicator = BitmapFactory.decodeResource(getResources(), R.drawable.indicator_s2);

        invalidate();
    }

    private void startTimer() {
        if (timer == null) {
            timer = new Timer();
        }
        if (task == null) {
            task = new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            };
        }
        if (backTask == null) {
            backTask = new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                }
            };
        }
        timeCount = 0;
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
        if (backTask != null) {
            backTask.cancel();
            backTask = null;
        }
    }

    public void setVoltageAndRPMImageView(VoltageAndRPMImageView voltageAndRPMImageView) {
        mVoltageAndRPMImageView = voltageAndRPMImageView;
    }

    public void setData(int speed, float voltage, int rpm, float oilConsumption) {
        stopTimer();
        startTimer();
        targetSpeed = speed;
        targetVoltage = voltage;
        targetRPM = rpm;
        if (targetSpeed <= 10 && currentSpeed <= 10) {
            stepSpeed = 0;
            currentSpeed = targetSpeed;
        } else {
            stepSpeed = (targetSpeed - currentSpeed) / 16;
        }
        stepVoltage = (targetVoltage - currentVoltage) / 16;
        stepRPM = (targetRPM - currentRPM) / 16;
        currentOilConsumption = oilConsumption;
        timer.schedule(task, 0, 62);
    }

    private void reflashData() {
        if (currentSpeed <= 10) {
            speedAngle = 0;
        } else {
            speedAngle = (currentSpeed - 10) * 2;
        }
        voltageAngle = currentVoltage / 50 * 240;
        rpmAngle = currentRPM / 9000 * 240;
        mVoltageAndRPMImageView.setData(currentVoltage, currentRPM);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * getDrawable().getIntrinsicHeight() / getDrawable().getIntrinsicWidth();
        setMeasuredDimension(width, height);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        width = this.getWidth();
        height = this.getHeight();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mContext.getResources().getColor(R.color.instrument_text_defult_color));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(DensityUtil.sp2px(mContext, DEFAULT_TEXT_SIZE));
        canvas.drawText("当前速度", width * 0.87f, height * 0.294f, paint);
        canvas.drawText("当前油耗", width * 0.87f, height * 0.555f, paint);
        centerX = width * 0.32986003f;
        centerY = height * 0.42704478f;

        /* 添加判断条件动态显示数据 */
        paint.setColor(mContext.getResources().getColor(R.color.green));
        if (currentOilConsumption == 0) {
            canvas.drawText("-- L", width * 0.87f, height * 0.415f, paint);
        } else {
            canvas.drawText((float)(Math.round(currentOilConsumption * 100)) / 100 + " L", width * 0.87f, height * 0.415f, paint);
        }
        paint.setColor(mContext.getResources().getColor(R.color.light_blue));
        paint.setTextSize(DensityUtil.sp2px(mContext, BIG_TEXT_SIZE));
        canvas.drawText((int)currentSpeed + " Km/h", width * 0.87f, height * 0.138f, paint);

        /* 电压转速仪表盘绘制 */
        centerXLeft = width * 0.1518519f;
        centerYLeft = height * 0.87906473f;
        radius = width * 0.11876f;
        ringWidth = (int)(width * 0.030078f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(ringWidth);
        RectF rectLeft = new RectF(centerXLeft - (radius + 1 + ringWidth / 2),
                centerYLeft - (radius + 1 + ringWidth / 2),
                centerXLeft + (radius + 1 + ringWidth / 2),
                centerYLeft + (radius + 1 + ringWidth / 2));
        canvas.drawArc(rectLeft, 149, 242, false, paint);
        centerXRight = width * 0.51206f;
        centerYRight = height * 0.87696473f;
        RectF rectRight = new RectF(centerXRight - (radius + 1 + ringWidth / 2),
                centerYRight - (radius + 1 + ringWidth / 2),
                centerXRight + (radius + 1 + ringWidth / 2),
                centerYRight + (radius + 1 + ringWidth / 2));
        canvas.drawArc(rectRight, 149, 242, false, paint);
        paint.setColor(mContext.getResources().getColor(R.color.red));
        canvas.drawArc(rectLeft, 149, 1 + voltageAngle, false, paint);
        canvas.drawArc(rectRight, 149, 1 + rpmAngle, false, paint);

        /* 绘制三个表盘指针 */
        Matrix mainMatrix = new Matrix();
        mainMatrix.setTranslate(centerX - largeIndicator.getWidth() * 0.5f,
                centerY - largeIndicator.getHeight() * 0.644688f);
        mainMatrix.postRotate(230f + speedAngle, centerX, centerY);
        canvas.drawBitmap(largeIndicator, mainMatrix, paint);
        Matrix leftMatrix = new Matrix();
        leftMatrix.setTranslate(centerXLeft - smallIndicator.getWidth() * 0.5f,
                centerYLeft - smallIndicator.getHeight() * 0.8f);
        leftMatrix.postRotate(239f + voltageAngle, centerXLeft, centerYLeft);
        canvas.drawBitmap(smallIndicator, leftMatrix, paint);
        Matrix rightMatrix = new Matrix();
        rightMatrix.setTranslate(centerXRight - smallIndicator.getWidth() * 0.5f,
                centerYRight - smallIndicator.getHeight() * 0.8f);
        rightMatrix.postRotate(239f + rpmAngle, centerXRight, centerYRight);
        canvas.drawBitmap(smallIndicator, rightMatrix, paint);
    }
}
