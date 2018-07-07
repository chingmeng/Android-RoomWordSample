package mengws.com.roomwordsample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/*
* In this codelab you don't update the entities and the version numbers.
* When you modify the database schema, you'll need to update the version number
* and define how to handle migrations.
* For a sample, destroying and re-creating the database is a fine migration
* strategy. For a real app, you must implement a migration strategy.
* See Understanding migrations with Room.
* */

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDataBase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static WordRoomDataBase INSTANCE;

    public static WordRoomDataBase getDataBase(final Context context) {
        if(INSTANCE == null) {

            synchronized (WordRoomDataBase.class) {
                if (INSTANCE == null) {
                    //Create database here
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WordRoomDataBase.class,
                            "word_databases")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;

    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDataBase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            return null;
        }
    }

}
