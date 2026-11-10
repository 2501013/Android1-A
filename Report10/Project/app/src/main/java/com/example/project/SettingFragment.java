package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name1 = view.findViewById(R.id.name1);
        TextView name2 = view.findViewById(R.id.name2);
        TextView name3 = view.findViewById(R.id.name3);
        TextView name4 = view.findViewById(R.id.name4);

        name1.setOnClickListener(v ->
                Toast.makeText(getContext(), "홍길동", Toast.LENGTH_SHORT).show());

        name2.setOnClickListener(v ->
                Toast.makeText(getContext(), "이대한", Toast.LENGTH_SHORT).show());

        name3.setOnClickListener(v ->
                Toast.makeText(getContext(), "한민국", Toast.LENGTH_SHORT).show());

        name4.setOnClickListener(v ->
                Toast.makeText(getContext(), "이윤지", Toast.LENGTH_SHORT).show());
    }
}
