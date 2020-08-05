package com.example.myserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myserver.Save.DataBestellung;
import com.example.myserver.Save.Database;

import java.util.ArrayList;
import java.util.List;

public class Info extends AppCompatActivity {
   EditText name,email,strasse,plz,haus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waren);
         name=findViewById(R.id.name);
      email=findViewById(R.id.email);
        strasse=findViewById(R.id.strasse);
        plz=findViewById(R.id.plz);
        haus=findViewById(R.id.hausnum);

        Database database=Database.getInstance(this);

        Bundle bundle=getIntent().getExtras();
        String id=bundle.get("key").toString();
        Log.i("namefo", "onCreateView: "+name);
        List<DataBestellung> dataBestellungs= database.daoData().getsache(id);

        name.setText(dataBestellungs.get(0).getName());


    }
}