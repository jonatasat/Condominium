package com.example.android.myapplication.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
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

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void saveVisitEdit(View v){
        DBController db = new DBController(getBaseContext());

        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        EditText editTextDocument = (EditText) findViewById(R.id.editTextDocument);
        EditText editTextCarPlate = (EditText) findViewById(R.id.editTextCarPlate);

        Visit visit = new Visit();
        visit.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DBCreate.ID)));
        visit.setName(editTextName.getText().toString());
        visit.setPhone(editTextPhone.getText().toString());
        visit.setDocument(editTextDocument.getText().toString());
        visit.setCarPlate(editTextCarPlate.getText().toString());

        String result = db.updateVisitById(visit);

        Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();

        Intent intent = new Intent(VisitEditActivity.this,VisitListActivity.class);
        startActivity(intent);
        finish();
    }

    public void deleteVisitEdit(View v){
        DBController db = new DBController(getBaseContext());

        Visit visit = new Visit();
        visit.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DBCreate.ID)));

        db.deleteVisit(visit);

        Intent intent = new Intent(VisitEditActivity.this,VisitListActivity.class);
        startActivity(intent);
        finish();
    }
}
