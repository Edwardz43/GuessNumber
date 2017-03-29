package com.example.edlo.guessnumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button createAnswer, checkAnswer, giveUp;
    TextView showAB, showHist;
    EditText input;
    String answer;
    int n = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
        answer = createAnswer(n);
    }

    private void findview() {
        createAnswer = (Button)findViewById(R.id.button);
        checkAnswer =(Button)findViewById(R.id.button2);
        giveUp =(Button)findViewById(R.id.button3);
        showAB = (TextView)findViewById(R.id.textView);
        input = (EditText)findViewById(R.id.editText);
        showHist = (EditText)findViewById(R.id.editText2);
        createAnswer.setOnClickListener(c1);
        checkAnswer.setOnClickListener(c2);
        giveUp.setOnClickListener(c3);
    }
    View.OnClickListener c1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            createAnswer(3);
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
                showAB.setText(result+", Winner!\n再來一局吧!");
            }else{
                showAB.setText(result+", 繼續努力!");
                showHist.setText(showHist.getText().toString()+g+" "+result+"\n");
            }
        }
    };
    View.OnClickListener c3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        showAB.setText("可惜了...答案是 :"+ answer);
        }
    };
    public void clearInput(View view){
        input.setText("");
    }

    String checkAB(String a, String g){
        if(g.matches("^[\\d]{3}$")) {
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
        return "輸入不正確! 請重新輸入";
    }

    // method: create answer
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
