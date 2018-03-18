package com.example.ahmed.librarysystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
TextView user,email;
EditText isbnE,nameBookE,pubE,yearE,authE;
FirebaseUser firebaseUser;
    Book book1 = new Book();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference bookDB=firebaseDatabase.getReference("books");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Button button = (Button) findViewById(R.id.button2);
        user = (TextView) findViewById(R.id.nameTxt);
        email = (TextView) findViewById(R.id.EmailTxt);
        isbnE = (EditText) findViewById(R.id.isbnTxt);
        nameBookE = (EditText) findViewById(R.id.nameBookTxt);
        pubE = (EditText) findViewById(R.id.pubTxt);
        yearE = (EditText) findViewById(R.id.dateTxt);
        authE = (EditText) findViewById(R.id.authTxt);






        if(firebaseUser!=null){
           user.setText( firebaseUser.getDisplayName());
           email.setText(firebaseUser.getEmail());

        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book1.setName(nameBookE.getText().toString());
                book1.setISBN(Integer.parseInt(isbnE.getText().toString()));
                book1.setpYear(yearE.getText().toString());
                book1.setAuthor_name(authE.getText().toString());
                book1.setPublisher(pubE.getText().toString());
                bookDB.push().setValue(book1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Post Add Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error : post not add 🙁 " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
