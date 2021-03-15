package com.example.pawsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class OwnerProfile extends AppCompatActivity {

    TextInputEditText fullname,phoneNo,country,city;
    TextView name,user;
    ImageView edit;
    String userStr;
    String fullnameStr;
    Button addAnimal, viewAnimal, removeAnimal;
    Owner owner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);

        fullname = findViewById(R.id.fullname);
        phoneNo = findViewById(R.id.phoneNo);
        country = findViewById(R.id.country);
        city = findViewById(R.id.city);
        name = findViewById(R.id.name);
        user = findViewById(R.id.username);
        edit =findViewById(R.id.edit);
        addAnimal = findViewById(R.id.add_animal);
        viewAnimal = findViewById(R.id.view_animals);
        removeAnimal = findViewById(R.id.remove_animals);

        userStr = getIntent().getStringExtra("username");

        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("owner");
        Query checkUser = reference.orderByChild("username").equalTo(userStr);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnaphot){

                if (dataSnaphot.exists()) {
                    //owner = dataSnaphot.child(userStr).getValue(Owner.class);
                    fullnameStr = dataSnaphot.child(userStr).child("fullName").getValue(String.class);
                    String phoneStr = dataSnaphot.child(userStr).child("phoneNumber").getValue(String.class);
                    String countryStr = dataSnaphot.child(userStr).child("country").getValue(String.class);
                    String cityStr = dataSnaphot.child(userStr).child("city").getValue(String.class);
                    fullname.setText(fullnameStr);
                    phoneNo.setText(phoneStr);
                    country.setText(countryStr);
                    city.setText(cityStr);
                    name.setText(fullnameStr);
                    user.setText(userStr);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){
            }

        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerProfile.this,EditOwnerProfile.class);
                intent.putExtra("username", userStr);
                startActivity(intent);
            }
        });

        addAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerProfile.this,AddAnimal.class);
                intent.putExtra("username", userStr);
                intent.putExtra("fullname", fullnameStr);
                startActivity(intent);
            }
        });

        viewAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerProfile.this,AnimalList.class);
                intent.putExtra("username", userStr);
                startActivity(intent);
            }
        });

        removeAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerProfile.this,RemoveAnimal.class);
                startActivity(intent);
            }
        });

    }
}
