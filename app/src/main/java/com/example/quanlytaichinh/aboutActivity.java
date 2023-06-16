package com.example.quanlytaichinh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class aboutActivity extends AppCompatActivity {
    ImageButton ibexit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ibexit = findViewById(R.id.ibexit);
        ibexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutActivity.super.onBackPressed();
//                System.exit(0);
//                Intent i = new Intent(aboutActivity.this,ThongKeActivity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.anim_enter,R.anim.anim_dira);
            }
        });
    }
}
