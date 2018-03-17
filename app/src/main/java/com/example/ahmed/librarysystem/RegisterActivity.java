package com.example.ahmed.librarysystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    Button SignBtn;
    EditText Email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Email = (EditText) findViewById(R.id.txtEmailR);
        pass = (EditText) findViewById(R.id.txtpassR);
        SignBtn = (Button) findViewById(R.id.Signbtn);
        SignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String password = pass.getText().toString();

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            if (task.getException().getMessage().equals("The email address is already in use by another account.")) {
                                Toast.makeText(RegisterActivity.this, "هذا الايميل مستخدم من قبل", Toast.LENGTH_SHORT).show();

                            }
                            Toast.makeText(RegisterActivity.this,"Check your data !",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
