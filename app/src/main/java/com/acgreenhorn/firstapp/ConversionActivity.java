package com.acgreenhorn.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ConversionActivity extends AppCompatActivity {

    Spinner type, opt_1, opt_2;
    String current_type = "长度";
    int idx_opt_1 = 0, idx_opt_2 = 0;
    Button button_change;
    TextView input, output;
    ArrayAdapter<String> typeAdapter, opt_1_Adapter, opt_2_Adapter;
    String [][]dataList = new String[][]{
            {"1", "0.125", "0.000122070313", "0.000000119209", "1.164153e-10", "1.136868e-13", "1.110223e-16"},
            {"8", "1", "0.0009765625", "0.000000953674", "9.313226e-10", "9.094947e-13", "8.881784e-16"},
            {"8192", "1024", "1", "0.0009765625", "0.000000953674", "9.313226e-10", "9.094947e-13"},
            {"8388608", "1048576", "1024", "1", "0.0009765625", "0.000000953674", "9.313226e-10"},
            {"8589934592", "1073741824", "1048576", "1024","1", "0.0009765625", "0.000000953674"},
            {"8.796093e+12", "1.099512e+12", "1073741824", "1048576", "1024", "1", "0.0009765625"},
            {"9.007199e+15", "1.1259e+15", "1.099512e+12", "1073741824", "1048576", "1024", "1"}
    };
    String [][]timeList = new String[][]{
            {"1", "52", "365", "8760", "525600", "31536000", "3.1536e+10"},
            {"0.01917808", "1", "7", "168", "10080", "604800", "604800000"},
            {"0.002739726", "0.142857142857", "1", "24", "1440", "86400", "86400000"},
            {"0.00011415525", "0.005952380952", "0.041666666667", "1", "60", "3600", "3600000"},
            {"0.000001902587", "0.000099206349", "0.000694444444", "0.016666666667", "1", "60", "60000"},
            {"3.170919e-8", "0.000001653439", "0.000011574074", "0.000277777778", "0.016666666667", "1", "1000"},
            {"3.170919e-11", "1.653439e-9", "1.157407e-8", "0.000000277777", "0.000016666667", "0.001", "1"}
    };
    double []LEN = new double[]{1e3, 1, 1e-12, 1e-9, 1e-6, 1e-3, 1e-2, 1e-1};
    double []CAP = new double[]{1e-9, 1e-6, 1e-3, 1, 1e9, 1e-6, 1e-3};
    double []QUL = new double[]{1e-6, 1e-3, 2e-1, 1, 1e3, 1e6};
    double []AREA = new double[]{1e-18, 1e-12, 1e-6, 1e-4, 1e-2, 1, 1e4, 1e6};
    double []DATA = new double[]{1.25e-1, 1, 1.024e3, 1.048576e6, 1.073741824e9, 1.099512e+12, 1.1259e+15};
    double []TIME = new double[]{3.65e2, 7, 1, 2.4e1, 1.44e3, 8.64e4, 8.64e7, 8.64e10};
//    原来是以秒位基准的
    String[] all_type = new String[]{"长度", "容量", "质量", "面积", "数据", "时间",};
    String[] length = new String[]{"千米(km)", "米(m)", "皮米(pm)", "纳米(nm)", "微米(um)", "毫米(mm)", "厘米(cm)", "分米(dm)"};
    String[] capacity = new String[]{"立方毫米(mm³)", "立方厘米(cm³)", "立方分米(dm³)", "立方米(m³)", "立方千米(km³)", "毫升(ml)", "升(l)"};
    String[] quality = new String[]{"微克(mcg)", "毫克(mg)", "克拉(cg)", "克(g)", "千克(kg)", "吨(t)"};
    String[] area = new String[]{"平方纳米(nm²)", "平方微米(um²)", "平方毫米(mm²)", "平方厘米(cm²)", "平方分米(dm²)", "平方米(m²)", "公顷(ha)", "平方千米(km²)"};
    String[] data = new String[]{"Bit", "Byte", "Kib", "Mib", "Gib", "Tib", "Pib"};
    String[] time = new String[]{"年", "周", "天", "时", "分", "秒", "毫秒"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        opt_1 = (Spinner) findViewById(R.id.opt_1);
        opt_2 = (Spinner) findViewById(R.id.opt_2);
        type = (Spinner) findViewById(R.id.type);
        input = findViewById(R.id.conversion_input);
        output = findViewById(R.id.conversion_output);
        button_change = findViewById(R.id.conversion_button);

        button_change.setOnClickListener(new ConversionActivity.ClickOnLisenter());
        setSpinner();
    }

    class ClickOnLisenter implements View.OnClickListener {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.conversion_button) {
                double value;
                if (is_double(input.getText().toString())) {
                    value = Double.parseDouble(input.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "输入数据不合法！", Toast.LENGTH_SHORT).show();
                    return;
                }
                double t_1, t_2;
                BigDecimal ans;
                switch (current_type) {
                    case "长度":
                        t_1 = LEN[idx_opt_1];
                        t_2 = LEN[idx_opt_2];
                        break;
                    case "容量":
                        t_1 = CAP[idx_opt_1];
                        t_2 = CAP[idx_opt_2];
                        break;
                    case "质量":
                        t_1 = QUL[idx_opt_1];
                        t_2 = QUL[idx_opt_2];
                        break;
                    case "面积":
                        t_1 = AREA[idx_opt_1];
                        t_2 = AREA[idx_opt_2];
                        break;
                    case "数据":
                        t_1 = DATA[idx_opt_1];
                        t_2 = DATA[idx_opt_2];
                        break;
                    default:
                        t_1 = TIME[idx_opt_1];
                        t_2 = TIME[idx_opt_2];
                        break;
                }
                BigDecimal p_value = new BigDecimal(Double.toString(value));

                if (current_type.equals("数据") || current_type.equals("时间")) {
                    if (current_type.equals("数据")) {
                        BigDecimal findnow = new BigDecimal(dataList[idx_opt_1][idx_opt_2]);
                        ans = findnow.multiply(p_value);
                        output.setText(ans.toString());
                    } else {
                        BigDecimal findnow = new BigDecimal(timeList[idx_opt_1][idx_opt_2]);
                        ans = findnow.multiply(p_value);
                        output.setText(ans.toString());
                    }
                } else {
                    BigDecimal p_1 = new BigDecimal(Double.toString(t_1));
                    BigDecimal p_2 = new BigDecimal(Double.toString(t_2));
                    ans = p_value.multiply(p_1);
                    ans = ans.divide(p_2);
                    output.setText(ans.toString());
                }
            }
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

    private void setSpinner() {
        typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, all_type);
        type.setAdapter(typeAdapter);
        type.setSelection(0, true);

        opt_1_Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, length);
        opt_1.setAdapter(opt_1_Adapter);
        opt_1.setSelection(0, true);

        opt_2_Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, length);
        opt_2.setAdapter(opt_2_Adapter);
        opt_2.setSelection(0, true);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                current_type = all_type[i];
                switch (current_type) {
                    case "长度":
                        opt_1_Adapter = new ArrayAdapter<>(ConversionActivity.this, android.R.layout.simple_spinner_item, length);
                        opt_1.setAdapter(opt_1_Adapter);
                        opt_2_Adapter = new ArrayAdapter<>(ConversionActivity.this, android.R.layout.simple_spinner_item, length);
                        opt_2.setAdapter(opt_2_Adapter);
                        break;
                    case "容量":
                        opt_1_Adapter = new ArrayAdapter<>(ConversionActivity.this, android.R.layout.simple_spinner_item, capacity);
                        opt_1.setAdapter(opt_1_Adapter);
                        opt_2_Adapter = new ArrayAdapter<>(ConversionActivity.this, android.R.layout.simple_spinner_item, capacity);
                        opt_2.setAdapter(opt_2_Adapter);
                        break;
                    case "质量":
                        opt_1_Adapter = new ArrayAdapter<>(ConversionActivity.this, android.R.layout.simple_spinner_item, quality);
                        opt_1.setAdapter(opt_1_Adapter);
                        opt_2_Adapter = new ArrayAdapter<>(ConversionActivity.this, android.R.layout.simple_spinner_item, quality);
                        opt_2.setAdapter(opt_2_Adapter);
                        break;
                    case "面积":
                        opt_1_Adapter = new ArrayAdapter<>(ConversionActivity.this, android.R.layout.simple_spinner_item, area);
                        opt_1.setAdapter(opt_1_Adapter);
                        opt_2_Adapter = new ArrayAdapter<>(ConversionActivity.this, android.R.layout.simple_spinner_item, area);
                        opt_2.setAdapter(opt_2_Adapter);
                        break;
                    case "数据":
                        opt_1_Adapter = new ArrayAdapter<>(ConversionActivity.this, android.R.layout.simple_spinner_item, data);
                        opt_1.setAdapter(opt_1_Adapter);
                        opt_2_Adapter = new ArrayAdapter<>(ConversionActivity.this, android.R.layout.simple_spinner_item, data);
                        opt_2.setAdapter(opt_2_Adapter);
                        break;
                    default:
                        opt_1_Adapter = new ArrayAdapter<>(ConversionActivity.this, android.R.layout.simple_spinner_item, time);
                        opt_1.setAdapter(opt_1_Adapter);
                        opt_2_Adapter = new ArrayAdapter<>(ConversionActivity.this, android.R.layout.simple_spinner_item, time);
                        opt_2.setAdapter(opt_2_Adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        opt_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idx_opt_1 = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        opt_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idx_opt_2 = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}