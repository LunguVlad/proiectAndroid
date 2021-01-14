package Models;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import Models.DAOS.AppointmentDAO;
import Models.DAOS.UserDAO;
import Models.Entities.Appointment;
import Models.Entities.User;

@Database(entities = {User.class, Appointment.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
    public abstract AppointmentDAO appointmentDAO();
}


