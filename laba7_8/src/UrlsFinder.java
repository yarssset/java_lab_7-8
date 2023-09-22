import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UrlsFinder {
    private Pattern pattern = Pattern.compile("<a\\s*href\\s*=\\s*\"(?<url>http:\\/\\/\\S*)\"");//шаблон ссылки

    public List<String> findUrls(String startUrl) throws IOException {
        var urls = new ArrayList<String>();
        URL oracle = new URL(startUrl);//даёт доступ к сети
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));//читает информацию сайта байтами и переводит его в текст

        String inputLine;
        while ((inputLine = in.readLine()) != null)//построчно ищет ссылки по шаблону и возвращает ссылки
        {
            var matcher = pattern.matcher(inputLine);

            while (matcher.find()) {
                var matchUrl = matcher.group("url");
                if (matchUrl.endsWith("/"))
                    matchUrl = matchUrl.substring(0, matchUrl.length() - 1);
                urls.add(matchUrl);
            }
        }
        in.close();
        return urls;
    }
}
