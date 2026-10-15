package com.example.project;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    Button button1, button2, button3;

    final int pinkColor = Color.parseColor("#FF4081");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity2.this);

                builder.setTitle("알림");
                builder.setIcon(R.drawable.alarm);
                builder.setMessage("이번주 레포트는 Dialog 입니다");

                builder.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(
                                        MainActivity2.this,
                                        "확인했습니다",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        });

                AlertDialog dialog = builder.create();

                dialog.setOnShowListener(
                        new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface dialogInterface) {

                                Button positiveButton =
                                        ((AlertDialog) dialogInterface)
                                                .getButton(AlertDialog.BUTTON_POSITIVE);

                                positiveButton.setTextColor(pinkColor);
                            }
                        });

                dialog.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity2.this);

                builder.setTitle("이름 입력");

                final EditText editText = new EditText(MainActivity2.this);

                ColorStateList pinkTint = new ColorStateList(
                        new int[][]{
                                new int[]{android.R.attr.state_focused},
                                new int[]{}
                        },
                        new int[]{
                                Color.parseColor("#FF4081"),
                                Color.parseColor("#FF4081")
                        }
                );

                editText.setBackgroundTintList(pinkTint);

                builder.setView(editText);

                builder.setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String name = editText.getText()
                                        .toString().trim();

                                if (name.isEmpty()) {
                                    Toast.makeText(
                                            MainActivity2.this,
                                            "입력해주세요",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                } else {
                                    Toast.makeText(
                                            MainActivity2.this,
                                            name,
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            }
                        });

                AlertDialog dialog = builder.create();

                dialog.setOnShowListener(
                        new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface dialogInterface) {

                                AlertDialog dlg =
                                        (AlertDialog) dialogInterface;

                                dlg.getButton(AlertDialog.BUTTON_POSITIVE)
                                        .setTextColor(pinkColor);

                                dlg.getButton(AlertDialog.BUTTON_NEGATIVE)
                                        .setTextColor(pinkColor);
                            }
                        });

                dialog.show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] items = {
                        "독서", "등산", "수영", "수집", "영화"
                };

                final boolean[] checkedItems = {
                        false, false, false, false, false
                };

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(
                                MainActivity2.this,
                                R.style.PinkDialogTheme
                        );

                builder.setTitle("당신의 취미는?");
                builder.setIcon(R.drawable.grade_icon);

                builder.setMultiChoiceItems(
                        items,
                        checkedItems,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(
                                    DialogInterface dialog,
                                    int which,
                                    boolean isChecked
                            ) {
                                checkedItems[which] = isChecked;
                            }
                        }
                );

                builder.setPositiveButton("닫기",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                List<String> selectedList = new ArrayList<>();

                                for (int i = 0; i < checkedItems.length; i++) {
                                    if (checkedItems[i]) {
                                        selectedList.add(items[i]);
                                    }
                                }

                                if (selectedList.isEmpty()) {
                                    Toast.makeText(
                                            MainActivity2.this,
                                            "취미를 선택해주세요",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                } else {
                                    StringBuilder sb =
                                            new StringBuilder("취미 : ");

                                    for (int i = 0; i < selectedList.size(); i++) {
                                        sb.append(selectedList.get(i));

                                        if (i < selectedList.size() - 1) {
                                            sb.append(", ");
                                        }
                                    }

                                    Toast.makeText(
                                            MainActivity2.this,
                                            sb.toString(),
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            }
                        });

                AlertDialog dialog = builder.create();

                dialog.setOnShowListener(
                        new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface dialogInterface) {

                                Button positiveButton =
                                        ((AlertDialog) dialogInterface)
                                                .getButton(AlertDialog.BUTTON_POSITIVE);

                                positiveButton.setTextColor(pinkColor);
                            }
                        });

                dialog.show();
            }
        });
    }
}