package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MemberDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);

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

        int position = getIntent().getIntExtra("position", 0);
        String member = getIntent().getStringExtra("member");
        String birth = getIntent().getStringExtra("birth");
        String job = getIntent().getStringExtra("job");

        int age = 2026 - Integer.parseInt(birth) + 1;

        textNumber.setText((position + 1) + " 번째");
        textName.setText(member + " (" + age + " 세)");
        textJob.setText(job);

        btnBack.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(MainActivity.current));

        btnBack.setTextColor(MainActivity.currentText);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
