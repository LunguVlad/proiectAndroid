package Models.DAOS;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Models.Entities.Appointment;
import Models.Entities.User;


@Dao
    public interface AppointmentDAO {
        @Query("SELECT * FROM appointment")
        List<Appointment> getAll();

        @Query("SELECT * FROM appointment WHERE userEmail IN (:userEmail)")
        List<Appointment> getAllByEmail(String userEmail);

        @Insert
        void insertAll(Appointment... appointments);

        @Insert(entity = Appointment.class)
        void insert(Appointment appointment);

        @Delete
        void delete(Appointment appointment);
    }
