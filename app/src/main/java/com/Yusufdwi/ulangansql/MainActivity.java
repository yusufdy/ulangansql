package com.yusufdwi.ulangansql;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.yusufdwi.a03_yusufulangansql.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    AlertDialog.Builder builder;
    AlertDialog dialog;

    private Button saveButton;
    private EditText titleText, descriptionText;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        byPassActivity();


        floatingActionButton = findViewById(R.id.floatingActionButton);




        //Checking if note was added successfully.

        List<NoteModel> noteModelList = databaseHelper.getAllNotes();

        for(NoteModel noteModel : noteModelList)
        {
            Log.d("Main", "Data: "+noteModel.getDataNoteAdded());
        }



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                createDialogPopUp();

            }
        });

    }




    public void saveNotes(View view)
    {
        NoteModel noteModel = new NoteModel();

        String titleData = titleText.getText().toString().trim();
        String descriptionData = descriptionText.getText().toString().trim();

        noteModel.setTitle(titleData);
        noteModel.setDescription(descriptionData);

        databaseHelper.addItem(noteModel);
        Snackbar.make(view, "Note Saved!", Snackbar.LENGTH_LONG).show();

        //Removing the dialog after inActivity;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                dialog.dismiss();
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        }, 1200); // 1.2 seconds.

    }

    private void createDialogPopUp()
    {
        builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.popup, null);
//        saveButton = view.findViewById(R.id.saveButton);
        titleText = view.findViewById(R.id.titleText);
        descriptionText = view.findViewById(R.id.descriptionText);

        saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(titleText.getText().toString().isEmpty() && descriptionText.getText().toString().isEmpty())
                {
                    Snackbar.make(v, "enter title and description!", Snackbar.LENGTH_SHORT).show();
                }

                else
                {
                    saveNotes(v);

                }

            }
        });

        builder.setView(view);
        dialog = builder.create(); //creating the dialong
        dialog.show(); //showing the dialog


    }

    private void byPassActivity()
    {
        if(databaseHelper.getItemsCount() > 0)
        {
            startActivity(new Intent(MainActivity.this, ListActivity.class));
            finish();
        }


    }
}
