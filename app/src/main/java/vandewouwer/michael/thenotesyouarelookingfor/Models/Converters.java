package vandewouwer.michael.thenotesyouarelookingfor.Models;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;

class Converters {


    @TypeConverter
    public static LocalDateTime creationTime(String dateString) {
        if (dateString == null) return null;
        else return LocalDateTime.parse(dateString);
    }

    @TypeConverter
    public static String dateToString(LocalDateTime ldt) {
        return ldt == null ? null : ldt.toString();
    }
}
