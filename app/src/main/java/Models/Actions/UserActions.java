package Models.Actions;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

import Models.AppDatabase;
import Models.DAOS.UserDAO;
import Models.Entities.User;

public class UserActions {

    private String DB_NAME = "proiect-android";

    private AppDatabase userDB;

    public UserActions(Context context) {
        userDB = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

   public UserDAO userDAO = userDB.userDao();

    public List<User> gettAllUsers(){
         List<User> users = userDAO.getAll();
         return users;
    }

    public void insertUser(User user){
        userDAO.insert(user);
    }

}