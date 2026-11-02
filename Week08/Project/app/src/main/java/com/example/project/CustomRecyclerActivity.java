package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CustomRecyclerActivity extends AppCompatActivity {

    String[] members;
    String[] births;
    String[] jobs;

    int[] images = {
            R.drawable.lee2,
            R.drawable.name2,
            R.drawable.name5,
            R.drawable.name6,
            R.drawable.lee1
    };

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Custom RecyclerView");
        }

        listView = findViewById(R.id.customListView);

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

        MemberAdapter adapter = new MemberAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            showMemberDialog(position);
        });
    }

    class MemberAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Math.min(members.length, 6);
        }

        @Override
        public Object getItem(int position) {
            return members[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;

            if (view == null) {
                view = LayoutInflater.from(CustomRecyclerActivity.this)
                        .inflate(R.layout.item_custom_member, parent, false);
            }

            ImageView memberImage = view.findViewById(R.id.memberImage);
            TextView memberName = view.findViewById(R.id.memberName);
            TextView memberBirth = view.findViewById(R.id.memberBirth);
            TextView memberJob = view.findViewById(R.id.memberJob);

            int age = 2026 - Integer.parseInt(births[position]) + 1;

            memberImage.setImageResource(images[position % images.length]);

            String nameAge = members[position] + " (" + age + " 세)";
            SpannableString spannable = new SpannableString(nameAge);

            int start = members[position].length() + 2;
            int end = start + String.valueOf(age).length();

            spannable.setSpan(
                    new ForegroundColorSpan(Color.RED),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );

            memberName.setText(spannable);
            memberBirth.setText(births[position] + "년생");
            memberJob.setText(jobs[position]);

            return view;
        }
    }

    private void showMemberDialog(int position) {

        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.member_dialog, null);

        ImageView dialogImage = dialogView.findViewById(R.id.dialogImage);
        TextView dialogName = dialogView.findViewById(R.id.dialogName);
        TextView dialogInfo = dialogView.findViewById(R.id.dialogInfo);

        int age = 2026 - Integer.parseInt(births[position]) + 1;

        dialogImage.setImageResource(images[position % images.length]);

        String nameAge = members[position] + " (" + age + " 세)";
        SpannableString spannable = new SpannableString(nameAge);

        int start = members[position].length() + 2;
        int end = start + String.valueOf(age).length();

        spannable.setSpan(
                new ForegroundColorSpan(Color.RED),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        dialogName.setText(spannable);
        dialogInfo.setText(births[position] + "년생\n" + jobs[position]);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("확인", null)
                .create();

        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(Color.BLUE);
    }
}
