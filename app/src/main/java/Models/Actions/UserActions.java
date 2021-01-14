package Models.Actions;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

import Models.AppDatabase;
import Models.DAOS.UserDAO;
import Models.Entities.User;

public class UserActions {

    private final String DB_NAME = "proiectdatabase";

    private AppDatabase userDB;
    private UserDAO userDAO;

    public UserActions(Context context) {
        userDB = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
        userDAO = userDB.userDao();
    }


    public List<User> gettAllUsers(){
         List<User> users = userDAO.getAll();
         return users;
    }

    public void insertUser(User user){
        System.out.println("insertUser");
        userDAO.insert(user);
    }

    public User getUserByEmail(String email){
        return userDAO.getUserByEmail(email);
    }

}