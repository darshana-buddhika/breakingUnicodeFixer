package PDF;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
 import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.text.TextPosition;

import java.io.*;
import java.util.List;

/**
 * Created by gayan@ciperlabs.com on 4/19/18.
 */
public class Read {


    public static void main(String[] args) {
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        File file = new File("test.pdf");
        FileInputStream fis = null;
        try {
//             PDFBox 2.0.8 require org.apache.pdfbox.io.RandomAccessRead
             RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
             PDFParser parser = new PDFParser(randomAccessFile);

            parser.parse();
            cosDoc = parser.getDocument();
            PDFTextStripper pdfStripper = new PDFTextStripper() {
                String prevBaseFont = "";

                protected void writeString(String text, List<TextPosition> textPositions) throws IOException
                {
                    StringBuilder builder = new StringBuilder();

                    for (TextPosition position : textPositions)
                    {
                        String baseFont = position.getFont().getName();
                        if (baseFont != null && !baseFont.equals(prevBaseFont))
                        {
                            builder.append('[').append(baseFont).append(']');
                            prevBaseFont = baseFont;
                        }
                        builder.append(position.getUnicode());
                    }

                    writeString(builder.toString());
                }
            };
            pdDoc = new PDDocument(cosDoc);
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(5);
            String parsedText = pdfStripper.getText(pdDoc);
            System.out.println(parsedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
