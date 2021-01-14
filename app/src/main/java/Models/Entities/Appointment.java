package Models.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "appId",
        childColumns = "appointmentId",
        onDelete = ForeignKey.NO_ACTION))

public class Appointment {
    @PrimaryKey(autoGenerate = true)
    public int appointmentId;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "time")
    public String time;


}