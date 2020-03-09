package vandewouwer.michael.thenotesyouarelookingfor.Models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, entities = {Note.class}, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class NoteDB extends RoomDatabase {

    private static NoteDB sharedInstance;

    public static NoteDB getSharedInstance(Context c) {
        if (sharedInstance == null)
            createDB(c);
        return sharedInstance;
    }

    private static void createDB(Context c) {
        sharedInstance = Room.databaseBuilder(c, NoteDB.class, "note_db").build();
    }

    public abstract NoteDAO getNotesDAO();

    public static ExecutorService dbExecutor = Executors.newFixedThreadPool(4);
}
