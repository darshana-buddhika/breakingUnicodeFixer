import org.apache.poi.xwpf.usermodel.XWPFDocument;
import word.WDXToUnicode;

import java.io.*;

/**
 * Created by gayan@ciperlabs.com on 4/21/18.
 */
public class test {
    public static void main(String[] args) {
        File file = new File("test.docx");

        String fileName = file.getName();
        System.out.println(fileName);
        String[] fnameSplitted = fileName.split("\\.");
        String outPutFileName="";
        int i =0;
        while (i<fnameSplitted.length-1){
            outPutFileName+=fnameSplitted[i];
            i++;
        }
        outPutFileName += "-converted." + fnameSplitted[fnameSplitted.length-1];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file.getAbsolutePath());
            try {
                XWPFDocument docx = new XWPFDocument(fileInputStream);
                WDXToUnicode docxConverter = new WDXToUnicode(docx);
                XWPFDocument convertedFile = docxConverter.startConversion();
                try {

                    convertedFile.write(new FileOutputStream(outPutFileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}
