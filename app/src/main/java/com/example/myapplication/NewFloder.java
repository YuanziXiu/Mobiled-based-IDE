package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewFloder extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_folder);
        Button confirm = findViewById(R.id.confirm1);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                EditText edit_floder_name = findViewById(R.id.edit_floder_name);
                String inputText = edit_floder_name.getText().toString().trim();
                // pass the folder name to new_prj if it is not empty
                if (!inputText.isEmpty()) {
                    returnIntent.putExtra("Folder_NAME", inputText);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                } else {
                    //notify user
                    Toast.makeText(NewFloder.this, "Please enter a folder name", Toast.LENGTH_SHORT).show();

                }
            }
        });

        // back to new prj
        Button back = findViewById(R.id.back1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }}