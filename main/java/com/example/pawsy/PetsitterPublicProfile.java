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

public class PetsitterPublicProfile extends AppCompatActivity {

    TextInputEditText fullname,phoneNo,country,city,price,description;
    TextView name,user;
    String userStr,profile,email;
    Button seeReviews,makeReviews;
    ImageView sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petsitter_public_profile);

        fullname = findViewById(R.id.fullname);
        phoneNo = findViewById(R.id.phoneNo);
        country = findViewById(R.id.country);
        city = findViewById(R.id.city);
        name = findViewById(R.id.name);
        user = findViewById(R.id.username);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        seeReviews = (Button) findViewById(R.id.reviews);
        makeReviews = (Button) findViewById(R.id.make_review);
        sendEmail = findViewById(R.id.send_mail);

        userStr = getIntent().getStringExtra("username");
        profile = getIntent().getStringExtra("profile");
        email = getIntent().getStringExtra("email");

        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("petsitter");
        Query checkUser = reference.orderByChild("username").equalTo(profile);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnaphot){

                if (dataSnaphot.exists()) {
                    //owner = dataSnaphot.child(userStr).getValue(Owner.class);
                    String fullnameStr = dataSnaphot.child(profile).child("fullName").getValue(String.class);
                    String phoneStr = dataSnaphot.child(profile).child("phoneNumber").getValue(String.class);
                    String countryStr = dataSnaphot.child(profile).child("country").getValue(String.class);
                    String cityStr = dataSnaphot.child(profile).child("city").getValue(String.class);
                    String priceStr = dataSnaphot.child(profile).child("price").getValue(String.class);
                    String descriptionStr = dataSnaphot.child(profile).child("description").getValue(String.class);
                    fullname.setText(fullnameStr);
                    phoneNo.setText(phoneStr);
                    country.setText(countryStr);
                    city.setText(cityStr);
                    name.setText(fullnameStr);
                    user.setText(profile);
                    price.setText(priceStr);
                    description.setText(descriptionStr);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){
            }

        });

        seeReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetsitterPublicProfile.this,ReviewPage.class);
                intent.putExtra("username", profile);
                startActivity(intent);
            }
        });

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetsitterPublicProfile.this,SendEmail.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        makeReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetsitterPublicProfile.this,LeaveReview.class);
                intent.putExtra("username", userStr);
                intent.putExtra("petsitter", profile);
                startActivity(intent);
            }
        });
    }
}