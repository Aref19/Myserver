package com.example.myserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myserver.Save.DataBestellung;
import com.example.myserver.Save.Database;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Info extends AppCompatActivity {
   EditText name,email,strasse,plz,haus;
    Database database;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waren);
         name=findViewById(R.id.name);
      email=findViewById(R.id.email);
        strasse=findViewById(R.id.strasse);
        plz=findViewById(R.id.plz);
        haus=findViewById(R.id.hausnum);
        database=Database.getInstance(this);
        dataladen();
        laddata();




    }
    private void dataladen(){
        Bundle bundle=getIntent().getExtras();
         id=bundle.get("key").toString();
        Log.i("id", "dataladen: "+id);
        int i=Integer.parseInt( bundle.get("pos").toString());
        Log.i("namefo", "onCreateView: "+name);
        List<DataBestellung> dataBestellungs= database.daoData().getsache(id);


    }

    public void forderung(View view) {
        Intent intent=new Intent(this,ForderungZeiger.class);
        Bundle bundle=new Bundle();
        bundle.putString("selct",id);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private  void laddata(){
        final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.collection("benutzer").document(id);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                   if(task.isSuccessful()){
                      DocumentSnapshot dataSnapshot=task.getResult();
                      if(dataSnapshot.exists()){
                          Map<String,Object> person=dataSnapshot.getData();
                          name.setText(person.get("name").toString());
                          email.setText(person.get("Email").toString());
                          strasse.setText(person.get("Strasse").toString());
                          plz.setText(person.get("plz").toString());
                          haus.setText(person.get("haus").toString());

                      }

                   }
            }
        });
    }
}