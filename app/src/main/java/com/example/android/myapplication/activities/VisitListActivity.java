package com.example.android.myapplication.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.android.myapplication.classes.DBCreate;
import com.example.android.myapplication.R;
import com.example.android.myapplication.dao.DBController;

/**
 * Created by Jonatas on 02/07/2017.
 */

public class VisitListActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    private ListView list;
    private DBController db;
    private Cursor cursor;
    ArrayAdapter<String> fileList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_list);
        listVisits();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        list = (ListView)findViewById(R.id.listViewVisitList);
        list.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String codigo;
        cursor.moveToPosition(position);
        codigo = cursor.getString(cursor.getColumnIndexOrThrow(DBCreate.ID));
        openVisitEdit(codigo);

        return true;
    }


    public void listVisits(){
        DBController db = new DBController(getBaseContext());
        Cursor cursor = db.listVisits();
        String[] from = new String[]{DBCreate.NAME};
        int[] to = new int[] {android.R.id.text1};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), android.R.layout.simple_list_item_1, cursor, from, to);
        list = (ListView)findViewById(R.id.listViewVisitList);
        list.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void openVisitEdit(String codigo){
        Intent intent = new Intent(VisitListActivity.this,VisitEditActivity.class);
        intent.putExtra("codigo",codigo);
        startActivity(intent);
        finish();
    }

    public void openVisitFormActivity(View v){
        Intent intent = new Intent(VisitListActivity.this,VisitFormActivity.class);
        startActivity(intent);
        finish();
    }
}
