package mengws.com.roomwordsample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/*
* Annotate the method with @Insert.
* You don't have to provide any SQL!
* (There are also @Delete and @Update annotations for deleting
* and updating a row, but you are not using them in this app.)
* */

/*
* In this codelab, you do not need a conflict strategy,
* because the word is your primary key,
* and the default SQL behavior is ABORT,
* so that you cannot insert two items with the same primary key
* into the database.

If the table has more than one column, you can use

@Insert(onConflict = OnConflictStrategy.REPLACE)

to replace a row.
* */

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();


    /*
    * If you use LiveData independently from Room,
    * you have to manage updating the data.
    *
    * LiveData has no publicly available methods to update the stored data.
    *
    * If you, the developer, want to update the stored data,
    * you must use MutableLiveData instead of LiveData.
    *
    * The MutableLiveData class has two public methods that allow you to
    * set the value of a LiveData object, setValue(T) and postValue(T).
    *
    * Usually, MutableLiveData is used in the ViewModel, and then the
    * ViewModel only exposes immutable LiveData objects to the observers.
    * */

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

}
