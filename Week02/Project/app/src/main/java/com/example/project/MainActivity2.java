package com.example.project;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity {
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle(R.string.app2);

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(100);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);

        TextView textView1 = findViewById(R.id.textView1);
        textView1.setSelected(true);
        textView1.startAnimation(anim);

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setTextColor(ContextCompat.getColor(MainActivity2.this, R.color.red));
        textView2.startAnimation(anim);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    button.setBackgroundColor(ContextCompat.getColor(MainActivity2.this, R.color.blue));
                    Toast.makeText(MainActivity2.this, "Button Clicked", LENGTH_SHORT).show();
                } else {
                    button.setBackgroundColor(ContextCompat.getColor(MainActivity2.this, R.color.gray));

                    // v 대신 루트 뷰를 전달하거나 v.getRootView()를 사용할 수 있습니다.
                    Snackbar.make(v.getRootView(), "Button Clicked", BaseTransientBottomBar.LENGTH_SHORT).show();
                }
                flag = !flag;
            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity2.this, "Long Clicked", LENGTH_SHORT).show();
                return true;
            }
        });
    }

    public void textViewClicked(View view) {
        Toast.makeText(MainActivity2.this, "클릭했습니다", LENGTH_SHORT).show();
    }
}