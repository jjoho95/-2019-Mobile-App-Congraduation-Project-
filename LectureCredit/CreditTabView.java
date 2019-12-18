package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreditTabView extends AppCompatActivity {
    SQLiteDatabase db;
    private TableLayout tableLayout;
    String myMajor = DBHelper.getInstance(this).getMyMajor();
    DBHelper helper;
    int tableRows;

    ArrayList<TextView> cnameList = new ArrayList<>();
    ArrayList<TextView> creditList = new ArrayList<>();
    ArrayList<Spinner> gradeList = new ArrayList<>();
    ArrayList<TextView> categoryList = new ArrayList<>();
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
                }
            }});

        helper = DBHelper.getInstance(this);
        db = helper.getReadableDatabase();
        if (helper.isFirst()){
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
                TextView category = tableRow.findViewById(R.id.category);
                CheckBox takes = tableRow.findViewById(R.id.takes);

                Integer creditResult = c.getInt(c.getColumnIndex("Credit"));
                cname.setText(c.getString(c.getColumnIndex("Cname")));
                credit.setText(creditResult.toString());
                grade.setAdapter(getCreditAdapter());

                Cursor c2 = db.rawQuery("SELECT Grade " +
                        "   FROM TAKE_COURSE" +
                        "   WHERE Cnum = "+ c.getInt(c.getColumnIndex("Cnumber"))+
                        "   ORDER BY Cnum;", null);
                if (c2.getCount() > 0){
                    c2.moveToFirst();
                    takes.setChecked(true);
                    String selected = c2.getString(c.getColumnIndex("Grade"));
                    for (int i = 0; i < grade.getAdapter().getCount(); i++) {
                        if(grade.getAdapter().getItem(i).toString().contains(selected)) {
                            grade.setSelection(i);
                            break;
                        }
                    }
                }
                c2 = db.rawQuery("SELECT Category " +
                        "   FROM COURSE_CATEGORY" +
                        "   WHERE Cno ="+ c.getInt(c.getColumnIndex("Cnumber")) +
                        "   AND Major = '"+ myMajor + "'" +
                        "   ORDER BY Cno;", null);
                if (c2.getCount() > 0){
                    c2.moveToFirst();
                    do {
                        category.setText(c2.getString(c2.getColumnIndex("Category"))+"\n");
                    } while(c2.moveToNext());
                }
                tableLayout.addView(tableRow);
                c2.close();

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
            TextView takes = takesList.get(i);

            Cursor c = db.rawQuery("SELECT Cnumber FROM COURSE WHERE Cname = '"+ cname.getText().toString() +"';", null);
            if (c.getCount() > 0){
                c.moveToFirst();
                cnum = c.getInt(c.getColumnIndex("Cnumber"));
            }

            if (takes.toString() != null){
                c = db.rawQuery("SELECT Cnum FROM TAKE_COURSE WHERE Cnum = "+ cnum + ";", null);
                if (c.getCount() > 0){
                    helper.updateTakeCourse(db, grade.toString(), cnum);
                }
                else {
                    helper.insertTakeCourse(db, cnum, grade.toString());
                }
            }
            else {
                helper.deleteTakeCourse(db, cnum);
            }
            c.close();
        }
        Toast.makeText(this,"저장되었습니다", Toast.LENGTH_SHORT).show();
    }

    public void onAddGrade(View view){

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
        return new ArrayAdapter<>(this, R.layout.spinner_item, R.id.spinner_grade, arrayList);
    }
}
