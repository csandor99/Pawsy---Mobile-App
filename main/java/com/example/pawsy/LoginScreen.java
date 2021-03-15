package com.example.pawsy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginScreen extends AppCompatActivity {
    TextInputLayout username, password, email, phoneNumber;
    Boolean valid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        valid = false;
        Button signup = (Button) findViewById(R.id.signup_btn);
        Button login = (Button) findViewById(R.id.login_btn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isUser();
            }
        });
    }

    private void isUser() {
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("owner");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        DatabaseReference reference1  = FirebaseDatabase.getInstance().getReference("petsitter");
        Query checkUser1 = reference1.orderByChild("username").equalTo(userEnteredUsername);
        DatabaseReference reference2  = FirebaseDatabase.getInstance().getReference("association");
        Query checkUser2 = reference2.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnaphot){

                if (dataSnaphot.exists()) {
                    String passwordFromDatabase = dataSnaphot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDatabase.equals(userEnteredPassword)){
                        valid = true;
                        Toast.makeText(getApplicationContext(),"Logged in successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                        intent.putExtra("username",userEnteredUsername);
                        finish();
                        startActivity(intent);
                    } else {
                        password.setError("Invalid password");
                    }
                }
                else{
                    //username.setError("Inexistent username");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){
            }

        });

        if(!valid){
        checkUser1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnaphot){

                if (dataSnaphot.exists()) {
                    String passwordFromDatabase = dataSnaphot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDatabase.equals(userEnteredPassword)){
                        valid = true;
                        Toast.makeText(getApplicationContext(),"Logged in successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                        intent.putExtra("username",userEnteredUsername);
                        finish();
                        startActivity(intent);
                    } else {
                        password.setError("Invalid password");
                    }
                }
                else{
                    //username.setError("Inexistent username");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){
            }

        });
        }

        if(!valid) {
            checkUser2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnaphot) {

                    if (dataSnaphot.exists()) {
                        String passwordFromDatabase = dataSnaphot.child(userEnteredUsername).child("password").getValue(String.class);

                        if (passwordFromDatabase.equals(userEnteredPassword)) {
                            Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                            intent.putExtra("username", userEnteredUsername);
                            finish();
                            startActivity(intent);
                        } else {
                            password.setError("Invalid password");
                        }
                    } else {
                        //username.setError("Inexistent username");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }

            });
        }

    }
}