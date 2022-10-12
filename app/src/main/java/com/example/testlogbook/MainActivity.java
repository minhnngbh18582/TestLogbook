package com.example.testlogbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;

    Button btnAdd;
    Button btnLeft, btnRight;
    EditText txtImg;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.addImage);
        btnLeft = findViewById(R.id.buttonBackImage);
        btnRight = findViewById(R.id.buttonNextImage);
        txtImg = findViewById(R.id.imageurl);

        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtImg.length() == 0){
                    txtImg.setError("Must input this field");
                }else {
                    dbHelper.insertImage(txtImg.getText().toString());
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        });

        List<Image> images = dbHelper.getImages();
        imgView = findViewById(R.id.imageView);

        DownloadImageTask task = new DownloadImageTask(mProgressDialog, this, imgView);
        task.execute(images.get(4).getImageURL());
        Log.w(images.get(4).getImageURL(), "Link");

    }
}