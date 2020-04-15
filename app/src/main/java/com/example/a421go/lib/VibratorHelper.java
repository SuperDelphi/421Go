package com.example.a421go.lib;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import com.example.a421go.metier.GlobalApplication;

public class VibratorHelper {
    public static void vibrate(int duration) {
        Vibrator v = (Vibrator) GlobalApplication.getAppContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(duration);
        }
    }
}
