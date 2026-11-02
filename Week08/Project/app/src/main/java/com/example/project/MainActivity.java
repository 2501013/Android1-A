package com.example.project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private AppCompatButton button1, button2, button3;

    public static int current = Color.BLUE;
    public static int currentText = Color.WHITE;
    public static int[] color = new int[]{0, 0, 255};

    private boolean imageCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainLayout = findViewById(R.id.mainLayout);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        applyButtonColor();
        applyBackground();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleListActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleRecyclerActivity.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomRecyclerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (button1 != null) {
            applyButtonColor();
        }
    }

    private void applyButtonColor() {
        button1.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(current));

        button2.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(current));

        button3.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(current));

        button1.setTextColor(currentText);
        button2.setTextColor(currentText);
        button3.setTextColor(currentText);
    }

    private void applyBackground() {
        if (imageCheck) {
            mainLayout.setBackgroundResource(R.drawable.bg_14);
        } else {
            mainLayout.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);

        MenuItem colorItem = menu.findItem(R.id.menu_color);
        View colorView = colorItem.getActionView();

        colorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorPopup(v);
            }
        });

        menu.findItem(R.id.menu_image_set).setChecked(imageCheck);
        menu.findItem(R.id.menu_image_remove).setChecked(!imageCheck);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_image_set) {
            imageCheck = true;
            item.setChecked(true);
            applyBackground();
            return true;
        }

        if (id == R.id.menu_image_remove) {
            imageCheck = false;
            item.setChecked(true);
            applyBackground();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showColorPopup(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.color_popup, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.menu_btn_bg) {
                    ColorSetting colorSetting =
                            new ColorSetting(MainActivity.this, true);

                    colorSetting.setting(button1, button2, button3);
                    return true;
                }

                if (id == R.id.menu_btn_textcolor) {
                    ColorSetting colorSetting =
                            new ColorSetting(MainActivity.this, false);

                    colorSetting.setting(button1, button2, button3);
                    return true;
                }

                return false;
            }
        });

        popup.show();
    }
}