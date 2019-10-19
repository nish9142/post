package com.example.addpost;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class post extends AppCompatActivity {
    private static final String TAG = "post";
    public static String imageCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


        ImageView mgallery = findViewById(R.id.gallery);
        final RelativeLayout relativeLayout= findViewById(R.id.displayImageView);
        relativeLayout.setVisibility(View.INVISIBLE);
        mgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(post.this,gallery.class);
                post.this.startActivity(i);
                finish();
            }
        });

        if(imageCursor!=null){
            relativeLayout.setVisibility(View.VISIBLE);
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(post.this));
            SquareImageView displayimage = findViewById(R.id.displayImage);

            imageLoader.displayImage(imageCursor, displayimage, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    Log.e(TAG, "onLoadingComplete: " + imageUri);

                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });

        }

        Button Closebtn = findViewById(R.id.displayImageCloseBtn);
       Closebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               relativeLayout.setVisibility(View.INVISIBLE);
           }
       });

        final ProgressBar progressBar =findViewById(R.id.editTextPrgBar);
        progressBar.setMax(120);

        final EditText editText=findViewById(R.id.postcontent);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                progressBar.setProgress(editText.getText().toString().length());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }




}
