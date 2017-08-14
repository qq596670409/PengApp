package me.peng.pengapp.diary.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.os.Build;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/8/7.
 */

public class LinedEditText extends AppCompatEditText {
    public LinedEditText(Context context) {
        super(context);
        initPaint();
    }

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public LinedEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onDraw(Canvas canvas) {

        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.LTGRAY);

        PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 5);
        mPaint.setPathEffect(effects);

        int left = getLeft();
        int right = getRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int height = getHeight();
        int lineHeight = getLineHeight();
        int spcingHeight = (int) getLineSpacingExtra();
        int count = (height - paddingTop - paddingBottom) / lineHeight;

        for (int i = 0; i < count; i++){
            int baseline = lineHeight * (i + 1) + paddingTop - spcingHeight / 2;
            canvas.drawLine(paddingLeft,
                    (int)(baseline * 1.0),
                    right - paddingRight * (int)1.8,
                    (int)(baseline * 1.0), mPaint);
        }
        super.onDraw(canvas);
    }
}
