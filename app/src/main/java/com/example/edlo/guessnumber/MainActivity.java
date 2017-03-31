package com.example.edlo.guessnumber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView showGame, showTip, showDiff;
    EditText setDiff;
    Button startGame;
    int diff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
    }

    private void findview() {
        showGame = (TextView)findViewById(R.id.textView2);
        showTip = (TextView)findViewById(R.id.textView3);
        showTip.setText("遊戲說明:\n電腦會以亂數產生一個不重複的多位數字讓你猜。\n" +
                "輸入數字之後，電腦會比對數字，並輸出結果。\n" +
                "結果的格式是『？A？B』，A代表位置及數值都相同，B表示只有數值相同但位置不同。\n" +
                "例如：答案是1234，而你猜1789，結果就是1A0B。因為只有1位置及數值都對。\n" +
                "如果猜2189，結果就是0A2B，2和1數值都對，但位置錯誤。\n" +
                "根據每次猜的結果，就可以慢慢推算出答案。\n" +
                "除了運氣之外，實力也很重要哦！");
        showDiff = (TextView)findViewById(R.id.textView6);
        setDiff = (EditText)findViewById(R.id.editText3);
        startGame = (Button)findViewById(R.id.button18);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               start();
            }
        });
    }

    private void start() {
        diff = Integer.parseInt(setDiff.getText().toString());
        if(diff > 1 && diff < 6) {
            Intent intPlay = new Intent();
            intPlay.setClass(MainActivity.this, Main2Activity.class);
            Bundle bund1 = new Bundle();
            bund1.putInt("diff", diff);
            intPlay.putExtras(bund1);
            startActivity(intPlay);
        }else{
            Toast toast = Toast.makeText(this, "請輸入正確難度!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }



    /*
    遊戲說明:\n電腦會以亂數產生一個不重複的多位數字讓你來猜。
    你輸入數字之後，電腦會比對數字，並輸出結果。\n
    結果的格式是 『？A？B』，A代表位置及數值都相同，B表示只有數值相同但位置不同。\n
    例如答案是1234，而你猜1789，結果就是1A0B。因為只有1位置及數值都對了，789這三個數都沒猜對。\n
    如果猜2189，結果就是0A2B，2和1數值都對，但位置錯誤。\n
    根據每次猜的結果，就可以慢慢推算出答案。\n
    除了運氣之外，實力也很重要哦！
    */
}
