package com.example.pawsy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditAssociationProfile extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference ref;
    TextInputLayout fullname,phoneNo,country,city,description,link,password;
    String userStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_association_profile);

        userStr = getIntent().getStringExtra("username");

        fullname = findViewById(R.id.fullname);
        phoneNo = findViewById(R.id.phoneNo);
        country = findViewById(R.id.country);
        city = findViewById(R.id.city);
        link = findViewById(R.id.link);
        description = findViewById(R.id.description);
        password = findViewById(R.id.password);

        Button back = (Button)findViewById(R.id.back);
        Button update = (Button)findViewById(R.id.update);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditAssociationProfile.super.onBackPressed();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = fullname.getEditText().getText().toString();
                String phoneNoStr = phoneNo.getEditText().getText().toString();
                String cityStr = city.getEditText().getText().toString();
                String countryStr = country.getEditText().getText().toString();
                String linkStr = link.getEditText().getText().toString();
                String descriptionStr = description.getEditText().getText().toString();
                String passwordStr = password.getEditText().getText().toString();

                //update in firebase
                if(!validatePhoneNumber() | !validatePassword()){
                    return;
                }
                else {
                    rootNode = FirebaseDatabase.getInstance();
                    DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("association");
                    Query checkUser = reference.orderByChild("username").equalTo(userStr);

                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnaphot){

                            if (dataSnaphot.exists()) {

                                String emailStr = dataSnaphot.child(userStr).child("email").getValue(String.class);
                                ref = rootNode.getReference("association");
                                Association association = new Association(nameStr,countryStr,cityStr,userStr,passwordStr,emailStr,phoneNoStr,descriptionStr,linkStr);
                                ref.child(userStr).setValue(association);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError){
                        }

                    });

                }

                Intent intent = new Intent(EditAssociationProfile.this, MainActivity.class);
                intent.putExtra("username", userStr);
                startActivity(intent);
            }
        });
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
}