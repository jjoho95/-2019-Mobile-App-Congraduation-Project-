package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TabHost;

public class CreditTabView extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_tab_view);

        TabHost tabHost1 = findViewById(R.id.tabHost1);
        tabHost1.setup();

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("성적 입력");
        tabHost1.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("보고서");
        tabHost1.addTab(ts2);

        db = DBHelper.getInstance(this).getReadableDatabase();
        loadCourse();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void loadCourse(){

    }

}
