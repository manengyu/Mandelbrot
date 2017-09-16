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
	                // 画中心点坐标
	                // 先计算出来当前中心点坐标的值
	                //String centerString = "(" + (po.x-centerX) / 2 + ","
	                //       + (centerY-po.y ) / 2 + ")";
	                // 然后显示坐标
	                //canvas.drawText(st, centerX - 25, centerY + 15, paint);
	                //invalidate();
	            }
	            double zx,zy,cx,cy,tmpx;
		        int[] color={
		                Color.rgb(255,0,0),Color.rgb(255,125,0),
		                Color.rgb(255,255,0),Color.rgb(0,255,0),
		                Color.rgb(0,0,255),Color.rgb(0,255,255),
		                Color.rgb(255,0,255),Color.BLACK};//红橙黄绿蓝靛紫rgb格式
		        for(int i=0;i<640;i++){
		           cx = -2.1 + (1.1 - -2.1) * (i / 640.0);
		           for(int j=0;j<480;j++){
		              zx=0;zy=0;
		              cy=-1.2 + (1.2 - -1.2) * (j / 480.0);
		              int iter=400;
				      while (zx * zx + zy * zy < 4 && iter > 0) {//根号小于2
				      tmpx = zx * zx - zy * zy + cx;//积和后的实部
				      zy = 2.0 * zx * zy + cy;//虚部
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
	         * 画三角形 用于画坐标轴的箭头
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
	            // 绘制这个多边形
	            canvas.drawPath(path, paint);
	        }   
	    }
}
