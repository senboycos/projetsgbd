import Tess4J.Ocr;
import com.sun.deploy.ref.Helpers;
import jsoup.JsoupScrapper;
import jsoup.Url;
import utils.helper.CommuniqueHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
         Ocr ocr = new Ocr();
        File ett = new File("C:\\Users\\boycos\\Desktop\\master GLSI\\BD avance\\projet\\im1.jpg");

        String text  = ocr.getTextFromFile(ett);
        JsoupScrapper jsoupScrapper = new JsoupScrapper();
        String date="mardi-23-mars-2021";
        System.out.println(new SimpleDateFormat("EEEEE-dd-MMMMMMM-yyyy", Locale.FRANCE).format(new Date()));
        int numCommunique=386;
        String dateCommunique = new SimpleDateFormat("EEEEE-dd-MMMMMMM-yyyy", Locale.FRANCE).format(new Date());
        System.out.println("__________date communique : " + dateCommunique);
        List<Url> urls =  jsoupScrapper.extractUrlFile(date, 387);
        System.out.println(urls.get(0).getLink() + "###########################");
    }
}
