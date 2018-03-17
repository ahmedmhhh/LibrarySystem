package com.example.ahmed.librarysystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
TextView user,email;
ImageView image;
FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Button button = (Button) findViewById(R.id.button2);
        user = (TextView) findViewById(R.id.nameTxt);
        email = (TextView) findViewById(R.id.EmailTxt);
        image = (ImageView) findViewById(R.id.imageView3);

        if(firebaseUser!=null){
           user.setText( firebaseUser.getDisplayName());
           email.setText(firebaseUser.getEmail());
           image.setImageURI(firebaseUser.getPhotoUrl());
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
