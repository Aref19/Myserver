package com.example.myserver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Path;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class Home extends Fragment implements View.OnClickListener {
    View view;
    ImageView imageView;
    Uri imgUri;
    EditText name, des, preis;
    FirebaseStorage storage;
    Ware ware;
    Context context;
    public static int num;
    public static String im;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home, container, false);
        Button button = view.findViewById(R.id.schek);
        Button fotohoch = view.findViewById(R.id.fotouplod);
        imageView = view.findViewById(R.id.uploudimage);
        imageView.setOnClickListener(this);
        button.setOnClickListener(this);
        fotohoch.setOnClickListener(this);
        name = view.findViewById(R.id.name);
        des = view.findViewById(R.id.des);
        preis = view.findViewById(R.id.preis);
        storage = FirebaseStorage.getInstance();
        Choildern.context = view.getContext();
        Log.i("context", "onCreateView: " + context);
        preis.setError(null);
        return view;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.uploudimage) {
            imageholen();
        } else if (view.getId() == R.id.schek) {
            loadImage();
            Choildern.uploudData();
        } else if (view.getId() == R.id.fotouplod) {
            imageStroge();

        }


    }


    private void imageStroge() {
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference(name.getText().toString().trim().toLowerCase());
        // Get the data from an ImageView as bytes
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = storageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.i("sucss", "onFailure: " + "sucss");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...

            }
        });

    }

    private void imageholen() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imgUri = data.getData();
        imageView.setImageURI(imgUri);
        Log.i("name", "onActivityResult: " + imageView.getId());
    }

    private void loadImage() {
        StorageReference storageReference = storage.getReference(name.getText().toString().trim().toLowerCase());
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String image = uri.toString();
                uplaodWare(image);
                Log.i("image1", "onSuccess: " + image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.i("image2", "onSuccess: " + e.toString());
            }
        });
    }

    private void uplaodWare(final String image) {

        Ware ware = new Ware(">" + name.getText().toString(), ">" + des.getText().toString(), ">" + image);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("images");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long j = dataSnapshot.getChildrenCount();
                Log.i("aref", "onDataChange: ");
                int i = (int) j + 1;
                Choildern choildern = new Choildern();
                String imagnum = "im" + i + "ge";

                Uri[] imges = new Uri[(int) dataSnapshot.getChildrenCount()];
                String[] daten;
                String gesmtdata = "";

                for (int v = 1; v <= imges.length; v++) {
                    String s = "im" + v + "ge";
                    gesmtdata = dataSnapshot.child(s).getValue().toString();
                    Log.i("gesamt", "onDataChange: " + gesmtdata);
                    Log.i("TAGgg1", "onDataChange: " + gesmtdata.contains(name.getText().toString().toLowerCase().trim()));
                    if (gesmtdata.contains(name.getText().toString().toLowerCase().trim())) {
                        Log.i("TAGgg", "onDataChange: " + gesmtdata.contains(name.getText().toString().toLowerCase().trim()));
                        choildern.child(s, image, name.getText().toString(), des.getText().toString(), context, preis.getText().toString());

                    } else {
                        Log.i("MaG", "onDataChange: " + gesmtdata.contains(name.getText().toString().toLowerCase().trim()));
                        choildern.child(imagnum, image, name.getText().toString(), des.getText().toString(), context, preis.getText().toString());

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("iacont", "onDataChange: " + databaseError.toString());
            }
        });
        // databaseReference.setValue(ware);
        //databaseReference.se;


    }

    private void fugdata(int i, String image) {
        num = i;
        im = image;

    }

    private void imageStroge2() {

        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference(name.getText().toString().trim().toLowerCase());
        // Get the data from an ImageView as bytes
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = storageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.i("sucss", "onFailure: " + "sucss");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...

            }
        });

    }


}

class Choildern {
    public static String num;
    public static String mes;
    private static String name;
    private static String des;
    static Context context;
    private static String preis;

    public void child(String n, String m, String name, String des, Context context, String preis) {
        String i = n;
        String s = m;
        this.num = i;
        this.mes = s;
        this.name = name;
        this.des = des;
        this.preis = preis;


    }

    static void uploudData() {
        try {
            Log.i("num", "uploudData: " + num);
            if (mes != null) {
                FirebaseDatabase.getInstance().getReference().child("images").child(num).setValue(">" + (name.toLowerCase()) + ">" + des + ">" + mes);
                FirebaseDatabase.getInstance().getReference().child("images2").child(name.toLowerCase()).setValue((name.toLowerCase()) + ">" + des + ">" + mes);
                FirebaseDatabase.getInstance().getReference().child("preis").child((name.toLowerCase())).setValue(preis);
            } else {

                Toast.makeText(context.getApplicationContext(), "try agin", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {

        }

    }
}
