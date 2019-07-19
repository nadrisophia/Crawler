package CrawlerModule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Crawler implements Runnable {

    private String url;
    private String domain;
    private ExecutorService executor;
    private List<String> processedUrls;

    public Crawler(String url,
                   ExecutorService executor,
                   List<String> processed,
                   String domain){
        this.url = url;
        this.executor = executor;
        this.processedUrls = processed;
        this.domain = domain;
    }

    @Override
    public void run() {
        List<String> urls = process();// this is when i execute the connection
        Util.count--;
        //submit found urls if they don't already exist
        for(String url : urls){
            synchronized (processedUrls) {
                if (url != null && url.length() > 0 && url.endsWith("/")) {
                    url = url.substring(0, url.length() - 1);
                }
                if(!processedUrls.contains(url)){
                    Util.count++;
                    executor.submit(new Crawler(url, executor, processedUrls, domain));
                }
            }
        }
        //check if we reached the end of the crawling
        if(Util.count <= 0){
            try {
                TimeUnit.SECONDS.sleep(10);
                if(Util.count == 0){
                    executor.shutdown();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public List<String> process() {
        List<String> res = new ArrayList<>();
        try {
            //trim the end to avoid repetitions
            if (url != null && url.length() > 0 && url.endsWith("/")) {
                url = url.substring(0, url.length() - 1);
            }

            //process
            Document doc = Jsoup.connect(url).get();
            if (processedUrls.contains(url)) {
                return res;
            }
            processedUrls.add(url);
            Elements otherURLs = doc.select("a[href]");
            for (Element link : otherURLs) {
                if (Util.trim(link.attr("abs:href")).startsWith(domain)) {
                    res.add(link.attr("abs:href"));
                }
            }

        }catch(SocketException e) {
            // re add the url to be processed
            res.add(url);
        }catch(SocketTimeoutException e1){
            // re add the url to be processed
            res.add(url);
        }catch(IOException e2){
            //System.out.println("the url: " + url + " wasn't added to the list: " + e2);

        }
        return res;
    }

}
