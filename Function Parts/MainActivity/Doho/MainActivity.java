package com.example.sdfasdf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGradeButtonClicked(View view) {
        Intent intent1 = new Intent(MainActivity.this, GradeActivity.class);
        startActivity(intent1);
    }

    public void onLangButtonClicked(View view) {
        Intent intent2 = new Intent(MainActivity.this, LangActivity.class);
        startActivity(intent2);
    }

    public void onInternButtonClicked(View view) {
        Intent intent3 = new Intent(MainActivity.this, InternActivity.class);
        startActivity(intent3);
    }

    public void onAwardButtonClicked(View view) {
        Intent intent4 = new Intent(MainActivity.this, AwardActivity.class);
        startActivity(intent4);
    }

    public void onAPIButtonClicked(View view) {
        Intent intent5 = new Intent(MainActivity.this, APIActivity.class);
        startActivity(intent5);
    }
}
