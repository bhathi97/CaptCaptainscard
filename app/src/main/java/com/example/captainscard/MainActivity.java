 package com.example.captainscard;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText loginemail;
    private EditText loginpassword;
    private Button loginbtn;
    private Button sigupbtn;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginemail = findViewById(R.id.loginemail);
        loginpassword = findViewById(R.id.loginpassword);
        loginbtn = findViewById(R.id.loginbtn);
        sigupbtn = findViewById(R.id.sigupbtn);
        mAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(view -> {
            loginUser();
        });

        sigupbtn.setOnClickListener(view -> {

            Intent intent = new Intent(this,SigActivity.class);
            startActivity(intent);

        });

    }



    private void loginUser(){
        String email = loginemail.getText().toString();
        String password = loginpassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            loginemail.setError("Enter the email");
            loginemail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            loginpassword.setError("Enter the password");
            loginpassword.requestFocus();
        }else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"User login success",Toast.LENGTH_SHORT).show();
                        finish();
                        Intent openActivity = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(openActivity);

                    }else{
                        Toast.makeText(MainActivity.this,"Login Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}