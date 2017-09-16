package com.example.asinbx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	SeekBar seekBar01,seekBar02;
	TextView tv01,tv02;
	MyApplication myApp;
	final int mandelbrot=111;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        seekBar01=(SeekBar) findViewById(R.id.sb01);
        seekBar02=(SeekBar) findViewById(R.id.sb02);
        tv01=(TextView) findViewById(R.id.tv01);
        tv02=(TextView) findViewById(R.id.tv02);
 //获取MyApplication对象       
        myApp=(MyApplication)getApplication();
        
        seekBar01.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
     	   public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser){
     		  tv01.setText("a为"+progress);
     		  myApp.seta(progress);  		  
     	   }
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO 自动生成的方法存根
		}
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO 自动生成的方法存根
		}
        });
        seekBar02.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
       	   public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser){
       		 tv02.setText("b为"+(double)progress/100);
       		 myApp.setb((double)progress/10);
       	   }
 		@Override
 		public void onStartTrackingTouch(SeekBar seekBar) {
 			// TODO 自动生成的方法存根			
 		}
 		@Override
 		public void onStopTrackingTouch(SeekBar seekBar) {
 			// TODO 自动生成的方法存根			
 		}
        });      
	}
	
	public boolean onCreateOptionsMenu(android.view.Menu menu){
		
		menu.addSubMenu(0,mandelbrot,0,"show Mandelbrot");
		return super.onCreateOptionsMenu(menu);
	}
	public boolean onOptionsItemSelected(MenuItem mi){
		switch(mi.getItemId()){
		case mandelbrot:startActivity(new Intent(MainActivity.this,Mandelbrot.class));
		}
		return true;
	}
}
