package com.example.myserver;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Work extends Fragment implements ListView.OnLongClickListener , View.OnClickListener{
    View view;
    ListView listView;
    ArrayList<String>name;
    ArrayList<Forderung>forRequ;
    ArrayList<Person>forPerson;
    ArrayList<String>namePerson;
    Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       dsa: view=inflater.inflate(R.layout.work,container,false);
        listView=view.findViewById(R.id.nameofperson);
        button=view.findViewById(R.id.click);
        name=new ArrayList<>();
        requestholen();

        return  view;

    }



    @Override
    public boolean onLongClick(View view) {

        Toast.makeText(view.getContext(),"das",Toast.LENGTH_LONG).show();
        return false;
    }

    private void requestholen(){
        final FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference().child("Reqest");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 Iterable<DataSnapshot> id=dataSnapshot.getChildren();
                String name="";
               for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                    name+=dataSnapshot1.getKey()+"-";
                   Log.i("name", "onDataChange: "+name);

               }
               String [] key=name.split("-");
               forderung(key);
                //name.add(forderung.getName());
                //ArrayAdapter arrayAdapter=new ArrayAdapter(view.getContext(),R.layout.support_simple_spinner_dropdown_item,name);
                //listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("eror", "onCancelled: "+databaseError.toString());

            }
        });




    }


    private void forderung(final String [] keys){
        final FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        int i=0;
        forRequ=new ArrayList<>();
        forPerson=new ArrayList<>();
        namePerson=new ArrayList<>();
        Log.i("keys", "forderung: "+keys.length);
        do {
            Log.i("keys", "forderung: "+keys[1]);
        DatabaseReference databaseReference=firebaseDatabase.getReference().child("Reqest").child(keys[i]);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int j=0;
                Forderung [] arrayforderung;
                Map<String, Forderung[]> stringForderungMap=new HashMap<>();
                Person person=new Person();
                person = dataSnapshot.getValue(Person.class);
                forPerson.add(person);
                namePerson.add(person.getName());
                String name=person.getName();
                ArrayAdapter arrayAdapter=new ArrayAdapter(view.getContext(),R.layout.support_simple_spinner_dropdown_item,namePerson);
                listView.setAdapter(arrayAdapter);
                int anzahl=(int)dataSnapshot.child("forderung").getChildrenCount();
                 arrayforderung=new Forderung[anzahl];
                 for (int i=0;i<anzahl;i++){
                     Forderung forderung=new Forderung();
                     forderung=  dataSnapshot.child("forderung").child(String.valueOf(i)).getValue(Forderung.class);
                     forRequ.add(forderung);
                     arrayforderung[i]=forderung;
                     Log.i("i", "onDataChange: "+i);
                     if(anzahl==(i+1)){
                         stringForderungMap.put(name,arrayforderung);

                     }

                 }
                 j++;


                //name.add(forderung.getName());
                //ArrayAdapter arrayAdapter=new ArrayAdapter(view.getContext(),R.layout.support_simple_spinner_dropdown_item,name);
                //listView.setAdapter(arrayAdapter);

        }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("eror", "onCancelled: "+databaseError.toString());

            }
        });
        i++;

        }while (keys.length>i);


    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.click){
          Toast.makeText(view.getContext(),"dsa",Toast.LENGTH_LONG).show();

        }
    }
}
