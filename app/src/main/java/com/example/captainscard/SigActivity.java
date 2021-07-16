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

public class SigActivity extends AppCompatActivity {

    EditText addname, addpw, addemail;
    Button siginbtn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig);

        addname = findViewById(R.id.addname);
        addpw = findViewById(R.id.addpw);
        addemail = findViewById(R.id.addemail);
        siginbtn = findViewById(R.id.sigupbtn);
        mAuth = FirebaseAuth.getInstance();

        siginbtn.setOnClickListener(view ->{
            createUser();
        });

    }

    private void createUser(){
        String email = addemail.getText().toString();
        String password = addpw.getText().toString();

        if (TextUtils.isEmpty(email)){
            addemail.setError("Email cannot be empty");
            addemail.requestFocus( );
        }else if(TextUtils.isEmpty(password)){
            addpw.setError("Password cannot be empty");
            addpw.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SigActivity.this,"Registration successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SigActivity.this, MainActivity.class));
                    }else {
                        Toast.makeText(SigActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SigActivity.this, MainActivity.class));
                    }
                }
            });
        }

    }
}