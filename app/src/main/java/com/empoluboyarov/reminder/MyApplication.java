package com.empoluboyarov.reminder;

import android.app.Application;

/**
 * Created by Evgeniy on 04.05.2016.
 */
public class MyApplication extends Application {

    private static boolean activityVisible;

    public static boolean isActivityVisible(){
        return activityVisible;
    }

    public static void activityResumed(){
        activityVisible = true;
    }

    public static void activityPaused(){
        activityVisible = false;
    }
}
