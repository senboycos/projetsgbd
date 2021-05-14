package Tess4J;


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

public class Ocr {
    Tesseract tesseract = new Tesseract();
    File directory = new File (".");

    public String getTextFromFile( File ett){


        try {
            tesseract.setDatapath(directory.getCanonicalPath() + "\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
        } catch (IOException e) {
            e.printStackTrace();
        }
        tesseract.setLanguage("eng");
        // the path of your tess data folder
        // inside the extracted file
        String text
                = null;
        try {
            text = tesseract.doOCR(ett);
        } catch (TesseractException tesseractException) {
            tesseractException.printStackTrace();
        }

        // path of your image file
        StringTokenizer st =  new StringTokenizer( text , " " );

        while  (st.hasMoreTokens ()) {
            System.out.println (st.nextToken ());
        }
        return text;
    }


}