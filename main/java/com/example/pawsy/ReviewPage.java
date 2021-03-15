package com.example.pawsy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewPage extends AppCompatActivity {
    String userStr;
    ArrayList<Review> list;
    ArrayAdapter<Review> adapter;
    Review review;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);

        userStr = getIntent().getStringExtra("username");

        ListView listView = findViewById(R.id.list_view);
        listView.setClickable(true);
        review = new Review();
        list = new ArrayList<>();
        adapter = new ArrayAdapter<Review>(this,android.R.layout.simple_list_item_1,list);

        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("reviews");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    review = ds.getValue(Review.class);
                    if(review.getUsernamePetSitter().equals(userStr)) {
                        list.add(review);
                    }
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}