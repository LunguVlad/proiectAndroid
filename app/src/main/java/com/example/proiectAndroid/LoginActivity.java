package com.example.proiectAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import Models.Actions.UserActions;
import Models.AppDatabase;
import Models.DAOS.UserDAO;
import Models.Entities.User;

public class LoginActivity extends AppCompatActivity {

   // UserActions userActions = new UserActions(this.getApplicationContext());

    private AppDatabase userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         userDB = Room.databaseBuilder(this, AppDatabase.class, "project-database").build();

    }

    private static class InsertUserAsyncTask extends AsyncTask<Void, Void, Integer> {

        //Prevent leak

       private User user;
       private AppDatabase userDB;

        public InsertUserAsyncTask(User user, AppDatabase userDB ) {

            this.user = user;
            this.userDB = userDB;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            UserDAO userDAO = userDB.userDao();
            userDAO.insert(this.user);
            return 0;
        }

        @Override
        protected void onPostExecute(Integer agentsCount) {

        }
    }



    public void handleLoginButton(){


    }

    public void handleRegisterButton(View view){
        EditText editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText editTextPassowrd = (EditText) findViewById(R.id.editTextTextPassword);

        String email = editTextEmail.getText().toString();
        String password = editTextPassowrd.getText().toString();


        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
       new InsertUserAsyncTask(user,userDB);
    }



}