package ConvertionEngine;

import ConvertionEngine.LegacyToUnicodeFontMappings.FMAbhaya;
import ConvertionEngine.LegacyToUnicodeFontMappings.Thibus;

/**
 * Created by gayan@ciperlabs.com on 4/21/18.
 */
public class Engine {
    private static String sinhalaUnicodeFont = "SINHALA";
    private static String tamilFont = "TAMIL";
    private static String lastFont = "";
    private static boolean sinhalaLastCharError;
    private static boolean tamilLastCharError;


    public Engine(){
        lastFont = "";
        sinhalaLastCharError = false;
        tamilLastCharError = false;

    }
    public String[] toUnicode(String text, String font){
        String unicodeText = text;
        String newFont = null;
        if(font == null){
            font = "";
        }
        else {
            lastFont=font;
        }
        if (text == null){

            return new String[]{text,sinhalaUnicodeFont};
        }
        if(lastFont.equals("LTRL") && font.equals("")){
            font="LTRL";
            lastFont=font;
            //System.out.println("LTRL : "+text);

        }
        else if(lastFont.equals("LTRL") && font.equalsIgnoreCase("Arial")){
            font="LTRL";
            lastFont="LTRL";
            //System.out.println("Arial : "+text);

        }

        /*
            Starts font mappings
         */
        if (font.equals("Thibus16STru")|| font.equals("Thibus15STru") || font.equals("Thibus02S")
                || font.equals("Thibus02STru")|| font.equals("Thibus05STru")){

            tamilLastCharError = false;
            if(Thibus.lastCharError(text)){
                text = text.substring(0,text.length()-1);
            }
            if(sinhalaLastCharError){
                text = Thibus.fixLastCharError(text);
            }

            unicodeText = Thibus.convert(text);

            return new String[]{unicodeText,sinhalaUnicodeFont};

        }
        else return new String[]{text,font};
    }
}
