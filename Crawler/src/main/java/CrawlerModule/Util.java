package CrawlerModule;

public class Util {
    public static int count = 1;
    public static String trim(String url){
        String res = url;
        //trim the http://
        if(res.startsWith("http://")){
            res = res.substring(7);
        }else if(res.startsWith("https://")){
            res = res.substring(8);
        }
        //trim the www.
        if(res.startsWith("www.")){
            res = res.substring(4);
        }
        return res;
    }
}
