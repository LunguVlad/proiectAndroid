package Models.DAOS;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import Models.Entities.User;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE userId IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE email IN (:email)")
    User getUserByEmail(String email);

//    @Insert
//    void insertAll(User... users);

    @Insert(entity = User.class)
    void insert(User user);

//    @Delete
//    void delete(User user);
}