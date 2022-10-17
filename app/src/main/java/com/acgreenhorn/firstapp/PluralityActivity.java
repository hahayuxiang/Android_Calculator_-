package com.acgreenhorn.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PluralityActivity extends AppCompatActivity {

    Button button_plural_jia, button_plural_jian, button_plural_chen, button_plural_chu, button_plural_dengyu;
    TextView tip1, tip2, tip3, input1, input2, print1, ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plurality);
        button_plural_jia = findViewById(R.id.plural_jia);
        button_plural_jian = findViewById(R.id.plural_jian);
        button_plural_chen = findViewById(R.id.plural_chen);
        button_plural_chu = findViewById(R.id.plural_chu);
        button_plural_dengyu = findViewById(R.id.plural_dengyu);
        tip1 = findViewById(R.id.plural_tip_1);
        tip2 = findViewById(R.id.plural_tip_2);
        tip3 = findViewById(R.id.plural_tip_3);
        input1 = findViewById(R.id.plural_input_1);
        input2 = findViewById(R.id.plural_input_2);
        print1 = findViewById(R.id.plural_print_1);
        ans = findViewById(R.id.plural_ans);

        input1.setText("");
        input2.setText("");
        print1.setText("+");
        ans.setText("");

        button_plural_jia.setOnClickListener(new ClickOnLisenter());
        button_plural_jian.setOnClickListener(new ClickOnLisenter());
        button_plural_chen.setOnClickListener(new ClickOnLisenter());
        button_plural_chu.setOnClickListener(new ClickOnLisenter());
        button_plural_dengyu.setOnClickListener(new ClickOnLisenter());

    }
    /** 初始化选择不同计算器的菜单*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_plurality, menu);
        return true;
    }


    /**
     * 对菜单不同选项进行不同处理
     * 弹出Toast显示选择的计算器
     * 请求启动相应组件
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //通过item.getItemId()对菜单不同选项进行不同的处理
        switch (item.getItemId())
        {
            case R.id.main:
                Toast.makeText(getApplicationContext(), "标准计算器", Toast.LENGTH_SHORT).show();
                Intent intent_main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent_main);
                break;
            case R.id.unit_conversion:
                Toast.makeText(getApplicationContext(), "单位换算计算器", Toast.LENGTH_SHORT).show();
                Intent intent_unit_conversion = new Intent(getApplicationContext(), ConversionActivity.class);
                startActivity(intent_unit_conversion);
                break;
            case R.id.loan:
                Toast.makeText(getApplicationContext(), "车、房贷计算器", Toast.LENGTH_SHORT).show();
                Intent intent_loan = new Intent(getApplicationContext(), LoanActivity.class);
                startActivity(intent_loan);
                break;
            case R.id.currency:
                Toast.makeText(getApplicationContext(), "汇率换算器", Toast.LENGTH_SHORT).show();
                Intent intent_currency = new Intent(getApplicationContext(), CurrencyActivity.class);
                startActivity(intent_currency);
                break;
            default:
                break;
        }
        return true;
    }

    class ClickOnLisenter implements View.OnClickListener {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.plural_jia:
                    print1.setText( "+");
                    break;
                case R.id.plural_jian:
                    print1.setText( "-");
                    break;
                case R.id.plural_chen:
                    print1.setText( "×");
                    break;
                case R.id.plural_chu:
                    print1.setText("÷");
                    break;
                case R.id.plural_dengyu:
                    String input_1 = input1.getText().toString();
                    String input_2 = input2.getText().toString();
                    if(is_plural_legal(input_1) && is_plural_legal(input_2)){
                        String ans_temp = compute_plural(input_1, input_2);
                        ans.setText(ans_temp);
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private String compute_plural(String input_1, String input_2) {
        double a1, b1, a2, b2;
        double a_ans = 0, b_ans = 0;
        if(input_1.indexOf('i') != -1){
            if(input_1.indexOf('+') != -1){
                a1 = Double.parseDouble(input_1.substring(0, input_1.indexOf('+')));
                String i = input_1.substring(input_1.indexOf('+') + 1, input_1.indexOf('i'));
                if(i.equals("")){
                    b1 = 1;
                }
                else b1 = Double.parseDouble(i);
            }else if(input_1.indexOf('-') != -1){
                if(input_1.substring(0, input_1.indexOf('-')).equals("")) a1 = 0;
                else a1 = Double.parseDouble(input_1.substring(0, input_1.indexOf('-')));
                String i = input_1.substring(input_1.indexOf('-'), input_1.indexOf('i'));
                if(i.equals("-")) {
                    b1 = -1;
                }
                else b1 = Double.parseDouble(i);
            }
            else{
                a1 = 0;
                if(input_1.substring(0, input_1.indexOf('i')).equals("")) b1 = Double.parseDouble("1");
                else b1 = Double.parseDouble(input_1.substring(0, input_1.indexOf('i')));
            }
        }else{
            a1 = Double.parseDouble(input_1);
            b1 = 0;
        }
        if(input_2.indexOf('i') != -1){
            if(input_2.indexOf('+') != -1){
                a2 = Double.parseDouble(input_2.substring(0, input_2.indexOf('+')));
                String i = input_2.substring(input_2.indexOf('+') + 1, input_2.indexOf('i'));
                if(i.equals("")){
                    b2 = 1;
                }
                else b2 = Double.parseDouble(i);
            }else if(input_2.indexOf('-') != -1){
                if(input_2.substring(0, input_2.indexOf('-')).equals("")) a2 = 0;
                else a2 = Double.parseDouble(input_2.substring(0, input_2.indexOf('-')));
                String i = input_2.substring(input_2.indexOf('-'), input_2.indexOf('i'));
                if(i.equals("-")) {
                    b2 = -1;
                }
                else b2 = Double.parseDouble(i);
            }
            else{
                a2 = 0;
                if(input_2.substring(0, input_2.indexOf('i')).equals("")) b2 = Double.parseDouble("1");
                else b2 = Double.parseDouble(input_2.substring(0, input_2.indexOf('i')));
            }
        }else{
            a2 = Double.parseDouble(input_2);
            b2 = 0;
        }
        String opera_plural = print1.getText().toString();
        switch (opera_plural){
            case "+":
                a_ans = a1 + a2;
                b_ans = b1 + b2;
                break;
            case "-":
                a_ans = a1 - a2;
                b_ans = b1 - b2;
                break;
            case "×":
                a_ans = a1 * a2 - b1 * b2;
                b_ans = a1 * b2 + a2 * b1;
                break;
            case "÷":
                if(a2 == 0 && b2 == 0){
                    Toast.makeText(getApplicationContext(), "除数不能为0", Toast.LENGTH_SHORT).show();
                }else{
                    a_ans = (a1 * a2 + b1 * b2) / (a2 * a2 + b2 * b2);
                    b_ans = (b1 * a2 - a1 * b2) / (a2 * a2 + b2 * b2);
                }
                break;
        }

        String ANS = "";
        if(a_ans != 0){
            if(Math.abs(a_ans - Math.round(a_ans)) < 1e-6) {
                ANS = ANS + (int)a_ans;
            }else ANS = ANS + String.format("%.3f", a_ans);
            if(b_ans > 0) ANS = ANS + " + ";
            if(b_ans < 0) {
                b_ans = -b_ans;
                ANS = ANS + " - ";
            }
        }

        if(b_ans != 0) {
            if(Math.abs(b_ans - Math.round(b_ans)) < 1e-6) {
                ANS = ANS + (int)b_ans + " i";
            }else ANS = ANS + String.format("%.3f", b_ans) + " i";
        }
        if(a_ans == 0 && b_ans == 0) ANS = "0";
        return ANS;
    }

    private boolean is_plural_legal(String input) {
//        当前可能是 a+bi 或 a-bi 或 bi 或 -bi
        if(input.indexOf('i') != -1){
//            当前可能是 a+bi
            if(input.indexOf('+') != -1){
                String plural_a = input.substring(0, input.indexOf('+'));
                String plural_b = input.substring(input.indexOf('+') + 1, input.indexOf('i'));
                if(plural_b.equals("")) plural_b = "1";
                return is_double(plural_a) && is_double(plural_b);
            }
//            当前可能是 a-bi
            else if(input.indexOf('-') !=-1){
                String plural_a = input.substring(0, input.indexOf('-'));
                if(plural_a.equals("")) plural_a = "0";
                String plural_b = input.substring(input.indexOf('-'), input.indexOf('i'));
                if(plural_b.equals("-")) plural_b = "-1";
                return is_double(plural_a) && is_double(plural_b);
            }
//            当前可能是bi
            else{
                String plural_b = input.substring(0, input.indexOf('i'));
                if(plural_b.equals("")) plural_b = "1";
                return is_double(plural_b);
            }
        }
//        当前可能是a
        else{
            return is_double(input);
        }
    }

    private boolean is_double(String input) {
        try{
            Double.parseDouble(input);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }

}