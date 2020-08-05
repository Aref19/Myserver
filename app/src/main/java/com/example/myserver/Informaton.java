package com.example.myserver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.zip.Inflater;

public class Informaton extends Fragment  {
    View view;
    TextView name,email,strasse,plz,hus;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.waren,container,false);
        name= view.findViewById(R.id.name);
        email= view.findViewById(R.id.email);
        strasse= view.findViewById(R.id.strasse);
        plz= view.findViewById(R.id.plz);
        hus= view.findViewById(R.id.hausnum);

        return view;
    }


}
