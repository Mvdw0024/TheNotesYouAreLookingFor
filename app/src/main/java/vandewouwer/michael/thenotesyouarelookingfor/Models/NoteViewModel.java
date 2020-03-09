package vandewouwer.michael.thenotesyouarelookingfor.Models;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.preference.PreferenceManager;

import java.util.List;

import vandewouwer.michael.thenotesyouarelookingfor.R;

public class NoteViewModel extends AndroidViewModel {


    private LiveData<List<Note>> notes;
    private NoteDB dataBase;


    public NoteViewModel(@NonNull Application application) {
        super(application);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(application);
        String key = prefs.getString("prefsort", "None");
        dataBase = NoteDB.getSharedInstance(application);
        switch (key) {
            case "None":
                notes = dataBase.getNotesDAO().getNotes();
            case "Alphabetical":
                notes = dataBase.getNotesDAO().getNotesAlphabetical();
            case "Chronological":
                notes = dataBase.getNotesDAO().getNotesChronological();
                break;
        }


    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public NoteDB getDataBase() {
        return dataBase;
    }

    public void updateNotes(final Note note) {
        NoteDB.dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dataBase.getNotesDAO().updateNote(note);
            }
        });
    }


    public void addNote(final Note n) {
        NoteDB.dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dataBase.getNotesDAO().insertNote(n);
            }
        });
    }

    public void removeNote(final Note n) {
        NoteDB.dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dataBase.getNotesDAO().delNote(n);
            }
        });
    }


}
