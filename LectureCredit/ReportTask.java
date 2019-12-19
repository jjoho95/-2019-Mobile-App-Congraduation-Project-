package com.example.teamproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ReportTask extends AsyncTask<Integer, Double, Integer> {

    TextView credit;
    ProgressBar progress;
    CheckBox check;
    TextView info;
    SQLiteDatabase db;
    String myMajor;
    int totalCredit;

    @Override
    protected void onPreExecute() {
        check.setClickable(false);
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        Double requiredCredit = 0D;
        Cursor c = null;
        double gradeSum = 0;
        Double averageGrade = 0D;
        Double currentCredit = 0D;
        Double gradeCount = 0D;

        db.execSQL("DROP VIEW IF EXISTS view_report;");
        db.execSQL("CREATE VIEW view_report AS" +
                "   SELECT Cnumber, Cname, Credit, Grade, Category" +
                "   FROM COURSE, TAKE_COURSE" +
                "   WHERE Cnum = Cnumber" +
                "   ORDER BY Cnumber;");

        switch(integers[0]){
            case 0: // 전체 학점
                requiredCredit = (double)totalCredit;
                credit.setText("   총 학점 :");
                c = db.rawQuery("SELECT Credit, Grade FROM view_report;", null);
                break;
            case 1: // 글솝 - 전공
                requiredCredit = 51D;
                credit.setText("   전공 :");
                c = db.rawQuery("SELECT Credit, Grade FROM view_report WHERE Category = '전공';", null);
                break;
            case 2: // 글솝 - 융합
                requiredCredit = 36D;
                credit.setText("   융합전공 :");
                c = db.rawQuery("SELECT Credit, Grade FROM view_report WHERE Category = '융합전공';", null);
                break;
            case 3: // 글솝 - 교양
                requiredCredit = 30D;
                credit.setText("   교양 :");
                c = db.rawQuery("SELECT Credit, Grade FROM view_report WHERE Category = '교양';", null);
                break;
            case 4: // 글솝 - 창업교과목
                requiredCredit = 9D;
                credit.setText("   창업교과목 :");
                c = db.rawQuery("SELECT Credit, Grade FROM view_report WHERE Category = '창업교과목';", null);
                break;
            case 5: // 심컴 - 공학전공
                requiredCredit = 75D;
                credit.setText("   공학전공 :");
                c = db.rawQuery("SELECT Credit, Grade FROM view_report WHERE Category = '공학전공';", null);
                break;
            case 6: // 심컴 - 전공기반
                requiredCredit = 22D;
                credit.setText("   전공기반 :");
                c = db.rawQuery("SELECT Credit, Grade FROM view_report WHERE Category = '전공기반';", null);
                break;
            case 7: // 심컴 - 기본소양
                requiredCredit = 15D;
                credit.setText("   기본소양 :");
                c = db.rawQuery("SELECT Credit, Grade FROM view_report WHERE Category = '기본소양';", null);
                break;
        }
        if ((gradeCount = (double)c.getCount()) > 0){
            do {
                c.moveToFirst();
                int tempCredit = c.getInt(c.getColumnIndex("Credit"));
                currentCredit += tempCredit;
                double tempGrade;
                if ((tempGrade = getNumberGrade(c.getString(c.getColumnIndex("Grade")))) >= 0){
                    gradeSum += tempGrade;
                } else if (tempGrade == -1){
                    gradeCount--;
                } else if (tempGrade == -2){
                    currentCredit -= tempCredit;
                }
            } while(c.moveToNext());
            averageGrade = gradeSum / gradeCount;
            publishProgress(averageGrade, currentCredit, requiredCredit);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Double... values) {
        super.onProgressUpdate(values);
        progress.setMax(values[2].intValue());
        progress.setProgress(values[1].intValue());
        if (values[1] == values[2]){
            check.setChecked(true);
        }
        info.setText(String.format("현재 이수학점/졸업 기준학점 : (%d/%d)\n성적 : %.2f\n",
                values[1].intValue(), values[2].intValue(), values[0]));
    }

    private double getNumberGrade(String grade){
        switch (grade){
            case "A+":
                return 4.3;
            case "A0":
                return 4.0;
            case "A-":
                return 3.7;
            case "B+":
                return 3.3;
            case "B0":
                return 3.0;
            case "B-":
                return 2.7;
            case "C+":
                return 2.3;
            case "C0":
                return 2.0;
            case "C-":
                return 1.7;
            case "D+":
                return 1.3;
            case "D0":
                return 1.0;
            case "D-":
                return 0.7;
            case "F":
                return 0;
            case "S":
                return -1;
            case "U":
                return -2;
        }
        return -3;
    }

    public void setCredit(TextView credit){
        this.credit = credit;
    }
    public void setMyMajor(String myMajor) {
        this.myMajor = myMajor;
    }
    public void setCheck(CheckBox check) {
        this.check = check;
    }
    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }
    public void setProgress(ProgressBar progress) {
        this.progress = progress;
    }
    public void setInfo(TextView info) {
        this.info = info;
    }
    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }
}

