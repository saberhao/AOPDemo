package com.saberhao.aopdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    public String test() {
        Log.d("AOPDemo","In test function(PointCut)");
        //测试@AfterThrowing时，手动抛出异常
        throw new NullPointerException("check pointcut throwing");
        //return "I am return";
    }
}
