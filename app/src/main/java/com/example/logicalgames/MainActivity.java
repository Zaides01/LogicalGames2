package com.example.logicalgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.example.logicalgames.R.layout.list_item;

public class MainActivity extends AppCompatActivity {
    Button deleteButton, okButton, nullButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton;
    TextView number;
    ListView list;
    int bulls, cows;

    SimpleAdapter simpleAdapter;
    LinkedList<HashMap<String, String>> mapNumber = new LinkedList<>();

    private static final String LEVEL = "level";
    String level;

    long startTime, endTime, time;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.number);
        deleteButton = findViewById(R.id.deleteButton);
        okButton = findViewById(R.id.okButton);
        nullButton = findViewById(R.id.nullButton);
        oneButton = findViewById(R.id.oneButton);
        twoButton = findViewById(R.id.twoButton);
        threeButton = findViewById(R.id.threeButton);
        fourButton = findViewById(R.id.fourButton);
        fiveButton = findViewById(R.id.fiveButton);
        sixButton = findViewById(R.id.sixButton);
        sevenButton = findViewById(R.id.sevenButton);
        eightButton = findViewById(R.id.eightButton);
        nineButton = findViewById(R.id.nineButton);
        list = findViewById(R.id.list);

        //TODO расчет времени игры
        startTime = System.currentTimeMillis();
        intent = new Intent(this, MainMenu.class);

        level = getIntent().getStringExtra(LEVEL);
        final RandomNumber randomNumber = new RandomNumber(Integer.parseInt(level));
        final int levelInt = Integer.parseInt(String.valueOf(randomNumber.generate()));

        String[] keyFrom = {"numberbc", "bulls", "cows"};
        int[] idTo = {R.id.numberbc, R.id.bulls, R.id.cows};
        simpleAdapter = new SimpleAdapter(this, mapNumber, list_item, keyFrom, idTo);
        list.setAdapter(simpleAdapter);
        //TODO работа с адаптером
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkedList<String> numbers = new LinkedList();
                if (!number.getText().toString().equals("")) {
                    numbers.add(number.getText().toString());
                    bulls = numberOfBulls(levelInt, Integer.parseInt(number.getText().toString()));
                    cows = numberOfCaws(levelInt, Integer.parseInt(number.getText().toString()));
                    for (int i = 0; i < numbers.size(); i++) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("numberbc", numbers.get(i));
                        map.put("bulls", String.valueOf(bulls));
                        map.put("cows", String.valueOf(cows));
                        mapNumber.add(map);
                    }
                    simpleAdapter.notifyDataSetChanged();
                    number.setText("");
                    if (bulls == 4){
                        onStop();
                    }
                }
            }
        });
    }

    public void numbers(Button but){
        String num = but.getText().toString();
        if (!num.equals("")){
            switch (num){
                case ("0"):
                    number.append(nullButton.getText().toString()); break;
                case ("1"):
                    number.append(oneButton.getText().toString()); break;
                case ("2"):
                    number.append(twoButton.getText().toString()); break;
                case ("3"):
                    number.append(threeButton.getText().toString()); break;
                case ("4"):
                    number.append(fourButton.getText().toString()); break;
                case ("5"):
                    number.append(fiveButton.getText().toString()); break;
                case ("6"):
                    number.append(sixButton.getText().toString()); break;
                case ("7"):
                    number.append(sevenButton.getText().toString()); break;
                case ("8"):
                    number.append(eightButton.getText().toString()); break;
                case ("9"):
                    number.append(nineButton.getText().toString()); break;
                case ("DEL"):
                    String str = number.getText().toString();
                    if (!str.isEmpty()){
                        str = str.substring(0, str.length()-1);
                        number.setText(str);} break;
            }
        }
    }

    public void onClick (View v){
        numbers ((Button) v);
    }

    public int numberOfBulls(int originalNumber, int enteredNumber){
        int k = 0;
        while (originalNumber != 0){
            if (originalNumber % 10 == enteredNumber % 10)
                k++;
            originalNumber /= 10;
            enteredNumber /= 10;
        }
        return k;
    }

    public int numberOfCaws (int originalNumber, int enteredNumber){
        int k = 0;
        String s = String.valueOf(originalNumber);
        String ss = String.valueOf(enteredNumber);
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < ss.length(); j++) {
                if (i != j){
                    if (s.charAt(i) == ss.charAt(j))
                        k++;
                }
            }
        }
        return k;
    }

    @Override
    protected void onStop() {
        super.onStop();
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        intent.putExtra("time", time);
        startActivity(intent);
    }
}