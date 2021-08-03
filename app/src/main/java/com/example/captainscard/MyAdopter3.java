package com.example.captainscard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdopter3 extends RecyclerView.Adapter<MyAdopter3.MyViewHolder> {
    List<Model3> rList;
    Context context;
    List<String> keyList;

    public MyAdopter3(Context context, List<Model3> rList, List<String> keyList){
        this.rList = rList;
        this.context = context;
        this.keyList = keyList;
    }

    @NonNull
    @Override
    public MyAdopter3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item3, parent,false);
        return new MyAdopter3.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdopter3.MyViewHolder holder, int position) {
        Model3 model3 = rList.get(position);

        final String nameFromPage = model3.getName();
        holder.name.setText(nameFromPage);
    }


    @Override
    public int getItemCount() {
        return rList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.rankName);

        }
    }
}
