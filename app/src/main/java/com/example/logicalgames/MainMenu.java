package com.example.logicalgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        intent = new Intent(this, MainActivity.class);
    }

    public void onClick (Button button){

    }

    public void onClick(View view) {
        String s = String.valueOf(view);
        intent.putExtra("level", s);
        startActivity(intent);
    }
}