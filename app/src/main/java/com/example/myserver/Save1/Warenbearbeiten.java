package com.example.myserver.Save1;

import java.util.ArrayList;
import java.util.Map;

public class Warenbearbeiten {

    public WarenBestellung bestellung(ArrayList<WarenBestellung> maps){
      WarenBestellung warenbearbeiten=new WarenBestellung(maps.get(0).getDate(),maps.get(0).getName(),maps.get(0).getAnzahle()
      );

        return warenbearbeiten;
    }
}
