package com.example.khalilurrehman.roomcrud.DBHelper;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.khalilurrehman.roomcrud.Models.NotesData;

@Database(entities = {NotesData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoomHelperInterface roomHelperInterface();
}
