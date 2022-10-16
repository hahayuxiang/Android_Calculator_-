package com.acgreenhorn.firstapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 车房贷计算器
 *
 * 支持两种方式计算利息，等额本金、等额本息
 *
 * 可计算还款月数、月均还款，本息总额、利息总额
 */
public class LoanActivity extends AppCompatActivity {
    /** 下拉框，利息计算方式*/
    Spinner spinner;
    /** 利息计算方式*/
    String loan_current_option = "";
    /** 下拉框选项*/
    List<String> loan_options = new ArrayList<>();
    ArrayAdapter<String> adapter;
    /** loan_clear为重置，loan_compute为计算*/
    Button loan_clear, loan_compute;
    /** input1为输入贷款总额，input2为输入贷款年限，input3为输入贷款利率*/
    TextView input1, input2, input3;
    /** output1为贷款月数，output2还款月数，output3为利息总额，output4为本息总额*/
    TextView output1, output2, output3, output4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局文件
        setContentView(R.layout.activity_loan);
        //获取控件
        loan_clear = findViewById(R.id.loan_button_1);
        loan_compute = findViewById(R.id.loan_button_2);
        input1 = findViewById(R.id.loan_input_1);
        input2 = findViewById(R.id.loan_input_2);
        input3 = findViewById(R.id.loan_input_3);

        input1.setText("");
        input2.setText("");
        input3.setText("");

        output1 = findViewById(R.id.loan_output_1);
        output2 = findViewById(R.id.loan_output_2);
        output3 = findViewById(R.id.loan_output_3);
        output4 = findViewById(R.id.loan_output_4);

        //事件监听
        loan_clear.setOnClickListener(new ClickOnLisenter());
        loan_compute.setOnClickListener(new ClickOnLisenter());

        loan_options.add("等额本息");
        loan_options.add("等额本金");
        spinner = (Spinner) findViewById(R.id.loan_spinner_data);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, loan_options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loan_current_option = loan_options.get(i);//返回利息计算方式
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * 事件响应
     * 数据重置与数据计算
     */
    class ClickOnLisenter implements View.OnClickListener{
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.loan_button_1://按下重置，将所有输出文本清空
                        input1.setText("");
                        input2.setText("");
                        input3.setText("");
                        break;
                    case R.id.loan_button_2://按下计算，计算后将结果输出
                        String loan_all, loan_term, loan_rate;
                        loan_all = input1.getText().toString();
                        loan_term = input2.getText().toString();
                        loan_rate = input3.getText().toString();
                        //检测数据合法性
                        if(!loan_all.equals("") && !loan_term.equals("") && !loan_rate.equals("")) {
                            if(is_double(loan_all) && is_double(loan_term) && is_double(loan_rate)){
                                loan_display_result(loan_all, loan_term, loan_rate, loan_current_option);
                            }else {
                                Toast.makeText(getApplicationContext(), "数据输入错误！", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
            }
        }
    }

    /**
     * 计算车房贷结果，并且显示
     * @param loan_all 贷款总额
     * @param loan_term 贷款年限
     * @param loan_rate 贷款利率
     * @param loan_current_option 利息计算方式
     */
    @SuppressLint("DefaultLocale")
    private void loan_display_result(String loan_all, String loan_term, String loan_rate, String loan_current_option) {
        double num_all = Double.parseDouble(loan_all) * 10000;//还款总额
        double num_term = Double.parseDouble(loan_term) * 12; //还款月数
        double num_rate = Double.parseDouble(loan_rate) / 1200; // 月利率
        if(loan_current_option.equals("等额本息")){
            output1.setText(String.format("%.2f", num_term));
            double num_month = num_all * num_rate * Math.pow(1 + num_rate, num_term) / (Math.pow(1+num_rate, num_term) - 1);
            output2.setText(String.format("%.2f", num_month));
            double num_ben_and_xi = num_month * num_term;
            output4.setText(String.format("%.2f", num_ben_and_xi));
            double num_xi = num_ben_and_xi - num_all;
            output3.setText(String.format("%.2f", num_xi));
        }else{
            double num_xi_and_ben = 0;
            for (int i = 0; i < num_term; i ++){
                num_xi_and_ben = num_xi_and_ben + num_all / num_term + (num_all - num_all / num_term * i) * num_rate;
            }
            double num_month = num_xi_and_ben / num_term;
            double num_xi = num_xi_and_ben - num_all;
            output1.setText(String.format("%.2f", num_term));
            output2.setText(String.format("%.2f", num_month));
            output3.setText(String.format("%.2f", num_xi));
            output4.setText(String.format("%.2f", num_xi_and_ben));
        }
    }

    /**
     * 检测数据是否合法
     * @param input 输入数据
     * @return true-合法 false-非法
     */
    private boolean is_double(String input) {
        try{
            Double.parseDouble(input);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
}