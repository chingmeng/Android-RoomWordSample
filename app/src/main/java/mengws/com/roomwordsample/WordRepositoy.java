package mengws.com.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;


/*
*
* What is a Repository?
* A Repository is a class that abstracts access to multiple data sources.
* The Repository is not part of the Architecture Components libraries,
* but is a suggested best practice for code separation and architecture.
*
*  [DAO] <---- [Repository] ---> [Network]
*
* A Repository class handles data operations.
* It provides a clean API to the rest of the app for app data.
*
* Why use a Repository?
* A Repository manages query threads and allows you to use multiple backends.
* In the most common example, the Repository implements the logic for deciding
* whether to fetch data from a network or use results cached in a local database.
*
*
* */

public class WordRepositoy {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepositoy(Application application) {
        WordRoomDataBase db = WordRoomDataBase.getDataBase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


}
