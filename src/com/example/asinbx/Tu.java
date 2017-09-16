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
//poΪ���������ĵ�����
    Point po = new Point();
//�������� 
    int centerX, centerY;

    /*
     * �ؼ��������֮������ʾ֮ǰ������������������ʱ���Ի�ȡ�ؼ��Ĵ�С ���õ����������������Բ�����ڵĵ㡣
     */
    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX = w / 2;
        centerY = h / 2;
        po.set(centerX, centerY);
        super.onSizeChanged(w, h, oldw, oldh);
    }
//��дonDraw
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

// ��������
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
 // ��ֱ��
            canvas.drawLine(0, centerY, 2 * centerX, centerY, paint);
            canvas.drawLine(centerX, 0, centerX, 2 * centerY, paint);
// ��X���ͷ
            int x = 2 * centerX, y = centerY;
            drawTriangle(canvas, new Point(x, y), new Point(x - 10, y - 5),
                    new Point(x - 10, y + 5));
            canvas.drawText("X", x - 15, y + 18, paint);
// ��Y���ͷ
            x = centerX;
            y = 0;
            drawTriangle(canvas, new Point(x, y), new Point(x - 5, y + 10),
                    new Point(x + 5, y + 10));
            canvas.drawText("Y", x + 12, y + 15, paint);
//������ʾab��ֵ
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
//�Զ������sinx����
    private void drawPoint(Canvas canvas,Paint paint,float x,float y,float b,float a){
        drawPoint(canvas,paint,(float) (10.0/b*x),a*y);
    }
//�Զ��������μ�ͷ����
    private void drawTriangle(Canvas canvas, Point p1, Point p2, Point p3) {
        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.close();

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
// ������������
        canvas.drawPath(path, paint);
    }
//��¼�϶���ʼ�����λ������
    int x0, y0;
//�϶�ʱ�Զ��ص��÷���
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
//x,yΪ�϶�ʱ���ϱ仯������
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
//����ʱx,yΪ��ʼ���꣬��ֵ��x0,y0
        case MotionEvent.ACTION_DOWN: // ����
            x0 = x;
            y0 = y;
            Log.i("down", "(" + x0 + "," + y0 + ")");
            break;
        case MotionEvent.ACTION_MOVE: // �϶�
// x-x0�����ƶ��ľ��룬po.xΪ��������ĵľ��룬��Ӿ���������������ĵľ���
// Ȼ�����invalidate()��ϵͳ����onDraw()ˢ��������Ļ����ʵ���������ƶ���
            po.x += x - x0;
            po.y += y - y0;
            x0 = x;
            y0 = y;
            Log.i("move", "(" + x0 + "," + y0 + ")");
            invalidate();//����
            break;
        case MotionEvent.ACTION_UP: // ����
            break;
        }
        return true;       
    }
}

