package com.example.myserver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myserver.Save.DataBestellung;
import com.example.myserver.Save.Database;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class Work extends Fragment implements ListView.OnItemLongClickListener {
    View view;
    ListView listView;
    ArrayList<String> lsitper;
    Calendar date;
    TextView news;

    Button button, button1;
    Database database;
        Calendar calendar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.work, container, false);
        listView = view.findViewById(R.id.nameofperson);
        lsitper = new ArrayList<>();
        requestholen();
        database = Database.getInstance(view.getContext());
        listView.setOnItemLongClickListener(this);
        news=view.findViewById(R.id.news);
        calendar=Calendar.getInstance();

        return view;

    }

    private void requestholen() {
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Reqest");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = "";
                Log.i("schlu", "listViewFullen: " + name);
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    name += dataSnapshot1.getKey() + "-";
                }
                String[] key = name.split("-");
                for (int i=0;i<key.length;i++) {
                    lsitper.add(key[i]);

                }

                Log.i("schlu", "listViewFullen: " + key[0]);
              //  fordrungholen(key);
              //  listViewFullen(key);
                 forderung(key);

                     ArrayAdapter arrayAdapter=new ArrayAdapter(view.getContext(),R.layout.support_simple_spinner_dropdown_item,lsitper);
                     listView.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("eror", "onCancelled: " + databaseError.toString());

            }
        });


    }


    private void forderung(final String [] keys){
        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        final FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        int v=0;
        Log.i("keys", "forderung: "+keys.length);
        final ArrayList<String > strings=new ArrayList<>();
   do {
        DocumentReference documentReference=firebaseFirestore.collection("Request").document(keys[v]).collection(keys[v]).document();
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentReference=task.getResult();
                if(documentReference.exists()){
                    Map<String,Object>f=documentReference.getData();
                    Log.i("name", "onComplete: "+f.get("name"));
                }
                Log.i("task1", "onComplete: "+task.isSuccessful());

            }
        });
        DatabaseReference databaseReference=firebaseDatabase.getReference().child("Request").child(keys[v]).child(keys[v]);
        final int j=v;

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 String name="";

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                      name+=dataSnapshot1.getKey()+",";
                    Log.i("name", "onDataChange: "+name);

                }
                String [] dat=name.split(",");

                int day=calendar.get(Calendar.DAY_OF_MONTH);
                for(int i=0;i<name.split(",").length;i++){
                    Log.i("welche", "onDataChange: "+dat[i]);
                    if(dat[i].contains(""+day)){
                        strings.add(""+j);
                    }

                }        String welche="";
                for(int j=0;j<strings.size();j++){
                      welche +=strings.get(j)+",";
                      news.setText(welche);
                }


        }





            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("eror", "onCancelled: "+databaseError.toString());

            }
        });
        v++;

        }while (keys.length>v);
     

    }


/*
    public void listViewFullen(String[] schlussel) {
        FirebaseFirestore firebaseDatabase = FirebaseFirestore.getInstance();
        int i = 0;
        Log.i("schlu", "listViewFullen: " + schlussel[0]);
        do {
            DocumentReference documentReference = firebaseDatabase.collection("benutzer").document(schlussel[i]);
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (documentSnapshot.exists()) {
                            Map<String, Object> personMap = documentSnapshot.getData();
                            Person person = new Person();
                            person.setEmail(personMap.get("Email").toString());
                            Log.i("status", "onComplete: "+database.daoData().check(personMap.get("id").toString()));
                    //  if (!database.daoData().check(personMap.get("id").toString())) {
                      //          database.daoData().insert(new DataBestellung(personMap.get("id").toString(), personMap.get("Email").toString(), personMap.get("name").toString(),
                                        personMap.get("Strasse").toString()
                                        , personMap.get("haus").toString(), personMap.get("plz").toString()));
                              Log.i("malse", "onComplete: "+database.daoData().getsache(personMap.get("id").toString()).get(0).getName());
                                Work.this.lsitper.add(personMap.get("id").toString());
                                ArrayAdapter arrayAdapter = new ArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item, Work.this.lsitper);
                                listView.setAdapter(arrayAdapter);

                          //  }else {
                          //Work.this.lsitper.add(personMap.get("id").toString());
                          //ArrayAdapter arrayAdapter = new ArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item, Work.this.lsitper);
                          //listView.setAdapter(arrayAdapter);
                      }


                        }
                    }


           //     }
         //   });
            i++;
        //} while (schlussel.length > i);

    }

 */


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedView=((TextView) view).getText().toString();
        Intent intent=new Intent(view.getContext(), Info.class);
        Bundle bundle=new Bundle();
        bundle.putString("key",selectedView);
        bundle.putString("pos"  ,""+i);
        intent.putExtras(bundle);
        startActivity(intent);
        return false;
    }
    private void fordrungholen(String []requst){

        int i=0;
        do {

            i++;
      }while (requst.length>i);
    }


}



