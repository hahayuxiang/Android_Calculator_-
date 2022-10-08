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


public class CurrencyActivity extends AppCompatActivity {

    Spinner spinner_1, spinner_2;
    Button button_1, button_2;
    TextView tip_1, tip_2, input, output;
    ArrayAdapter<String> adapter_1, adapter_2;
    int idx_1, idx_2;


    String []all_country = new String[]{"人民币", "美元", "欧元", "日元", "港币", "英镑", "澳元", "新西兰元", "瑞士法郎",
            "加元"};
    List<String> all_money = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        spinner_1 = findViewById(R.id.currency_spinner_1);
        spinner_2 = findViewById(R.id.currency_spinner_2);
        button_1 = findViewById(R.id.currency_button_1);
        button_2 = findViewById(R.id.currency_button_2);
        tip_1 = findViewById(R.id.currency_tip_1);
        tip_2 = findViewById(R.id.currency_tip_2);
        input = findViewById(R.id.currency_input);
        output = findViewById(R.id.currency_output);

        button_1.setOnClickListener(new ClickOnLisenter());
        button_2.setOnClickListener(new ClickOnLisenter());

        setspinner();

        all_money.add("1");
        tip_1.setText("当前未获得信息");

    }

    private void setspinner() {
        adapter_1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, all_country);
        spinner_1.setAdapter(adapter_1);
        spinner_1.setSelection(0, true);

        adapter_2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, all_country);
        spinner_2.setAdapter(adapter_2);
        spinner_2.setSelection(0, true);

        spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idx_1 = i;
                tip_2.setText(all_country[i]);
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

    class ClickOnLisenter implements View.OnClickListener {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.currency_button_1:
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
                case R.id.currency_button_2:
                    if(is_double(input.getText().toString())) {
                        if (tip_1.getText().toString().equals("当前已获得信息")) {
                            presettlt_list();
                            BigDecimal num_1 = new BigDecimal(all_money.get(idx_1));
                            BigDecimal num_2 = new BigDecimal(all_money.get(idx_2));
                            BigDecimal value = new BigDecimal(input.getText().toString());
                            value = value.multiply(num_1);
                            value = value.divide(num_2, 10, RoundingMode.HALF_UP);
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

    private void presettlt_list() {
        Double t_1 = Double.parseDouble(all_money.get(3));
        t_1 /= 100;
        all_money.set(3, t_1 + "");
    }
    private boolean is_double(String input) {
        try{
            Double.parseDouble(input);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
    public static boolean checkNetworkAvailable(Context context) {
         ConnectivityManager connectivity = (ConnectivityManager) context
                         .getSystemService(Context.CONNECTIVITY_SERVICE);
         if (connectivity == null) {
             return false;
         } else {
             NetworkInfo[] info = connectivity.getAllNetworkInfo();
             if (info != null) {
                 for (NetworkInfo networkInfo : info) {
                     if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                         if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
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
    private void settle_data() {
        OkHttpClient client =new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.mxnzp.com/api/exchange_rate/list?app_id=rfjowlnqqhqsnqrm&app_secret=endFeFJNamZNdXg1VlkxRkFKRVJhdz09")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String Data = response.body().string();
                json2data(Data);
            }
        });
    }

    private void json2data(String Data) {
        try {
            //String转JSONObject
            JSONObject jsonObject = new JSONObject(Data);
            //取数据
            JSONArray money_array = new JSONArray(jsonObject.getJSONArray("data").toString());
            for(int i = 0; i < 10; i ++){
                JSONObject now_country = (JSONObject) money_array.get(i);
                all_money.add(now_country.getString("price"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}