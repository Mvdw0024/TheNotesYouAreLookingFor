package vandewouwer.michael.thenotesyouarelookingfor.Models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


@Entity
public class Note implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title, inhoud;
    private LocalDateTime creationTime;

    public Note() {
    }

    @Ignore
    public Note(String titel, String inhoud) {
        this.title = titel;
        this.inhoud = inhoud;
        this.creationTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInhoud() {
        return inhoud;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }


    @Override
    public String toString() {
        return "created: " + creationTime +
                '}';
    }
}
