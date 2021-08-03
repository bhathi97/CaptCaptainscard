package com.example.captainscard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RankActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private DatabaseReference databaseReference;

    private MyAdopter3 adopter;
    private ArrayList<Model3> valueListFromFirebase = new ArrayList<>();
    private List<String> keyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        recyclerView = findViewById(R.id.rankRecyclerView);
        databaseReference =  FirebaseDatabase.getInstance().getReference("value");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Model3 model = dataSnapshot.getValue(Model3.class);
                    valueListFromFirebase.add(model);
                }
                Comparator<Model3> compareValues = Comparator.comparing(Model3::getValue);
                List<Model3> sortedValues = valueListFromFirebase.stream().sorted(compareValues).collect(Collectors.toList());
                Log.d("AAAAAAAAAAAAAAAAA", String.valueOf(sortedValues.size()));
                Log.d("BBBBBBBBBBBBBBB", String.valueOf(valueListFromFirebase.size()));
                List<Model3> decendendValues = new ArrayList<>();
                for (int i = sortedValues.size(); i > 0; i--) {
                    decendendValues.add(sortedValues.get(i - 1));
                }
                executeList(decendendValues);
                adopter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void executeList(List<Model3> sortedValues) {
        adopter = new MyAdopter3(this, sortedValues,keyList);

        recyclerView.setAdapter(adopter);
    }
}