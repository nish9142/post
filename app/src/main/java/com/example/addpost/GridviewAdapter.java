package com.example.addpost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

public class GridviewAdapter extends RecyclerView.Adapter<ViewHolderItem> {

    private final Context mContext;
    private final ArrayList<String> imageuris;
    private final  String mAppend;
    private final  String TAG = "gridview";


    // 1


    public GridviewAdapter(Context mContext,int layoutResource, ArrayList<String> imageuris, String mAppend) {
        this.mContext = mContext;
        this.imageuris = imageuris;
        this.mAppend = mAppend;
    }






    @NonNull
    @Override
    public ViewHolderItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) ((Activity) mContext).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.layout, viewGroup, false);

        // well set up the ViewHolder
       ViewHolderItem viewHolder = new ViewHolderItem(convertView);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderItem viewHolderItem, int postion) {

        String imgURL = imageuris.get(postion);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));

        imageLoader.displayImage(mAppend + imgURL, viewHolderItem.imageView, new ImageLoadingListener() {
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

        viewHolderItem.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = viewHolderItem.getPosition();
                post.imageCursor= mAppend + imageuris.get(p);
                Intent i = new Intent(mContext,post.class);
                mContext.startActivity(i);

                ((Activity)mContext).finish();




            }
        });





    }

    @Override
    public int getItemCount() {
        return imageuris.size();
    }
}


class ViewHolderItem extends RecyclerView.ViewHolder {
    SquareImageView imageView;

    public ViewHolderItem(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.gimage);
    }
}