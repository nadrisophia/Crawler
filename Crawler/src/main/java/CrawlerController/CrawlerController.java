package CrawlerController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import CrawlerModule.Crawler;
import CrawlerModule.Util;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlerController {


    @RequestMapping("/crawl")
    public List<String> crawl(@RequestParam(value="url", defaultValue="https://monzo.com") String name) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        List<String> processed = new ArrayList<>();
        String domain = Util.trim(name);
        System.out.println(name);
        System.out.println(domain);
        executor.submit(new Crawler(name, executor, processed, domain));
        executor.awaitTermination(60, TimeUnit.SECONDS);
        System.out.println(processed.size());
        Collections.sort(processed);
        return processed;
    }
}
