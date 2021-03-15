package com.example.pawsy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AssociationPublicProfile extends AppCompatActivity {

    TextInputEditText fullname,phoneNo,country,city,description;
    Button donate;
    TextView name,user;
    ImageView edit;
    String userStr,email;
    String linkStr;
    ImageView sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_association_public_profile);

        fullname = findViewById(R.id.fullname);
        phoneNo = findViewById(R.id.phoneNo);
        country = findViewById(R.id.country);
        city = findViewById(R.id.city);
        name = findViewById(R.id.name);
        user = findViewById(R.id.username);
        edit =findViewById(R.id.edit);
        donate = findViewById(R.id.donate);
        description = findViewById(R.id.description);
        sendEmail = findViewById(R.id.send_mail);

        userStr = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");

        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("association");
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
                    String descriptionStr = dataSnaphot.child(userStr).child("description").getValue(String.class);
                    linkStr = dataSnaphot.child(userStr).child("linkSite").getValue(String.class);
                    fullname.setText(fullnameStr);
                    phoneNo.setText(phoneStr);
                    country.setText(countryStr);
                    city.setText(cityStr);
                    name.setText(fullnameStr);
                    user.setText(userStr);
                    description.setText(descriptionStr);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){
            }

        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(linkStr.equals("Your site here!")){
                    Toast.makeText(getApplicationContext(),"User doesn't have a donation link!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Uri uri = Uri.parse(linkStr);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

            }
        });

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssociationPublicProfile.this,SendEmail.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }
}