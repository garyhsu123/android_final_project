package com.example.user.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    Button game1,game2,finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        game1 = (Button)findViewById(R.id.game1);
        game1.setOnClickListener(choice);
        game2 = (Button)findViewById(R.id.game2);
        game2.setOnClickListener(choice);
        finish = (Button)findViewById(R.id.button6);
        finish.setOnClickListener(choice);
    }
    private Button.OnClickListener choice = new Button.OnClickListener() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.game1:

                    intent.setClass(Main2Activity.this,Main22Activity.class);
                    int game1=1;
                    bundle.putInt("game",game1);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    break;

                    case R.id.game2:

                        intent.setClass(Main2Activity.this,Main22Activity.class);
                        int game2=2;
                        bundle.putInt("game",game2);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        break;

            }

        }
    };


}
