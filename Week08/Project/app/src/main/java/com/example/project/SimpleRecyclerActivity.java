package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SimpleRecyclerActivity extends AppCompatActivity {

    String[] members;
    String[] births;
    String[] jobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("RecyclerView 예제");
        }

        ListView listView = findViewById(R.id.recyclerView);
        Button btnBack = findViewById(R.id.btnBack);

        btnBack.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(MainActivity.current));

        btnBack.setTextColor(MainActivity.currentText);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        members = getResources().getStringArray(R.array.member);
        births = getResources().getStringArray(R.array.birth);
        jobs = getResources().getStringArray(R.array.job);

        String[] memberInfo = new String[members.length];

        for (int i = 0; i < members.length; i++) {
            int age = 2026 - Integer.parseInt(births[i]) + 1;

            memberInfo[i] = members[i] + " (" + age + " 세)\n"
                    + jobs[i];
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_recycler,
                R.id.textMember,
                memberInfo
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(
                    SimpleRecyclerActivity.this,
                    MemberDetailActivity.class
            );

            intent.putExtra("position", position);
            intent.putExtra("member", members[position]);
            intent.putExtra("birth", births[position]);
            intent.putExtra("job", jobs[position]);

            startActivity(intent);
        });
    }
}