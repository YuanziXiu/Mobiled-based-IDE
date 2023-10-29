package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNewPrj extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_project);
        Button confirm = findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewPrj.this, new_prj.class);
                EditText edit_project_name = findViewById(R.id.edit_project_name);
                //get the input of project name
                String inputText = edit_project_name.getText().toString().trim();
                RadioGroup radioGroup = findViewById(R.id.language_group);
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                //check non-empty
                if (selectedRadioButtonId != -1 && !inputText.isEmpty()) {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    //get language
                    String selectedText = selectedRadioButton.getText().toString();
                    //pass the project name and language to new_prj
                    intent.putExtra("PRJ_NAME_BTN", inputText+'.'+ selectedText);
                    startActivity(intent);
                } else {
                    //notify user
                    Toast.makeText(CreateNewPrj.this, "Please enter a project name and select a language", Toast.LENGTH_SHORT).show();

                }
            }
        });

        Button back = findViewById(R.id.back);
        // back to mainactivity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewPrj.this, MainActivity.class);
                startActivity(intent);
            }
        });

}}