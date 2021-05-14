package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


    public class JsoupScrapper {

        private static final String file = "file";


        public List<Url> extractUrlFile(String date,int numCommunique) {
            String EBAY_GLOBAL_DEALS_URL = "http://www.sante.gouv.sn/Actualites/" +
                    "coronavirus-communiqu%C3%A9-de-presse-n%C2%B0"+ numCommunique +"-du-" + date + "-du-minist%C3%A8re-de-la-sant%C3%A9-et";

            List<Url> listUrl = new ArrayList<>();


            Document doc;
            try {
                doc = Jsoup.connect(EBAY_GLOBAL_DEALS_URL)
                        .ignoreContentType(true)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36")
                        .timeout(10*1500000)
                        .followRedirects(true)
                        .get();

            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println(EBAY_GLOBAL_DEALS_URL);
            }

            Elements elements = doc.getElementsByClass(file);
            System.out.println(elements.size());
            if (elements.size() == 0){
                List<String> urls = new ArrayList<>(Arrays.asList(EBAY_GLOBAL_DEALS_URL.split("-")));
                urls.remove("et");
                String urlsSiteMinistere = String.join("-", urls);

                try {
                    Document doc2 =Jsoup.connect(urlsSiteMinistere)
                            .ignoreContentType(true)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36")
                            .timeout(15*100000)
                            .followRedirects(true)
                            .get();
                    elements = doc2.getElementsByClass(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            System.out.println(elements);
//            System.out.println(EBAY_GLOBAL_DEALS_URL);
         //   System.out.println(doc);

            for (Element fileElement : elements) {
                Url url = new Url();
                Elements linksOnPage = fileElement.select("a[href]");
                System.out.println("____________FILE_ELEMENT___________");
                System.out.println(linksOnPage.get(0).attr("href"));
                url.setLink(linksOnPage.get(0).attr("href"));
                System.out.println("____________FILE_ELEMENT___________");
                url.setLink(linksOnPage.get(0).attr("href"));


                listUrl.add(url);
            }

            return listUrl;
        }
    }

