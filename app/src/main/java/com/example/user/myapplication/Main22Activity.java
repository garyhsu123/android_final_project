package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class Main22Activity extends AppCompatActivity {

    private TextView show;
    private Button go;
    int game;
    private LinearLayout linearlayout1,linearlayout2;
    RadioGroup radioGroup1 ;
    RadioGroup radioGroup2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        show= (TextView)findViewById(R.id.show);
        linearlayout1 = (LinearLayout) findViewById(R.id.linearlayout1);
        linearlayout2 = (LinearLayout) findViewById(R.id.linearlayout2);
        radioGroup1 = (RadioGroup)findViewById(R.id.radio1);
        radioGroup2 = (RadioGroup)findViewById(R.id.radio2);

        Intent intent = this.getIntent();
        Bundle bundle= intent.getExtras();
        game=bundle.getInt("game");

        if(game==1){show.setText("終極密碼");}
        if(game==2){show.setText("猜數字");
            linearlayout1.setVisibility(View.GONE);
            linearlayout2.setVisibility(View.GONE);}

        go = (Button)findViewById(R.id.go);
        go.setOnClickListener(go_game);

    }





    private Button.OnClickListener go_game = new Button.OnClickListener(){

        Intent intent2 = new Intent();
        Bundle bundle2 = new Bundle();
        @Override
        public void onClick(View v) {

            RadioButton radioButton1 = (RadioButton)findViewById(radioGroup1.getCheckedRadioButtonId());
            RadioButton radioButton2 = (RadioButton)findViewById(radioGroup2.getCheckedRadioButtonId());


            if(game==1){intent2.setClass(Main22Activity.this, Main24Activity.class);}
            if(game==2){intent2.setClass(Main22Activity.this, Main23Activity.class);}

            String mode = radioButton1.getText().toString();
            String people = radioButton2.getText().toString();
            int out_mode=1;
            int out_people=1;

            if(mode.equals("簡單")){out_mode=3;}
            if(mode.equals("普通")){out_mode=4;}
            if(mode.equals("困難")){out_mode=5;}

            if(people.equals("1人")){out_people=1;}
            if(people.equals("2人")){out_people=2;}
            if(people.equals("3人")){out_people=3;}


            bundle2.putInt("people",out_people);
            bundle2.putInt("mode",out_mode);
            intent2.putExtras(bundle2);

            startActivity(intent2);

        }
    };
}
