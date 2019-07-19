package CrawlerModule;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<String> processed = new ArrayList<>();
        Date time = new Date();
        System.out.println(time);
        executor.submit(new Crawler("https://monzo.com", executor, processed, "monzo.com"));
        executor.awaitTermination(60,TimeUnit.SECONDS);
        Date time2 = new Date();
        System.out.println(time2);
        for(String s: processed){
            System.out.println(s);
        }
    }
}
