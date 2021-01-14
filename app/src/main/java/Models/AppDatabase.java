package Models;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.android.gms.common.util.JsonUtils;

import Models.DAOS.AppointmentDAO;
import Models.DAOS.UserDAO;
import Models.Entities.Appointment;
import Models.Entities.User;

@Database(entities = {User.class, Appointment.class},version = 1)
abstract public class AppDatabase extends RoomDatabase {
    abstract public UserDAO userDao();
    public abstract AppointmentDAO appointmentDao();
}




