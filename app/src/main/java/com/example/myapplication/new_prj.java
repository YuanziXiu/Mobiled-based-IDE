package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.View;
import android.widget.PopupMenu;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class new_prj extends AppCompatActivity {

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                //update navigation list when user finish creating new folder, file and resource
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        // get name
                        String folderName = data.getStringExtra("Folder_NAME");
                        NavigationView navigationView = findViewById(R.id.navigation_view);
                        Menu menu = navigationView.getMenu();
                        MenuItem appMenuItem = menu.findItem(R.id.nav_app);
                        SubMenu appSubMenu = appMenuItem.getSubMenu();
                        //add name to menu
                        appSubMenu.add(Menu.NONE, Menu.NONE, 2, folderName);
                        navigationView.invalidate();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_project);
        ImageButton menu= findViewById(R.id.btn_menu);
        Button file= findViewById(R.id.btn_file);
        Intent intent = getIntent();
        String receivedText = intent.getStringExtra("PRJ_NAME_BTN");
        file.setText(receivedText);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(new_prj.this, v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.new_prj_menu, popup.getMenu());
                popup.show();
            }
        });


        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //back to mainactivity
        ImageButton home= findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new_prj.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // jump to create new folder
        Button btn_folder = findViewById(R.id.buttonNewFolder);
        btn_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new_prj.this, NewFloder.class);
                mStartForResult.launch(intent);
            }
        });

        // jump to create new file
        Button btn_file = findViewById(R.id.buttonNewFile);
        btn_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new_prj.this, NewFile.class);
                mStartForResult.launch(intent);
            }
        });

        // jump to upload resource
        Button upload_resource = findViewById(R.id.buttonUploadResource);
        upload_resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new_prj.this, UploadResource.class);
                mStartForResult.launch(intent);
            }
        });

        // set text when user click run
        Button run = findViewById(R.id.run);
        TextView termianl = findViewById(R.id.termianl_text);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                termianl.setText("Mobile based IDE pretends to start running...");
            }
        });

        // set text when user click debug
        Button debug = findViewById(R.id.debug);
        debug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                termianl.setText("Mobile based IDE pretends to start debugging...");
            }
        });

        //notify user the result of save
        ImageButton btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(new_prj.this, "Save Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        EditText editText = findViewById(R.id.editTextTextMultiLine);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // remove TextWatcher
                editText.removeTextChangedListener(this);

                String text = s.toString();
                Spannable spannable = new SpannableString(text);

                // set the color of "if"、"ifelse"、"else" to orange
                String[] orangeWords = {"if", "ifelse", "else"};
                for (String word : orangeWords) {
                    int index = text.indexOf(word);
                    while (index >= 0) {
                        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA500")), index, index + word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        index = text.indexOf(word, index + word.length());
                    }
                }

                // set the color of attributes' type to purple
                String[] purpleWords = {"int", "String", "float","boolean"};
                for (String word : purpleWords) {
                    int index = text.indexOf(word);
                    while (index >= 0) {
                        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#EE00FF")), index, index + word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        index = text.indexOf(word, index + word.length());
                    }
                }

                // set the color of "for" to blue
                int index = text.indexOf("for");
                while (index >= 0) {
                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00BFFF")), index, index + 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    index = text.indexOf("for", index + 3);
                }

                editText.setText(spannable);
                editText.setSelection(text.length());

                // add TextWatcher back
                editText.addTextChangedListener(this);
            }
        };

        editText.addTextChangedListener(textWatcher);
    }
    }

