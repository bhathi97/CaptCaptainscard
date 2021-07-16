package com.example.captainscard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class TeamMemberActivity extends AppCompatActivity {

    private EditText newName;
    private EditText newAge;
    private EditText newPosition;
    private EditText newHeight;
    private EditText newWeight;
    private EditText newNote;
    private Button AddToTeam;
    private Button backtohomefromteam;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference root = database.getReference().child("player");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_member);

        backtohomefromteam = findViewById(R.id.backtohomefromteam);
        newName = findViewById(R.id.newName);
        newAge = findViewById(R.id.newAge);
        newPosition = findViewById(R.id.newPosition);
        newHeight = findViewById(R.id.newHeight);
        newWeight = findViewById(R.id.newWeight);
        newNote = findViewById(R.id.newNote);

        AddToTeam = findViewById(R.id.AddToTeam);


        //--------------back-------------------
        backtohomefromteam.setOnClickListener(view ->{
            Toast.makeText(TeamMemberActivity.this,"Welcome back Home ",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TeamMemberActivity.this,HomeActivity.class);
            startActivity(intent);

        } );

        //-------------------save data------------------
        AddToTeam.setOnClickListener(view -> {
            String name = newName.getText().toString();
            String age = newAge.getText().toString();
            String position = newPosition.getText().toString();
            String height = newHeight.getText().toString();
            String weight = newWeight.getText().toString();
            String note = newNote.getText().toString();

            HashMap<String,String> userMap = new HashMap<>();

            userMap.put("name" , name);
            userMap.put("age" , age);
            userMap.put("position" , position);
            userMap.put("height" , height);
            userMap.put("weight" , weight);
            userMap.put("note" , note);

            root.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(TeamMemberActivity.this,"Successfully added to the team card ",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TeamMemberActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
            });



        });



    }
}