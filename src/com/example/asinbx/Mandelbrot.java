package com.example.asinbx;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.View;

public class Mandelbrot extends ActionBarActivity{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(new Mandelbrota(this));
	}
	
	@SuppressLint("DrawAllocation") 
	public class Mandelbrota extends View {
	        public Mandelbrota(Context context) {
	            super(context, null);   
	        }

	        public Mandelbrota(Context context, AttributeSet attrs) {
	            super(context, attrs);
	        }
	        Point pa = new Point(10, 10);
	        Point pb = new Point(20, 40);

	        Point po = new Point();
	        int centerX, centerY;
	        public void onSizeChanged(int w, int h, int oldw, int oldh) {
	            centerX = w / 2;
	            centerY = h / 2;
	            po.set(centerX, centerY);
	            super.onSizeChanged(w, h, oldw, oldh);
	        }
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
	                // �����ĵ�����
	                // �ȼ��������ǰ���ĵ������ֵ
	                //String centerString = "(" + (po.x-centerX) / 2 + ","
	                //       + (centerY-po.y ) / 2 + ")";
	                // Ȼ����ʾ����
	                //canvas.drawText(st, centerX - 25, centerY + 15, paint);
	                //invalidate();
	            }
	            double zx,zy,cx,cy,tmpx;
		        int[] color={
		                Color.rgb(255,0,0),Color.rgb(255,125,0),
		                Color.rgb(255,255,0),Color.rgb(0,255,0),
		                Color.rgb(0,0,255),Color.rgb(0,255,255),
		                Color.rgb(255,0,255),Color.BLACK};//��Ȼ���������rgb��ʽ
		        for(int i=0;i<640;i++){
		           cx = -2.1 + (1.1 - -2.1) * (i / 640.0);
		           for(int j=0;j<480;j++){
		              zx=0;zy=0;
		              cy=-1.2 + (1.2 - -1.2) * (j / 480.0);
		              int iter=400;
				      while (zx * zx + zy * zy < 4 && iter > 0) {//����С��2
				      tmpx = zx * zx - zy * zy + cx;//���ͺ��ʵ��
				      zy = 2.0 * zx * zy + cy;//�鲿
				      zx = tmpx;
				      iter--;
				      }
				      paint.setColor(color[7-iter%8]);
				      canvas.drawPoint((float)(j),(float)(i),paint);
				                /*switch(iter%8){
				                case 0:paint.setColor(Color.BLACK);canvas.drawPoint((float)(j),(float)(i),paint);break;
				                case 1:paint.setColor(Color.rgb(255,0,0));canvas.drawPoint((float)(j),(float)(i),paint);break;
				                case 2:paint.setColor(Color.rgb(255,125,0));canvas.drawPoint((float)(j),(float)(i),paint);break;
				                case 3:paint.setColor(Color.rgb(255,255,0));canvas.drawPoint((float)(j),(float)(i),paint);break;
				                case 4:paint.setColor(Color.rgb(0,255,0));canvas.drawPoint((float)(j),(float)(i),paint);break;
				                case 5:paint.setColor(Color.rgb(0,0,255));canvas.drawPoint((float)(j),(float)(i),paint);break;
				                case 6:paint.setColor(Color.rgb(0,255,255));canvas.drawPoint((float)(j),(float)(i),paint);break;
				                case 7:paint.setColor(Color.rgb(255,0,255));canvas.drawPoint((float)(j),(float)(i),paint);break;
				                }*/
				                //if(iter%11==0)canvas.drawPoint((float)(j),(float)(i),paint);
				                //if(iter%7==0){paint.setColor(Color.BLUE);canvas.drawPoint((float)j,(float)i,paint);};
		           }
		        }
	        }


	        /**
	         * �������� ���ڻ�������ļ�ͷ
	         */
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
	    }
}
