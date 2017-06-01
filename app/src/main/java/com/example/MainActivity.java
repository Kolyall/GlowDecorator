package com.example;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import by.unick.glowdecorator.GlowDecorator;

public class MainActivity extends AppCompatActivity {

    private View mFrameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrameView = findViewById(R.id.dialog_frame);
        GlowDecorator.get(this)
                .glowRadius(32)
                .margin(60)
                .glowColor(ContextCompat.getColor(this,R.color.glowColor))
                .applyTo(mFrameView);
    }
}
