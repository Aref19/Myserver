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

import java.util.ArrayList;
import java.util.Map;

public class Work extends Fragment implements ListView.OnLongClickListener , View.OnClickListener{
    View view;
    ListView listView;
    ArrayList<String>name;
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
                 String id=dataSnapshot.getValue().toString();
                Log.i("req", "onDataChange: "+id);


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


    private void forderung(){
        final FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference().child("Reqest").child("SCAm3KbOkzNS4Y6wL1tWPhrsxwv2");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Forderung forderung=new Forderung();
                long anzahl=  dataSnapshot.child("forderung").getChildrenCount();
                forderung=  dataSnapshot.child("forderung").child("0").getValue(Forderung.class);
                Log.i("data", "onDataChange: "+forderung.getName());

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

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.click){
          Toast.makeText(view.getContext(),"dsa",Toast.LENGTH_LONG).show();

        }
    }
}
