package com.example.proiectAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import Models.Actions.UserActions;
import Models.AppDatabase;
import Models.DAOS.UserDAO;
import Models.Entities.User;

public class LoginActivity extends AppCompatActivity {

    UserActions userActions;
    protected  User user;


    private AppDatabase userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userActions = new UserActions(getApplicationContext());
        //System.out.println(userActions);

        // userDB = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "projectdatabase").build();


    }

    @Override
    protected void onStart() {
        super.onStart();

        try{
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.shared_pref),Context.MODE_PRIVATE);
            Integer fontSize = sharedPreferences.getInt(getString(R.string.font_size),R.integer.font_size);
            String email = sharedPreferences.getString(getString(R.string.email_saved),"");
            EditText editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
            editTextEmail.setText(email);


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void handleSettingsButton(View view) {
        Intent it = new Intent(this,SettingsActivity.class);
        startActivity(it);
    }

    private  class InsertUserAsyncTask extends AsyncTask<Void, Void, User> {

        //Prevent leak

       private User user;
       private UserActions userActions;

        public InsertUserAsyncTask(User user, UserActions userActions ) {

            this.user = user;
            this.userActions = userActions;
        }

        @Override
        protected User doInBackground(Void... params) {
//            UserDAO userDAO = userDB.userDao();
//            userDAO.insert(this.user);
            try {
                userActions.insertUser(user);
                return userActions.getUserByEmail(user.getEmail());
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(User user){
            FirebaseDatabase rootNode = FirebaseDatabase.getInstance("https://proiectdam-2b128-default-rtdb.europe-west1.firebasedatabase.app/");
            DatabaseReference reference = rootNode.getReference("Users");

            reference.child(String.valueOf(user.getUserId())).child("email").setValue(user.getEmail());
            reference.child(String.valueOf(user.getUserId())).child("password").setValue(user.getPassword());
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
                LoginActivity:user = user;
                System.out.println("POSTEXEC");
                Intent it = new Intent(getApplicationContext(),IntroActivity.class);
                it.putExtra("logged_user", (Parcelable) user);
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


        RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton2);
        if(radioButton.isChecked()){
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("shared-preferences",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.email_saved),email);
            editor.apply();
        }






        User user = new User();
        user.setEmail(email);
        user.setPassword(password);


       // mDatabase.child("1").child("email").setValue(user.getEmail());



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









        System.out.println(user.toString());


       new InsertUserAsyncTask(user,userActions).execute();



    }



}