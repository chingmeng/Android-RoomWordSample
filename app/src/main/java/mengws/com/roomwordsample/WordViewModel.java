package mengws.com.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/*
*
* What is a ViewModel?

The ViewModel's role is to provide data to the UI and survive configuration
changes. A ViewModel acts as a communication center between the Repository
and the UI. You can also use a ViewModel to share data between fragments.
The ViewModel is part of the lifecycle library.

Why use a ViewModel?

A ViewModel holds your app's UI data in a lifecycle-conscious way that \
survives configuration changes.

Separating your app's UI data from your Activity and Fragment classes lets
you better follow the single responsibility principle: Your activities and
fragments are responsible for drawing data to the screen, while your
ViewModel can take care of holding and processing all the data needed for the UI.

In the ViewModel, use LiveData for changeable data that the UI will use or
display. Using LiveData has several benefits:

You can put an observer on the data (instead of polling for changes)
and only update the UI when the data actually changes.

The Repository and the UI are completely separated by the ViewModel.
There are no database calls from the ViewModel, making the code more testable.


Warning: Never pass context into ViewModel instances.

Do not store Activity, Fragment, or View instances or their Context in the
ViewModel.

For example, an Activity can be destroyed and created many times during the
lifecycle of a ViewModel as the device is rotated.
If you store a reference to the Activity in the ViewModel,
you end up with references that point to the destroyed Activity.

This is a memory leak.

If you need the application context, use AndroidViewModel,
as shown in this codelab.

Important: ViewModel is not a replacement for the onSaveInstanceState() method,
because the ViewModel does not survive a process shutdown.

* */


public class WordViewModel extends AndroidViewModel {

    private WordRepositoy mRepository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepositoy(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }

}
