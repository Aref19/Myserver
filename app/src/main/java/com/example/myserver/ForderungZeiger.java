package com.example.myserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myserver.Save1.Fordrung0;
import com.example.myserver.Save1.Database1;
import com.example.myserver.Save1.Warenbearbeiten;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ForderungZeiger extends AppCompatActivity  {
     ListView listView;
     String id;
     Warenbearbeiten warenbearbeiten;
     Database1 database1;
     TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forderung_zeiger);
     Bundle intent=getIntent().getExtras();
     id=intent.get("selct").toString();
     listView=findViewById(R.id.date);
     textView=findViewById(R.id.zeiger);
     warenbearbeiten=new Warenbearbeiten();
     database1=Database1.getInstance(this);

        date();
    }
    private void date(){
        /*
        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        final DocumentReference documentReference=firebaseFirestore.collection("Request").document("Pzj0LpjRgjfHc6IzZToYYdNOtGr2");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot=task.getResult();
               if (documentSnapshot.exists()){
                Map<String,Object>maps=documentSnapshot.getData();
                String s= maps.get("size").toString();
                   Object ob= new HashMap<>();
                   String war = null;
                   HashMap<String,String> lachen=new HashMap<>();
                 for(int i=0; i<Integer.parseInt(s);i++){
                     JSONObject jsonObject=new JSONObject();
                     Brobe brobe=
                      Log.i("getdata", "onComplete: "+);
                 }


                  //  warenbearbeiten.bestellung(bestellungs);

                    }

                }
            }Uri uri=Uri.parse("https://console.firebase.google.com/project/foes-c608b/database/firestore/data~2FRequest");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);

        });

         */

        anzeigen();

    }

  private void anzeigen(){
      FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

      final DatabaseReference databaseReference = firebaseDatabase.getReference().child("Reqest").child(id);
      databaseReference.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              String datum ="";
              for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                  datum+=dataSnapshot1.getKey()+"-";
              }

              String []kdatu= datum.split("-");
              ArrayList<String>datums=new ArrayList<>();

              for (int i=0;i<kdatu.length;i++){
                  datums.add(kdatu[i]);
              }
              ArrayAdapter arrayAdapter=new ArrayAdapter(getApplication(),R.layout.support_simple_spinner_dropdown_item,datums);
              listView.setAdapter(arrayAdapter);
              listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                     String textView=( (TextView)view).getText().toString();
                         fordrungholen(textView,i);

                  }
              });

          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {
              Log.i("malsehen", "onDataChange: "+databaseError);
          }
      });


  }
  private  void fordrungholen(String key,int i){
      FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
           final DatabaseReference databaseReference = firebaseDatabase.getReference().child("Reqest").child(id).child(key);
           databaseReference.addValueEventListener(new ValueEventListener() {
               @SuppressLint("ResourceAsColor")
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   ArrayList<Fordrung0>arraylistFordrung =new ArrayList<>();
                   String name ="";
                   for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                       name+=dataSnapshot1.getKey()+"-";
                       Log.i("child", "onDataChange: "+name);
                   }


                   if(!name.contains("status")){
                       for(int i=0;i<name.split("-").length;i++){
                           Fordrung0 fordrung0=dataSnapshot.child("fordrung"+i).getValue(Fordrung0.class);
                           arraylistFordrung.add(fordrung0);
                       }
                       String sachen="" ;
                       for(int i=0;i<arraylistFordrung.size();i++){
                           sachen+= "name :"+arraylistFordrung.get(i).getName()+" anzahl :"+arraylistFordrung.get(i).getAnzahl()+"\n";

                           Log.i("size", "onDataChange: "+arraylistFordrung.size());
                       }
                       textView.setText(sachen );
                   }else {
                       textView.setText("ist gemacht");
                       return;
                   }


               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           });
       }



    private void jeson(String s){
        Toast.makeText(getApplication(),"dsa",Toast.LENGTH_LONG).show();
     JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, s, null,
             new Response.Listener<JSONObject>() {
                 @Override
                 public void onResponse(JSONObject response) {
                     try {
                         Toast.makeText(getApplication(),"dsa",Toast.LENGTH_LONG).show();
                         JSONObject jsonObject=response.getJSONObject("fordrung");
                         Fordrung0 brobe=new Fordrung0();
                         brobe.setName(jsonObject.getString("name"));
                         Toast.makeText(getApplication(),"dsa"+brobe.getName(),Toast.LENGTH_LONG).show();

                     } catch (JSONException e) {
                         Toast.makeText(getApplication(),"dsa"+e,Toast.LENGTH_LONG).show();
                     }
                 }
             }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
             Toast.makeText(getApplication(),"dsa"+error,Toast.LENGTH_LONG).show();
         }
     });
    }


}