package com.example.asinbx;

import android.app.Application;

public class MyApplication extends Application {

	 private double a,b;
	 private static MyApplication app;
 //���巵��MyApplication����ķ���    
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
