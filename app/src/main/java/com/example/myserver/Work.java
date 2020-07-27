package com.example.myserver;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myserver.Save.DataBestellung;
import com.example.myserver.Save.Database;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Work extends Fragment implements ListView.OnLongClickListener , View.OnClickListener{
    View view;
    ListView listView;
    ArrayList<String>name;
    ArrayList<Forderung>forRequ;
    ArrayList<Person>forPerson;
    ArrayList<String>namePerson;
    Button button,button1;
    Database  database;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       dsa: view=inflater.inflate(R.layout.work,container,false);
        listView=view.findViewById(R.id.nameofperson);
        button=view.findViewById(R.id.click);
        button1=view.findViewById(R.id.button);
        button1.setOnClickListener(this);
        name=new ArrayList<>();
        requestholen();
     database=Database.getInstance(view.getContext());

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
        int v=0;
        forRequ=new ArrayList<>();
        forPerson=new ArrayList<>();
        namePerson=new ArrayList<>();
        Log.i("keys", "forderung: "+keys.length);
        do {
            Log.i("keys", "forderung: "+keys[1]);
        DatabaseReference databaseReference=firebaseDatabase.getReference().child("Reqest").child(keys[v]);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int j=0;
                Forderung [] arrayforderung;
                Map<String, Forderung[]> stringForderungMap=new HashMap<>();
                Person person=new Person();
                person = dataSnapshot.getValue(Person.class);
                forPerson.add(person);
                namePerson.add( person.getName()+ " | "+ person.getEmail());
                String namepersonemail=person.getName()+person.getEmail();
                Log.i("save", "onDataChange: "+namepersonemail);
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
                         stringForderungMap.put(name+person.getEmail(),arrayforderung);
                         Log.i("save", "onDataChange: "+name+person.getEmail());

                         Date date=new Date();
                            for (int v=0;v<arrayforderung.length;v++){
                               // database.daoData().inserNmaeAnzahl(arrayforderung[i].getName(),String.valueOf(arrayforderung[i].anzahl),date.toString(),v);
                               database.daoData().insert(new DataBestellung(namepersonemail,arrayforderung[i].getName(),String.valueOf(arrayforderung[i].anzahl),date.toString()));
                                Log.i("mal", "onLongClick: "+"dsaasdasd");

                            }


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
        v++;

        }while (keys.length>v);


    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.click){
          Toast.makeText(view.getContext(),"dsa",Toast.LENGTH_LONG).show();

        }else if(view.getId()==R.id.button){
            List<DataBestellung  >dataBestellungs;          Log.i("mal", "onLongClick: "+database.daoData().sele());
            dataBestellungs=database.daoData().getsache("Khalelarefobaid6@gmail.com ".trim());

            Log.i("mal", "onLongClick: "+database.daoData().sele()+dataBestellungs.get(0).getNamePersEmail());
        }
    }
    private String newIdErstellen(String name,ArrayList<String >names){
       Map<String,String>id;
        return name;
    }

}
class adbter extends BaseAdapter{


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
