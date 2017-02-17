package com.firstcode.asimplenews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//第一行代码里面的简易的新闻面板，兼容平板和手机
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
