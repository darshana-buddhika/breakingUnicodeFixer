package word;

import ConvertionEngine.Engine;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;

import java.io.*;
import java.util.List;

/**
 * Created by gayan@ciperlabs.com on 4/21/18.
 */
public class WDXToUnicode {

    private XWPFDocument docx = null;
    private CTRPr sinhalaUnicodeCTRPr = null;
    private CTRPr tamilUnicodeCTRPr= null;
    private static String sinhalaUnicodeFont = "Iskoola Pota";
    private static String tamilUnicodeFont = "Latha";
    private static String sinhalaExtraFont = "Nirmala UI";

    private WDXToUnicode(){}                            // Avoid accidental creation of Object without File

    public WDXToUnicode(XWPFDocument docx){
        this.docx = docx;

        sinhalaUnicodeCTRPr =  CTRPr.Factory.newInstance();                        // Set All types of fonts for sinhala types
        CTFonts fonts = CTFonts.Factory.newInstance();
        fonts.setAscii(sinhalaUnicodeFont);
        fonts.setHAnsi(sinhalaUnicodeFont);
        fonts.setCs(sinhalaUnicodeFont);
        sinhalaUnicodeCTRPr.setRFonts(fonts);

        tamilUnicodeCTRPr =  CTRPr.Factory.newInstance();                        // Set All types of fonts for Tamil types
        CTFonts fonts2 = CTFonts.Factory.newInstance();
        fonts2.setAscii(tamilUnicodeFont);
        fonts2.setHAnsi(tamilUnicodeFont);
        fonts2.setCs(tamilUnicodeFont);
        tamilUnicodeCTRPr.setRFonts(fonts2);
    }

    public XWPFDocument startConversion() {

        List<XWPFParagraph> paragraphs = docx.getParagraphs();
        int paragraphsCount = paragraphs.size();
        int currentParagraphPosition = 0;
        Engine engine = new Engine();

        while (currentParagraphPosition<paragraphsCount){

            XWPFParagraph paragraph = paragraphs.get(currentParagraphPosition);
            List<XWPFRun> runs = paragraph.getRuns();
            if (runs != null) {
                int runsLength = runs.size();
                int i = 0;
                while (i < runsLength) {
                    XWPFRun run = runs.get(i);

                    String[] convertedText = engine.toUnicode(run.getText(0),run.getFontFamily());
                    run.setText(convertedText[0],0);
                    run.setFontFamily(convertedText[1]);


                    if(convertedText[1].equals("SINHALA")){                                 // Setting Font Family
                        run.getCTR().setRPr(sinhalaUnicodeCTRPr);
                    }
                    else if(convertedText[1].equals("TAMIL")){
                        run.getCTR().setRPr(tamilUnicodeCTRPr);
                    }
                    i++;

                }
            }
            currentParagraphPosition++;
        }

        return docx;
    }

}


