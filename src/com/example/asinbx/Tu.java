package com.example.asinbx;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;



@SuppressLint({ "DrawAllocation", "ClickableViewAccessibility" }) public class Tu extends View {	
    public Tu(Context context) {
        super(context, null);       
    }
    public Tu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
//po为组件相对中心的坐标
    Point po = new Point();
//中心坐标 
    int centerX, centerY;

    /*
     * 控件创建完成之后，在显示之前都会调用这个方法，此时可以获取控件的大小 并得到中心坐标和坐标轴圆心所在的点。
     */
    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX = w / 2;
        centerY = h / 2;
        po.set(centerX, centerY);
        super.onSizeChanged(w, h, oldw, oldh);
    }
//重写onDraw
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

// 画坐标轴
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
 // 画直线
            canvas.drawLine(0, centerY, 2 * centerX, centerY, paint);
            canvas.drawLine(centerX, 0, centerX, 2 * centerY, paint);
// 画X轴箭头
            int x = 2 * centerX, y = centerY;
            drawTriangle(canvas, new Point(x, y), new Point(x - 10, y - 5),
                    new Point(x - 10, y + 5));
            canvas.drawText("X", x - 15, y + 18, paint);
// 画Y轴箭头
            x = centerX;
            y = 0;
            drawTriangle(canvas, new Point(x, y), new Point(x - 5, y + 10),
                    new Point(x + 5, y + 10));
            canvas.drawText("Y", x + 12, y + 15, paint);
//中心显示ab的值
            //String ab="("+MyApplication.haha().geta()+","+MyApplication.haha().getb()/10+")";
            //canvas.drawText(ab, centerX - 25, centerY + 15, paint);
            invalidate();
        }
        if (canvas != null) {
        	for(float x=-10f*(float)Math.PI;x<10f*(float)Math.PI;x+=0.01){
                drawPoint(canvas, paint, x,(float)Math.sin(x),(float)MyApplication.haha().getb(),(float)MyApplication.haha().geta());
            }
        }
    }   
    private void drawPoint(Canvas canvas,Paint paint,float x,float y){
        canvas.drawPoint(x+po.x,po.y-y,paint);
    }
//自定义绘制sinx方法
    private void drawPoint(Canvas canvas,Paint paint,float x,float y,float b,float a){
        drawPoint(canvas,paint,(float) (10.0/b*x),a*y);
    }
//自定义三角形箭头方法
    private void drawTriangle(Canvas canvas, Point p1, Point p2, Point p3) {
        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.close();

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
// 绘制这个多边形
        canvas.drawPath(path, paint);
    }
//记录拖动初始组件的位置坐标
    int x0, y0;
//拖动时自动回调该方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
//x,y为拖动时不断变化的坐标
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
//按下时x,y为初始坐标，赋值给x0,y0
        case MotionEvent.ACTION_DOWN: // 按下
            x0 = x;
            y0 = y;
            Log.i("down", "(" + x0 + "," + y0 + ")");
            break;
        case MotionEvent.ACTION_MOVE: // 拖动
// x-x0就是移动的距离，po.x为相对于中心的距离，相加就是现在相对于中心的距离
// 然后调用invalidate()让系统调用onDraw()刷新以下屏幕，即实现了坐标移动。
            po.x += x - x0;
            po.y += y - y0;
            x0 = x;
            y0 = y;
            Log.i("move", "(" + x0 + "," + y0 + ")");
            invalidate();//更新
            break;
        case MotionEvent.ACTION_UP: // 弹起
            break;
        }
        return true;       
    }
}

