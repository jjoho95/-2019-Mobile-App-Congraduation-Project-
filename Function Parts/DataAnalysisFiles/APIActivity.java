package com.example.congraduation;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class APIActivity extends AppCompatActivity {
    TextView tv;
    private ArrayList<String> aryList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> SpinaryList;
    private ArrayAdapter<String> Spinadapter;
    private ListView listView;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.openapi_layout);

        spinner = (Spinner)findViewById(R.id.spinner);
        SpinaryList = new ArrayList<String>();
        listView = (ListView)findViewById(R.id.apilistview);
        aryList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(
                this,
                R.layout.public_item,
                R.id.result,
                aryList
        );
        listView.setAdapter(adapter);
        Spinadapter = new ArrayAdapter<String>(
                this,
                R.layout.spinpublic_item,
                R.id.spinresult,
                SpinaryList
        );
        spinner.setAdapter(Spinadapter);


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String item = (String)adapterView.getItemAtPosition(position);
                        Toast.makeText(APIActivity.this,SpinaryList.get(position),Toast.LENGTH_LONG).show();

//                        SpinaryList.add(item);
//                        spinner.setAdapter(Spinadapter);

                    }
                }
        );

        StrictMode.enableDefaults();

//        TextView status1 = (TextView)findViewById(R.id.result); //파싱된 결과확인!

        boolean initem = false, inAddr = false, inChargeTp = false, inCpId = false, inCpNm = false;
        boolean inCpStat = false, inCpTp = false, inCsId = false, inCsNm = false, inLat=false;
        boolean inLongi = false, inStatUpdateDatetime = false;

        String addr = null, chargeTp = null, cpId = null, cpNm = null, cpStat = null, cpTp=null, csId=null, csNm=null;
        String lat = null, longi = null, statUpdateDatetime = null;


        try{
            URL url = new URL("http://apis.data.go.kr/B551982/openApiNewHire/openXmlNewHire?"
                    + "serviceKey=QJCQzdyrL%2B%2FS8LdGWhPZUnIAfJP2AVuNAfH2w6FnR0HEHb7091BNrgCauojlhRfjyra3Y5Wd7xxd20Me0OcKfQ%3D%3D&type=xml&numOfRows=10&pageNo=1&ac_year=2018"
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("No")){ //title 만나면 내용을 받을수 있게 하자
                            inAddr = true;
                        }
                        if(parser.getName().equals("AC_YEAR")){ //address 만나면 내용을 받을수 있게 하자
                            inChargeTp = true;
                        }
                        if(parser.getName().equals("ENT_NAME")){ //mapx 만나면 내용을 받을수 있게 하자
                            inCpId = true;
                        }
                        if(parser.getName().equals("TOTALEMP")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCpNm = true;
                        }
                        if(parser.getName().equals("ENGINEER")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCpStat = true;
                        }
                        if(parser.getName().equals("COLLEGE")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCpTp = true;
                        }
                        if(parser.getName().equals("HSCHOOL")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCsId = true;
                        }
                        if(parser.getName().equals("WOMAN")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCsNm = true;
                        }
                        if(parser.getName().equals("HANDICAP")){ //mapy 만나면 내용을 받을수 있게 하자
                            inLat = true;
                        }
                        if(parser.getName().equals("NKL_RESIDENT")){ //mapy 만나면 내용을 받을수 있게 하자
                            inLongi = true;
                        }
                        if(parser.getName().equals("YOUNG")){ //mapy 만나면 내용을 받을수 있게 하자
                            inStatUpdateDatetime = true;
                        }
//                        if(parser.getName().equals("YOUNG_F")){ //message 태그를 만나면 에러 출력
////                            status1.setText(status1.getText()+"에러");
//                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
//                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inAddr){ //isTitle이 true일 때 태그의 내용을 저장.
                            addr = parser.getText();
                            inAddr = false;
                        }
                        if(inChargeTp){ //isAddress이 true일 때 태그의 내용을 저장.
                            chargeTp = parser.getText();
                            inChargeTp = false;
                        }
                        if(inCpId){ //isMapx이 true일 때 태그의 내용을 저장.
                            cpId = parser.getText();
                            inCpId = false;
                        }
                        if(inCpNm){ //isMapy이 true일 때 태그의 내용을 저장.
                            cpNm = parser.getText();
                            inCpNm = false;
                        }
                        if(inCpStat){ //isMapy이 true일 때 태그의 내용을 저장.
                            cpStat = parser.getText();
                            inCpStat = false;
                        }
                        if(inCpTp){ //isMapy이 true일 때 태그의 내용을 저장.
                            cpTp = parser.getText();
                            inCpTp = false;
                        }
                        if(inCsId){ //isMapy이 true일 때 태그의 내용을 저장.
                            csId = parser.getText();
                            inCsId = false;
                        }
                        if(inCsNm){ //isMapy이 true일 때 태그의 내용을 저장.
                            csNm = parser.getText();
                            inCsNm = false;
                        }
                        if(inLat){ //isMapy이 true일 때 태그의 내용을 저장.
                            lat = parser.getText();
                            inLat = false;
                        }
                        if(inLongi){ //isMapy이 true일 때 태그의 내용을 저장.
                            longi = parser.getText();
                            inLongi = false;
                        }
                        if(inStatUpdateDatetime){ //isMapy이 true일 때 태그의 내용을 저장.
                            statUpdateDatetime = parser.getText();
                            inStatUpdateDatetime = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
//                            status1.setText(status1.getText()+"No : "+ addr +" 기업명 : " + cpId
//                                    +" 총 모집 인원 : " + cpNm +  " 기술직 : " + cpStat+ " 전문대졸 : " + cpTp
//                                    +" 고졸 : " +csId + " 여성 : " + csNm + " 장애인 : " +lat
//                                    +" 지방인재 : " +longi +" 신입 : " +statUpdateDatetime+"\n\n");
                            SpinaryList.add(" 기업명 : " + cpId.replaceAll("\n","")+"\n"
                                    +" 총 모집 인원 : " + cpNm.replaceAll("\n","")+"\n" +  " 기술직 : " + cpStat.replaceAll("\n","")+"\n"+ " 전문대졸 : " + cpTp.replaceAll("\n","")
                                    +"\n"+" 고졸 : " +csId.replaceAll("\n","") +"\n"+ " 여성 : " + csNm.replaceAll("\n","") +"\n"+ " 장애인 : " +lat.replaceAll("\n","")
                                    +"\n" +" 지방인재 : " +longi.replaceAll("\n","") +"\n"+" 신입 : " +statUpdateDatetime.replaceAll("\n",""));
                            aryList.add(cpId);
                            listView.setAdapter(adapter);


                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch(Exception e){
//            status1.setText(e.toString());
        }
    }
}



















//    }
//    public class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            try {
//                Document document = Jsoup.connect(html).get();
////                tv.setText(document);
//               Log.d("TAG", "Msg : " + document);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
//}
