package org.prajwalan.app.prajwalan.utility;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

/**
 * Created by Moiz on 21/01/2016.
 */
public class RoundedBitmapDrawableUtility {

    public static RoundedBitmapDrawable createRoundedBitmapDrawableWithBorder(Bitmap bitmap,Resources mResources){
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int borderWidthHalf = 10; // In pixels

        int bitmapRadius = Math.min(bitmapWidth,bitmapHeight)/2;

        int bitmapSquareWidth = Math.min(bitmapWidth,bitmapHeight);


        int newBitmapSquareWidth = bitmapSquareWidth+borderWidthHalf;
        Bitmap roundedBitmap = Bitmap.createBitmap(newBitmapSquareWidth,newBitmapSquareWidth,Bitmap.Config.ARGB_8888);


        Canvas canvas = new Canvas(roundedBitmap);

        canvas.drawColor(Color.RED);

        int x = borderWidthHalf + bitmapSquareWidth - bitmapWidth;
        int y = borderWidthHalf + bitmapSquareWidth - bitmapHeight;


        canvas.drawBitmap(bitmap, x, y, null);

        // Initializing a new Paint instance to draw circular border
        Paint borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidthHalf*2);
        borderPaint.setColor(Color.WHITE);


        canvas.drawCircle(canvas.getWidth() / 2, canvas.getWidth() / 2, newBitmapSquareWidth / 2, borderPaint);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mResources, roundedBitmap);

        roundedBitmapDrawable.setCornerRadius(bitmapRadius);

        roundedBitmapDrawable.setAntiAlias(true);

        return roundedBitmapDrawable;
    }
}
