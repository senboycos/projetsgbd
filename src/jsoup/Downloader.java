package jsoup;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Downloader {
    public static File getFile(String url ,int num) throws MalformedURLException {
        try {
            URI u = URI.create(url);
            InputStream initialStream = u.toURL().openStream();
            File targetFile = new File("communique/communique-"+ num +".pdf");
            OutputStream outStream = new FileOutputStream(targetFile);

            try (FileOutputStream outputStream = new FileOutputStream(targetFile)) {

                int read;
                byte[] bytes = new byte[1024];

                while ((read = initialStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                return targetFile;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


