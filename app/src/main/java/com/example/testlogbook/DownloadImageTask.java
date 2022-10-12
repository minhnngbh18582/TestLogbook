package com.example.testlogbook;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask {

    ProgressDialog mProgressDialog;
    Context context;
    ImageView imageView;

    public DownloadImageTask(ProgressDialog mProgressDialog, Context context, ImageView imageView) {
        this.mProgressDialog = mProgressDialog;
        this.context = context;
        this.imageView = imageView;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        String imageURL = (String) objects[0];
        Bitmap bitmap = null;
        try{
            InputStream input = new java.net.URL(imageURL).openStream();
            bitmap = BitmapFactory.decodeStream(input);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle("Download Image");
        mProgressDialog.setMessage("Load...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(Object o){
        imageView.setImageBitmap((Bitmap) o);
        mProgressDialog.dismiss();
    }
}
