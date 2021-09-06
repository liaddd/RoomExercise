package com.liadhorovitz.roomexercise;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by Liad Horovitz on 01,July,2021
 */
public class WordViewModel extends ViewModel {

    private WordRepository wordRepository;

    public MutableLiveData<Boolean> sortAlphabetized = new MutableLiveData<>();

    public WordViewModel() {
        wordRepository = new WordRepository();
    }

    void saveWord(Word word){
        wordRepository.saveWord(word);
    }

    public LiveData<List<Word>> getAllWords(){
        return wordRepository.getAllWords();
    }

    public LiveData<List<Word>> getSortedWords(){
        return wordRepository.getSortedWords();
    }

    public void deleteAllWords(){
        wordRepository.deleteAllWords();
    }
}
