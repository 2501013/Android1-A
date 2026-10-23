package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button button1, button2;
    ArrayList<String> fruits = new ArrayList<>(
            Arrays.asList("사과", "배", "귤", "바나나", "복숭아", "수박")
    );
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, fruits);
        listView.setAdapter(adapter);

        button1.setOnClickListener(v -> {
            View dialogView = getLayoutInflater()
                    .inflate(R.layout.dialog_input, null);
            EditText editText = dialogView.findViewById(R.id.editFruit);

            AlertDialog inputDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("과일 입력")
                    .setView(dialogView)
                    .setPositiveButton("확인", null)
                    .create();

            inputDialog.setOnShowListener(d -> {
                inputDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(0xFF6200EE);
                inputDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setOnClickListener(view -> {
                            String fruit = editText.getText().toString().trim();
                            if (fruit.isEmpty()) {
                                Toast.makeText(MainActivity.this,
                                        "과일 이름을 입력해주세요",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                fruits.add(fruit);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this,
                                        "입력하였습니다",
                                        Toast.LENGTH_SHORT).show();
                                inputDialog.dismiss();
                            }
                        });
            });
            inputDialog.show();
        });

        button2.setOnClickListener(v -> {
            ArrayList<Integer> selectedPositions = new ArrayList<>();
            ArrayList<String> selectedFruits = new ArrayList<>();

            for (int i = 0; i < fruits.size(); i++) {
                if (listView.isItemChecked(i)) {
                    selectedPositions.add(i);
                    selectedFruits.add(fruits.get(i));
                }
            }

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("선택한 과일 목록");

            if (selectedFruits.isEmpty()) {
                builder.setMessage("과일을 선택해주세요.");
                builder.setPositiveButton("확인", null);
            } else {
                builder.setItems(selectedFruits.toArray(new String[0]), null);

                builder.setNegativeButton("삭제", (dialog, which) -> {
                    int count = selectedPositions.size();
                    for (int i = selectedPositions.size() - 1; i >= 0; i--) {
                        fruits.remove((int) selectedPositions.get(i));
                    }
                    adapter.notifyDataSetChanged();
                    listView.clearChoices();
                    Toast.makeText(MainActivity.this,
                            count + "개 삭제되었습니다.",
                            Toast.LENGTH_SHORT).show();
                });

                builder.setPositiveButton("확인", (dialog, which) -> {
                    String message = String.join(", ", selectedFruits)
                            + "를 선택하였습니다.";
                    Toast.makeText(MainActivity.this,
                            message, Toast.LENGTH_SHORT).show();
                });
            }

            AlertDialog resultDialog = builder.create();
            resultDialog.setOnShowListener(d -> {
                resultDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(0xFF6200EE);
                if (resultDialog.getButton(AlertDialog.BUTTON_NEGATIVE) != null) {
                    resultDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                            .setTextColor(0xFF6200EE);
                }
            });
            resultDialog.show();
        });
    }
}
