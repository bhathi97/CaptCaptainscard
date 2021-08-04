package com.example.captainscard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    Button logoutbtn;
    FirebaseAuth mAuth;
    private Button teambtn;
    private Button summerybtn;
    private Button diarybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logoutbtn = findViewById(R.id.logoutbtn);
        teambtn = findViewById(R.id.teambtn);
        summerybtn = findViewById(R.id.summerybtn);
        diarybtn = findViewById(R.id.diarybtn);
        mAuth = FirebaseAuth.getInstance();

        //------------------logOutBtn------------------------------------
        logoutbtn.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
            Toast.makeText(this, "signed out successfully", Toast.LENGTH_LONG).show();
        });

        //-----------------teamMemberPageBtn-----------------------------
        teambtn.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this,TeamMemberActivity.class));
        });

        //-----------------summeryBtn---------------------------------------
        summerybtn.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this,SummeryActivity.class));
        });

        //-----------------diaryBtn-----------------------------------------
        diarybtn.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, DiaryActivity.class));
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
        }
    }













}