package Models.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(indices = {@Index(value = {"appId"},
        unique = true)})
public class User {

    @PrimaryKey(autoGenerate = true)
    public int userId;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "appId")
    public int appId;


    public int getId() {
        return userId;
    }

    public void setPassword(String password ){
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail(){
        return  this.email;
    }
}