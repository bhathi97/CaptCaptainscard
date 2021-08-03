package com.example.captainscard;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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
    private FirebaseDatabase rootNode;
    private DatabaseReference valueReference;
    private Integer valueOfName;
    private String key;

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

        final String nameFromPage = model2.getName();
        holder.name.setText(nameFromPage);



        holder.point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext()).setContentHolder(new ViewHolder(R.layout.daily_dialog)).setExpanded(true,700).create();
//                dialogPlus.show();

                View view = dialogPlus.getHolderView();
                TextView name = view.findViewById(R.id.Dname);

                Button submitBtn = view.findViewById(R.id.submit);

                name.setText(nameFromPage);
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

                      //  Toast.makeText(view.getContext(), "QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ", Toast.LENGTH_SHORT).show();

                        String a0 = zero.getText().toString();
                        String a1 = one.getText().toString();
                        String a2 = two.getText().toString();
                        String a3 = three.getText().toString();
                        String a4 = four.getText().toString();
                        String a5 = five.getText().toString();


//                        HashMap<String,String> userMap3 = new HashMap<>();
//                        userMap3.put("name",name.getText().toString());

                        Map<String,Object> userMap3 = new HashMap<>();

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

                        rootNode = FirebaseDatabase.getInstance();
                        valueReference = rootNode.getReference("value");

                        valueReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DataSnapshot dataSnapshot : task.getResult().getChildren()) {
                                        Model2 valueFromFirebase = dataSnapshot.getValue(Model2.class);
                                        if (valueFromFirebase != null) {
                                            Toast.makeText(view.getContext(), nameFromPage, Toast.LENGTH_SHORT).show();
                                            if (valueFromFirebase.getName().equals(nameFromPage)) {
                                                valueOfName = valueFromFirebase.getValue();
                                                key = dataSnapshot.getKey();
                                                Integer fullMark = valueOfName + Integer.parseInt(userMap3.get("value").toString());
//                                                Log.d("value aded", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA " + fullMark);

                                                valueReference.child(key).child("value").setValue(fullMark, new DatabaseReference.CompletionListener() {
                                                    @Override
                                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                                        Toast.makeText(holder.name.getContext(), "daily value added", Toast.LENGTH_LONG).show();
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }
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
        //String value;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.shownameforadd);
            point = itemView.findViewById(R.id.point);

        }
    }
}
