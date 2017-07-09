package com.example.android.myapplication.activities;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.android.myapplication.R;
import com.example.android.myapplication.classes.DBCreate;
import com.example.android.myapplication.dao.DBController;
import com.example.android.myapplication.pojo.Visit;

/**
 * Created by Jonatas on 09/07/2017.
 */

public class VisitEditActivity extends AppCompatActivity{
    DBController bd;
    String codigo;
    EditText name;
    EditText phone;
    EditText doc;
    EditText carplate;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_edit);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        codigo = this.getIntent().getStringExtra("codigo");
        bd = new DBController(getBaseContext());

        name = (EditText)findViewById(R.id.editTextNameEdit);
        phone = (EditText)findViewById(R.id.editTextPhoneEdit);
        doc = (EditText)findViewById(R.id.editTextDocumentEdit);
        carplate = (EditText)findViewById(R.id.editTextCarPlateEdit);

        int id = Integer.parseInt(codigo);

        cursor = bd.listVisitById(id);

        name.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBCreate.NAME)));
        phone.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBCreate.PHONE)));
        doc.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBCreate.DOCUMENT)));
        carplate.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBCreate.CARPLATE)));

        ImageButton btnEdit = (ImageButton)findViewById(R.id.btnSaveEdit);
        btnEdit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String result = bd.updateVisitById(codigo, name.getText().toString(), phone.getText().toString(),
                        doc.getText().toString(), carplate.getText().toString());

                Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(VisitEditActivity.this,VisitListActivity.class);
                startActivity(intent);
                finish();

            }
        });

        ImageButton btnDelete = (ImageButton)findViewById(R.id.btnDeleteEdit);
        btnDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String result = bd.deleteVisit(codigo);

                Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(VisitEditActivity.this,VisitListActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

}
