package com.clutchapp.a421go.lib;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import com.clutchapp.a421go.metier.GlobalApplication;

/**
 * Classe permettant d'accéder plus simplement à la fonction de vibration du terminal de l'utilisateur.
 */
public class VibratorHelper {
    /**
     * Fait vibrer le terminal de l'utilisateur pendant une durée définie.
     * @param duration la durée (en millisecondes)
     */
    public static void vibrate(int duration) {
        Vibrator v = (Vibrator) GlobalApplication.getAppContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(duration);
        }
    }
}
