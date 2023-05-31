package com.example.myapplication;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.Interval;
import org.joda.time.Years;
import org.joda.time.Months;
import org.joda.time.Weeks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText startTimeEditText;
    private EditText endTimeEditText;
    private Button calculateButton;
    private Spinner unitSpinner;
    private TextView resultTextView;

    private String[] units = {"全部", "周+天","年", "月", "天", "小时", "分钟", "秒" };
    private boolean[] unitSelections = {false, false, false, false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startTimeEditText = findViewById(R.id.startTimeEditText);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        String currentTime = dateFormat.format(new Date());
        Toast.makeText(MainActivity.this, currentTime, Toast.LENGTH_LONG).show();
        // 将当前时间设置为EditText的默认文本
        startTimeEditText.setText(currentTime);
        endTimeEditText = findViewById(R.id.endTimeEditText);
        endTimeEditText.setText(currentTime);
        calculateButton = findViewById(R.id.calculateButton);
        unitSpinner = findViewById(R.id.unitSpinner);
        resultTextView = findViewById(R.id.resultTextView);


        startTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(startTimeEditText);
            }
        });

        endTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(endTimeEditText);
            }
        });

        // 设置单位下拉菜单的适配器
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(unitAdapter);

        // 监听单位下拉菜单的选择事件
        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 根据选择情况更新单位选择数组
                for (int i = 0; i < unitSelections.length; i++) {
                    unitSelections[i] = (i == position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startTimeString = startTimeEditText.getText().toString();
                String endTimeString = endTimeEditText.getText().toString();

                DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
                try {
                    DateTime startTime = format.parseDateTime(startTimeString);
                    DateTime endTime = format.parseDateTime(endTimeString);

                    Interval interval = new Interval(startTime, endTime);
                    Period period = new Period(startTime, endTime);
                    long seconds = interval.toDuration().getStandardSeconds();
                    long minutes = interval.toDuration().getStandardMinutes();
                    long hours = interval.toDuration().getStandardHours();
                    long days = interval.toDuration().getStandardDays();

//                    long years = interval.toDuration().getMillis();
//                    long months = interval.toDuration().getMillis();
                    // 计算年数差异
                    Years years = Years.yearsBetween(startTime, endTime);
                    int yearDifference = years.getYears();

                    // 计算月数差异
                    Months months = Months.monthsBetween(startTime, endTime);
                    int monthDifference = months.getMonths();

                    // 计算周数差异
                    Weeks weeks = Weeks.weeksBetween(startTime, endTime);
                    int weekDifference = weeks.getWeeks();
//                    int days = period.getDays();
//                    int hours = period.getHours();
//                    int minutes = period.getMinutes();
//                    int seconds = period.getSeconds();


                    // 获取差异的年、月、日、小时、分钟和秒
                    int Pyears = period.getYears();
                    int Pmonths = period.getMonths();
                    int Pweeks = period.getWeeks();
                    int Pdays = period.getDays();
                    int Phours = period.getHours();
                    int Pminutes = period.getMinutes();
                    int Pseconds = period.getSeconds();

                    // 拼接差异结果
                    String all = Pyears + "年" + Pmonths + "月" + Pweeks + "周"+ Pdays + "天" + Phours + "小时" + Pminutes + "分钟" + Pseconds + "秒";
//                    String all = String.valueOf(period);


                    StringBuilder resultBuilder = new StringBuilder();
                    for (int i = 0; i < unitSelections.length; i++) {
                        if (unitSelections[i]) {
                            switch (i) {
                                case 0: // 全部
//                                    if (seconds > 0) {
                                    resultBuilder.append(all);
//                                    }
                                    break;
                                case 1: // 周+天
//                                    if (seconds > 0) {
//                                    resultBuilder.append(weekDifference).append("周 ");
                                    resultBuilder.append(weekDifference).append("周").append(days%7).append("天");
//                                    }
                                    break;
                                case 2: // 年
//                                    if (years > 0) {
                                        resultBuilder.append(yearDifference).append("年 ");
//                                    }
                                    break;
                                case 3: // 月
//                                    if (months > 0) {
                                        resultBuilder.append(monthDifference).append("月 ");
//                                    }
                                    break;
                                case 4: // 天
//                                    if (days > 0) {
                                        resultBuilder.append(days).append("天 ");
//                                    }
                                    break;
                                case 5: // 小时
//                                    if (hours > 0) {
                                        resultBuilder.append(hours).append("小时 ");
//                                    }
                                    break;
                                case 6: // 分钟
//                                    if (minutes > 0) {
                                        resultBuilder.append(minutes).append("分钟 ");
//                                    }
                                    break;
                                case 7: // 秒
//                                    if (seconds > 0) {
                                        resultBuilder.append(seconds).append("秒 ");
//                                    }
                                    break;
                            }
                        }
                    }

                    String result = resultBuilder.toString().trim();
                    resultTextView.setText(result);

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "时间格式错误", Toast.LENGTH_SHORT).show();
                    String errorMessage = "时间格式错误";
                    resultTextView.setText(errorMessage);
                }
            }
        });
    }

    private void showDateTimePicker(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // 更新EditText文本为选择的日期时间
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                        String dateTime = format.format(calendar.getTime());
                        editText.setText(dateTime);
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        }, year, month, day);
        datePickerDialog.show();
    }
}
