package com.example.congraduation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import java.io.File;
import java.util.ArrayList;

public class AwardActivity extends AppCompatActivity {

    private static final int PICK_FROM_ALBUM = 42;
    private File tempFile;

    ArrayList<String> imgUrl = new ArrayList<>();
    private ArrayList<Content> mArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager Manager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award);

        //ArrayList<String> list = new ArrayList<>();


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mArrayList = new ArrayList<>();
        adapter = new MyAdapter(this, mArrayList);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getBaseContext(), ResultActivity.class);

                intent.putExtra("title", Content.getTitle());
                intent.putExtra("content", Content.getContent());
                intent.putExtra("period", Content.getPeriod());
                intent.putExtra("feeling", Content.getFeeling());

                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public void onClickListener(View view) {
        int id = view.getId();
        Intent intent;


        //final AlertDialog dialog;
        switch (id) {
            case R.id.buttonAddItem:
                AlertDialog.Builder builder = new AlertDialog.Builder(AwardActivity.this);
                View v = LayoutInflater.from(AwardActivity.this).inflate(R.layout.layout_editbox, null, false);
                builder.setView(v);

                final EditText editTextTitle = (EditText)v.findViewById(R.id.editTextTitle);
                final EditText editTextContent = (EditText)v.findViewById(R.id.editTextContent);
                final EditText editTextPeriod = (EditText)v.findViewById(R.id.editTextPeriod);
                final EditText editTextFeeling = (EditText)v.findViewById(R.id.editTextFeeling);

                final AlertDialog dialog = builder.create();

                final Button buttonSave = (Button)v.findViewById(R.id.buttonSave);
                buttonSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String strTitle = editTextTitle.getText().toString();
                        String strContent = editTextContent.getText().toString();
                        String strPeriod = editTextPeriod.getText().toString();
                        String strFeeling = editTextFeeling.getText().toString();

                        Content cont = new Content(strTitle, strContent, strPeriod, strFeeling);
                        mArrayList.add(0, cont);
                        //adapter.addItems(mArrayList);

                        adapter.notifyItemInserted(0);

                        adapter.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });
                dialog.show();

                final Button buttonAddFile = (Button)v.findViewById(R.id.buttonAddFile);
                buttonAddFile.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                        intent.addCategory(Intent.CATEGORY_OPENABLE);

                        intent.setType("*/*");


                        startActivityForResult(intent, PICK_FROM_ALBUM);
                    }
                });

                break;

            case R.id.buttonSave:

                /*String strTitle = editTextTitle.getText().toString();
                String strContent = editTextContent.getText().toString();
                String strPeriod = editTextPeriod.getText().toString();
                String strFeeling = editTextFeeling.getText().toString();

                Content cont = new Content(strTitle, strContent, strPeriod, strFeeling);
                mArrayList.add(0, cont);

                adapter.notifyItemInserted(0);
*/

                //dialog.dismiss();


            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == PICK_FROM_ALBUM && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            Cursor cursor = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("TAG", "Uri: " + uri.toString());
                //showImage(uri);
                ImageView imageView = (ImageView)findViewById(R.id.editImageView);
                imageView.setImageURI(uri);
            }


            try {

                String[] proj = {MediaStore.Images.Media.DATA};

                assert uri != null;
                cursor = getContentResolver().query(uri, proj, null, null, null);


                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));


            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }


            setImage();
        }
    }



    private void setImage() {



    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context applicationContext, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(applicationContext, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if(child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if(child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
