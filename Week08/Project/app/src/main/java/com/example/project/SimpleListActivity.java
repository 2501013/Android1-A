package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SimpleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("ListView 예제");
        }

        ListView listView = findViewById(R.id.listView);
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

        String[] members = getResources().getStringArray(R.array.member);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_member,
                R.id.textMember,
                members
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(
                    SimpleListActivity.this,
                    members[position],
                    Toast.LENGTH_SHORT
            ).show();
        });
    }
}