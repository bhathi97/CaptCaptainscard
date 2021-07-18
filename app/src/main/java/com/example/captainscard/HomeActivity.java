package com.example.captainscard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    Button logoutbtn;
    FirebaseAuth mAuth;

    private Button teambtn;
    private Button summerybtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logoutbtn = findViewById(R.id.logoutbtn);
        teambtn = findViewById(R.id.teambtn);
        summerybtn = findViewById(R.id.summerybtn);



        mAuth = FirebaseAuth.getInstance();

        //------------------logOutBtn-----------------------
        logoutbtn.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
        });

        //-----------------teamMemberPageBtn-----------------------------
        teambtn.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this,TeamMemberActivity.class));
        });

        //-----------------summeryBtn---------------------------------------
        summerybtn.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this,SummeryActivity.class));
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