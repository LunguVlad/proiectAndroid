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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
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
            Integer fontSize = sharedPreferences.getInt(getString(R.string.font_size),14);
            String email = sharedPreferences.getString(getString(R.string.email_saved),"");




            //Get views
            EditText editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
            EditText editTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
            Button btnRegister = (Button) findViewById(R.id.btnRegister);
            Button btnLogin = (Button) findViewById(R.id.btnLogin);
            Button btnSettings = (Button) findViewById(R.id.button);
            TextView textViewMessage = (TextView) findViewById(R.id.textView2);
            RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton2);







            //Set prefrences
            editTextEmail.setText(email);
            editTextEmail.setTextSize(fontSize);
            editTextPassword.setTextSize(fontSize);
            btnRegister.setTextSize(fontSize);
            btnLogin.setTextSize(fontSize);
            btnSettings.setTextSize(fontSize);
            textViewMessage.setTextSize(fontSize);
            radioButton.setTextSize(fontSize);



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
                if(userActions.getUserByEmail(user.getEmail()) == null){
                    userActions.insertUser(user);
                    return userActions.getUserByEmail(user.getEmail());
                }else{
                   return null;
                }
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(User user){
            if(user!=null) {
                FirebaseDatabase rootNode = FirebaseDatabase.getInstance("https://proiectdam-2b128-default-rtdb.europe-west1.firebasedatabase.app/");
                DatabaseReference reference = rootNode.getReference("Users");

                reference.child(String.valueOf(user.getUserId())).child("email").setValue(user.getEmail());
                reference.child(String.valueOf(user.getUserId())).child("password").setValue(user.getPassword());

                Toast.makeText(LoginActivity.this, "USER REGISTERED", Toast.LENGTH_SHORT).show();
                EditText editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
                EditText editTextPassowrd = (EditText) findViewById(R.id.editTextTextPassword);
                editTextEmail.setText("");
                editTextPassowrd.setText("");
            }
            else{
                Toast.makeText(LoginActivity.this, "USER ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                EditText editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
                EditText editTextPassowrd = (EditText) findViewById(R.id.editTextTextPassword);
                editTextEmail.setText("");
                editTextPassowrd.setText("");
            }
        }
    }



    private class getUserByEmail extends android.os.AsyncTask<Void, Void, User>{

        private UserActions userActions;
        private String email;
        private String password;

        getUserByEmail(UserActions userActions,String email,String password) {
            this.userActions = userActions;
            this.email = email;
            this.password = password;
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
                if(password.equals(user.getPassword())){
                    LoginActivity:user = user;
                    System.out.println("POSTEXEC");
                    Intent it = new Intent(getApplicationContext(),IntroActivity.class);
                    it.putExtra("logged_user", (Parcelable) user);
                    startActivity(it);
                }else{
                    Toast.makeText(LoginActivity.this, "WRONG EMAIL OR PASSWORD", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(LoginActivity.this, "WRONG EMAIL OR PASSWORD", Toast.LENGTH_SHORT).show();
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
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.shared_pref),Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.email_saved),email);
            editor.apply();
        }






        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://proiectdam-247d7-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = database.getReference("user");



        reference.setValue("1234");


       // mDatabase.child("1").child("email").setValue(user.getEmail());



        new getUserByEmail(userActions,email,password).execute();
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