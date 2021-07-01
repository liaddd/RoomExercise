package com.liadhorovitz.roomexercise;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Liad Horovitz on 01,July,2021
 */

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

}
