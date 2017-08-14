package com.example.asusnb.myapplication;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        logo=(ImageView)findViewById(R.id.imageLogo) ;
        logo.setOnClickListener(btnClock);

    }
    private Button.OnClickListener btnClock = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }
    };
}
