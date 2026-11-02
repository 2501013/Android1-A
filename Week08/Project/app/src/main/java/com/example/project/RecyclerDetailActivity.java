package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RecyclerDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("RecyclerView 예제");
        }

        TextView textNumber = findViewById(R.id.textNumber);
        TextView textName = findViewById(R.id.textName);
        TextView textJob = findViewById(R.id.textJob);

        Button btnBack = findViewById(R.id.btnBack);

        String[] members = getResources().getStringArray(R.array.member);
        String[] births = getResources().getStringArray(R.array.birth);
        String[] jobs = getResources().getStringArray(R.array.job);

        int position = getIntent().getIntExtra("position", 0);

        int age = 2026 - Integer.parseInt(births[position]);

        textNumber.setText((position + 1) + " 번째");
        textName.setText(members[position] + " (" + age + " 세)");
        textJob.setText(jobs[position]);

        btnBack.setBackgroundColor(MainActivity.current);
        btnBack.setTextColor(MainActivity.currentText);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
