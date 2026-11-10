package com.example.project;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.graphics.Color;
import android.content.res.ColorStateList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private View first;
    private View second;
    private ViewPager2 viewPager2;
    private Button btnToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);

        second.setVisibility(View.GONE);

        btnToggle = findViewById(R.id.btnToggle);

        btnToggle.setBackgroundTintList(
                ColorStateList.valueOf(Color.LTGRAY)
        );

        btnToggle.setTextColor(Color.BLACK);

        viewPager2 = findViewById(R.id.viewPager2);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        btnToggle.setText("가로로 슬라이드");

        ViewPager2Adapter adapter1 = new ViewPager2Adapter(this);
        viewPager2.setAdapter(adapter1);

        ViewPagerAdapter adapter2 = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter2);

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {

                    switch (position) {
                        case 0:
                            tab.setText("HOME");
                            tab.setIcon(android.R.drawable.ic_menu_myplaces);
                            break;

                        case 1:
                            tab.setText("CHATTING");
                            tab.setIcon(android.R.drawable.ic_dialog_email);
                            break;

                        case 2:
                            tab.setText("NEWS");
                            tab.setIcon(android.R.drawable.ic_menu_agenda);
                            break;

                        case 3:
                            tab.setText("SETTING");
                            tab.setIcon(android.R.drawable.ic_menu_preferences);
                            break;
                    }
                }).attach();

        btnToggle.setOnClickListener(v -> {

            if (viewPager2.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {

                viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                btnToggle.setText("세로로 슬라이드");

            } else {

                viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                btnToggle.setText("가로로 슬라이드");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menuSlide) {

            item.setChecked(!item.isChecked());

            if (item.isChecked()) {
                first.setVisibility(View.GONE);
                second.setVisibility(View.VISIBLE);
                setTitle("Report02");
            } else {
                first.setVisibility(View.VISIBLE);
                second.setVisibility(View.GONE);
                setTitle("ViewPager2");
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}




