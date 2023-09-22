

import java.util.ArrayList;

public class Crawler implements Runnable{
    private int maxDepth;
    private ArrayList<String> errors = new ArrayList<String>();//ArrayList с исключениями
    private UrlsFinder finder = new UrlsFinder();

    public Crawler(int max_depth) {
        maxDepth = max_depth;
    }

    public ArrayList<String> getErrors(){//возвращает ошибки

        return errors;
    }

    @Override
    public void run() {// запускает в отдельном потоке сканер ссылок

        URLDepthPair startUrlDepthPair = null;

        do {
            startUrlDepthPair = UrlsContainer.getFreeElement();
            if (startUrlDepthPair == null)
                return;
        } while (startUrlDepthPair.getDepth() >= maxDepth);
        try
        {
            var urls = finder.findUrls(startUrlDepthPair.getUrl());
            var newDepth = startUrlDepthPair.getDepth() + 1;
            UrlsContainer.addUnchecked( urls, newDepth);
        }
        catch (Exception e)
        {
            errors.add(e.getMessage());
        }
    }
}
