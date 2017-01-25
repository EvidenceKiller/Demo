package com.test.exportword;

import android.content.Context;
import android.util.Log;

import java.util.Map;

/**
 * User : user
 * Date : 2016-11-15
 * Time : 17:11
 */
public class WordTest {

    private static final String TAG = "WordTest";

    private static final String TEMPLATE_NAME = "test2.ftl";

    private Context mContext;

//    private Configuration mConfiguration = null;
//
//    public WordTest(Context context) {
//        this.mContext = context;
//        mConfiguration = new Configuration(Configuration.VERSION_2_3_25);
//        mConfiguration.setDefaultEncoding("UTF-8");
//    }
//
//    public void createWord() {
//        Map<String, Object> dataMap = new HashMap<String, Object>();
//        getData(dataMap);
//
//        File externalFile = mContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
//        String externalFilePath = new StringBuffer(externalFile.getPath()).append("/").toString();
//        Log.i(TAG, "external file : " + externalFilePath);
//        File templateFile = new File(externalFile, TEMPLATE_NAME);
//        if (!templateFile.exists()) {
//            Utils.copyTemplate(mContext, templateFile);
//        }
//
//        if (!templateFile.exists()) {
//            Log.e(TAG, "template is not exists");
//            return;
//        }
//
//        mConfiguration.setClassForTemplateLoading(this.getClass(), externalFilePath);
//        DefaultObjectWrapperBuilder owb = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
//        owb.setForceLegacyNonListCollections(false);
//        owb.setDefaultDateType(TemplateDateModel.DATETIME);
//        mConfiguration.setObjectWrapper(owb.build());
//        mConfiguration.setLogTemplateExceptions(true);
//        mConfiguration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
//        try {
//            mConfiguration.setDirectoryForTemplateLoading(externalFile);
//        } catch (IOException e) {
//            Log.e(TAG, "template directory is null");
//            e.printStackTrace();
//        }
//
//        Template template = null;
//        try {
//            template = mConfiguration.getTemplate(TEMPLATE_NAME);
//        } catch (Exception e) {
//            Log.e(TAG, "getTemplate has exception");
//            e.printStackTrace();
//            return;
//        }
//
//        File outFile = new File(externalFile, "freemarkerdemo.doc");
//        if (outFile.exists()) {
//            outFile.delete();
//        }
//
//        Writer writer = null;
//        try {
//            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
//        } catch (FileNotFoundException fnfe) {
//            fnfe.printStackTrace();
//        }
//
//        try {
//            template.process(dataMap, writer);
//        } catch (TemplateException te) {
//            te.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }

    private void getData(Map<String, Object> dataMap) {
        Log.i(TAG, "getData");
        dataMap.put("title0", "Hello EveryOne");
        dataMap.put("title1", "Time");
        dataMap.put("text1", String.valueOf(System.currentTimeMillis()));
        dataMap.put("title2", "People");
        dataMap.put("text2", "Tom, Tony");
    }

}
