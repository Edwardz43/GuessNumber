package com.example.edlo.guessnumber;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    Button createAnswer, checkAnswer, giveUp,
            b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,back, clear;
    TextView showAB, showHist, input;
    String answer;
    int n;
    int count;
    MediaPlayer mp1, mp2, mp3;
    private SoundPool pool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle = getIntent().getExtras();
        n=bundle.getInt("diff");
        findview();
        //加入BGM的設定
        try {
            mp1 = MediaPlayer.create(this, R.raw.chiken);
            mp1.setLooping(true);
        }catch(Exception e){
            Toast to2 = Toast.makeText(this, "Error" ,Toast.LENGTH_SHORT);
            to2.show();
        }
    }
    private void findview() {
        createAnswer = (Button)findViewById(R.id.button);
        checkAnswer =(Button)findViewById(R.id.button2);
        giveUp =(Button)findViewById(R.id.button3);
        showAB = (TextView)findViewById(R.id.textView);
        input = (TextView)findViewById(R.id.textView4);
        showHist = (TextView) findViewById(R.id.textView5);
        b1 = (Button)findViewById(R.id.button4);
        b2 = (Button)findViewById(R.id.button5);
        b3 = (Button)findViewById(R.id.button6);
        b4 = (Button)findViewById(R.id.button7);
        b5= (Button)findViewById(R.id.button8);
        b6 = (Button)findViewById(R.id.button9);
        b7 = (Button)findViewById(R.id.button10);
        b8 = (Button)findViewById(R.id.button11);
        b9 = (Button)findViewById(R.id.button12);
        b0 = (Button)findViewById(R.id.button14);
        back = (Button)findViewById(R.id.button13);
        clear = (Button)findViewById(R.id.button15);
        createAnswer.setOnClickListener(c1);
        checkAnswer.setOnClickListener(c2);
        giveUp.setOnClickListener(c3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(input.getText().toString()+"1");
                soundNum();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(input.getText().toString()+"2");
                soundNum();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(input.getText().toString()+"3");
                soundNum();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(input.getText().toString()+"4");
                soundNum();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(input.getText().toString()+"5");
                soundNum();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(input.getText().toString()+"6");
                soundNum();
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(input.getText().toString()+"7");
                soundNum();
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(input.getText().toString()+"8");
                soundNum();
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(input.getText().toString()+"9");
                soundNum();
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(input.getText().toString()+"0");
                soundNum();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInput();
                soundNum();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
                soundNum();
            }
        });
    }
    //按鍵音效
    private void soundNum() {
        mp2 = MediaPlayer.create(this ,R.raw.cursor1);
        mp2.start();
        mp2.release();
    }
    //倒退鍵的邏輯
    private void back() {
        String s = input.getText().toString();
        if(s.length() >= 1){
            input.setText(s.substring(0 ,s.length()-1));
        }
    }
    View.OnClickListener c1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            answer=createAnswer(n);
            showAB.setText("開始遊戲!");
            showHist.setText("");
        }
    };


    View.OnClickListener c2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String a = answer;
            String g = input.getText().toString();
            String result = checkAB(a, g);
            if(result.equals(n+"A0B")){
                cheer();
                showAB.setText(result+", 完全正確!\n再來一局吧!");
                showHist.setText("恭喜!您一共猜了【"+count+"】次!");
                count=0;
                clearInput();
            }else if(result.equals("輸入不正確! 請重新輸入")){
                showAB.setText("輸入錯誤!");
                String s = showHist.getText().toString()+"輸入錯誤"+" "+"0A0B; ";
                showHist.setText(s);
                count++;
                clearInput();
            }else{
                showAB.setText(result+", 繼續努力!");
                String s = showHist.getText().toString()+g+" "+result+"; ";
                showHist.setText(s);
                count++;
                clearInput();
            }
        }
    };

    private void cheer() {
        mp3= MediaPlayer.create(this, R.raw.fireworks01);
        mp3.start();
    }

    View.OnClickListener c3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showAB.setText("可惜了...答案是:\n"+ answer);
            count=0;
            clearInput();
        }
    };

    //新學到的  控制音樂的寫法
    //onResume 一進到畫面就要做的事  override 加了一個播放音樂
    @Override
    protected void onResume() {
    // TODO Auto-generated method stub
        super.onResume();
        mp1.start();
    }
    //onPause 暫停
    @Override
    protected void onPause() {
    // TODO Auto-generated method stub
        super.onPause();
        mp1.pause();
    }
    //onDestroy 離開畫面要做的事  關閉音樂
    @Override
    protected void onDestroy() {
    // TODO Auto-generated method stub
        super.onDestroy();
        mp1.release();
    }

    //清除輸入欄位
    public void clearInput(){
        input.setText("");
    }

    String checkAB(String a, String g){
        try {
            if (g.length() == a.length()) {
                int A, B;
                A = B = 0;
                for (int i = 0; i < g.length(); i++) {
                    if (g.charAt(i) == a.charAt(i)) {
                        A++;
                    } else if (a.indexOf(g.charAt(i)) != -1) {
                        B++;
                    }
                }
                return A + "A" + B + "B";
            }
        }catch(Exception e){
            Toast to1 = Toast.makeText(this, "請先按下<Create>鍵!",Toast.LENGTH_SHORT);
            to1.show();
        }
        return "輸入不正確! 請重新輸入";
    }
    String createAnswer(int number){
        boolean[] check = new boolean[10];
        int[] poker = new int[number];
        int temp;
        for(int i=0;i<poker.length;i++){
            do{
                temp = (int)(Math.random()*10);
            }while(check[temp]);

            poker[i] = temp;
            check[temp]=true;
        }
        StringBuffer ret = new StringBuffer();
        for(int v :poker) ret = ret.append(v);
        return ret.toString();
    }
}
