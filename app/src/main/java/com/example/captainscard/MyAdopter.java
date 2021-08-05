package com.example.captainscard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdopter extends RecyclerView.Adapter<MyAdopter.MyViewHolder> {
    ArrayList<Model> mList;
    Context context;
    List<String> keyList;

    public MyAdopter(Context context, ArrayList<Model> mList, List<String> keyList){
        this.mList = mList;
        this.context = context;
        this.keyList = keyList;
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
        holder.positionF.setText(model.getPosition());
        holder.height.setText(model.getHeight());
        holder.weight.setText(model.getWeight());
        holder.note.setText(model.getNote());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext()).setContentHolder(new ViewHolder(R.layout.update_dialog)).setExpanded(true,1000).create();
                View view = dialogPlus.getHolderView();
                EditText name = view.findViewById(R.id.nameNew);
                EditText age = view.findViewById(R.id.ageNew);
                EditText positionFF = view.findViewById(R.id.positionNew);
                EditText height = view.findViewById(R.id.heightNew);
                EditText weight = view.findViewById(R.id.weightNew);
                EditText note = view.findViewById(R.id.noteNew);
                Button upBtn = view.findViewById(R.id.update);
                Button delete= view.findViewById(R.id.delete);

                name.setText(model.getName());
                age.setText(model.getAge());
                positionFF.setText(model.getPosition());
                height.setText(model.getHeight());
                weight.setText(model.getWeight());
                note.setText(model.getNote());
                dialogPlus.show();

                upBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("age",age.getText().toString());
                        map.put("position",positionFF.getText().toString());
                        map.put("height",height.getText().toString());
                        map.put("weight",weight.getText().toString());
                        map.put("note",note.getText().toString());

                        FirebaseDatabase.getInstance().getReference("player").child(keyList.get(position)).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(holder.name.getContext(), "Updated successfully", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.name.getContext(), "EROR EROR", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Delete data cant be undo");
                builder.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference("player").child(keyList.get(position)).removeValue();
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView age;
        TextView positionF;
        TextView height;
        TextView weight;
        TextView note;
        Button edit;
        Button delete;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.nameShow);
            age = itemView.findViewById(R.id.ageShow);
            positionF = itemView.findViewById(R.id.positionShow);
            height = itemView.findViewById(R.id.heightShow);
            weight = itemView.findViewById(R.id.weightShow);
            note = itemView.findViewById(R.id.noteShow);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
