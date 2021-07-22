package com.example.captainscard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdopter2 extends RecyclerView.Adapter<MyAdopter2.MyViewHolder> {

    ArrayList<Model2> nList;
    Context context;

    public MyAdopter2(Context context, ArrayList<Model2> nList){
        this.nList = nList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdopter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item2, parent,false);
        return new MyAdopter2.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdopter2.MyViewHolder holder, int position) {
        Model2 model = nList.get(position);

        holder.name.setText(model.getName());

    }

    @Override
    public int getItemCount() {
        return nList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.shownameforadd);

        }
    }
}
