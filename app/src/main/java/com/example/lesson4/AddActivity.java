package com.example.lesson4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    String color;
    String model;
    String body;
    Date year;
    TextView dateTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dateTV = findViewById(R.id.tv_add_year);

        final EditText colorET = findViewById(R.id.et_add_color);
        final EditText modelET = findViewById(R.id.et_add_model);
        EditText bodyET = findViewById(R.id.et_add_body);

        bodyET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                body = s.toString();
            }
        });

        modelET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                model = s.toString();
            }
        });

        colorET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                color = s.toString();
            }
        });

        Button chooseDate = findViewById(R.id.btn_add_year);
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Log.d("ololo", "year: " + year + " month: " + (month + 1) + " day: " + day);
                                dateTV.setText(dayOfMonth + "." + (month + 1) + "." + year);
                                AddActivity.this.year = new Date(year, month, dayOfMonth);
                            }
                        }, year, month, day);
                dialog.show();
            }
        });

        Button save = findViewById(R.id.btn_save_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Car car = new Car();
                car.body = body;
                car.color = color;
                car.model = model;
                car.year = year;

                Intent intent = new Intent();
                intent.putExtra("car", car);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        Intent intent = getIntent();
        if (intent != null) {
            Car car = (Car) intent.getSerializableExtra("car");
            if (car != null) {

                modelET.setText(car.model);
                colorET.setText(car.color);
                bodyET.setText(car.body);
                DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                chooseDate.setText(format.format(car.year));
            }
        }

    }
}