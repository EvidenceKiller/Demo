package com.test.exportword;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mText = (TextView) findViewById(R.id.hello);
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeThread();
            }
        });
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Runnable wordRunnable = new Runnable() {
        @Override
        public void run() {
//            WordTest wordTest = new WordTest(getApplicationContext());
//            wordTest.createWord();

//            ITextDemo demo = new ITextDemo(getApplicationContext());
//            try {
//                demo.exportDoc();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

//            PoiExportWord poiExportWord = new PoiExportWord(getApplicationContext());
//            try {
//                poiExportWord.html2Word();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            poiExportWord.createWord();

//            try {
//                poiExportWord.exportDoc();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            poiExportWord.exportSampleDocx();

            Docx4jDemo docx4jDemo = new Docx4jDemo();
            try {
                docx4jDemo.html2Wrod(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void executeThread() {
        mHandler.post(wordRunnable);
    }
}
