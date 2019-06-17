import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * @author owl
 * @Date 2019/6/17
 * @Description
 */
public class YoudaoTranslate {
    private final static String YOU_DAO_URL = "http://fanyi.youdao.com/openapi.do?keyfrom=lewe518&key=70654389&type=data&doctype=json&version=1.1&q=";

    public static String sendGet(String str) throws IOException {


        URL realUrl =new URL(YOU_DAO_URL + str);
        //打开和URL之间的连接
        URLConnection conn = realUrl.openConnection();
        String result = null;
        try(InputStream input = conn.getInputStream()){
            result = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8.name())).lines().collect(Collectors.joining("\n"));
        }

        return result;
    }

}