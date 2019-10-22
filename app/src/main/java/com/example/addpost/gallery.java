package com.example.addpost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.addpost.Files.FilePaths;
import com.example.addpost.Files.FileSearch;
import com.example.addpost.Permissions.Permissions;

import java.util.ArrayList;
import java.util.List;

public class gallery extends AppCompatActivity {
    private ArrayList<String> directories;
    private static final String TAG = "gallery";
    public static List<String>  SelectedImgUrls = new ArrayList<>();
    private Spinner directorySpinner;
    public static Button SelectImgBTn;
    private String mAppend = "file:/";
    RecyclerView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = findViewById(R.id.toolbar_gallery);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),post.class));
                finish();
            }
        });


        directorySpinner = findViewById(R.id.spinner);
        gridView = findViewById(R.id.recycler_view);



           init();

           SelectImgBTn= findViewById(R.id.noOfSelectImgBtn);
           SelectImgBTn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   Intent i = new Intent(gallery.this,post.class);
                   gallery.this.startActivity(i);

               }
           });







    }
    private void init(){
        FilePaths filePaths = new FilePaths();

        //check for other folders indide "/storage/emulated/0/pictures"
        if (FileSearch.getDirectoryPaths(filePaths.PICTURES) != null) {
            directories = FileSearch.getDirectoryPaths(filePaths.PICTURES);
        }
        directories.add(filePaths.CAMERA);

        ArrayList<String> directoryNames = new ArrayList<>();
        for (int i = 0; i < directories.size(); i++) {
            Log.e(TAG, "init: directory: " + directories.get(i));
            int index = directories.get(i).lastIndexOf("/");
            String string = directories.get(i).substring(index+1);
            directoryNames.add(string);

        }

        Log.e(TAG, "init: "+ directories.toString());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, directoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        directorySpinner.setAdapter(adapter);

        directorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemClick: selected: " + directories.get(position));

//                //setup our image grid for the directory chosen
                setupGridView(directories.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupGridView(String selectedDirectory){
        Log.e(TAG, "setupGridView: directory chosen: " + selectedDirectory);
        final ArrayList<String> imgURLs = FileSearch.getFilePaths(selectedDirectory);
        Log.e(TAG, "setupGridView: "+ imgURLs.toString());

        //set the grid column width

        gridView.setLayoutManager(new GridLayoutManager(this,3));

        //use the grid adapter to adapter the images to gridview
       GridviewAdapter gridviewAdapter = new GridviewAdapter(this,R.layout.layout,imgURLs, mAppend);
        gridView.setAdapter(gridviewAdapter);
        //set the first image to be displayed when the activity fragment view is inflate




    }



}

