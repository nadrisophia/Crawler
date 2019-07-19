package CrawlerModuleTest;

import CrawlerModule.Crawler;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CrawlerTest {


    //private static List<String> processed = new ArrayList<>();
    /*@Before
    public static void before() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Date time = new Date();
        System.out.println(time);
        executor.submit(new Crawler("http://monzo.com", executor, processed, "monzo.com"));
        executor.awaitTermination(200, TimeUnit.SECONDS);
        Date time2 = new Date();
        System.out.println(time2);
    }*/

    @Test
    public void processedListIsNotEmpty() throws InterruptedException {
        List<String> processed = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new Crawler("http://monzo.com", executor, processed, "monzo.com"));
        executor.awaitTermination(10, TimeUnit.SECONDS);
        assertTrue(processed.size() > 1);
    }

    @Test
    public void processedListIsEmpty() throws InterruptedException {
        List<String> processed = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new Crawler("thisIsATestURL.com", executor, processed, "thisIsATestURL.com"));
        executor.awaitTermination(10, TimeUnit.SECONDS);
        assertTrue(processed.size() == 0);
    }



}