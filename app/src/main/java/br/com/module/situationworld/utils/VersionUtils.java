package br.com.module.situationworld.utils;

import android.os.Build;
import android.transition.Explode;
import android.view.Window;

public class VersionUtils {

    public static void checkVersioAndEnableTransition(Window window) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            window.setExitTransition(new Explode());
        }
    }
}
