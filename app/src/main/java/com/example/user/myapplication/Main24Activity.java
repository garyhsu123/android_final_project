package com.example.user.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main24Activity extends AppCompatActivity {
    EditText you_guess; //玩家猜的答案
    TextView play_number; //遊戲人數
    TextView set_level; //遊戲難度
    TextView start,end;
    TextView who_guess;
    ImageView head_picture;
    Button send_answer;// 玩家送出答案
    TextView guess_number;
    boolean set_init = false;
    int get_number=2;//接收遊戲人數
    int get_level=1;//接收遊戲難度
    int number;//遊戲人數
    int now_answer=0; //儲存目前的答案
    int play_1,play_2,play_3,play_4,play_5;
    int x=0,y=0; // 儲存始尾的數字
    int bomb=0;
    boolean your_turn = true;
    boolean gaming = false;
    int turn = 0;
    //static int x=0,y=100;
    Random ran = new Random();
    Handler handler = new Handler();
    Timer timer = new Timer();
    int abc = 0;
    int imgID[] = {R.drawable.me,R.drawable.img01,R.drawable.img02,R.drawable.img03,};
    //---------------------------------------------------------
    static float x1=0, y1=0;    // 原本圖片存在的X,Y軸位置
    static int  mx=0 ,my=0; // 圖片被拖曳的X ,Y軸距離長度
    ImageView viewtest;
    TextView text,text2;
    LinearLayout x7;
    static float height5;
    private MediaPlayer mediaPlayer;
    private  final  String SONGPATH = Environment.getExternalStorageDirectory().getPath() + "/";
    String[] songfile = new String[]{};
    ////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main24);

        Intent intent = this.getIntent();
        Bundle bundle= intent.getExtras();

        get_level=bundle.getInt("mode")-2;
        get_number=bundle.getInt("people");
///////////////////////////////////////////////////////////
//        //x7=(LinearLayout)findViewById(R.id.xy);
//
//
//
//
//
//        viewtest = (ImageView)findViewById(R.id.imageView_viewtest) ;
//        //text = (TextView)findViewById(R.id.textView);
//        //text2 = (TextView)findViewById(R.id.textView2);
//        viewtest.setOnTouchListener(listener);
//        //x7.setOnTouchListener(listener0);
        //----------------------------------------------

        you_guess = (EditText)findViewById(R.id.editText_guess);

        play_number = (TextView)findViewById(R.id.textview_play_number);
        set_level = (TextView)findViewById(R.id.textView_level);
        start = (TextView)findViewById(R.id.textView_start);
        end = (TextView)findViewById(R.id.textView_end);
        who_guess = (TextView)findViewById(R.id.textView_who_guess);
        send_answer = (Button) findViewById(R.id.button_send_answer);
        guess_number = (TextView)findViewById(R.id.textView_guess_number);
        head_picture = (ImageView)findViewById(R.id.imageView_headpicture) ;
        //if (!set_init) {
        init(); // 初始化
        //}
        send_answer.setOnClickListener(button_listener);

    }

    public void turn_over_cp() {

        //Toast.makeText(Main24Activity.this, "in turn_over_cp", Toast.LENGTH_SHORT).show();
        if(turn%number !=0){
            //Toast.makeText(Main24Activity.this, "turn%number= "+turn%number, Toast.LENGTH_SHORT).show();
            switch (turn%number){
                case 1:
                    who_guess.setText("player 1");
                    head_picture.setImageResource(imgID[turn%number]);
                    break;
                case 2:
                    who_guess.setText("player 2");
                    head_picture.setImageResource(imgID[turn%number]);
                    break;
                case 3:
                    who_guess.setText("player 3");
                    head_picture.setImageResource(imgID[turn%number]);
                    break;
            }
            final int cp_answer;
            cp_answer = ran.nextInt(y-x-1)+x+1;
            guess_number.setText(String.valueOf(cp_answer));

            handler.postDelayed(new Runnable(){

                @Override
                public void run() {

                    //Toast.makeText(Main24Activity.this, "cp: = "+turn%number+" / cp_ans"+cp_answer, Toast.LENGTH_SHORT).show();

                    if(detect_answer(cp_answer)==1){

                        turn_over_cp();
                    }

                }}, 2000);


        }else{
            who_guess.setText("you");
            head_picture.setImageResource(imgID[0]);
            you_guess.setText("");
            guess_number.setText("");
            Toast.makeText(Main24Activity.this, "現在輪到你, Range is "+x + " ~ " + y , Toast.LENGTH_SHORT).show();
        }
    }

    public int  detect_answer(int value){

        if (value >= y || value <= x) {
            Toast.makeText(Main24Activity.this, "請輸入" + x + "到" + y + "的數字", Toast.LENGTH_SHORT).show();
            you_guess.setText("");

            return 0;
        } else {
            now_answer = value;
            //cp_answer = ran.nextInt(101);
            //cp_guess.setText("電腦猜的:"+cp_answer+";");
            if (value < bomb) {
                x = value;
            } else if (value > bomb) {
                y = value;
            } else {
                guess_number.setText("BOOM");
                //Toast.makeText(Main24Activity.this, "BOOM in :" + value, Toast.LENGTH_SHORT).show();

                String x;
                if(turn%number==0){
                    x="Game Over!, ";
                }else {
                    x="Player "+turn%number+" lose, ";
                }
                x=x+"BOMB in "+bomb;

                new AlertDialog.Builder(Main24Activity.this)

                        .setTitle(x)
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
                                intent2.setClass(Main24Activity.this, Main2Activity.class);
                                startActivity(intent2);
                            }
                        })
                        .show();
                return 2;
            }
            //Toast.makeText(MainActivity.this, "YOU GUESS"+value , Toast.LENGTH_SHORT).show();
            //Toast.makeText(Main24Activity.this, x+" ~ "+y , Toast.LENGTH_SHORT).show();

            change_set();
            turn++;

            return 1;

        }


    }
    private Button.OnClickListener button_listener = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            //Toast.makeText(Main24Activity.this, "7787878777" , Toast.LENGTH_SHORT).show();
            if (turn%number==0) {
                final int your_answer;
                if (!you_guess.getText().toString().equals("")) {
                    your_answer = Integer.valueOf(you_guess.getText().toString());

                    guess_number.setText(String.valueOf(your_answer));
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            if (detect_answer(your_answer) == 1) {

                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(you_guess.getWindowToken(), 0);
                                turn_over_cp();
                            }
                            ;
                        }
                    }, 500);
                }
                else{
                    Toast.makeText(Main24Activity.this, "請輸入一個數字", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    private void init() {
        detect_number(); // 先偵測遊戲人數
        detect_level(); //偵測遊戲難度
        create_bomb();
        head_picture.setImageResource(imgID[0]);
        who_guess.setText("you");
        //set_init = true;
    }
    private void change_set(){
        start.setText(String.valueOf(x));
        end.setText(String.valueOf(y));
    }
    private void create_bomb() {

        bomb = ran.nextInt(y);
    }

    private void detect_level() {
        switch (get_level){
            case 1:
                x=0;
                y=100;
                set_level.setText("簡單");
                break;
            case 2:
                x=0;
                y=500;
                set_level.setText("普通");
                break;
            case 3:
                x=0;
                y=1000;
                set_level.setText("困難");
                break;
        }
        start.setText(String.valueOf(x));
        end.setText(String.valueOf(y));
    }

    private void detect_number(){
        switch (get_number){
            case 1:
                number = 2;
                break;
            case 2:
                number = 3;
                break;
            case 3:
                number = 4;
                break;


        }
        play_number.setText(String.valueOf(number));
    }


private Button.OnTouchListener listener0 = new Button.OnTouchListener(){

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int[] position01 = new int[2];
        v.getLocationInWindow(position01);


        height5=position01[1];
Toast.makeText(Main24Activity.this,"123",Toast.LENGTH_SHORT).show();
        return false;

    }
};
    private Button.OnTouchListener listener = new Button.OnTouchListener(){

        @Override
        public boolean onTouch(View v, MotionEvent event) {



            switch (event.getAction()) {          //判斷觸控的動作

                case MotionEvent.ACTION_DOWN:// 按下圖片時

                    x1 = event.getX();                  //觸控的X軸位置

                    y1 = event.getY();                  //觸控的Y軸位置

                    //text.setText("x1= "+x1+",y1= "+y1+"rawx= "+event.getRawX()+",rawy= "+event.getRawY());
                   break;
                case MotionEvent.ACTION_MOVE:// 移動圖片時

                    //getX()：是獲取當前控件(View)的座標

                    //getRawX()：是獲取相對顯示螢幕左上角的座標
                    mx = (int) (event.getRawX() - x1);
                    my = (int) (event.getRawY() - y1-210);
                    //text2.setText("mx= "+mx+",my= "+my+" ,rawx = "+event.getRawX()+" ,rawy = "+event.getRawY());
                    v.layout(mx,my, mx + v.getWidth(), my + v.getHeight());
                    //v.layout(286,341,286+v.getWidth(),341+v.getHeight());
                    break;



            }

            return true;
        }
    };

}
