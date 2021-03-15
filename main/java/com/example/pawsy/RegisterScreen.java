package com.example.pawsy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterScreen extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference ref;
    Button back, signup;
    TextInputLayout name, username, email, phoneNo, city, country,password;
    Spinner accountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_screen);

        back = (Button) findViewById(R.id.back_btn);
        signup = (Button) findViewById(R.id.signup_btn);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phoneNo = findViewById(R.id.phoneNo);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        password = findViewById(R.id.password);
        accountType = findViewById(R.id.accountType);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterScreen.super.onBackPressed();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               isUser("owner");
               isUser("petsitter");
               isUser("association");
            }

        });
    }

    private void isUser(String table) {
        final String userEnteredUsername = username.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(table);
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnaphot) {

                if (dataSnaphot.exists()) {
                    username.setError("Username already used!");
                    Toast.makeText(getApplicationContext(),"test1",Toast.LENGTH_SHORT).show();
                }
                else{
                    username.setError(null);
                    username.setErrorEnabled(false);
                    isEmail(table);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                username.setError("Inexistent username");
            }

        });
    }

    void isEmail(String table){
        final String userEnteredEmail = email.getEditText().getText().toString().trim();

        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference(table);
        Query checkEmail = reference.orderByChild("email").equalTo(userEnteredEmail);
        checkEmail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnaphot){

                if (dataSnaphot.exists()) {
                    email.setError("Email already used!");
                    Toast.makeText(getApplicationContext(),"test2",Toast.LENGTH_SHORT).show();
                }
                else{
                    email.setError(null);
                    email.setErrorEnabled(false);
                    rootNode = FirebaseDatabase.getInstance();
                    if(!validateUsername() | !validatePassword() | !validateEmail() | !validatePhoneNumber()) {
                        return;
                    }
                    else{
                        String nameStr = name.getEditText().getText().toString();
                        String usernameStr = username.getEditText().getText().toString();
                        String emailStr = email.getEditText().getText().toString();
                        String phoneNoStr = phoneNo.getEditText().getText().toString();
                        String cityStr = city.getEditText().getText().toString();
                        String countryStr = country.getEditText().getText().toString();
                        String passwordStr = password.getEditText().getText().toString();


                        if (accountType.getSelectedItem().toString().equals("Owner")) {
                            ref = rootNode.getReference("owner");
                            Owner owner = new Owner(nameStr, countryStr, cityStr, usernameStr, passwordStr, emailStr, phoneNoStr);
                            ref.child(usernameStr).setValue(owner);
                        } else if (accountType.getSelectedItem().toString().equals("Pet Sitter")) {
                            ref = rootNode.getReference("petsitter");
                            PetSitter petSitter = new PetSitter(nameStr, countryStr, cityStr, usernameStr, passwordStr, emailStr, phoneNoStr, "Your description here!","0");
                            ref.child(usernameStr).setValue(petSitter);
                        } else if (accountType.getSelectedItem().toString().equals("Association")) {
                            ref = rootNode.getReference("association");
                            Association association = new Association(nameStr, countryStr, cityStr, usernameStr, passwordStr, emailStr, phoneNoStr, "Your description here!", "Your site here!");
                            ref.child(usernameStr).setValue(association);
                        }

                        RegisterScreen.super.onBackPressed();
                        Toast.makeText(getApplicationContext(),"Registered successfully",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){
            }

        });
    }



    private Boolean validateUsername(){
        String val = username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            username.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)){
            username.setError("White spaces are not allowed");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +  //at least one digit
                "(?=.*[A-Z])" +  //at least one upper case letter
                //"(?=.*[@#$%^&*+=])" +  //at least one special character
                "(?=.*[a-zA-Z])" +  //any letter
                "(?=\\S+$)" +  //no white spaces
                ".{4,}" +  //at least 4 characters
                "$";
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = email.getEditText().getText().toString();
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(val);
        if (val.isEmpty()){
            email.setError("Field cannot be empty");
            return false;
        } else if (!matcher.matches()) {
            email.setError("Invalid email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNumber(){
        String val = phoneNo.getEditText().getText().toString();
        String regex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(val);
        if (val.isEmpty()){
            phoneNo.setError("Field cannot be empty");
            return false;
        } else if (!matcher.matches()) {
            phoneNo.setError("Invalid phone number!");
            return false;
        } else {
            phoneNo.setError(null);
            phoneNo.setErrorEnabled(false);
            return true;
        }

    }
}