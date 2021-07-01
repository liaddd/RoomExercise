package com.liadhorovitz.roomexercise;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

/**
 * Created by Liad Horovitz on 01,July,2021
 */
public class RoomExerciseApp extends Application {

    private static RoomExerciseApp instance;

    public static synchronized RoomExerciseApp getInstance() {
        if (instance == null){
            instance = new RoomExerciseApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
