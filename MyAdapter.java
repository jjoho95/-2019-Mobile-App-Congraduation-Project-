package com.example.congraduation;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    //private ArrayList<String> mData=null;
    //private String[] mData;
    private ArrayList<Content> mList = new ArrayList<>();
    Context context;


    public class CustomViewHolder extends RecyclerView.ViewHolder{

        protected TextView title;
        protected TextView content;
        protected TextView period;
        protected TextView feeling;

        public CustomViewHolder(View view) {
            super(view);

            this.title = (TextView) view.findViewById(R.id.itemTitle);
            this.content = (TextView) view.findViewById(R.id.itemContent);
            this.period = (TextView) view.findViewById(R.id.itemPeriod);
            this.feeling = (TextView) view.findViewById(R.id.itemFeeling);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO : process click event

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        // TODO : user pos


                    }


                }
            });

            //view.setOnCreateContextMenuListener(this);
        }

    }


    public MyAdapter(Context context, ArrayList<Content> list) {
        this.mList = list;
    }

    public MyAdapter() {
        this.context = context;
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item, parent, false);
        CustomViewHolder vh = new CustomViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.CustomViewHolder holder, int position) {
        //holder.textView1.setText(mData[position]);
        holder.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        holder.content.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        holder.period.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        holder.feeling.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        //holder.title.setGravity(Gravity.CENTER);

        holder.title.setText(mList.get(position).getTitle());
        holder.content.setText(mList.get(position).getContent());
        holder.period.setText(mList.get(position).getPeriod());
        holder.feeling.setText(mList.get(position).getFeeling());
    }

    public void addItem(Content item){
        mList.add(item);
    }

    public void addItems(ArrayList<Content> item) {
        this.mList = item;
    }
    @Override
    public int getItemCount() {
        return (null !=mList? mList.size() : 0);
    }


}
