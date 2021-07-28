package com.example.captainscard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdopter2 extends RecyclerView.Adapter<MyAdopter2.MyViewHolder> {

    ArrayList<Model2> nList;
    Context context;
    List<String> keyList;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference root3 = database.getReference().child("value");

    public MyAdopter2(Context context, ArrayList<Model2> nList,List<String> keyList){
        this.nList = nList;
        this.context = context;
        this.keyList = keyList;
    }

    @NonNull
    @Override
    public MyAdopter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item2, parent,false);
        return new MyAdopter2.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdopter2.MyViewHolder holder, int position) {
        Model2 model2 = nList.get(position);

        holder.name.setText(model2.getName());

        holder.point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext()).setContentHolder(new ViewHolder(R.layout.daily_dialog)).setExpanded(true,700).create();


//                dialogPlus.show();

                View view = dialogPlus.getHolderView();
//                EditText name = view.findViewById(R.id.Dname);
//
                Button submitBtn = view.findViewById(R.id.submit);
//
//                name.setText(model2.getName());

//
                RadioButton zero = view.findViewById(R.id.zero);
                RadioButton one = view.findViewById(R.id.one);
                RadioButton two = view.findViewById(R.id.two);
                RadioButton three = view.findViewById(R.id.three);
                RadioButton four = view.findViewById(R.id.four);
                RadioButton five = view.findViewById(R.id.five);

                dialogPlus.show();


                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a0 = zero.getText().toString();
                        String a1 = one.getText().toString();
                        String a2 = two.getText().toString();
                        String a3 = three.getText().toString();
                        String a4 = four.getText().toString();
                        String a5 = five.getText().toString();

//                        Map<String,Object> map = new HashMap<>();
//                        map.put("name",name.getText().toString());
                        HashMap<String,String> userMap3 = new HashMap<>();


                        if (zero.isChecked()){
                            userMap3.put("value" , a0);
                        }else if (one.isChecked()){
                            userMap3.put("value" , a1);
                        }else if (two.isChecked()){
                            userMap3.put("value" , a2);
                        }else if (three.isChecked()){
                            userMap3.put("value" , a3);
                        }else if (four.isChecked()){
                            userMap3.put("value" , a4);
                        }else if (five.isChecked()){
                            userMap3.put("value" , a5);
                        }

                        root3.push().setValue(userMap3).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(holder.name.getContext(),"daily mark was added successfully", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });




                    }
                });



            }
        });

    }

    @Override
    public int getItemCount() {
        return nList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        Button point;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.shownameforadd);
            point = itemView.findViewById(R.id.point);

        }
    }
}
