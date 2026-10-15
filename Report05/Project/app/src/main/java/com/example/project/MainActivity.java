package com.example.project;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.graphics.Typeface;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);


        editTextName.setCursorVisible(false);
        editTextName.setShowSoftInputOnFocus(false);


        SpannableString hint =
                new SpannableString("학년 입력 바람");

        hint.setSpan(
                new StyleSpan(Typeface.BOLD),
                0,
                hint.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        editTextName.setHint(hint);


        editTextName.setOnClickListener(v -> showGradeDialog());
    }

    private void showGradeDialog() {

        String[] grades = {
                "1학년",
                "2학년",
                "3학년",
                "4학년"
        };

        AlertDialog.Builder dialog =
                new AlertDialog.Builder(MainActivity.this);

        dialog.setTitle("몇학년 입니까?");


        dialog.setIcon(R.drawable.grade_icon);

        dialog.setSingleChoiceItems(
                grades,
                -1,
                (d, which) -> {
                    editTextName.setText(grades[which]);
                    editTextName.setCursorVisible(false);

                    d.dismiss();
                }
        );


        dialog.setNegativeButton("닫기", (d, which) -> {
            d.dismiss();
        });

        dialog.show();
    }
}