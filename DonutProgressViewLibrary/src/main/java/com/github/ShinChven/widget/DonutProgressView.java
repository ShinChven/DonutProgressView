package com.github.ShinChven.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.github.ShinChven.DonutProgressView.library.R;

/**
 * Created by ShinChven on 15/9/25.
 */
public class DonutProgressView extends View {

    private static final int STYLE_IN_CENTER = 0;
    private static final int STYLE_IN_FILL = 1;

    private String text = "30%";


    private float lineWidth = 20;

    private int maxProgress = 100;
    private int progress = 0;
    private int progressBackgroundColor = 0;
    private int progressColor = 0;
    // 设置半径
    int radius = 0;
    private float textSize = 60;
    private int textColor = Color.BLACK;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取控件高宽，以居中
        int x = getWidth();
        int y = getHeight();


        // 设置半径
        if (x > y) {
            radius = (int) ((y / 2) - lineWidth / 2);
        } else {
            radius = (int) ((x / 2) - lineWidth / 2);
        }

        // 设置画笔元素
        Paint paint = new Paint();
        // 开抗锯齿
        paint.setAntiAlias(true);
        // 设置样式为线条
        paint.setStyle(Paint.Style.STROKE);
        // 设置线条色
        paint.setColor(progressBackgroundColor);
        // 设置线条宽度
        paint.setStrokeWidth(lineWidth);
        // 画线条
        canvas.drawCircle(x / 2, y / 2, radius, paint);
        // 设置进度颜色
        paint.setColor(progressColor);
        // 设置填充，开始画圆头
        paint.setStyle(Paint.Style.FILL);
        // 画圆头
        int startX = x / 2;
        int startY = (int) ((y / 2 - radius));
        canvas.drawCircle(startX, startY, lineWidth / 2, paint);
        // 设置画线
        paint.setStyle(Paint.Style.STROKE);
        // 创建弧形位置属性
        RectF oval = new RectF();
        // 计算弧形居中
        oval.set(x / 2 - radius, y / 2 - radius, x / 2 + radius, y / 2 + radius);
        // 画弧形
        float sweepAngle = 360 * (((float) progress) / ((float) maxProgress));
        canvas.drawArc(oval, -90, sweepAngle, false, paint);


        // 设置填充
        paint.setStyle(Paint.Style.FILL);
        // 计算位置

        // 第二点
        double endX = startX + (radius) * Math.cos((sweepAngle - 90) * Math.PI / 180);
        double endY = startY + (radius) * Math.sin((sweepAngle - 90) * Math.PI / 180);
        canvas.drawCircle((float) endX, (float) endY + radius, lineWidth / 2, paint);

        // 画字
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        drawCenter(canvas, paint, text);

    }

    private void drawCenter(Canvas canvas, Paint paint, String text) {
        int cHeight = canvas.getClipBounds().height();
        int cWidth = canvas.getClipBounds().width();
        Rect r = new Rect();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }

    private void init() {
//        progressBackgroundColor = getContext().getResources().getColor(R.color.circle_progress_default_background_color);
//        progressColor = getContext().getResources().getColor(R.color.circle_progress_default_color);
    }

    public DonutProgressView(Context context) {
        super(context);
        init();
    }


    public DonutProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.zagyou_circle_progress);
        try {
            text = ta.getString(R.styleable.zagyou_circle_progress_text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (text == null) {
            text = "";
        }
        try {
            textColor = ta.getColor(R.styleable.zagyou_circle_progress_textColor, Color.BLACK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            textSize = ta.getDimension(R.styleable.zagyou_circle_progress_textSize, dip2px(context, 10));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            progress = ta.getInteger(R.styleable.zagyou_circle_progress_progress, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            maxProgress = ta.getInteger(R.styleable.zagyou_circle_progress_maxProgress, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            progressBackgroundColor = ta.getColor(R.styleable.zagyou_circle_progress_progressBackgroundColor, context
                    .getResources().getColor(R.color.circle_progress_default_background_color));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        try {
            progressColor = ta.getColor(R.styleable.zagyou_circle_progress_progressColor, context
                    .getResources().getColor(R.color.circle_progress_default_color));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        try {
            lineWidth = ta.getDimension(R.styleable.zagyou_circle_progress_lineWidth, dip2px(context, 10));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public float dip2px(Context context, float dpValue) {
        try {
            float scale = context.getResources().getDisplayMetrics().density;
            return (dpValue * scale + 0.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public float px2dip(Context context, float pxValue) {
        try {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (pxValue / scale + 0.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public DonutProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (TextUtils.isEmpty(text)) {
            this.text = "";
        } else {
            this.text = text;
        }
        invalidateView();
    }

    public void invalidateView() {
        try {
            invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void updateLineWidth(float lineWidth) {
        setLineWidth(lineWidth);
        invalidateView();
    }

    public int getProgressBackgroundColor() {
        return progressBackgroundColor;
    }

    public void setProgressBackgroundColor(int progressBackgroundColor) {
        this.progressBackgroundColor = progressBackgroundColor;
    }

    public void updateProgressBackgroundColor(int progressBackgroundColor) {
        setProgressBackgroundColor(progressBackgroundColor);
        invalidateView();
    }


    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public void updateProgressColor(int progressColor) {
        setProgressColor(progressColor);
        invalidateView();
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void updateTextSize(float textSize) {
        setTextSize(textSize);
        invalidateView();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void updateTextColor(int textColor) {
        setTextColor(textColor);
        invalidateView();
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public void updateMaxProgress(int maxProgress) {
        setMaxProgress(maxProgress);
        invalidateView();
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void updateProgress(int progress) {
        setProgress(progress);
        invalidateView();
    }
}
