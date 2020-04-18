package com.example.a421go.lib;

import android.content.Context;
import android.util.TypedValue;

/**
 * Représente un convertisseur de dimensions.
 */
public class DimensionConverter {
    /**
     * Convertit une dimension en pixels en DPI.
     * @param context le contexte
     * @param value la valeur en pixels à convertir
     * @return la valeur convertie en DPI
     */
    public static int convertPixelToDP(Context context, int value) {
        int dimensionInDP = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value,
                context.getResources().getDisplayMetrics()
        );
        return dimensionInDP;
    }
}
