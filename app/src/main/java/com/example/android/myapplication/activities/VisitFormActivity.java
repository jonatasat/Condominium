package com.example.android.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.myapplication.R;
import com.example.android.myapplication.dao.DBController;
import com.example.android.myapplication.pojo.Visit;

/**
 * Created by Jonatas on 02/07/2017.
 */

public class VisitFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_form);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void saveVisit(View v){
        DBController db = new DBController(getBaseContext());
        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        EditText editTextDocument = (EditText) findViewById(R.id.editTextDocument);
        EditText editTextCarPlate = (EditText) findViewById(R.id.editTextCarPlate);
        Visit visit = new Visit();
        visit.setName(editTextName.getText().toString());
        visit.setPhone(editTextPhone.getText().toString());
        visit.setDocument(editTextDocument.getText().toString());
        visit.setCarPlate(editTextCarPlate.getText().toString());
        String result = db.insertVisit(visit);
        Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(VisitFormActivity.this,VisitListActivity.class);
        startActivity(intent);
        finish();
    }

    public void cancelVisit(View view){
        Intent intent = new Intent(VisitFormActivity.this,VisitListActivity.class);
        startActivity(intent);
        finish();
    }
}
