package com.example.khalilurrehman.roomcrud.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.khalilurrehman.roomcrud.Helper.Notes_tbl;

@Entity(tableName = Notes_tbl.NOTES_TBL)
public class NotesData {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Notes_tbl.ID)
    public long ID;
    @ColumnInfo(name = Notes_tbl.TITLE)
    public String TITLE;
    @ColumnInfo(name = Notes_tbl.NOTES)
    public String NOTES;
    @ColumnInfo(name = Notes_tbl.DATE)
    public String DATE;

    public NotesData(String TITLE, String NOTES, String DATE) {
        this.TITLE = TITLE;
        this.NOTES = NOTES;
        this.DATE = DATE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getNOTES() {
        return NOTES;
    }

    public String getDATE() {
        return DATE;
    }
}
