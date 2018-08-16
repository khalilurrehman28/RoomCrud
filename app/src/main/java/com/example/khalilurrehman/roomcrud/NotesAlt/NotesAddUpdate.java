package com.example.khalilurrehman.roomcrud.NotesAlt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.khalilurrehman.roomcrud.R;


public class NotesAddUpdate extends AppCompatActivity {

    EditText notes_text;
    EditText notesTitle;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_add_update);
        notes_text = findViewById(R.id.notes_text);
        notesTitle = findViewById(R.id.notes_title);
        id = getIntent().getIntExtra("note_id",0);
        //Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
        notes_text.setText(getIntent().getStringExtra("note_text"));
        notesTitle.setText(getIntent().getStringExtra("note_title"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.save:
                Intent i = new Intent();
                i.putExtra("note_id",id);
                i.putExtra("note_text",notes_text.getText().toString());
                i.putExtra("note_title",notesTitle.getText().toString());
                setResult(Activity.RESULT_OK,i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
