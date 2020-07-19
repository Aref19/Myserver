package com.example.myserver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Work extends Fragment implements ListView.OnLongClickListener{
    View view;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       dsa: view=inflater.inflate(R.layout.work,container,false);
        listView=view.findViewById(R.id.nameofperson);
        ArrayList<String>name=new ArrayList<>();
        name.add("aref");
        ArrayAdapter arrayAdapter=new ArrayAdapter(view.getContext(),R.layout.support_simple_spinner_dropdown_item,name);
        listView.setAdapter(arrayAdapter);
        return  view;

    }



    @Override
    public boolean onLongClick(View view) {
        Toast.makeText(view.getContext(),"das",Toast.LENGTH_LONG).show();
        return false;
    }
}
