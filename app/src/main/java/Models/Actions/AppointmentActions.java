package Models.Actions;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import Models.AppDatabase;
import Models.DAOS.AppointmentDAO;
import Models.DAOS.UserDAO;
import Models.Entities.Appointment;
import Models.Entities.User;

public class AppointmentActions {

    private final String DB_NAME = "proiectdatabase";

    private AppDatabase appointmentDB;
    private AppointmentDAO appointmentDAO;

    public AppointmentActions(Context context) {
        appointmentDB = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
        appointmentDAO = appointmentDB.appointmentDao();
    }


    //public List<Appointment> gettAllAppointments(){
      //  List<Appointment> appointments = appointmentDAO.getAll();
        //return appointments;
    //}

    public void insertAppointment(Appointment appointment){
        System.out.println("insertUser");
        appointmentDAO.insert(appointment);
    }

    public List<Appointment> getAppointmentsByEmail(String email){
        List<Appointment> list =  appointmentDAO.getAllByEmail(email);
        return list;
    }


}

