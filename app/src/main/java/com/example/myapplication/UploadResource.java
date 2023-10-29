package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UploadResource extends AppCompatActivity {
    private ImageView[] imageViews;
    private int selectedPhotoIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_resource);

        imageViews = new ImageView[]{
                findViewById(R.id.imageView11),
                findViewById(R.id.imageView12),
                findViewById(R.id.imageView13),
                findViewById(R.id.imageView14)
        };

        //unselect all images at the beginning
        for (ImageView image : imageViews) {
            image.setSelected(false);
        }

        Button confirm = findViewById(R.id.confirm3);
        Button back = findViewById(R.id.back3);

        // back to new prj
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        for (int i = 0; i < imageViews.length; i++) {
            final int index = i;
            imageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedPhotoIndex = index;
                    // notify user which image is selected
                    Toast.makeText(UploadResource.this, "Photo " + (selectedPhotoIndex + 1) + " selected", Toast.LENGTH_SHORT).show();
                }
            });
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pass the image back to new prj
                if (selectedPhotoIndex != -1) {
                    Intent returnIntent = new Intent();
                    String inputText = "Photo " + (selectedPhotoIndex + 1) + ".jpg";
                    returnIntent.putExtra("Folder_NAME", inputText);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                } else {
                    Toast.makeText(UploadResource.this, "Please select a photo", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }}