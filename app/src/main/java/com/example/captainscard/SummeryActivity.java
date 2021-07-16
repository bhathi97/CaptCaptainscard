package com.example.captainscard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SummeryActivity extends AppCompatActivity {

    private Button ranklistbtn;
    private Button playerdetailbtn;
    private Button iniperf;
    private Button backtohomebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summery);

        playerdetailbtn = findViewById(R.id.playerdetailbtn);

        //-------------------playerDetailsPage----------------------
        playerdetailbtn.setOnClickListener(view ->{
         startActivity(new Intent(SummeryActivity.this, PlayerDetailActivity.class));
        });

    }
}