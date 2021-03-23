package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


    public class JsoupScrapper {

        private static final String file = "file";


        public List<Url> extractUrlFile(String date,int numCommunique) {
            final String EBAY_GLOBAL_DEALS_URL = "http://www.sante.gouv.sn/Actualites/" +
                    "coronavirus-communiqu%C3%A9-de-presse-n%C2%B0"+ numCommunique +"-du-" + date + "-du-minist%C3%A8re-de-la-sant%C3%A9-et";

            List<Url> listUrl = new ArrayList<>();

            Document doc;
            try {
                doc = Jsoup.connect(EBAY_GLOBAL_DEALS_URL).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Elements elements = doc.getElementsByClass(file);
            for (Element fileElement : elements) {
                Url url = new Url();
                Elements linksOnPage = fileElement.select("a[href]");
                System.out.println("____________FILE_ELEMENT___________");
                System.out.println(linksOnPage.get(0).attr("href"));
                System.out.println("____________FILE_ELEMENT___________");
                    url.setLink(fileElement.attr("href"));


                listUrl.add(url);
            }

            return listUrl;
        }
    }

