package com.example.captainscard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

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
        ranklistbtn= findViewById(R.id.ranklistbtn);
        iniperf= findViewById(R.id.iniperf);
        backtohomebtn= findViewById(R.id.backtohomefromteam);

        //-------------------playerDetailsPage----------------------
        playerdetailbtn.setOnClickListener(view ->{
         startActivity(new Intent(SummeryActivity.this, PlayerDetailActivity.class));
        });

        //-----------------rankListBtn-------------------------------
        ranklistbtn.setOnClickListener(view ->{
            startActivity(new Intent(SummeryActivity.this, RankActivity.class));
            Toast.makeText(this, "Here you can see the players Ranking top to bottom", Toast.LENGTH_LONG).show();
        });

//        //-----------------individual performance page---------------
//        iniperf.setOnClickListener(view ->{
//            startActivity(new Intent(SummeryActivity.this, PerformanceActivity.class));
//        });
//
//        //------------------backbtn---------------------------------
//        iniperf.setOnClickListener(view ->{
//            startActivity(new Intent(SummeryActivity.this, PerformanceActivity.class));
//        });

    }
}