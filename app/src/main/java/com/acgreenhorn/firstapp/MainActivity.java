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


import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 实现基本计算器功能
 *
 * 十进制加减乘除、带（）表达式、开方平方
 *
 * 十六进制加减乘除
 *
 */
public class MainActivity extends AppCompatActivity {

    Button button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7,  button_8,
            button_9, button_fuhao, button_dot, button_leftkuohao, button_rightkuohao, button_c,
            button_backspace, button_change, button_pingfang, button_kaifang, button_chu, button_chen,
            button_jian, button_jia, button_dengyu, button_A, button_B, button_C, button_D, button_E,
            button_F;
    /** last_output显示表达式与结果，info显示进制*/
    TextView last_out, current_output, info;

    /** 存放 + - * / 运算符      */
    Set<String> s_yunsuanfu = new HashSet<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局文件
        setContentView(R.layout.activity_main);
        s_yunsuanfu.add("+");
        s_yunsuanfu.add("-");
        s_yunsuanfu.add("÷");
        s_yunsuanfu.add("×");

        //获取控件
        info = findViewById(R.id.info);
        last_out = findViewById(R.id.last_output);
        current_output = findViewById(R.id.current_out);

        //设置默认显示
        last_out.setText("0");
        current_output.setText("");
        info.setText("D");

        //获取控件
        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_fuhao = findViewById(R.id.button_fuhao);
        button_dot = findViewById(R.id.button_dot);
        button_leftkuohao = findViewById(R.id.button_leftkuohao);
        button_rightkuohao = findViewById(R.id.button_rightkuohao);
        button_c = findViewById(R.id.button_c);
        button_backspace = findViewById(R.id.button_backspace);
        button_change = findViewById(R.id.button_change);
        button_pingfang = findViewById(R.id.button_pingfang);
        button_kaifang = findViewById(R.id.button_kaifang);
        button_chu = findViewById(R.id.button_chu);
        button_chen = findViewById(R.id.button_chen);
        button_jian = findViewById(R.id.button_jian);
        button_jia = findViewById(R.id.button_jia);
        button_dengyu = findViewById(R.id.button_dengyu);
        button_A = findViewById(R.id.button_A);
        button_B = findViewById(R.id.button_B);
        button_C = findViewById(R.id.button_C);
        button_D = findViewById(R.id.button_D);
        button_E = findViewById(R.id.button_E);
        button_F = findViewById(R.id.button_F);

