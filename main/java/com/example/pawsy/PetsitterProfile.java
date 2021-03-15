package com.example.pawsy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PetsitterProfile extends AppCompatActivity {

    TextInputEditText fullname,phoneNo,country,city,price,description;
    TextView name,user;
    ImageView edit;
    String userStr;
    Button seeReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petsitter_profile);

        fullname = findViewById(R.id.fullname);
        phoneNo = findViewById(R.id.phoneNo);
        country = findViewById(R.id.country);
        city = findViewById(R.id.city);
        name = findViewById(R.id.name);
        user = findViewById(R.id.username);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        edit =findViewById(R.id.edit);
        seeReviews = (Button) findViewById(R.id.reviews);

        userStr = getIntent().getStringExtra("username");

        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("petsitter");
        Query checkUser = reference.orderByChild("username").equalTo(userStr);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnaphot){

                if (dataSnaphot.exists()) {
                    //owner = dataSnaphot.child(userStr).getValue(Owner.class);
                    String fullnameStr = dataSnaphot.child(userStr).child("fullName").getValue(String.class);
                    String phoneStr = dataSnaphot.child(userStr).child("phoneNumber").getValue(String.class);
                    String countryStr = dataSnaphot.child(userStr).child("country").getValue(String.class);
                    String cityStr = dataSnaphot.child(userStr).child("city").getValue(String.class);
                    String priceStr = dataSnaphot.child(userStr).child("price").getValue(String.class);
                    String descriptionStr = dataSnaphot.child(userStr).child("description").getValue(String.class);
                    fullname.setText(fullnameStr);
                    phoneNo.setText(phoneStr);
                    country.setText(countryStr);
                    city.setText(cityStr);
                    name.setText(fullnameStr);
                    user.setText(userStr);
                    price.setText(priceStr);
                    description.setText(descriptionStr);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){
            }

        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetsitterProfile.this,EditPetsitterProfile.class);
                intent.putExtra("username", userStr);
                startActivity(intent);
            }
        });

        seeReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetsitterProfile.this,ReviewPage.class);
                intent.putExtra("username", userStr);
                startActivity(intent);
            }
        });
    }
}