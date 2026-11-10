package com.example.project;

import android.os.Bundle;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PageFragment extends Fragment {

    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("page", page);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.pageText);

        int page = getArguments().getInt("page");

        textView.setText(page + " Page");

        switch (page) {
            case 1:
                view.setBackgroundColor(Color.rgb(173, 101, 201));
                break;

            case 2:
                view.setBackgroundColor(Color.RED);
                break;

            case 3:
                view.setBackgroundColor(Color.GREEN);
                break;

            case 4:
                view.setBackgroundColor(0xFFFF9800);
                break;

            case 5:
                view.setBackgroundColor(Color.BLUE);
                break;

            case 6:
                view.setBackgroundColor(0xFF87CEEB);
                break;
        }

        textView.setTextColor(Color.WHITE);
        textView.setGravity(android.view.Gravity.CENTER);
    }
}


