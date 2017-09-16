package com.example.asinbx;

import android.app.Application;

public class MyApplication extends Application {

	 private double a,b;
	 private static MyApplication app;
 //定义返回MyApplication对象的方法    
	 public static MyApplication haha(){
			return app;}
     public void onCreate() {
             super.onCreate();
             app=this;
     }
     public double geta() {
             return a;
     }
     public double getb(){
    	 return b;
     }
     public void seta(double a) {
             this.a=a;
     }
     public void setb(double b){
    	 this.b=b;  
     }    
}
