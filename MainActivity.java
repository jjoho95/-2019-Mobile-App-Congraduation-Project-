package com.example.congraduation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickButton(View view) {
        int id = view.getId();
        Intent intent;

        switch (id) {
            case R.id.buttonIntern:
                intent = new Intent(MainActivity.this, IntActivity.class);
                startActivity(intent);
                break;

            case R.id.buttonAward:
                intent = new Intent(MainActivity.this, AwardActivity.class);
                startActivity(intent);
                break;


        }
    }
}
