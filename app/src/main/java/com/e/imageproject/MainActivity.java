package com.e.imageproject;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mprofileImage;
    private Button btchooseImage;
    private static final int PICK_IMAGE_REQUEST =1;
    private Uri mimageUri;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requesting permission to access external storage

        if (Build.VERSION.SDK_INT > 22) {

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},2);


        }

        //finding the elements by their xml id
        mprofileImage = (ImageView)findViewById(R.id.iv_profile_id);
        btchooseImage =(Button)findViewById(R.id.bt_chooseimage_id);

        btchooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             ChooseImage();

            }
        });



    }

    private void ChooseImage() {

        //creating an intent for choose image button
        Intent intentImage = new Intent();
        //setting the type of media
        intentImage.setType("image/*");

        intentImage.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentImage, PICK_IMAGE_REQUEST);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null ){

            mimageUri = data.getData();
            mprofileImage.setImageURI(mimageUri);

        }
    }
}
