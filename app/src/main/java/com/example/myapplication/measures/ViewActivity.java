package com.example.myapplication.measures;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;

public class ViewActivity extends AppCompatActivity {
    private TextView tvHello;
    private ConstraintLayout conViewActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        tvHello = findViewById(R.id.tv_hello);
        conViewActivity = findViewById(R.id.con_viewactivity);
        ConstraintLayout.LayoutParams constraintLayout = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);
        RectPointView rectPointView = new RectPointView(this);
        constraintLayout.topToBottom = R.id.tv_hello;
        constraintLayout.startToEnd = R.id.tv_hello;
        conViewActivity.addView(rectPointView, constraintLayout);


    }
}
