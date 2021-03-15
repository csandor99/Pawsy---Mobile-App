package com.example.pawsy;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LeaveReview extends AppCompatActivity {
    private RatingBar ratingStars;
    private CheckBox checkBox;
    private Button leaveReview;
    private TextInputLayout review;
    FirebaseDatabase rootNode;
    DatabaseReference ref;
    float rating;
    String userStr,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_review);

        ratingStars = findViewById(R.id.rating);
        checkBox = findViewById(R.id.checkbox);
        leaveReview = findViewById(R.id.leaveReview);
        review = findViewById(R.id.review);

        userStr = getIntent().getStringExtra("username");
        profile = getIntent().getStringExtra("petsitter");

        ratingStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b){
                //int rating = (int) v;
                String message = null;
                rating = ratingBar.getRating();
            }
        });

        leaveReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                String uniqueID = rootNode.getReference("reviews").push().getKey();
                String reviewText = review.getEditText().getText().toString();
                String owner = userStr;
                String petsitter = profile;
                Boolean checked = false;
                if (checkBox.isChecked()) checked = true;

                ref = rootNode.getReference("reviews");
                Review review = new Review(uniqueID, petsitter, owner, reviewText, rating, checked);
                ref.child(uniqueID).setValue(review);

                LeaveReview.super.onBackPressed();
                Toast.makeText(getApplicationContext(),"Review successfully given!",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
