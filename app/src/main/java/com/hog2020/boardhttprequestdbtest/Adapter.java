package com.hog2020.boardhttprequestdbtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter {

    ArrayList<Item> items;
    Context context;

    public Adapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =LayoutInflater.from(context);
        View itemView =inflater.inflate(R.layout.card,parent,false);
        VH holder = new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;

        Item item = items.get(position);
        vh.tvname.setText(item.name);
        vh.tvmsg.setText(item.msg);
        vh.tvdate.setText(item.date);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {

        TextView tvname;
        TextView tvmsg;
        TextView tvdate;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvname= itemView.findViewById(R.id.tv_name);
            tvmsg=itemView.findViewById(R.id.tv_message);
            tvdate=itemView.findViewById(R.id.tv_date);
        }
    }
}
