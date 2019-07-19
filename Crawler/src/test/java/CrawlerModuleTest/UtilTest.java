package CrawlerModuleTest;

import CrawlerModule.Util;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void trim() {
        String url = "https://www.monzo.com/";
        String url1 = "http://monzo.com";
        String url2 = "https://monzo.com/features/";
        assertEquals("monzo.com", Util.trim(url1));
        assertEquals("monzo.com/", Util.trim(url));
        assertEquals("monzo.com/features/", Util.trim(url2));
    }
}