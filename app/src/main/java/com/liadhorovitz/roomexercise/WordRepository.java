package com.liadhorovitz.roomexercise;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liad Horovitz on 01,July,2021
 */
public class WordRepository {

    private WordDao wordDao;
    private MutableLiveData<List<Word>> wordsLiveData = new MutableLiveData();

    public WordRepository() {
        WordDatabase wordDatabase = WordDatabase.getDatabase(RoomExerciseApp.getInstance());
        wordDao = wordDatabase.wordDao();
    }

    public void saveWord(Word word) {
        WordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.insert(word);
            }
        });
    }

    public LiveData<List<Word>> getAllWords() {
        WordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Word> allWords = wordDao.getAllWords();
                wordsLiveData.postValue(allWords);
            }
        });

        return wordsLiveData;
    }

    public LiveData<List<Word>> getSortedWords() {
        MutableLiveData<List<Word>> wordsLiveData = new MutableLiveData();

        WordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Word> allWords = wordDao.getAlphabetizedWords();
                wordsLiveData.postValue(allWords);
            }
        });

        return wordsLiveData;
    }

    public void deleteAllWords() {
        WordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.deleteAll();
                wordsLiveData.postValue(new ArrayList<>());
            }
        });
    }
}
