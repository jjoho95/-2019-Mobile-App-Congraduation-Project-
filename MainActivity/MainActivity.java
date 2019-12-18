package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DBHelper helper = DBHelper.getInstance(this);
        if ("global".equals(helper.getMyMajor(this))){
            setContentView(R.layout.activity_main);
        } else if ("computer".equals(helper.getMyMajor(this))){
            setContentView(R.layout.activity_main);
        }
        else {
            setContentView(R.layout.activity_major_select);
            Spinner major = findViewById(R.id.select_major);
            Spinner specific = findViewById(R.id.select_major_specific);

            ArrayList<String> array1 = new ArrayList<>();
            ArrayList<String> array2 = new ArrayList<>();
            array1.add("컴퓨터학부");
            array2.add("심화컴퓨터전공");
            array2.add("글로벌SW융합전공");
            major.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_item, R.id.spinner_text, array1));
            specific.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_item, R.id.spinner_text, array2));
        }
        //deleteDatabase("test.db");
    }

    public void onSelectMajor(View view){
        Spinner specific = findViewById(R.id.select_major_specific);
        String result = specific.getSelectedItem().toString();
        if (result.equals("심화컴퓨터전공")){
            DBHelper.getInstance(this).setMyMajor("computer", this);
        } else {
            DBHelper.getInstance(this).setMyMajor("global", this);
        }
        setContentView(R.layout.activity_main);
    }

    public void onGradeButtonClicked(View view) {
        Intent intent1 = new Intent(MainActivity.this, CreditTabView.class);
        startActivity(intent1);
    }

    public void onLangButtonClicked(View view) {
//        Intent intent2 = new Intent(MainActivity.this, LangActivity.class);
//        startActivity(intent2);
    }

    public void onInternButtonClicked(View view) {
//        Intent intent3 = new Intent(MainActivity.this, InternActivity.class);
//        startActivity(intent3);
    }

    public void onAwardButtonClicked(View view) {
//        Intent intent4 = new Intent(MainActivity.this, AwardActivity.class);
//        startActivity(intent4);
    }

    public void onAPIButtonClicked(View view) {
//        Intent intent5 = new Intent(MainActivity.this, APIActivity.class);
//        startActivity(intent5);
    }
}
