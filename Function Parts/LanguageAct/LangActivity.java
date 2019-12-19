package com.example.congraduation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class LangActivity extends AppCompatActivity {
    private String _strPath ; //해당 파일에대한 경로
    private String fileName; //해당 파일명
    private ArrayList<String> aryList;
    private ArrayAdapter <String> adapter;
    private ListView listView;
    private int REQUEST_TEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang);

      listView = (ListView)findViewById(R.id.listView);
      aryList = new ArrayList<String>();
      adapter = new ArrayAdapter<String>(
                this,
                R.layout.lang_item,
                R.id.fileName,
                aryList
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String item = (String)adapterView.getItemAtPosition(position);
                        Toast.makeText(LangActivity.this,item + "selected",Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    public void onAddbtnclicked(View view) {//파일첨부버튼 클릭리스너
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // Set your required file type
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "DEMO"),1001);
    }
    public void onActivityResult(int requestCode, int resultCode, //activity result 받아옴
                                 Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        // super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001) {
            Uri currFileURI = data.getData();
            _strPath = getPathFromUri(getApplicationContext(),currFileURI);
            fileName = new File(_strPath).getName();
            Log.d("TAG1",currFileURI.toString());
            aryList.add(fileName);
            listView.setAdapter(adapter);

        }}

    public void showDocumentFile(View view) { //미리보기 버튼 클릭리스너
        Intent intent = new Intent();

        Log.d("TAG3","strPath = "+ _strPath+"  filename = "+ fileName);

        intent.setAction(android.content.Intent.ACTION_VIEW);
        File file = new File(_strPath);
//        File file = new File(_strPath);
//        // 파일 확장자별 Mime Type을 지정한다.
        if (_strPath.endsWith("mp3"))
        {
            intent.setDataAndType(FileProvider.getUriForFile(getApplicationContext(), "com.example.congraduation.fileProvider", file), "audio/*");
        }
        else if (_strPath.endsWith("mp4"))
        {
            intent.setDataAndType(FileProvider.getUriForFile(getApplicationContext(), "com.example.congraduation.fileProvider", file), "vidio/*");
        }
        else if (_strPath.endsWith("jpg") || _strPath.endsWith("jpeg") ||
                _strPath.endsWith("JPG") || _strPath.endsWith("gif") ||
                _strPath.endsWith("png") || _strPath.endsWith("bmp"))
        {
            intent.setDataAndType(FileProvider.getUriForFile(getApplicationContext(), "com.example.congraduation.fileProvider", file), "image/*");
        }
        else if (_strPath.endsWith("txt"))
        {
            intent.setDataAndType(FileProvider.getUriForFile(getApplicationContext(), "com.example.congraduation.fileProvider", file), "text/*");
        }
        else if (_strPath.endsWith("doc") || _strPath.endsWith("docx"))
        {
            intent.setDataAndType(FileProvider.getUriForFile(getApplicationContext(), "com.example.congraduation.fileProvider", file), "application/msword");
        }
        else if (_strPath.endsWith("xls") || _strPath.endsWith("xlsx"))
        {
            intent.setDataAndType(FileProvider.getUriForFile(getApplicationContext(), "com.example.congraduation.fileProvider", file),
                    "application/vnd.ms-excel");
        }
        else if (_strPath.endsWith("ppt") || _strPath.endsWith("pptx"))
        {
            intent.setDataAndType(FileProvider.getUriForFile(getApplicationContext(), "com.example.congraduation.fileProvider", file),
                    "application/vnd.ms-powerpoint");
        }
        else if (_strPath.endsWith("pdf")) {
            intent.setDataAndType(FileProvider.getUriForFile(getApplicationContext(), "com.example.congraduation.fileProvider", file), "application/pdf");

        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }
    //***********************************************
    //이 밑에서부터는 Uri의 실제 Path값 받아오는 함수들
    //***********************************************
    public static String getPathFromUri(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                       Uri.parse("content://downloads/all_downloads"), Long.valueOf(id));


                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


    public void onImgBtnClicked(View view) {
        Intent intent = new Intent();
        _strPath = "/storage/emulated/0/englang.png";

        intent.setAction(android.content.Intent.ACTION_VIEW);
        File file = new File(_strPath);
        intent.setDataAndType(FileProvider.getUriForFile(getApplicationContext(), "com.example.congraduation.fileProvider", file), "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }
}
