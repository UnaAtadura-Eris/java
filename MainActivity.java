package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Locale;
import androidx.appcompat.app.AppCompatActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText startTimeEditText;
    private EditText endTimeEditText;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startTimeEditText = findViewById(R.id.startTimeEditText);// 获取当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        String currentTime = dateFormat.format(new Date());
        // 将当前时间设置为EditText的默认文本
        startTimeEditText.setText(currentTime);

        endTimeEditText = findViewById(R.id.endTimeEditText);
        endTimeEditText.setText(currentTime);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startTimeString = startTimeEditText.getText().toString();
                String endTimeString = endTimeEditText.getText().toString();

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    Date startTime = format.parse(startTimeString);
                    Date endTime = format.parse(endTimeString);

                    long diff = endTime.getTime() - startTime.getTime();

                    long seconds = diff / 1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    long days = hours / 24;

                    resultTextView.setText(String.format("时间间隔：%d天 %d小时 %d分钟 %d秒", days, hours % 24, minutes % 60, seconds % 60));
                } catch (ParseException e) {
                    e.printStackTrace();
                    resultTextView.setText("时间格式错误");
                }
            }
        });
    }
}
