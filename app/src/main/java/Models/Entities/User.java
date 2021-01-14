package Models.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Appointment.class,
        parentColumns = "appId",
        childColumns = "appointmentId",
        onDelete = ForeignKey.NO_ACTION))

public class User {
    @PrimaryKey
    public int userId;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "appId")
    public int appId;
}