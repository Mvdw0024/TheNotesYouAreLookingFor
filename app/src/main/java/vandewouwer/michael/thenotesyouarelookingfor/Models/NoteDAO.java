package vandewouwer.michael.thenotesyouarelookingfor.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void insertNote(Note c);

    @Update
    void updateNote(Note c);

    @Delete
    void delNote(Note c);

    @Query(value = "SELECT * FROM Note")
    LiveData<List<Note>> getNotes();

    @Query(value = "SELECT * FROM Note ORDER BY title ASC")
    LiveData<List<Note>> getNotesAlphabetical();

    @Query(value = "SELECT * FROM Note ORDER BY creationTime ASC")
    LiveData<List<Note>> getNotesChronological();


}
