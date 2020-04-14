package com.example.roomcomponents;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Note {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getTexter() {
        return this.texter;
    }

    @NonNull
    private String texter;

    public Note(String id,String texter){
        this.id = id;
        this.texter = texter;

    }
}
