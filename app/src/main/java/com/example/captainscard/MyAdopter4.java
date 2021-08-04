package com.example.captainscard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdopter4 extends RecyclerView.Adapter<MyAdopter4.MyViewHolder> {
    List<Model3> pList;
    Context context;
    List<String> keyList;

    public MyAdopter4(Context context, List<Model3> rList, List<String> keyList){
        this.pList = rList;
        this.context = context;
        this.keyList = keyList;
    }

    @NonNull
    @Override
    public MyAdopter4.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item4, parent,false);
        return new MyAdopter4.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdopter4.MyViewHolder holder, int position) {
        Model3 model3 = pList.get(position);

        final String nameFromPage = model3.getName();
        final String valueFromPage = model3.getValue().toString();

        holder.name.setText(nameFromPage);
        holder.value.setText(valueFromPage);
    }

    @Override
    public int getItemCount() {
        return pList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView value;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.perfName);
            value = itemView.findViewById(R.id.perfValue);
        }
    }

}
