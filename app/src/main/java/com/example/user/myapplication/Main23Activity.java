package com.example.user.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main23Activity extends AppCompatActivity {

    private String result="";
    private char[] numAnswer = {};
    private char[] numInput = {};
    int number_big,mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);

        Intent intent = this.getIntent();
        Bundle bundle= intent.getExtras();
        mode=bundle.getInt("mode");
        number_big=mode;

        numAnswer = GenNum().toCharArray();





        Button guessBtn = (Button)findViewById(R.id.guessBtn);

        guessBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){

                int countA=0;
                int countB=0;


                EditText inputEt = (EditText) findViewById(R.id.inputEt);
                numInput = inputEt.getText().toString().toCharArray();


                for(int i=0; i<number_big; i++){
                    for(int j=0;j<number_big;j++){
                        if(numInput[i] == numAnswer[j]){
                            if(i==j)
                                countA+=1;
                            else
                                countB+=1;
                        }
                    }
                }


                result="=>"+countA+"A"+countB+"B";

                if( countA == number_big){
                    new AlertDialog.Builder(Main23Activity.this)
                            .setTitle("答對了!!")
                            .setIcon(R.mipmap.ic_launcher)
                            .setMessage("是否再來一局?")
                            .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            } )
                            .setNegativeButton("不要", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent2 = new Intent();
                                    intent2.setClass(Main23Activity.this, Main2Activity.class);
                                    startActivity(intent2);
                                }
                            })
                            .show();
                }

                TextView resultTv = (TextView)findViewById(R.id.resultTv);
                resultTv.setText(inputEt.getText().toString()+result);

                inputEt.setText("");
            }
        });
    }




    public String GenNum(){
        String number = "0123456789";
        String numAnswer = "";



        for(int i=0; i< number_big; i++){

            int randNum =(int)(Math.random()*(10-i));

            String randStr = number.substring(randNum, randNum+1);

            number = number.replace(randStr, "");

            numAnswer += randStr;
        }


        TextView tv = (TextView)findViewById(R.id.textView1);
        tv.setText(tv.getText().toString()+"( "+ numAnswer + " )");

        return numAnswer;
    }
}
