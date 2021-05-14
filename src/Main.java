import Tess4J.Ocr;
import com.sun.deploy.ref.Helpers;
import io.vertx.core.Vertx;
import jsoup.Downloader;
import jsoup.JsoupScrapper;
import jsoup.Url;
import models.MyApiVerticle;
import utils.helper.CommuniqueHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.util.Date.from;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
         Ocr ocr = new Ocr();
        JsoupScrapper jsoupScrapper = new JsoupScrapper();
        String date="mardi-23-mars-2021";
        System.out.println(new SimpleDateFormat("EEEEE-dd-MMMMMMM-yyyy", Locale.FRANCE).format(new Date()));
        LocalDateTime dateTmp = LocalDateTime.of(2021, 3, 28, 10, 34);
        String dateCommunique = new SimpleDateFormat("EEEEE-dd-MMMMMMM-yyyy", Locale.FRANCE).format(from(dateTmp.atZone(ZoneId.systemDefault())
                .toInstant()));
        int num = CommuniqueHelper.getNumComm(from(dateTmp.atZone(ZoneId.systemDefault())
                .toInstant()));
        System.out.println("__________date communique : " + dateCommunique);
        System.out.println("__________date time : " + dateTmp.atZone(ZoneId.systemDefault()).toInstant());
        List<Url> urls =  jsoupScrapper.extractUrlFile(dateCommunique, num );
        System.out.println(urls.get(0).getLink() + "###########################");
        File ett = Downloader.getFile(urls.get(0).getLink(), num);
        String text  = ocr.getTextFromFile(ett);
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MyApiVerticle());

    }
}
