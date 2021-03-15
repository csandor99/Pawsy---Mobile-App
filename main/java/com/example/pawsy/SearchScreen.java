package com.example.pawsy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchScreen extends AppCompatActivity {
    ListView listView;
    ArrayList<Object> petsitterList;
    ArrayList<Object> filter;
    //ArrayAdapter<PetSitter> adapter;
    ArrayAdapter<Object> adapter;
    ArrayList<Association> associationList;
    //ArrayAdapter<Association> adapter1;

    String userStr;
    PetSitter petsitter;
    Association assoc;

    SearchView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        petsitter = new PetSitter();
        assoc = new Association();
        petsitterList = new ArrayList<>();
        filter = new ArrayList<>();
        associationList = new ArrayList<>();
        adapter = new ArrayAdapter<Object>(this,android.R.layout.simple_list_item_1,petsitterList);
        //adapter1 = new ArrayAdapter<Association>(this,android.R.layout.simple_list_item_1,associationList);
        listView = findViewById(R.id.list_view);
        search = findViewById(R.id.search);
        listView.setClickable(true);


        userStr = getIntent().getStringExtra("username");

        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("petsitter");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    petsitter = ds.getValue(PetSitter.class);
                    petsitterList.add(petsitter);

                }
                //listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference reference1  = FirebaseDatabase.getInstance().getReference("association");

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    assoc = ds.getValue(Association.class);
                    petsitterList.add(assoc);

                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                if(o instanceof PetSitter) {
                    PetSitter person = (PetSitter) o;
                    String petsitterProfile = person.getUsername();
                    String email = person.getEmail();

                    Intent intent = new Intent(SearchScreen.this, PetsitterPublicProfile.class);
                    intent.putExtra("username", userStr);
                    intent.putExtra("profile", petsitterProfile);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }
                else{
                    Association person = (Association) o;
                    String associationProfile = person.getUsername();
                    String email = person.getEmail();

                    Intent intent = new Intent(SearchScreen.this, AssociationPublicProfile.class);
                    intent.putExtra("username", associationProfile);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }

            }
        });

//        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                search.clearFocus();
//                if(query.isEmpty()){
//
//                }
//                else{
//                    for(Object i : petsitterList){
//                        if(i instanceof PetSitter){
//                            PetSitter p = (PetSitter) i;
//                            if(p.getUsername().equals(query)){
//                                filter.add(i);
//                            }
//                        }
//                        if(i instanceof Association){
//                            Association p = (Association) i;
//                            if(p.getUsername().equals(query)){
//                                filter.add(i);
//                            }
//                        }
//                    }
//                    petsitterList = filter;
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });

    }
}