package com.example.captainscard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdopter extends RecyclerView.Adapter<MyAdopter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;

    public MyAdopter(Context context,ArrayList<Model>mList){

        this.mList = mList;
        this.context = context;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item, parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);

        holder.name.setText(model.getName());
        holder.age.setText(model.getAge());
        holder.position.setText(model.getPosition());
        holder.height.setText(model.getHeight());
        holder.weight.setText(model.getWeight());
        holder.note.setText(model.getNote());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,age,position,height,weight,note;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.nameShow);
            age = itemView.findViewById(R.id.ageShow);
            position = itemView.findViewById(R.id.positionShow);
            height = itemView.findViewById(R.id.heightShow);
            weight = itemView.findViewById(R.id.weightShow);
            note = itemView.findViewById(R.id.noteShow);

        }

    }
}
