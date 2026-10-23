package com.example.project;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        spinner = findViewById(R.id.spinner);
        String[] fruits = getResources().getStringArray(R.array.fruits);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, R.id.spinnerText, fruits) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = view.findViewById(R.id.spinnerText);
                text.setTextColor(Color.BLACK);
                text.setTextSize(14);
                text.setGravity(Gravity.CENTER_VERTICAL);
                text.setPadding(8, 0, 8, 0);
                text.setSingleLine(true);
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView text = view.findViewById(R.id.spinnerText);
                text.setTextColor(position == 0 ? Color.RED : Color.BLACK);
                text.setTextSize(14);
                int itemHeight = (int) (51 * getResources().getDisplayMetrics().density + 0.5f);
                text.setHeight(itemHeight);
                text.setGravity(Gravity.CENTER_VERTICAL);
                text.setPadding(8, 0, 8, 0);
                text.setSingleLine(true);
                text.setBackgroundColor(Color.rgb(250, 250, 250));
                return view;
            }
        };

        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    String fruit = parent.getItemAtPosition(position).toString();
                    Toast.makeText(MainActivity3.this, fruit + "는 맛있다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}