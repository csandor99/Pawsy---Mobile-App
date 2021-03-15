package com.example.pawsy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AnimalList extends AppCompatActivity {
    String userStr;
    ArrayList<Animal> list;
    ArrayAdapter<Animal> adapter;
    Animal pet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);

        userStr = getIntent().getStringExtra("username");

        ListView pets = findViewById(R.id.pet_list);
        pets.setClickable(false);
        pet = new Animal();
        list = new ArrayList<>();
        adapter = new ArrayAdapter<Animal>(this,android.R.layout.simple_list_item_1,list);

        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("animals");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    pet = ds.getValue(Animal.class);
                    if(pet.getOwner().equals(userStr)) {
                        list.add(pet);
                    }
                }
                pets.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}