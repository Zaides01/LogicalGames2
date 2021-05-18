package com.example.logicalgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    Button four;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //TODO можешь удолить кнопку и все, кроме интента, и потом в интент положи число чтобы он пошел на создания числа
        four = findViewById(R.id.four);
        intent = new Intent(this, MainActivity.class);
        intent.putExtra("level", four.getText().toString());

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}