package com.test.exportword;

import android.content.Context;


/**
 * User : user
 * Date : 2016-11-16
 * Time : 18:41
 */
public class PoiExportWord {

    private static final String TAG = "PoiExportWord";

    private static final String TEMPLATE_FILE = "test.doc";

    private Context mContext;

    public PoiExportWord(Context context) {
        this.mContext = context;
    }

//    public void createWord() {
//        File externalFile = mContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
//        String externalFilePath = new StringBuffer(externalFile.getPath()).append("/").toString();
//        Log.i(TAG, "external file : " + externalFilePath);
//        File templateFile = new File(externalFile, TEMPLATE_FILE);
//        if (!templateFile.exists()) {
//            Utils.copyTemplate(mContext, templateFile);
//        }
//        if (!templateFile.exists()) {
//            Log.e(TAG, "tempate is not exists");
//            return;
//        }
//
//        Map<String, String> dataMap = new HashMap<String, String>();
//        getData(dataMap);
//
//        Log.i(TAG, "execute out put file");
//        File outFile = new File(externalFile, "Output.doc");
//
//        try {
//            FileInputStream in = new FileInputStream(templateFile);
//            HWPFDocument doc = new HWPFDocument(in);
//            // Fields fields = hdt.getFields();
//            // 读取word文本内容
//            Range range = doc.getRange();
//            Log.i(TAG, "Range : " + range.text());
//            range.insertBefore("this is file start");
//
//            // 替换文本内容
//            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
//                range.replaceText(entry.getKey(), entry.getValue());
//            }
//
//            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
//            FileOutputStream out = new FileOutputStream(outFile, true);
//            doc.write(ostream);
//            // 输出字节流
//            out.write(ostream.toByteArray());
//            out.close();
//            ostream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void exportDoc() {
//        File externalFile = mContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
//        String externalFilePath = new StringBuffer(externalFile.getPath()).append("/").toString();
//        Log.i(TAG, "external file : " + externalFilePath);
//        File templateFile = new File(externalFile, TEMPLATE_FILE);
//        if (!templateFile.exists()) {
//            Utils.copyTemplate(mContext, templateFile);
//        }
//        if (!templateFile.exists()) {
//            Log.e(TAG, "tempate is not exists");
//            return;
//        }
//
//        Map<String, String> dataMap = new HashMap<String, String>();
//        getData(dataMap);
//
//        Log.i(TAG, "execute out put file");
//        File outFile = new File(externalFile, "Output.doc");
//        if (outFile.exists()) {
//            outFile.delete();
//        }
//        try {
//            FileInputStream in = new FileInputStream(templateFile);
//            HWPFDocument doc = new HWPFDocument(in);
//
//            Range range = doc.getRange();
//            Log.i(TAG, "Range : " + range.text());
//
//            // 替换文本内容
//            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
//                range.replaceText(entry.getKey(), entry.getValue());
//            }
//
//            Log.i(TAG, "section number : " + range.numSections());
//            for (int i = 0; i < range.numSections(); i++) {
//                Section section = range.getSection(i);
//                Log.i(TAG, "paragraph number : " + section.numParagraphs());
//                for (int j = 0; j < section.numParagraphs(); j++) {
//                    Paragraph paragraph = section.getParagraph(j);
//                    String text = paragraph.text().trim();
//                    Log.i(TAG, "text : " + text);
//                    if (TextUtils.isEmpty(text)) {
////                        CharacterRun run = paragraph.insertAfter("heheheheheheh");
////                        run.setItalic(true);
////                        run.setBold(true);
//
//                        String imagePath = new StringBuffer(Environment.getExternalStorageDirectory().getPath()).append("/test1.png").toString();
//                        String no = Math.random() * 100 + "";
//                        String binData1 = "<w:pict><w:binData w:name=\"wordml://" + no + ".png\" xml:space=\"preserve\">";
//                        String binData2 = "</w:binData><v:shape id=\"" + no + "\" type=\"#_x0000_t75\"><v:imagedata src=\"wordml://" + no + ".png\"/></v:shape>";
//                        String result = binData1 + getImageString(imagePath) + binData2 + "</w:pict>";
//                        paragraph.insertAfter(result);
//
//                    }
//                }
//            }
//
//            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
//            FileOutputStream out = new FileOutputStream(outFile, true);
//            doc.write(ostream);
//            // 输出字节流
//            out.write(ostream.toByteArray());
//            out.close();
//            ostream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void exportSampleDocx() {
//        try {
//            //新建一个文档
//            XWPFDocument docx = new XWPFDocument();
//            //创建一个段落
//            XWPFParagraph para = docx.createParagraph();
//
//            //一个XWPFRun代表具有相同属性的一个区域。
//            XWPFRun run = para.createRun();
//            run.setBold(true); //加粗
//            run.setText("加粗的内容");
//            run = para.createRun();
//            run.setColor("FF0000");
//            run.setText("红色的字。");
//            File externalFile = mContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
//            String externalFilePath = new StringBuffer(externalFile.getPath()).append("/").toString();
//            OutputStream os = new FileOutputStream(externalFilePath + "sample.docx");
//            //把doc输出到输出流
//            docx.write(os);
//            os.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void getData(Map<String, String> dataMap) {
//        Log.i(TAG, "getData");
//        dataMap.put("title0", "Hello EveryOne");
//        dataMap.put("${title1}", "Time");
//        dataMap.put("${text1}", String.valueOf(System.currentTimeMillis()));
//        dataMap.put("${title2}", "People");
//        dataMap.put("${text2}", "Tom, Tony");
//    }
//
//    private String getImageString(String imagePath) {
//        InputStream is = null;
//        byte[] data = null;
//        try {
//            is = new FileInputStream(imagePath);
//            data = new byte[is.available()];
//            is.read(data);
//            is.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(data);
//    }

//    public void html2Word() throws Exception {
//        //创建 POIFSFileSystem 对象
//        POIFSFileSystem poifs = new POIFSFileSystem();
//        //获取DirectoryEntry
//        DirectoryEntry directory = poifs.getRoot();
//        //创建输出流
//        File externalDir = mContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
//        File externalFile = new File(externalDir, "demo.doc");
//        OutputStream out = new FileOutputStream(externalFile);
//        InputStream is = mContext.getResources().openRawResource(R.raw.test3);
//        try {
//            //创建文档,1.格式,2.HTML文件输入流
//            directory.createDocument("WordDocument", is);
//            //写入
//            poifs.writeFilesystem(out);
//            //释放资源
//            out.close();
//            System.out.println("success");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
