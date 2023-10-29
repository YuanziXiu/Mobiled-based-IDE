package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_2200px);
        Button myButton = findViewById(R.id.create_project_btn);

        //Jump to create_new_project interface
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateNewPrj.class);
                startActivity(intent);
            }
    });

        Button projctx = findViewById(R.id.projectX);
        projctx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, new_prj.class);
                //get the name of projectx
                String inputText = projctx.getText().toString().trim();
                //pass the name to new_prj
                intent.putExtra("PRJ_NAME_BTN", inputText);
                startActivity(intent);
            }
        });

        ImageButton delete = findViewById(R.id.btn_delete);
        LinearLayout prjxlist = findViewById(R.id.prjxlist);

        //delete projectx
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup) prjxlist.getParent()).removeView(prjxlist);
            }
        });
}}