        //给按钮添加事件监听
        button_0.setOnClickListener(new ClickOnLisenter());
        button_1.setOnClickListener(new ClickOnLisenter());
        button_2.setOnClickListener(new ClickOnLisenter());
        button_3.setOnClickListener(new ClickOnLisenter());
        button_4.setOnClickListener(new ClickOnLisenter());
        button_5.setOnClickListener(new ClickOnLisenter());
        button_6.setOnClickListener(new ClickOnLisenter());
        button_7.setOnClickListener(new ClickOnLisenter());
        button_8.setOnClickListener(new ClickOnLisenter());
        button_9.setOnClickListener(new ClickOnLisenter());
        button_fuhao.setOnClickListener(new ClickOnLisenter());
        button_dot.setOnClickListener(new ClickOnLisenter());
        button_leftkuohao.setOnClickListener(new ClickOnLisenter());
        button_rightkuohao.setOnClickListener(new ClickOnLisenter());
        button_c.setOnClickListener(new ClickOnLisenter());
        button_backspace.setOnClickListener(new ClickOnLisenter());
        button_change.setOnClickListener(new ClickOnLisenter());
        button_pingfang.setOnClickListener(new ClickOnLisenter());
        button_kaifang.setOnClickListener(new ClickOnLisenter());
        button_chu.setOnClickListener(new ClickOnLisenter());
        button_chen.setOnClickListener(new ClickOnLisenter());
        button_jian.setOnClickListener(new ClickOnLisenter());
        button_jia.setOnClickListener(new ClickOnLisenter());
        button_dengyu.setOnClickListener(new ClickOnLisenter());
        button_A.setOnClickListener(new ClickOnLisenter());
        button_B.setOnClickListener(new ClickOnLisenter());
        button_C.setOnClickListener(new ClickOnLisenter());
        button_D.setOnClickListener(new ClickOnLisenter());
        button_E.setOnClickListener(new ClickOnLisenter());
        button_F.setOnClickListener(new ClickOnLisenter());

    }

    /** 初始化选择不同计算器的菜单*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
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
            case R.id.plural:
                Toast.makeText(getApplicationContext(), "复数计算器", Toast.LENGTH_SHORT).show();
                Intent intent_plural = new Intent(getApplicationContext(), PluralityActivity.class);
                startActivity(intent_plural);
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

    /**
     * 计算器按键的事件监听
     */
    class ClickOnLisenter implements View.OnClickListener{
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button_0:
                    String s0 = last_out.getText().toString();
                    s0 = clearstr(s0);
                    if(!s0.equals("0")) {//如果显示为‘0’,不改变。不为‘0’,在后添加‘0’
                        s0 += "0";
                    }
                    s0 = textForceLTR(s0);
                    last_out.setText(s0);
                    break;
                case R.id.button_1:
                    String s1 = last_out.getText().toString();
                    s1 = clearstr(s1);
                    if(s1.equals("0")) s1 = "1";//如果显示‘0’，表示没有其他的输入，进行替换
                    else s1 += "1";//不为0,后面添加
                    s1= textForceLTR(s1);
                    last_out.setText(s1);
                    break;
                case R.id.button_2:
                    String s2 = last_out.getText().toString();
                    s2 = clearstr(s2);
                    if(s2.equals("0")) s2 = "2";
                    else s2 += "2";
                    s2 = textForceLTR(s2);
                    last_out.setText(s2);
                    break;
                case R.id.button_3:
                    String s3 = last_out.getText().toString();
                    s3 = clearstr(s3);
                    if(s3.equals("0")) s3 = "3";
                    else s3 += "3";
                    s3 = textForceLTR(s3);
                    last_out.setText(s3);
                    break;
                case R.id.button_4:
                    String s4 = last_out.getText().toString();
                    s4 = clearstr(s4);
                    if(s4.equals("0")) s4 = "4";
                    else s4 += "4";
                    s4 = textForceLTR(s4);
                    last_out.setText(s4);
                    break;
                case R.id.button_5:
                    String s5 = last_out.getText().toString();
                    s5 = clearstr(s5);
                    if(s5.equals("0")) s5 = "5";
                    else s5 += "5";
                    s5 = textForceLTR(s5);
                    last_out.setText(s5);
                    break;
                case R.id.button_6:
                    String s6 = last_out.getText().toString();
                    s6 = clearstr(s6);
                    if(s6.equals("0")) s6 = "6";
                    else s6 += "6";
                    s6 = textForceLTR(s6);
                    last_out.setText(s6);
                    break;
                case R.id.button_7:
                    String s7 = last_out.getText().toString();
                    s7 = clearstr(s7);
                    if(s7.equals("0")) s7 = "7";
                    else s7 += "7";
                    s7 = textForceLTR(s7);
                    last_out.setText(s7);
                    break;
                case R.id.button_8:
                    String s8 = last_out.getText().toString();
                    s8 = clearstr(s8);
                    if(s8.equals("0")) s8 = "8";
                    else s8 += "8";
                    s8 = textForceLTR(s8);
                    last_out.setText(s8);
                    break;
                case R.id.button_9:
                    String s9 = last_out.getText().toString();
                    s9 = clearstr(s9);
                    if(s9.equals("0")) s9 = "9";
                    else s9 += "9";
                    s9 = textForceLTR(s9);
                    last_out.setText(s9);
                    break;
                case R.id.button_fuhao:
                    String s_fuhao = last_out.getText().toString();
                    s_fuhao = clearstr(s_fuhao);
                    if(!s_fuhao.equals("0")) {
                        String t_fuhao = s_fuhao.substring(s_fuhao.length() - 1);
                        //最后一个输入不是"√"时，在后添加‘-’
                        if (s_yunsuanfu.contains(t_fuhao)) {
                            s_fuhao = s_fuhao.substring(0, s_fuhao.length() - 1) + "-";
                        }else {
                            if(!t_fuhao.equals("√")) s_fuhao += "-";
                        }
                    }else s_fuhao = "-";//没有其他输入，直接显示‘-’
                    s_fuhao = textForceLTR(s_fuhao);
                    last_out.setText(s_fuhao);
                    break;
                case R.id.button_dot:
                    //十进制进行计算
                    if(info.getText().toString().equals("D")){
                        String s_dot = last_out.getText().toString();
                        s_dot = clearstr(s_dot);

//                        if (s_dot.indexOf('.') == -1) {
//                            String t_dot = s_dot.substring(s_dot.length() - 1);
//                            Character tt = t_dot.charAt(0);
//                            if (Character.isDigit(tt)) {
//                                s_dot += ".";
//                            }
//                            s_dot = textForceLTR(s_dot);
//                            last_out.setText(s_dot);
//                            break;
//                        }
                        if (s_dot.lastIndexOf('.') == -1) {//如果不存在小数点，则直接添加
                            String t_dot = s_dot.substring(s_dot.length() - 1);
                            Character tt = t_dot.charAt(0);
                            if (Character.isDigit(tt)) {
                                s_dot += ".";
                            }
                            s_dot = textForceLTR(s_dot);
                            last_out.setText(s_dot);
                            break;
                        } else {//已存在小数点，判断新添加是否重复或是否在数字后
                            int index_dot = s_dot.lastIndexOf('.');
                            int bool = 0;
                            int k = index_dot + 1;
                            String tt_dot = s_dot.substring(s_dot.length() - 1);
                            Character tt = tt_dot.charAt(0);
                            if (Character.isDigit(tt)) {
                                while (k < s_dot.length() - 1) {
                                    String t_dot = s_dot.substring(k, k + 1);
                                    if (s_yunsuanfu.contains(t_dot)) {
                                        bool = 1;
                                        break;
                                    }
                                    k++;
                                }if (bool == 1) {
                                    s_dot += ".";
                                    s_dot = textForceLTR(s_dot);
                                    last_out.setText(s_dot);
                                    break;
                                }
                            }
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "提示：十六进制只支持整数运算", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    //A、B、C、D、E、F的按键都只能在十六进制使用
                case R.id.button_A:
                    if(info.getText().toString().equals("H")) {
                        String s_A = last_out.getText().toString();
                        s_A = clearstr(s_A);
                        if(s_A.equals("0")) s_A = "A";
                        else s_A += "A";
                        s_A = textForceLTR(s_A);
                        last_out.setText(s_A);
                        break;
                    }
                    break;
                case R.id.button_B:
                    if(info.getText().toString().equals("H")) {
                        String s_B = last_out.getText().toString();
                        s_B = clearstr(s_B);
                        if(s_B.equals("0")) s_B = "B";
                        else s_B += "B";
                        s_B = textForceLTR(s_B);
                        last_out.setText(s_B);
                        break;
                    }
                    break;
                case R.id.button_C:
                    if(info.getText().toString().equals("H")) {
                        String s_C = last_out.getText().toString();
                        s_C = clearstr(s_C);
                        if(s_C.equals("0")) s_C = "C";
                        else s_C += "C";
                        s_C = textForceLTR(s_C);
                        last_out.setText(s_C);
                        break;
                    }
                    break;
                case R.id.button_D:
                    if(info.getText().toString().equals("H")) {
                        String s_D = last_out.getText().toString();
                        s_D = clearstr(s_D);
                        if (s_D.equals("0")) s_D = "D";
                        else s_D += "D";
                        s_D = textForceLTR(s_D);
                        last_out.setText(s_D);
                        break;
                    }
                    break;
                case R.id.button_E:
                    if(info.getText().toString().equals("H")) {
                        String s_E = last_out.getText().toString();
                        s_E = clearstr(s_E);
                        if(s_E.equals("0")) s_E = "E";
                        else s_E += "E";
                        s_E = textForceLTR(s_E);
                        last_out.setText(s_E);
                        break;
                    }
                    break;
                case R.id.button_F:
                    if(info.getText().toString().equals("H")) {
                        String s_F = last_out.getText().toString();
                        s_F = clearstr(s_F);
                        if(s_F.equals("0")) s_F = "F";
                        else s_F += "F";
                        s_F = textForceLTR(s_F);
                        last_out.setText(s_F);
                        break;
                    }
                    break;
                    //+ - × ÷ 如果最后一个字符为四个运算符中一个则替换，为数字则在后添加
                case R.id.button_jia:
                     String s_jia = last_out.getText().toString();
                    s_jia = clearstr(s_jia);
                    if (!s_jia.equals("0")) {
                        String t_jia = s_jia.substring(s_jia.length() - 1);
                        if (s_yunsuanfu.contains(t_jia)) {
                            s_jia = s_jia.substring(0, s_jia.length() - 1) + "+";
                        }else if(!t_jia.equals("√")) s_jia += "+";
                    }
                    s_jia = textForceLTR(s_jia);
                    last_out.setText(s_jia);
                    break;
                case R.id.button_jian:
                    String s_jian = last_out.getText().toString();
                    s_jian = clearstr(s_jian);
                    if (!s_jian.equals("0")) {
                        String t_jian = s_jian.substring(s_jian.length() - 1);
                        if (s_yunsuanfu.contains(t_jian)) {
                            s_jian = s_jian.substring(0, s_jian.length() - 1) + "-";
                        }else {
                            if(!t_jian.equals("√")) s_jian += "-";
                        }
                    }else s_jian = "-";//如果是‘0’,可替换，直接当负号处理
                    s_jian = textForceLTR(s_jian);
                    last_out.setText(s_jian);
                    break;
                case R.id.button_chen:
                    String s_chen = last_out.getText().toString();
                    s_chen = clearstr(s_chen);
                    if (!s_chen.equals("0")) {
                        String t_chen = s_chen.substring(s_chen.length() - 1);
                        if (s_yunsuanfu.contains(t_chen)) {
                            s_chen = s_chen.substring(0, s_chen.length() - 1) + "×";
                        }else if(!t_chen.equals("√")) s_chen += "×";
                    }
                    s_chen = textForceLTR(s_chen);
                    last_out.setText(s_chen);
                    break;
                case R.id.button_chu:
                    String s_chu = last_out.getText().toString();
                    s_chu = clearstr(s_chu);
                    if (!s_chu.equals("0")) {
                        String t_chu = s_chu.substring(s_chu.length() - 1);
                        if (s_yunsuanfu.contains(t_chu)) {
                            s_chu = s_chu.substring(0, s_chu.length() - 1) + "÷";
                        }else if(!t_chu.equals("√")) s_chu += "÷";
                    }
                    s_chu = textForceLTR(s_chu);
                    last_out.setText(s_chu);
                    break;
                    //清空显示
                case R.id.button_c:
                    last_out.setText("0");
                    last_out.setTextSize(1, 60);
                    current_output.setText("");
                    break;
                    //回退一个字符，如果只剩一个字符仍需回退，则置为初始状态‘0’
                case R.id.button_backspace:
                    String s_backspace = last_out.getText().toString();
                    s_backspace = clearstr(s_backspace);
                    if(s_backspace.length() > 1){
                        s_backspace = s_backspace.substring(0, s_backspace.length() - 1);
                    }else {
                        s_backspace = "0";
                        last_out.setTextSize(1, 60);
                        current_output.setText("");
                    }
                    s_backspace = textForceLTR(s_backspace);
                    last_out.setText(s_backspace);
                    break;
                case R.id.button_leftkuohao:
                    String s_leftkuohao = last_out.getText().toString();
                    s_leftkuohao = clearstr(s_leftkuohao);
                    String t_leftkuohao = s_leftkuohao.substring(s_leftkuohao.length() - 1);
                    if(s_leftkuohao.equals("0")) s_leftkuohao = "(";
                    else {
                        //√ （ ） 与+ - × ÷运算符后面可以直接添加（
                        if(t_leftkuohao.equals("√") || t_leftkuohao.equals("(")){
                            s_leftkuohao += "(";
                        }else if(s_yunsuanfu.contains(t_leftkuohao)){
                            s_leftkuohao += "(";
                        }
                    }
                    s_leftkuohao = textForceLTR(s_leftkuohao);
                    last_out.setText(s_leftkuohao);
                    break;
                case R.id.button_rightkuohao:
                    String s_rightkuohao = last_out.getText().toString();
                    s_rightkuohao = clearstr(s_rightkuohao);
                    String t_rightkuohao = s_rightkuohao.substring(s_rightkuohao.length() - 1);
                    //'('的个数只能<=')',超出不能添加
                    if (!s_rightkuohao.equals("0") && !s_yunsuanfu.contains(t_rightkuohao)) {
                        int cnt_l = 0, cnt_r = 0;
                        for (int i = 0; i < s_rightkuohao.length(); i ++){
                            if(s_rightkuohao.charAt(i) == '(') cnt_l ++;
                            if(s_rightkuohao.charAt(i) == ')') cnt_r ++;
                        }
                        if(cnt_l > cnt_r && !t_rightkuohao.equals("(")) s_rightkuohao = s_rightkuohao + ")";
                    }
                    s_rightkuohao = textForceLTR(s_rightkuohao);
                    last_out.setText(s_rightkuohao);
                    break;
                case R.id.button_change:
                    if(info.getText().toString().equals("D")){
                        info.setText("H");
                    }else {
                        info.setText("D");
                    }
                    last_out.setText("0");
                    last_out.setTextSize(1, 60);
                    current_output.setText("");
                    break;
                case R.id.button_pingfang:
                        String s_pingfang = last_out.getText().toString();
                        s_pingfang = clearstr(s_pingfang);
                        if (!s_pingfang.equals("0")) {
                            String t_pingfang = s_pingfang.substring(s_pingfang.length() - 1);
                            Character tt = t_pingfang.charAt(0);
                            if (Character.isDigit(tt) || tt.equals(')')) {
                                s_pingfang += "^(2)";
                            }
                            if (info.getText().toString().equals("H") && Character.isUpperCase(tt)) {
                                s_pingfang += "^(2)";
                            }
                        }
                        s_pingfang = textForceLTR(s_pingfang);
                        last_out.setText(s_pingfang);
                    break;
                case R.id.button_kaifang:
                    if(info.getText().toString().equals("D")) {
                        String s_kaifang = last_out.getText().toString();
                        s_kaifang = clearstr(s_kaifang);
                        String t_kaifang = s_kaifang.substring(s_kaifang.length() - 1);
                        if (!t_kaifang.equals(".")) {
                            if (s_kaifang.equals("0")) s_kaifang = "√";
                            else s_kaifang += "√";
                        }
                        s_kaifang = textForceLTR(s_kaifang);
                        last_out.setText(s_kaifang);
                    }else{
                        Toast.makeText(getApplicationContext(), "提示：十六进制不支持开方运算", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.button_dengyu:
                    String s_dengyu = last_out.getText().toString();
                    s_dengyu = clearstr(s_dengyu);
                    if (s_dengyu.endsWith("√")) {
                        Toast.makeText(getApplicationContext(), "异常，表达式不合法", Toast.LENGTH_SHORT).show();
                    }
                    else if (info.getText().toString().equals("H") && s_dengyu.indexOf('.') != -1) {
                        Toast.makeText(getApplicationContext(), "异常，十六进制不支持小数运算，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String result_dec = compute(s_dengyu);
                        //结果长度过长，字体减小显示
                        if (result_dec.length() > 11) {
                            last_out.setTextSize(1,45);
                        }else last_out.setTextSize(1,60);


                        if (info.getText().toString().equals("D")) {//十进制结果
//                            current_output.setText(result_dec);
                            result_dec = textForceLTR(result_dec);
                            last_out.setText(result_dec);
                        } else {//十六进制结果
                            String result_hex = "";
                            if (result_dec.indexOf('.') != -1) {//结果存在小数
                                result_hex = solve_hex_dot(result_dec);
                            }else {//不存在小数，是整数
                                if (result_dec.charAt(0) == '-') { //当前结果是负数，去掉符号处理
                                    result_dec = result_dec.substring(1);
                                    result_hex = Integer.toHexString((int) Double.parseDouble(result_dec));
                                    result_hex = "-" + result_hex;
                                } else//正整数直接处理
                                    result_hex = Integer.toHexString((int) Double.parseDouble(result_dec));
                            }
                            result_hex = result_hex.toUpperCase();
//                            current_output.setText(result_hex);
                            result_hex = textForceLTR(result_hex);
                            last_out.setText(result_hex);
                        }
                        current_output.setText("");
                    }
                    break;
            }
        }
    }

    private String solve_hex_dot(String result_dec) {
        boolean flag = false; //标记符号
        String result_int = result_dec.substring(0, result_dec.indexOf('.')); //整数部分
        if (result_dec.charAt(0) == '-') {
            result_int = result_int.substring(1);
            flag = true;
        }
        result_int = Integer.toHexString((int) Double.parseDouble(result_int));
        result_int = result_int + ".";
        if (flag) result_int = "-" + result_int;
        String result_dot = result_dec.substring(result_dec.indexOf('.') + 1);//小数部分
        while (result_dot.length() > 1 && result_dot.charAt(0) == '0') {
            result_dot = result_dot.substring(1);
        }
        int count = result_dec.length() - result_dec.indexOf('.') - 1;
        double t = Double.parseDouble(result_dot);
        t = t / Math.pow(10, count);
        for (int i = 0; i < count ; i ++ ) {
            t *= 16;
            result_int += Integer.toHexString((int)t);
            t -= (int)t;
        }
        return result_int;
    }

    /**
     * 计算结果
     * @param s_dengyu 表达式
     * @return 运算结果
     */
    private String compute(String s_dengyu) {
        //表达式预处理
        String s_begin = Pre_processing(s_dengyu);
        String s_middle = To_postfix(s_begin);
        return Caculating(s_middle);
    }

    /**
     *
     * @param s_middle
     * @return
     */
    @SuppressLint("DefaultLocale")
    private String Caculating(String s_middle) {
        String string_ans;
        double data = 0;
        Stack<Double> stack = new Stack<>();
        String item = "";//存放运算符
        // flag = -1 当前表达式异常
        // flag = 0 当前为运算数
        // flag = 1 当前为运算符
        int flag = -1;
        int curPos = 0;//目前遍历表达式下标
        while(s_middle.charAt(curPos) == ' ') curPos ++;
        while (curPos < s_middle.length()) {
            int k = curPos;
            item = "";
            if (s_middle.charAt(k) == '.') flag = -1;
            else if (Character.isDigit(s_middle.charAt(k)) || Character.isUpperCase(s_middle.charAt(k))) {
                while (k < s_middle.length() && (Character.isDigit(s_middle.charAt(k)) || s_middle.charAt(k) == '.' || Character.isUpperCase(s_middle.charAt(k)))) {
                    item = item + s_middle.substring(k, k + 1);
                    k++;
                }
                flag = 0;
            } else {
                item = s_middle.substring(k, k + 1);
                k++;
                flag = 1;
            }
            while (k < s_middle.length() && s_middle.charAt(k) == ' ') k++;
            curPos = k;
            if (flag == -1) {
                Toast.makeText(getApplicationContext(), "异常，表达式不合法", Toast.LENGTH_SHORT).show();
            } else if (flag == 1) {
                //读取到运算符进行运算
                data = Dooperator(stack, item);
                //运算结果入栈
                stack.push(data);
            } else {
                if(info.getText().toString().equals("D")){
                    data = Double.parseDouble(item);
                }else{
                    data = (double) Long.parseLong(item, 16);
                }
                stack.push(data);
            }
        }
        if(stack.size() == 1) {
            data = stack.peek();
            stack.pop();
        }
        else {
            Toast.makeText(getApplicationContext(), "异常，存在多余操作符0", Toast.LENGTH_SHORT).show();
        }
        if(Math.abs(data - Math.round(data)) < 1e-6){
            string_ans = String.valueOf((long)data);
        }
        //小数保留三位
        else string_ans = String.format("%.3f",data);
        return string_ans;
    }

    /**
     * 从栈中读取运算数，与输入的运算符进行相应计算
     * @param stack 存放运算数的栈
     * @param item 运算符
     * @return 运算结果
     */
    private double Dooperator(Stack<Double> stack, String item) {
        double res = 0;
        //单目与双目运算
        if(item.equals("^") || item.equals("!")){
            double oper_1;
            if(stack.size() > 0) {
                oper_1 = stack.peek();
                stack.pop();
                if (item.equals("^")) {
                    res = oper_1 * oper_1;
                } else {
                    if (oper_1 >= 0) res = Math.sqrt(oper_1);
                    else Toast.makeText(getApplicationContext(), "异常，根号下存在负值表达式", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "异常，存在多余操作符", Toast.LENGTH_SHORT).show();
            }
        }else {
            double oper_1, oper_2;
            //取出两个操作数进行相应计算
            if(stack.size() > 1) {
                oper_2 = stack.peek();
                stack.pop();
                oper_1 = stack.peek();
                stack.pop();
                switch (item) {
                    case "+":
                        res = oper_1 + oper_2;
                        break;
                    case "-":
                        res = oper_1 - oper_2;
                        break;
                    case "×":
                        res = oper_1 * oper_2;
                        break;
                    case "÷":
                        if (Math.abs(oper_2) < 1e-6) {
                            Toast.makeText(getApplicationContext(), "异常，分母不能为0", Toast.LENGTH_SHORT).show();
                            break;
                        } else res = oper_1 / oper_2;
                        break;
                }
            }else {
                Toast.makeText(getApplicationContext(), "异常，存在多余操作符", Toast.LENGTH_SHORT).show();
            }
        }
        return res;
    }

    /**
     *将表达式转为后缀表达式
     * @param s_begin 带转换的表达式
     * @return 已转换表达式
     */
    private String To_postfix(String s_begin) {
        String string_post = "";
        //存放转换后的后缀表达式
        Stack<Character> stack = new Stack<>();
        //存放运算符
        stack.push('#');
        //结束标志
        String item;
        //item用来保存每次从表达式中取出的操作数或操作符
        int flag;
        //flag = -1 表示当前表达式错误
        //flag = 0 表示当前是数字
        //flag = 1 表示当前为运算符
        int curPos = 0;
        //目前遍历表达式的位置
        while(s_begin.charAt(curPos) == ' ') curPos ++;
        //取出连续且无效的空格
        while (curPos < s_begin.length())
        {
            //只要表达式没结束，就一直执行
            int k = curPos;
            item = "";
            if (s_begin.charAt(k) == '.') flag = -1;
            else if (Character.isDigit(s_begin.charAt(k)) || Character.isUpperCase(s_begin.charAt(k)))
            {
                while (k < s_begin.length() && (Character.isDigit(s_begin.charAt(k)) || s_begin.charAt(k) == '.' || Character.isUpperCase(s_begin.charAt(k))))
                {
                    //取出连续的操作数
                    item += s_begin.substring(k, k + 1);
                    k++;
                }
                flag = 0;
            } else
            {
                //取出操作符
                item = s_begin.substring(k, k + 1);
                k ++;
                flag = 1;
            }
            while (k < s_begin.length() && s_begin.charAt(k) == ' ') k++;
            curPos = k;
            if (flag == -1)
            {
                Toast.makeText(getApplicationContext(), "异常，元素不合法", Toast.LENGTH_SHORT).show();
            } else if (flag == 1)
            {
                //读取到运算符，进行入栈
                Character curOP = item.charAt(0);
                //当前字符为’）‘，出栈先行运算
                if (curOP == ')')
                {
                    Character ch;
                    do {
                        ch = stack.peek();
                        stack.pop();
                        if (ch == '#')
                        {
                            Toast.makeText(getApplicationContext(), "异常，表达式不合法", Toast.LENGTH_SHORT).show();
                        }
                        if (ch != '(')
                        {
                            //如果是其他符号，则把出栈的符号加入到post中,直至’（‘符号出现停止
                            string_post = string_post + Character.toString(ch) + " ";
                        }
                    } while (ch != '(');
                } else
                {
                    //当前字符为除了’）‘以外其他运算符符号
                    Character ch;
                    ch = stack.peek();
                    while (ICP(curOP) <= ISP(ch))
                    {
                        stack.pop();
                        string_post = string_post + Character.toString(ch) + " ";
                        ch = stack.peek();
                    }
                    stack.push(curOP);
                }
            } else
            {
                //运算数直接添加进后缀表达式
                string_post = string_post + item + " ";
            }
        }
        //栈内还有剩余运算符
        while (!stack.empty())
        {
            Character ch;
            ch = stack.peek();
            stack.pop();
            if (ch != '#')
            {
                string_post = string_post + Character.toString(ch) + " ";
            }
        }
        if (string_post.length() > 0)
            //删去末尾多余空格
            string_post = string_post.substring(0, string_post.length() - 1);
        return string_post;
    }

    /**
     *对于表达式中负数、开方、平方的处理
     * @param s_dengyu 待处理的表达式
     * @return 处理完成的表达式
     */
    private String Pre_processing(String s_dengyu) {
        s_dengyu = s_dengyu.replace("\u202D", "");
        s_dengyu = s_dengyu.replace("\u202C", "");
        //负数处理，若前面无数字，在前面添加‘0’便于运算
        for (int i = 0; i < s_dengyu.length(); i ++) {
            if(s_dengyu.charAt(i) == '-') {
                if(i == 0) {
                    s_dengyu = "0" + s_dengyu;
                }else if(s_dengyu.charAt(i - 1) == '('){
                    s_dengyu = s_dengyu.substring(0, i) + "0" + s_dengyu.substring(i);
                }
            }
        }
        //平方处理
        for(int i = 0; i < s_dengyu.length(); i ++) {
            if(s_dengyu.charAt(i) == '^') {
                //补充省略的’×‘,小数
                if(i + 4 < s_dengyu.length() && (Character.isDigit(s_dengyu.charAt(i + 4)) || Character.isUpperCase(s_dengyu.charAt(i + 4)) )) {
                    s_dengyu = s_dengyu.substring(0, i + 1) + "×" + s_dengyu.substring(i + 4);
                }else if(i + 4 < s_dengyu.length() && s_dengyu.charAt(i + 4) == '.'){
                    s_dengyu = s_dengyu.substring(0, i + 1) + "×0" + s_dengyu.substring(i + 4);
                }
                else s_dengyu = s_dengyu.substring(0, i + 1) + s_dengyu.substring(i + 4);
            }
        }
        //开方处理，补充'√'前省略的乘号
        for (int i = 0; i < s_dengyu.length(); i ++){
            if(s_dengyu.charAt(i) == '√'){
                if(i > 0 && Character.isDigit(s_dengyu.charAt(i - 1)))
                    s_dengyu = s_dengyu.substring(0, i) + "×" + s_dengyu.substring(i);
            }
        }
        //对于要开方的数字进行标记
        for (int i = 0; i < s_dengyu.length(); i ++){
            if(s_dengyu.charAt(i) == '√') {
                if (Character.isDigit(s_dengyu.charAt(i + 1))||s_dengyu.charAt(i+1)=='√') {
                    int j = i + 1;
                    while (j + 1 < s_dengyu.length() && !s_yunsuanfu.contains(Character.toString(s_dengyu.charAt(j + 1)))
                        && s_dengyu.charAt(j + 1) != ')'){
                        j++;
                    } //最后一个或者下一个是双目运算符
                    if (j == s_dengyu.length() - 1)
                        s_dengyu = s_dengyu.substring(0, i) + s_dengyu.substring(i + 1) + "!";
                    else
                        s_dengyu = s_dengyu.substring(0, i) + s_dengyu.substring(i + 1, j + 1) + "!" + s_dengyu.substring(j + 1);
                    if(s_dengyu.charAt(i)=='√')
                        i--;
                } else if (s_dengyu.charAt(i + 1) == '(') {
                    int j = i + 1;
                    while (j + 1 < s_dengyu.length() && s_dengyu.charAt(j + 1) != ')') {
                        j++;
                    }
                    //缺少’)‘进行补充
                    if (j == s_dengyu.length() - 1) {
                        s_dengyu = s_dengyu.substring(0, i) + s_dengyu.substring(i + 1) + ")!";
                    } else if (j == s_dengyu.length() - 2)
                        s_dengyu = s_dengyu.substring(0, i) + s_dengyu.substring(i + 1) + "!";
                    else
                        s_dengyu = s_dengyu.substring(0, i) + s_dengyu.substring(i + 1, j + 2) + "!" + s_dengyu.substring(j + 2);
                }
            }
        }
//        System.out.println(s_dengyu);
        return s_dengyu;
    }

    /**
     *求栈内优先级
     * @param c 运算符
     * @return 栈内优先级
     */
    private int ISP(Character c){
      int value = -1;
      if(c.equals('#')) value = 0;
      else if(c.equals('(')) value = 1;
      else if(c.equals('^') || c.equals('!')) value = 7;
      else if(c.equals('×') || c.equals('÷')) value = 5;
      else if(c.equals('+') || c.equals('-')) value = 3;
      else if(c.equals(')')) value = 8;
      return value;
    }

    /**
     * 求栈外优先级
      * @param c 运算符
     * @return 栈外优先级
     */
    private int ICP(Character c){
        int value = -1;
        if(c.equals('#')) value = 0;
        else if(c.equals('(')) value = 8;
        else if(c.equals('^') || c.equals('!')) value = 6;
        else if(c.equals('×') || c.equals('÷')) value = 4;
        else if(c.equals('+') || c.equals('-')) value = 2;
        else if(c.equals(')')) value = 1;
        return value;
    }

    /**
     * 强制文本从左至右显示
     */
    public static String textForceLTR(String text) {
        return "\u202D" + text + "\u202C";
    }

    /**
     * 删除字符串不可见字符
     * @param s 待处理字符串
     * @return 处理后字符串
     */
    public static String clearstr(String s) {
        s = s.replace("\u202D", "");
        s = s.replace("\u202C", "");
        return s;
    }

}