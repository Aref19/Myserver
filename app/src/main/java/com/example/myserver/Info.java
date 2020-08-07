package com.example.myserver;

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

import java.util.ArrayList;
import java.util.List;

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




    }
    private void dataladen(){
        Bundle bundle=getIntent().getExtras();
         id=bundle.get("key").toString();
        int i=Integer.parseInt( bundle.get("pos").toString());
        Log.i("namefo", "onCreateView: "+name);
        List<DataBestellung> dataBestellungs= database.daoData().getsache(id);

        name.setText(dataBestellungs.get(i).getName());
        email.setText(dataBestellungs.get(i).getNamePersEmail());
        strasse.setText(dataBestellungs.get(i).getStrasse());
        plz.setText(dataBestellungs.get(i).getPlz());
        haus.setText(dataBestellungs.get(i).getDate());
    }

    public void forderung(View view) {
        Intent intent=new Intent(this,ForderungZeiger.class);
        Bundle bundle=new Bundle();
        bundle.putString("selct",id);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}