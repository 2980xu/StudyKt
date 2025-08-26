package com.example.myapplication.measures;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class RectPointView extends View {
    private static final String TAG = "RectPointView";
    private Paint paint;
    private Rect rect1;
    private Rect rect2;
    private RectF rectF1;
    private RectF rectF2;
    private int mX = -1;
    private int mY = -1;
    private Path path1;
    private Path path2;

    public RectPointView(Context context) {
        super(context);
        initView();
    }

    public RectPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RectPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw mX: " + mX + " mY: " + mY);
//        drawRect(canvas);
//        drawPathRect(canvas);
//        drawText(canvas);
//        drawPathText(canvas);
//        drawRegions(canvas);
//        drawRegionOval(canvas);
        drawClip(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mX = (int) event.getX();
        mY = (int) event.getY();
        Log.d(TAG, "onTouchEvent mX: " + mX + " mY: " + mY);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            invalidate();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            mX = -1;
            mY = -1;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initView() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        rect1 = new Rect(100, 200, 400, 500);
        rect2 = new Rect(300, 200, 600, 500);
        rectF1 = new RectF(100, 200, 300, 400);
        rectF2 = new RectF(400, 200, 600, 400);
    }

    private void drawRect(Canvas canvas) {
        Log.d(TAG, "drawRect: ");
        if (rect1.contains(mX, mY)) {
            paint.setColor(Color.GREEN);
        } else {
            paint.setColor(Color.RED);
        }
        canvas.drawRect(rect1, paint);

        if (rect1.contains(mX, mY)) {
            paint.setColor(Color.BLUE);
        } else {
            paint.setColor(Color.YELLOW);
        }
        canvas.drawRect(rect2, paint);

//        if (rect1.contains(mX, mY)) {
//            point.setColor(Color.MAGENTA);
//            rect1.union(700,800);
//            canvas.drawRect(rect1,point);
//        }

//        boolean isIntersects = rect1.intersect(rect2);
//        Log.d(TAG, "onDraw: " + isIntersects);
//        point.setColor(Color.WHITE);
//        canvas.drawRect(rect1,point);

    }

    private void drawPathRect(Canvas canvas) {
        Log.d(TAG, "drawPathRect: ");
        Path path1 = new Path();
        path1.addRect(rectF1, Path.Direction.CCW);
        Path path2 = new Path();
        path2.addRect(rectF2, Path.Direction.CW);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setTextSize(35);
        String text = "苦心人天不负,有志者事竟成";
        canvas.drawTextOnPath(text, path1, 0, 18, paint);//逆时针方向生成
        canvas.drawTextOnPath(text, path2, 0, 18, paint);//顺时针方向生成
    }

    private void drawText(Canvas canvas) {
        Log.d(TAG, "drawText: ");
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setTextSize(100);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        String text = "苦心人天不负,有志者事竟成";
        canvas.drawText(text, 100, 100, paint);
    }

    private void drawPathText(Canvas canvas) {
        Log.d(TAG, "drawPathText: ");
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        paint.setTextSize(45);
        paint.setStyle(Paint.Style.STROKE);

        Path path1 = new Path();
        path1.addCircle(200, 200, 100, Path.Direction.CCW);
        canvas.drawPath(path1, paint);


        Path path2 = new Path();
        path2.addCircle(500, 200, 100, Path.Direction.CCW);
        canvas.drawPath(path2, paint);


        String text = "苦心人天不负,有志者事竟成";
        canvas.drawTextOnPath(text, path1, 0, 35, paint);
        canvas.drawTextOnPath(text, path2, 0, 0, paint);
    }

    private void drawRegions(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);

        Region region = new Region(new Rect(50, 50, 200, 100));
        drawRegion(canvas, region, paint);
    }

    private void drawRegionOval(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        RectF rect = new RectF(50, 50, 200, 500);
        path.addOval(rect, Path.Direction.CCW);

        Region region = new Region(50, 50, 200, 500);
        region.setPath(path, region);
        drawRegion(canvas, region, paint);
    }

    private void drawRegion(Canvas canvas, Region rgn, Paint paint) {
        RegionIterator iter = new RegionIterator(rgn);
        Rect r = new Rect();
        while (iter.next(r)) {
            canvas.drawRect(r, paint);
        }
    }

    private void drawClip(Canvas canvas) {
        canvas.drawColor(Color.RED);
        //保存的画布大小为全屏幕大小
        canvas.save();

        canvas.clipRect(new Rect(100, 100, 800, 800));
        canvas.drawColor(Color.GREEN);
        //保存的画布大小为 Rect(100, 100, 800, 800)
        canvas.save();

        canvas.clipRect(new Rect(200, 200, 700, 700));
        canvas.drawColor(Color.BLUE);
        //保存的画布大小为 Rect(200, 200, 700, 700)
        canvas.save();

        canvas.clipRect(new Rect(300, 300, 600, 600));
        canvas.drawColor(Color.BLACK);
        //保存的画布大小为 Rect(300, 300, 600, 600)
        canvas.save();

        canvas.clipRect(new Rect(400, 400, 500, 500));
        canvas.drawColor(Color.WHITE);
    }
}
