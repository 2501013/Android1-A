package com.example.project;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private Handler handler;
    private Runnable progressRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        handler = new Handler(Looper.getMainLooper());


        Button button1 = findViewById(R.id.button1);

        button1.setOnClickListener(v -> {
            ProgressDialog progressDialog =
                    new ProgressDialog(MainActivity3.this);

            progressDialog.setTitle("프로그래스 다이얼로그\n(Spinner)");
            progressDialog.setIcon(R.drawable.key);
            progressDialog.setMessage("현재 진행중입니다.");

            progressDialog.setButton(
                    ProgressDialog.BUTTON_NEGATIVE,
                    "취소",
                    (dialog, which) -> {
                        Toast.makeText(
                                MainActivity3.this,
                                "취소하였습니다",
                                Toast.LENGTH_SHORT
                        ).show();

                        progressDialog.dismiss();
                    }
            );

            progressDialog.show();

            if (progressDialog.getWindow() != null) {
                progressDialog.getWindow().clearFlags(
                        WindowManager.LayoutParams.FLAG_DIM_BEHIND
                );
            }
        });


        Button button2 = findViewById(R.id.button2);

        button2.setOnClickListener(v -> {
            ProgressDialog dialog =
                    new ProgressDialog(MainActivity3.this);

            dialog.setTitle("프로그래스 다이얼로그\n(Horizontal)");
            dialog.setIcon(R.drawable.key);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMessage("잠시만 기다려 주세요.");
            dialog.setMax(100);
            dialog.setProgress(0);

            dialog.setButton(
                    ProgressDialog.BUTTON_NEGATIVE,
                    "취소",
                    (dialogInterface, which) -> {
                        if (progressRunnable != null) {
                            handler.removeCallbacks(progressRunnable);
                            progressRunnable = null;
                        }

                        Toast.makeText(
                                MainActivity3.this,
                                "취소하였습니다",
                                Toast.LENGTH_SHORT
                        ).show();

                        dialog.dismiss();
                    }
            );

            dialog.show();

            if (dialog.getWindow() != null) {
                dialog.getWindow().setDimAmount(0f);
            }

            final int[] progress = {0};

            progressRunnable = new Runnable() {
                @Override
                public void run() {
                    if (!dialog.isShowing()) {
                        handler.removeCallbacks(this);
                        return;
                    }

                    progress[0] += 10;
                    dialog.setProgress(progress[0]);

                    if (progress[0] < 100) {
                        handler.postDelayed(this, 500);
                    } else {
                        dialog.setMessage("로딩이 완료되었습니다.");

                        Toast.makeText(
                                MainActivity3.this,
                                "로딩이 완료되었습니다.",
                                Toast.LENGTH_SHORT
                        ).show();

                        progressRunnable = null;
                    }
                }
            };

            handler.postDelayed(progressRunnable, 500);
        });

        Button button3 = findViewById(R.id.button3);

        button3.setOnClickListener(v -> {

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(
                            MainActivity3.this,
                            R.style.CustomAlertDialogTheme
                    );


            TextView title = new TextView(MainActivity3.this);

            title.setText("커스텀 다이얼로그");
            title.setTextSize(24);
            title.setTextColor(Color.WHITE);
            title.setTypeface(null, Typeface.BOLD);
            title.setGravity(Gravity.CENTER_VERTICAL);
            title.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ma, 0, 0, 0
            );
            title.setCompoundDrawablePadding(20);
            title.setPadding(30, 200, 20, 200);

            builder.setCustomTitle(title);


            View dialogView = getLayoutInflater().inflate(
                    R.layout.custom_dialog,
                    null
            );

            builder.setView(dialogView);

            EditText editText1 =
                    dialogView.findViewById(R.id.editText1);

            EditText editText2 =
                    dialogView.findViewById(R.id.editText2);

            Button buttonLogin =
                    dialogView.findViewById(R.id.buttonLogin);


            builder.setNegativeButton(
                    "취소",
                    (dialogInterface, which) -> {
                        Toast.makeText(
                                MainActivity3.this,
                                "취소하였습니다",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
            );


            builder.setPositiveButton("확인", (dialogInterface, which) -> {
            });

            AlertDialog dialog = builder.create();

            dialog.setCancelable(false);


            dialog.getWindow();

            dialog.setOnShowListener(dialogInterface -> {
                if (dialog.getWindow() != null) {
                    dialog.getWindow().setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
                                    | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                    );
                }


                Button positiveButton = dialog.getButton(
                        AlertDialog.BUTTON_POSITIVE
                );

                if (positiveButton != null) {
                    positiveButton.setOnClickListener(view -> {
                        Toast.makeText(
                                MainActivity3.this,
                                "확인하였습니다",
                                Toast.LENGTH_SHORT
                        ).show();
                    });
                }
            });

            dialog.show();

            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT)
                );

                WindowManager.LayoutParams windowParams =
                        dialog.getWindow().getAttributes();

                windowParams.dimAmount = 0.8f;
                windowParams.gravity = Gravity.CENTER;

                dialog.getWindow().setAttributes(windowParams);

                dialog.getWindow().addFlags(
                        WindowManager.LayoutParams.FLAG_DIM_BEHIND
                );

                dialog.getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
                                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                );

                dialog.getWindow().setLayout(
                        (int) (
                                getResources().getDisplayMetrics().widthPixels
                                        * 0.90
                        ),
                        WindowManager.LayoutParams.WRAP_CONTENT
                );
            }


            editText1.requestFocus();

            editText1.postDelayed(() -> {
                InputMethodManager imm =
                        (InputMethodManager) getSystemService(
                                Context.INPUT_METHOD_SERVICE
                        );

                if (imm != null) {
                    imm.showSoftInput(
                            editText1,
                            InputMethodManager.SHOW_IMPLICIT
                    );
                }
            }, 250);


            buttonLogin.setOnClickListener(view -> {
                String id = editText1.getText().toString().trim();
                String pwd = editText2.getText().toString().trim();

                if (id.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(
                            MainActivity3.this,
                            "정확하게 입력해주세요",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    Toast.makeText(
                            MainActivity3.this,
                            id + " / " + pwd,
                            Toast.LENGTH_SHORT
                    ).show();
                }
            });
        });
    }

    @Override
    protected void onDestroy() {
        if (handler != null && progressRunnable != null) {
            handler.removeCallbacks(progressRunnable);
            progressRunnable = null;
        }

        super.onDestroy();
    }
}
