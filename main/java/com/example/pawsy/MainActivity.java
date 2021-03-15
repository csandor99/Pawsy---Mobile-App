package com.example.pawsy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    CardView search,profile,logout;
    TextView helloUser;
    String username;
    Boolean isOwner=false, isPetsitter=false, isAssociation=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        profile = findViewById(R.id.profile);
        logout = findViewById(R.id.logout);
        helloUser = findViewById(R.id.hello_user);

        username = getIntent().getStringExtra("username");
        helloUser.setText("Hello " + username + "!");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("owner");
                Query checkUser = reference.orderByChild("username").equalTo(username);
                DatabaseReference reference1  = FirebaseDatabase.getInstance().getReference("petsitter");
                Query checkUser1 = reference1.orderByChild("username").equalTo(username);
                DatabaseReference reference2  = FirebaseDatabase.getInstance().getReference("association");
                Query checkUser2 = reference2.orderByChild("username").equalTo(username);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnaphot){

                        if (dataSnaphot.exists()) {
                            Intent intent = new Intent(MainActivity.this, OwnerProfile.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError){
                    }

                });

                checkUser1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnaphot){

                        if (dataSnaphot.exists()) {
                            Intent intent1 = new Intent(MainActivity.this, PetsitterProfile.class);
                            intent1.putExtra("username", username);
                            startActivity(intent1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError){
                    }

                });

                checkUser2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnaphot){

                        if (dataSnaphot.exists()) {
                            Intent intent2 = new Intent(MainActivity.this, AssociationProfile.class);
                            intent2.putExtra("username", username);
                            startActivity(intent2);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError){
                    }

                });



            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("owner");
                Query checkUser = reference.orderByChild("username").equalTo(username);
                DatabaseReference reference1  = FirebaseDatabase.getInstance().getReference("petsitter");
                Query checkUser1 = reference1.orderByChild("username").equalTo(username);
                DatabaseReference reference2  = FirebaseDatabase.getInstance().getReference("association");
                Query checkUser2 = reference2.orderByChild("username").equalTo(username);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnaphot){

                        if (dataSnaphot.exists()) {
                            Intent intent = new Intent(MainActivity.this, SearchScreen.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError){
                    }

                });

                checkUser1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnaphot){

                        if (dataSnaphot.exists()) {
                            Intent intent1 = new Intent(MainActivity.this, SearchScreen.class);
                            intent1.putExtra("username", username);
                            startActivity(intent1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError){
                    }

                });

                checkUser2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnaphot){

                        if (dataSnaphot.exists()) {
                            Intent intent2 = new Intent(MainActivity.this, SearchScreen.class);
                            intent2.putExtra("username", username);
                            startActivity(intent2);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError){
                    }

                });

            }
        });
    }



}