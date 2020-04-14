package com.example.roomcomponents;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModal extends AndroidViewModel {

    private String  TAG = this.getClass().getSimpleName();
   private  NoteDao noteDao;
   private NoteRoomDatabase noteDb;
   private LiveData<List<Note>> mallnotes;

     public NoteViewModal(Application application) {
         super(application);
         noteDb = NoteRoomDatabase.getDatabase(application);
         noteDao = noteDb.noteDao();
         mallnotes = noteDao.getAllNotes();

     }


     public void insert(Note note){
         new InsertAsyncTask(noteDao).execute(note);
     }


     LiveData<List<Note>> getAllNotes(){
         return mallnotes;
     }

     public void delete(Note note){
         new DeleteAsyncTask(noteDao).execute(note);
     }

         @Override
         protected void  onCleared() {
         super.onCleared();
             Log.i(TAG,"ViewModal destroyed");
         }



         private class InsertAsyncTask extends AsyncTask<Note, Void, Void> {
         NoteDao mnoteDao;


             public InsertAsyncTask(NoteDao mnoteDao) {
                 this.mnoteDao = mnoteDao;
             }

             @Override
             protected Void doInBackground(Note... notes){
                 mnoteDao.insert(notes[0]);
                 return null;
             }
         }


    private class DeleteAsyncTask extends AsyncTask<Note ,Void,Void> {
         NoteDao mnoteDao;
          public DeleteAsyncTask(NoteDao noteDao){
          this.mnoteDao = noteDao;
          }


        @Override
        protected Void doInBackground(Note... notes) {
            mnoteDao.delete(notes[0]);
              return null;
        }
    }
}

