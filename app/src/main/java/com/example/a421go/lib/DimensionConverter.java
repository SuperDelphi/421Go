package com.example.a421go.lib;

import android.content.Context;
import android.util.TypedValue;

public class DimensionConverter {
    public static int convertPixelToDP(Context context, int value) {
        int dimensionInDP = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value,
                context.getResources().getDisplayMetrics()
        );
        return dimensionInDP;
    }
}
