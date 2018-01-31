package org.prajwalan.app.prajwalan.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Moiz on 15/01/2016.
 */
public class ImageUtility {

    @Nullable public static  Bitmap downloadImage(String url) throws Exception {
        Bitmap bitmap = null;
        InputStream stream = null;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 1;

        URL u=new URL(url);
        HttpURLConnection httpConn =  ( HttpURLConnection )  u.openConnection ();
        httpConn.setRequestMethod ("GET");
        httpConn.connect() ;

        if (httpConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new Exception("Status Code :" + httpConn.getResponseCode());
        }
        else {
            stream = httpConn.getInputStream();
            bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
            stream.close();
        }

        return bitmap;
    }

    public static Bitmap getDownloadedImage(Context context, String filename) {
        File dir = new File(context.getFilesDir() + "/Images");
        String s = filename + ".png";
        File file = new File(dir, s);
        Bitmap b = BitmapFactory.decodeFile(file.getAbsolutePath());
        return b;
    }

    public static void saveImageToMemory(Context context, String filename, Bitmap bitmap) throws IOException {
        File dir = new File(context.getFilesDir() + "/Images");
        dir.mkdirs();
        String s = filename + ".png";
        File file = new File(dir, s);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        FileOutputStream f = new FileOutputStream(file);
        if (f != null) {
            f.write(baos.toByteArray());
            f.flush();
            f.close();
        }
    }

    public static boolean exists(Context context, String filename)  {
        File dir = new File(context.getFilesDir() + "/Images");
        String s = filename + ".png";
        File file = new File(dir, s);
        if (file.getAbsoluteFile().exists())
            return true;
        return false;
    }
}

