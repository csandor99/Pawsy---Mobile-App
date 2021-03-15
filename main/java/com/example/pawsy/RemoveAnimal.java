package com.example.pawsy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RemoveAnimal extends AppCompatActivity {
    TextInputLayout microchip;
    FirebaseDatabase rootNode;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_animal);

        microchip = findViewById(R.id.microchip);
        Button remove = findViewById(R.id.remove_animals);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePet(microchip.getEditText().getText().toString());
            }
        });

    }

    void deletePet(String microchipID){
        ref = FirebaseDatabase.getInstance().getReference("animals").child(microchipID);
        ref.removeValue();
        RemoveAnimal.super.onBackPressed();
        Toast.makeText(getApplicationContext(),"Pet removed successfully",Toast.LENGTH_SHORT).show();
    }
}