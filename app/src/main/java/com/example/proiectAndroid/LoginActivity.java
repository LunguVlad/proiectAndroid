package com.example.proiectAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Models.Actions.UserActions;
import Models.AppDatabase;
import Models.DAOS.UserDAO;
import Models.Entities.User;

public class LoginActivity extends AppCompatActivity {

    UserActions userActions;

    private AppDatabase userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userActions = new UserActions(getApplicationContext());
        //System.out.println(userActions);

        // userDB = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "projectdatabase").build();


    }

    private static class InsertUserAsyncTask extends AsyncTask<Void, Void, Integer> {

        //Prevent leak

       private User user;
       private UserActions userActions;

        public InsertUserAsyncTask(User user, UserActions userActions ) {

            this.user = user;
            this.userActions = userActions;
        }

        @Override
        protected Integer doInBackground(Void... params) {
//            UserDAO userDAO = userDB.userDao();
//            userDAO.insert(this.user);
            try {
                userActions.insertUser(user);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer agentsCount) {
            System.out.println("User inserted!");
        }
    }


    private class GetAllUsersAsyncTask extends android.os.AsyncTask<Void, Void, List<User>> {

        private UserActions userActions;

        GetAllUsersAsyncTask(UserActions userActions) {
            this.userActions = userActions;
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return userActions.gettAllUsers();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            System.out.println();
        }
    }

    private class getUserByEmail extends android.os.AsyncTask<Void, Void, User>{

        private UserActions userActions;
        private String email;

        getUserByEmail(UserActions userActions,String email) {
            this.userActions = userActions;
            this.email = email;
        }

        @Override
        protected User doInBackground(Void... voids) {
            User user = userActions.getUserByEmail(email);
            System.out.println(user);
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            if(user!=null){
                System.out.println("POSTEXEC");
                Intent it = new Intent(getApplicationContext(),IntroActivity.class);
                startActivity(it);
            }else{
                System.out.println("1234");
            }
        }
    }



    public void handleLoginButton(View view){
        EditText editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText editTextPassowrd = (EditText) findViewById(R.id.editTextTextPassword);

        String email = editTextEmail.getText().toString();
        String password = editTextPassowrd.getText().toString();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAppId(57);

        new getUserByEmail(userActions,email).execute();
    }

    public void handleRegisterButton(View view){
        System.out.println("Clicked register");
       // String path = getDatabasePath("proiectandroid").getAbsolutePath();
        //System.out.println(path);
        EditText editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText editTextPassowrd = (EditText) findViewById(R.id.editTextTextPassword);

        String email = editTextEmail.getText().toString();
        String password = editTextPassowrd.getText().toString();


        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAppId(57);

        System.out.println(user.toString());


       new InsertUserAsyncTask(user,userActions).execute();



    }



}