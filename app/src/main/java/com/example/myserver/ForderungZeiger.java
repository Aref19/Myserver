package com.example.myserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myserver.Save1.Brobe;
import com.example.myserver.Save1.Database1;
import com.example.myserver.Save1.WarenBestellung;
import com.example.myserver.Save1.Warenbearbeiten;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

public class ForderungZeiger extends AppCompatActivity {
     ListView listView;
     String id;
     Warenbearbeiten warenbearbeiten;
     Database1 database1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forderung_zeiger);
     Bundle intent=getIntent().getExtras();
     id=intent.get("selct").toString();
     warenbearbeiten=new Warenbearbeiten();
     database1=Database1.getInstance(this);

        date();
    }
    private void date(){
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
                   Log.i("getdata", "onComplete: "+s);
                   jeson(maps.get("fordrung0").toString());
                   Object ob= new HashMap<>();
                 for(int i=0; i<Integer.parseInt(s);i++){
                       Object brobe= maps.get("fordrung"+i);


                             Log.i("getdata", "onComplete: "+brobe);

                 }


                  //  warenbearbeiten.bestellung(bestellungs);

                    }
                    Log.i("getdata", "onComplete: "+documentSnapshot.toString());
                }
            }
        });
        /*
        Uri uri=Uri.parse("https://console.firebase.google.com/project/foes-c608b/database/firestore/data~2FRequest");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);

         */
    }

    @Override
    protected void onStop() {
        super.onStop();
      //  date();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //date();
    }
    private void jeson(String s){
     JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, s, null,
             new Response.Listener<JSONObject>() {
                 @Override
                 public void onResponse(JSONObject response) {
                     try {
                         JSONObject jsonObject=response.getJSONObject("fordrung0");
                         Brobe brobe=new Brobe();
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