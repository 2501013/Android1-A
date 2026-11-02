package com.example.project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class ColorSetting {

    private Context context;
    private boolean isBackground;

    public ColorSetting(Context context, boolean isBackground) {
        this.context = context;
        this.isBackground = isBackground;
    }

    public void setting(Button targetButton1, Button targetButton2,
                        Button targetButton3) {

        View view = LayoutInflater.from(context).inflate(R.layout.color_dialog, null);

        TextView textView1 = view.findViewById(R.id.textView1);
        TextView textView2 = view.findViewById(R.id.textView2);
        TextView textView3 = view.findViewById(R.id.textView3);

        Button button = view.findViewById(R.id.button);

        SeekBar seekBar1 = view.findViewById(R.id.seekBar1);
        SeekBar seekBar2 = view.findViewById(R.id.seekBar2);
        SeekBar seekBar3 = view.findViewById(R.id.seekBar3);

        int originalColor;

        if (isBackground) {
            originalColor = MainActivity.current;
        } else {
            originalColor = MainActivity.currentText;
        }

        final int[] tempColor = {
                Color.red(originalColor),
                Color.green(originalColor),
                Color.blue(originalColor)
        };

        seekBar1.setProgress(tempColor[0]);
        seekBar2.setProgress(tempColor[1]);
        seekBar3.setProgress(tempColor[2]);

        SeekBar.OnSeekBarChangeListener listener =
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {

                        tempColor[0] = seekBar1.getProgress();
                        tempColor[1] = seekBar2.getProgress();
                        tempColor[2] = seekBar3.getProgress();

                        changeColor(button, textView1, textView2,
                                textView3, tempColor);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                };

        seekBar1.setOnSeekBarChangeListener(listener);
        seekBar2.setOnSeekBarChangeListener(listener);
        seekBar3.setOnSeekBarChangeListener(listener);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (isBackground) {
            builder.setTitle("버튼 배경색을 선택해주세요");
        } else {
            builder.setTitle("버튼 글자색을 선택해주세요");
        }

        builder.setView(view);

        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int finalColor = Color.rgb(
                                tempColor[0],
                                tempColor[1],
                                tempColor[2]
                        );

                        MainActivity.color = tempColor.clone();

                        if (isBackground) {
                            MainActivity.current = finalColor;

                            targetButton1.setBackgroundTintList(
                                    ColorStateList.valueOf(finalColor));

                            targetButton2.setBackgroundTintList(
                                    ColorStateList.valueOf(finalColor));

                            targetButton3.setBackgroundTintList(
                                    ColorStateList.valueOf(finalColor));

                        } else {
                            MainActivity.currentText = finalColor;

                            targetButton1.setTextColor(finalColor);
                            targetButton2.setTextColor(finalColor);
                            targetButton3.setTextColor(finalColor);
                        }
                    }
                });

        builder.setNegativeButton("취소", null);
        builder.setCancelable(false);

        builder.show();

        changeColor(button, textView1, textView2, textView3, tempColor);
    }

    private void changeColor(Button button, TextView textView1,
                             TextView textView2, TextView textView3,
                             int[] tempColor) {

        int r = tempColor[0];
        int g = tempColor[1];
        int b = tempColor[2];

        int previewColor = Color.rgb(r, g, b);

        textView1.setText("Red : " + r + "(" + String.format("%02X", r) + ")");
        textView2.setText("Green : " + g + "(" + String.format("%02X", g) + ")");
        textView3.setText("Blue : " + b + "(" + String.format("%02X", b) + ")");

        if (isBackground) {
            button.setBackgroundTintList(
                    ColorStateList.valueOf(previewColor));
        } else {
            button.setTextColor(previewColor);
        }
    }
}