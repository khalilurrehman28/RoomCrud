package com.example.khalilurrehman.roomcrud.DBHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.khalilurrehman.roomcrud.Helper.Notes_tbl;
import com.example.khalilurrehman.roomcrud.Models.NotesData;

import java.util.List;

@Dao
public interface RoomHelperInterface {
    //insert single row into databse
    @Insert
    void insert (NotesData notes);

    //get all rows from the database
    @Query("SELECT * FROM  "+ Notes_tbl.NOTES_TBL)
    List<NotesData> getAll();

}
