package com.example.addpost;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.addpost.Permissions.Permissions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(checkPermissionArray(Permissions.permissions))
        {


        }
        else {
            verifyPermission(Permissions.permissions);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,post.class);
                MainActivity.this.startActivity(i);



            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkPermissionArray(String[] permission) {

        for (int i=0;i<permission.length;i++){
            String singlep = permission[i];
            if(!checksinglep(singlep)){
                return false;
            }

        }

        return true;
    }

    private boolean checksinglep(String singlep) {

        int PermissionGranted = ActivityCompat.checkSelfPermission(MainActivity.this,singlep);
        if(PermissionGranted!= PackageManager.PERMISSION_GRANTED){
            return false;
        }
        else {
            return true;
        }

    }

    private void verifyPermission(String[] permissions) {
        Log.e("verify ", "verifyPermission: ");

        ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
    }
}
