package org.prajwalan.app.prajwalan;

import android.app.Application;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Nilesh1 on 08-01-2016.
 */
public class application extends Application {
    private Typeface normalFont;
    private Typeface boldFont;

    public void setTypeface(TextView textView) {
        if(textView != null) {
            if(textView.getTypeface() != null && textView.getTypeface().isBold()) {
                textView.setTypeface(getBoldFont());
            } else {
                textView.setTypeface(getNormalFont());
            }
        }
    }

    private Typeface getNormalFont() {
        if(normalFont == null) {
            normalFont = Typeface.createFromAsset(getAssets(), "fonts/sf.ttf");
        }
        return this.normalFont;
    }

    private Typeface getBoldFont() {
        if(boldFont == null) {
            boldFont = Typeface.createFromAsset(getAssets(),"fonts/sf.ttf");
        }
        return this.boldFont;
    }

}
