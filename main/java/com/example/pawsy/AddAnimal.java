package com.example.pawsy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAnimal extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference ref;
    Button back, add;
    TextInputLayout name, gender, description, specialNeeds, microchip,age;
    String userStr,ownerStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        back = (Button) findViewById(R.id.back_btn);
        add = (Button) findViewById(R.id.add_btn);
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        description = findViewById(R.id.description);
        specialNeeds = findViewById(R.id.special_needs);
        microchip = findViewById(R.id.microchip);
        age = findViewById(R.id.age);

        userStr = getIntent().getStringExtra("username");
        ownerStr = getIntent().getStringExtra("fullname");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAnimal.super.onBackPressed();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addAnimal();
            }

        });
    }

    void addAnimal(){
        rootNode = FirebaseDatabase.getInstance();
        String nameStr = name.getEditText().getText().toString();
        String genderStr = gender.getEditText().getText().toString();
        String descriptionStr = description.getEditText().getText().toString();
        String specialNeedsStr = specialNeeds.getEditText().getText().toString();
        String microchipStr = microchip.getEditText().getText().toString();
        String ageStr = age.getEditText().getText().toString();

        ref = rootNode.getReference("animals");
        Animal animal = new Animal(userStr,nameStr,genderStr,descriptionStr,specialNeedsStr,microchipStr,ageStr);
        ref.child(microchipStr).setValue(animal);

        AddAnimal.super.onBackPressed();
        Toast.makeText(getApplicationContext(),"Pet added successfully!",Toast.LENGTH_SHORT).show();
    }
}