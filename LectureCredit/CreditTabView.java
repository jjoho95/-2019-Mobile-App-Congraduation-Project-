package com.example.congraduation;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreditTabView extends AppCompatActivity {
    SQLiteDatabase db;
    TableLayout tableLayout;
    LinearLayout reportLayout;
    String myMajor;
    DBHelper helper;
    int tableRows;

    ArrayList<TextView> cnameList = new ArrayList<>();
    ArrayList<TextView> creditList = new ArrayList<>();
    ArrayList<Spinner> gradeList = new ArrayList<>();
    ArrayList<Spinner> categoryList = new ArrayList<>();
    ArrayList<CheckBox> takesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_tab_view);

        TabHost tabHost = findViewById(R.id.tabHost1);
        tabHost.setup();

        TabHost.TabSpec ts1 = tabHost.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("성적 입력");
        tabHost.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("보고서");
        tabHost.addTab(ts2);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            public void onTabChanged(String tabId) {
                if("Tab Spec 2".equals(tabId)) {
                    updateDB();
                    showReport();
                } else {
                    deleteReport();
                }
            }});

        helper = DBHelper.getInstance(this);
        myMajor = helper.getMyMajor(this);
        db = helper.getReadableDatabase();
        if (helper.isFirst(this)){
            helper.setFirst(0, this);
            helper.initDB(db);
        }
        setTable();
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateDB();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        updateDB();
    }

    private void setTable() {
        db.execSQL("DROP VIEW IF EXISTS view_credit;");
        db.execSQL("CREATE VIEW view_credit AS" +
                "   SELECT DISTINCT Cno AS Cnum" +
                "   FROM COURSE_CATEGORY" +
                "   WHERE Major = '"+ myMajor +"'" +
                "   ORDER BY Cno;");
        Cursor c = db.rawQuery("SELECT Cnumber, Cname, Credit " +
                "   FROM COURSE" +
                "   INNER JOIN view_credit ON Cnumber = Cnum" +
                "   ORDER BY Cnumber;", null);
        tableLayout = findViewById(R.id.tableLayout);

        tableRows = c.getCount();
        if (tableRows > 0) {
            c.moveToFirst();
            do {
                View tableRow = LayoutInflater.from(this).inflate(R.layout.layout_item_credit, null, false);
                TextView cname = tableRow.findViewById(R.id.cname);
                TextView credit = tableRow.findViewById(R.id.credit);
                Spinner grade = tableRow.findViewById(R.id.grade);
                Spinner category = tableRow.findViewById(R.id.category);
                CheckBox takes = tableRow.findViewById(R.id.takes);

                Integer creditResult = c.getInt(c.getColumnIndex("Credit"));
                cname.setText(c.getString(c.getColumnIndex("Cname")));
                credit.setText(creditResult.toString());
                grade.setAdapter(getCreditAdapter());

                Cursor c2 = db.rawQuery("SELECT Category " +
                        "   FROM COURSE_CATEGORY" +
                        "   WHERE Cno ="+ c.getInt(c.getColumnIndex("Cnumber")) +
                        "   AND Major = '"+ myMajor + "'" +
                        "   ORDER BY Cno;", null);
                if (c2.getCount() > 0) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    c2.moveToFirst();
                    do {
                        arrayList.add(c2.getString(c2.getColumnIndex("Category")));
                    } while (c2.moveToNext());
                    category.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_item, R.id.spinner_text, arrayList));
                    if (c2.getCount() == 1) {
                        category.setEnabled(false);
                    }
                }

                c2 = db.rawQuery("SELECT Grade, Category " +
                        "   FROM TAKE_COURSE" +
                        "   WHERE Cnum = "+ c.getInt(c.getColumnIndex("Cnumber"))+
                        "   ORDER BY Cnum;", null);
                if (c2.getCount() > 0){
                    c2.moveToFirst();
                    takes.setChecked(true);
                    String selected = c2.getString(c2.getColumnIndex("Grade"));
                    for (int i = 0; i < grade.getAdapter().getCount(); i++) {
                        if(grade.getAdapter().getItem(i).toString().contains(selected)) {
                            grade.setSelection(i);
                            break;
                        }
                    }
                    selected = c2.getString(c2.getColumnIndex("Category"));
                    for (int i = 0; i < category.getAdapter().getCount(); i++) {
                        if(category.getAdapter().getItem(i).toString().contains(selected)) {
                            category.setSelection(i);
                            break;
                        }
                    }
                }
                c2.close();
                tableLayout.addView(tableRow);
                cnameList.add(cname);
                creditList.add(credit);
                gradeList.add(grade);
                categoryList.add(category);
                takesList.add(takes);

            } while (c.moveToNext());
            c.close();
        }
    }

    private void updateDB(){
        for (int i = 0; i < tableRows; i++){
            int cnum = -1;
            TextView cname = cnameList.get(i);
            Spinner grade = gradeList.get(i);
            CheckBox takes = takesList.get(i);
            Spinner cateogry = categoryList.get(i);

            Cursor c = db.rawQuery("SELECT Cnumber FROM COURSE WHERE Cname = '"+ cname.getText().toString() +"';", null);
            if (c.getCount() > 0){
                c.moveToFirst();
                cnum = c.getInt(c.getColumnIndex("Cnumber"));
            }

            if (takes.isChecked()){
                c = db.rawQuery("SELECT Cnum FROM TAKE_COURSE WHERE Cnum = "+ cnum + ";", null);
                if (c.getCount() > 0){
                    helper.updateTakeCourse(db, grade.getSelectedItem().toString(), cateogry.getSelectedItem().toString(), cnum);
                }
                else {
                    helper.insertTakeCourse(db, cnum, grade.getSelectedItem().toString(), cateogry.getSelectedItem().toString());
                }
            }
            else {
                helper.deleteTakeCourse(db, cnum);
            }
            c.close();
        }
        Toast.makeText(this,"저장되었습니다", Toast.LENGTH_SHORT).show();
    }

    private ArrayAdapter<String> getCreditAdapter(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A+");
        arrayList.add("A0");
        arrayList.add("A-");
        arrayList.add("B+");
        arrayList.add("B0");
        arrayList.add("B-");
        arrayList.add("C+");
        arrayList.add("C0");
        arrayList.add("C-");
        arrayList.add("D+");
        arrayList.add("D0");
        arrayList.add("D-");
        arrayList.add("F");
        arrayList.add("U");
        arrayList.add("S");
        return new ArrayAdapter<>(this, R.layout.spinner_item, R.id.spinner_text, arrayList);
    }

    private void showReport(){
        reportLayout = findViewById(R.id.content2);
        ArrayList<Integer> list = new ArrayList<>();

        int totalCredit = 0;
        if (myMajor.equals("global")){
            list.add(0); list.add(2); list.add(1); list.add(3); list.add(4);
            totalCredit = 130;
        } else {
            list.add(0); list.add(5); list.add(6); list.add(7);
            totalCredit = 150;
        }

        db.execSQL("DROP VIEW IF EXISTS view_temp;");
        db.execSQL("CREATE VIEW view_temp AS" +
                "   SELECT Cnumber, Cname, Credit, Grade" +
                "   FROM COURSE, TAKE_COURSE" +
                "   WHERE Cnum = Cnumber" +
                "   ORDER BY Cnumber;");
        db.execSQL("DROP VIEW IF EXISTS view_report;");
        db.execSQL("CREATE VIEW view_report AS" +
                "   SELECT Cnumber, Cname, Credit, Grade, Category" +
                "   FROM view_temp JOIN COURSE_CATEGORY" +
                "   ON Cnumber = Cno" +
                "   WHERE Major = '" + myMajor + "'" +
                "   ORDER BY Cnumber;");

        for (int i : list){
            View report = LayoutInflater.from(this).inflate(R.layout.layout_item_report, null, false);
            View report_sub = LayoutInflater.from(this).inflate(R.layout.layout_item_report_2, null, false);
            TextView credit = report.findViewById(R.id.report_credit);
            ProgressBar progress = report.findViewById(R.id.report_progress);
            CheckBox check = report.findViewById(R.id.report_check);
            TextView info = report_sub.findViewById(R.id.report_info);

            ReportTask rTask = new ReportTask();
            rTask.setMyMajor(myMajor);
            rTask.setDb(db);
            rTask.setCredit(credit);
            rTask.setProgress(progress);
            rTask.setCheck(check);
            rTask.setInfo(info);
            rTask.setTotalCredit(totalCredit);
            try {
                rTask.execute(i);
                rTask.wait();
            }catch (Exception e){}

            reportLayout.addView(report);
            reportLayout.addView(report_sub);
        }
    }

    private void deleteReport(){
        reportLayout = findViewById(R.id.content2);
        reportLayout.removeAllViewsInLayout();
    }

}
