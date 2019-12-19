package com.example.congraduation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String title="";
        String content="";
        String period="";
        String feeling="";

        Bundle extras = getIntent().getExtras();

        title =extras.getString("title");
        content = extras.getString("content");
        period = extras.getString("period");
        feeling = extras.getString("feeling");

        TextView textView = (TextView)findViewById(R.id.resultTitle);
        textView.setText(title);

        textView = (TextView)findViewById(R.id.resultContent);
        textView.setText(content);

        textView = (TextView)findViewById(R.id.resultPeriod);
        textView.setText(period);

        textView = (TextView)findViewById(R.id.resultFeeling);
        textView.setText(feeling);

    }
}
