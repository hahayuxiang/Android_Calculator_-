package com.acgreenhorn.firstapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *汇率转换
 *支持十种货币相互转换
 */
public class CurrencyActivity extends AppCompatActivity {

    /** spinner_1为源货币，spinner_2为目标货币*/
    Spinner spinner_1, spinner_2;
    /** button_1为汇率获取，button_2为汇率转换*/
    Button button_1, button_2;
    /** 显示文本框，input输入货币，output输出货币*/
    TextView tip_1, tip_2, input, output;
    /** 下拉框选项，adapter_1为源货币，adapter_2为目标货币*/
    ArrayAdapter<String> adapter_1, adapter_2;
    /** 存储选择的货币，idx_1源货币，idx_2目标货币*/
    int idx_1, idx_2;

    /** 全部货币类型*/
    String []all_country = new String[]{"人民币", "美元", "欧元", "日元", "港币", "英镑", "澳元", "新西兰元", "瑞士法郎",
            "加元"};
    /** 全部货币汇率*/
    List<String> all_money = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);//设置布局文件
        spinner_1 = findViewById(R.id.currency_spinner_1);
        spinner_2 = findViewById(R.id.currency_spinner_2);
        button_1 = findViewById(R.id.currency_button_1);
        button_2 = findViewById(R.id.currency_button_2);
        tip_1 = findViewById(R.id.currency_tip_1);
        tip_2 = findViewById(R.id.currency_tip_2);
        input = findViewById(R.id.currency_input);
        output = findViewById(R.id.currency_output);

        button_1.setOnClickListener(new ClickOnLisenter());//添加事件监听
        button_2.setOnClickListener(new ClickOnLisenter());

        setspinner();

        all_money.add("1");//人民币->人民币 汇率为 1
        tip_1.setText("当前未获得信息");//设置初始状态显示

    }

    /**
     * 设置两个下拉框的选项，为各国货币
     */
    private void setspinner() {

        adapter_1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, all_country);//使用系统默认布局界面
        spinner_1.setAdapter(adapter_1);
        spinner_1.setSelection(0, true);

        adapter_2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, all_country);
        spinner_2.setAdapter(adapter_2);
        spinner_2.setSelection(0, true);

        spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idx_1 = i;//获取选中货币
                tip_2.setText(all_country[i]);//文本显示相应改变
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idx_2 = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * 事件响应
     * 实现获取汇率与货币转换
     */
    class ClickOnLisenter implements View.OnClickListener {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.currency_button_1://按下获取汇率信息
                    if (checkNetworkAvailable(getApplicationContext())) {
                        if (tip_1.getText().toString().equals("当前未获得信息")) {
                            settle_data();
                            tip_1.setText("当前已获得信息");
                            Toast.makeText(getApplicationContext(), "已获得今日的汇率信息", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "当前已经获得今日的汇率信息，请勿重复点击！", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "请确认网络是否连接及有效！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.currency_button_2://按下汇率转换
                    if(is_double(input.getText().toString())) {
                        if (tip_1.getText().toString().equals("当前已获得信息")) {
                            BigDecimal num_1 = new BigDecimal(all_money.get(idx_1));//源货币
                            BigDecimal num_2 = new BigDecimal(all_money.get(idx_2));//目标货币
                            BigDecimal value = new BigDecimal(input.getText().toString());
                            value = value.multiply(num_1);
                            value = value.divide(num_2, 10, RoundingMode.HALF_UP);//四舍五入保留十位小数
                            String display = input.getText().toString() + all_country[idx_1] + " = ";
                            display = display + value.toPlainString() + all_country[idx_2];
                            output.setText(display);
                        }else{
                            Toast.makeText(getApplicationContext(), "请先获取今日汇率信息！", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "请输入合法数据！", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    /**
     *
     * 对汇率进行预处理
     * 解决汇率单位不统一问题
     */
    private void presettlt_list() {
        Double t_1 = Double.parseDouble(all_money.get(3));
        t_1 /= 100;
        all_money.set(3, t_1 + "");
    }

    /**
     * 判断输入数据是否合法
     * @param input 输入的数据
     * @return true-合法 flase-不合法
     */
    private boolean is_double(String input) {
        try{
            Double.parseDouble(input);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }


    /**
     * 判断是否连接网络
     * @param context 上下文
     * @return true-连接 false-连接失败
     */
    public static boolean checkNetworkAvailable(Context context) {
        //获取网络，判断其是否能连接
         ConnectivityManager connectivity = (ConnectivityManager) context
                         .getSystemService(Context.CONNECTIVITY_SERVICE);
         if (connectivity == null) {
             return false;
         } else {
             NetworkInfo[] info = connectivity.getAllNetworkInfo();//获取设备支持的网络状态
             if (info != null) {
                 for (NetworkInfo networkInfo : info) {
                     if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                         if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {//wifi或是移动网络状态返回true
                             return true;
                         } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                             return true;
                         }
                     }
                 }
            }
         }
         return false;
     }


//  同步实现
//    private void settle_data() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    OkHttpClient client =new OkHttpClient();
//                    Request request = new Request.Builder()
//                            .url("https://www.mxnzp.com/api/exchange_rate/list?app_id=rfjowlnqqhqsnqrm&app_secret=endFeFJNamZNdXg1VlkxRkFKRVJhdz09")
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    String Data = response.body().string();
//                    json2data(Data);
//                }catch (Exception e){
//                    Toast.makeText(getApplicationContext(), "网络无连接或今日获取汇率次数已达1000次！", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }).start();
//    }
//  异步实现

    /**
     * 通过接口获取汇率
     */
    private void settle_data() {
        OkHttpClient client =new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.mxnzp.com/api/exchange_rate/list?app_id=rfjowlnqqhqsnqrm&app_secret=endFeFJNamZNdXg1VlkxRkFKRVJhdz09")
                .build();
        Call call = client.newCall(request);//请求加入调度
        call.enqueue(new Callback() {//重写回调方法，提交异步请求
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String Data = response.body().string();//以字符串返回
                json2data(Data);
             }
        });
    }

    /**
     * 将得到的字符串的数据以json进行处理
     * @param Data 汇率数据
     */
    private void json2data(String Data) {
        try {
            //String转JSONObject
            JSONObject jsonObject = new JSONObject(Data);
            //取数据
            JSONArray money_array = new JSONArray(jsonObject.getJSONArray("data").toString());
            for(int i = 0; i < 10; i ++){
                JSONObject now_country = (JSONObject) money_array.get(i);
                //数据进行处理后放到all_money中
                all_money.add(now_country.getString("price"));
            }
            presettlt_list();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}