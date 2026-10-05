package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txtNum1, txtNum2, txtResult;
    private SeekBar seekBar1, seekBar2;
    private Button btnLcm;

    private int num1 = 1;
    private int num2 = 1;


    private boolean isTouched1 = false;
    private boolean isTouched2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtNum1 = findViewById(R.id.txtNum1);
        txtNum2 = findViewById(R.id.txtNum2);
        txtResult = findViewById(R.id.txtResult);
        seekBar1 = findViewById(R.id.seekBar1);
        seekBar2 = findViewById(R.id.seekBar2);
        btnLcm = findViewById(R.id.btnLcm);

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                num1 = progress + 1;
                txtNum1.setText("자연수 입력(" + num1 + ")");
                isTouched1 = true; // 사용자가 움직였음을 체크
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                num2 = progress + 1;
                txtNum2.setText("자연수 입력(" + num2 + ")");
                isTouched2 = true; // 사용자가 움직였음을 체크
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        btnLcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isTouched1 || !isTouched2) {
                    Toast.makeText(getApplicationContext(), "입력 바람", Toast.LENGTH_SHORT).show();
                    return;
                }

                int gcd = getGCD(num1, num2);
                int lcm = (num1 * num2) / gcd;


                String resultText = num1 + ", " + num2 + "의 GCD(최대공약수)는 " + gcd + "입니다.\n" +
                        num1 + ", " + num2 + "의 LCM(최소공배수)는 " + lcm + "입니다.";
                txtResult.setText(resultText);
            }
        });
    }


    private int getGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getGCD(b, a % b);
    }
}