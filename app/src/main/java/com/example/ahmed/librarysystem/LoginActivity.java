package com.example.ahmed.librarysystem;

import android.content.Intent;
import android.net.Uri;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class LoginActivity extends AppCompatActivity {
Button LoginBtn;
EditText Email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = (EditText) findViewById(R.id.txtEmail);
        pass = (EditText) findViewById(R.id.txtpass);
        LoginBtn = (Button) findViewById(R.id.loginbtn);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String password = pass.getText().toString();

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            FirebaseUser user = task.getResult().getUser();

                            UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName("Ahmed hassan")
                                    .setPhotoUri(Uri.parse("https://ahmedmh.000webhostapp.com/ferrari.jpg"))
                                    .build();

                            user.updateProfile(userProfileChangeRequest);
                        }else {
                            Toast.makeText(LoginActivity.this,"Email or password wrong !",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
