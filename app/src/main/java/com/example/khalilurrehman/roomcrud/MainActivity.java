package com.example.khalilurrehman.roomcrud;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.example.khalilurrehman.roomcrud.Adapter.getNotesAdapter;
import com.example.khalilurrehman.roomcrud.DBHelper.AppDatabase;
import com.example.khalilurrehman.roomcrud.Helper.Notes_tbl;
import com.example.khalilurrehman.roomcrud.Models.NotesData;
import com.example.khalilurrehman.roomcrud.NotesAlt.NotesAddUpdate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    getNotesAdapter notesAdapter;
    List<NotesData> notesDataList;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    AppDatabase db;
    private static final int add_notes = 100;
    private static final int update_notes = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesDataList = new ArrayList<>();
        notesAdapter = new getNotesAdapter(this,notesDataList);
        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.fab);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Notes_tbl.DATABASE_NAME).build();

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(notesAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, NotesAddUpdate.class).putExtra("note_id",0),add_notes);
            }
        });
/*
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this,NotesAddUpdate.class);
                intent.putExtra("note_title",notesDataList.get(position).getTITLE());
                intent.putExtra("note_text",notesDataList.get(position).getNOTES());
                intent.putExtra("note_id",Integer.parseInt(notesDataList.get(position).getID()));
                startActivityForResult(intent,update_notes);
            }

            @Override
            public void onLongClick(View view, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // Add the buttons
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Toast.makeText(MainActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                        db.deleteData(Integer.parseInt(notesDataList.get(position).getID()));
                        readDataFromDB();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setTitle("Are you sure to delete?");
                dialog.setMessage(notesDataList.get(position).getTITLE());
                dialog.show();
            }
        }));*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case add_notes:
                if (resultCode==RESULT_OK){
                    assert data != null;
                        //Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
                        //readDataFromDB();
                        Log.d("Intent", "onActivityResult: "+data.getStringExtra("note_title")+"-----"+data.getStringExtra("note_text"));
                        new SaveData(data.getStringExtra("note_title"),data.getStringExtra("note_text"),getDateTime());
                }
                break;
            case update_notes:
                if (resultCode == RESULT_OK){
                    assert data != null;
                   // db.updateData(data.getStringExtra("note_title"),data.getStringExtra("note_text"),data.getIntExtra("note_id",0));
                    readDataFromDB();
                }
                break;
        }
    }

    private void readDataFromDB() {
        new getData().execute();
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        readDataFromDB();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    private class getData extends AsyncTask<Void, Void, Void> {


        public getData(){
        }

        @Override
        protected Void doInBackground(Void... params) {
            notesDataList.clear();
            List<NotesData> notesDataListLocal = db.roomHelperInterface().getAll();
            Log.d("Total", "doInBackground: "+notesDataListLocal.size());
            for (NotesData item: notesDataListLocal) {
                Log.d("Data", "doInBackground: "+item.getTITLE()+"----"+item.getNOTES());
                notesDataList.add(item);
                notesAdapter.notifyDataSetChanged();
            }
            return null;
        }

    }

    private class SaveData extends AsyncTask<Void, Void, Void> {

        String title,text,date;
        public SaveData(String note_title, String note_text, String dateTime){
            this.title = note_title;
            this.text = note_text;
            this.date = dateTime;
        }

        @Override
        protected Void doInBackground(Void... params) {
            db.roomHelperInterface().insert(new NotesData(title,text,date));
            readDataFromDB();
            return null;
        }

    }
}
