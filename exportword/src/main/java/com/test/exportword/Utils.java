package com.test.exportword;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * User : user
 * Date : 2016-11-17
 * Time : 09:39
 */
public class Utils {

    private static final String TAG = "Utils";

    public static void copyTemplate(Context context, File templateFile) {
        Log.i(TAG, "copyTemplate");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        int len;
        try {
            bis = new BufferedInputStream(context.getResources().openRawResource(R.raw.test2));
            bos = new BufferedOutputStream(new FileOutputStream(templateFile));
            while ((len = bis.read()) != -1) {
                bos.write(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) bis.close();
                if (bis != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
