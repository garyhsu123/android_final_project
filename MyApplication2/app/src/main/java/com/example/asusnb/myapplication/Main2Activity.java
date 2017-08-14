package com.example.asusnb.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText beacon1;
    EditText beacon2;
    CheckBox check_beacon1;
    CheckBox check_beacon2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        beacon1 = (EditText) findViewById(R.id.editText1);
        beacon2 = (EditText) findViewById(R.id.editText2);
        check_beacon1 = (CheckBox)findViewById(R.id.checkBox1);
        check_beacon2 = (CheckBox)findViewById(R.id.checkBox2);

        check_beacon1.setOnCheckedChangeListener(listener);
        check_beacon1.setOnCheckedChangeListener(listener);
    }
    private  CheckBox.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener()

    {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(check_beacon1.isChecked()){
                Toast.makeText(Main2Activity.this,"you choose beacon1",Toast.LENGTH_SHORT).show();
            }
            if(check_beacon2.isChecked()){
                Toast.makeText(Main2Activity.this,"you choose beacon2",Toast.LENGTH_SHORT).show();
            }
        }
    };
}
