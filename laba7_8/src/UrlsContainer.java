import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class UrlsContainer {
    private static Object locker = new Object();

    private static HashMap<Integer, URLDepthPair> uncheckedUrls = new HashMap<Integer, URLDepthPair>();//hashmap для необработанных сайтов
    private static ArrayList<URLDepthPair> checkedUrls = new ArrayList<URLDepthPair>();//для всех

    public static int getUncheckedSize() {//кол-во необработанных ссылок
        return uncheckedUrls.size();
    };

    public static ArrayList<URLDepthPair> getChecked() {//возвращает все обработанные ссылки
        return checkedUrls;
    }

    public static URLDepthPair getFreeElement() {//перебрасывает из необработанных во все сайты
        synchronized (locker) {
            if (getUncheckedSize() == 0)
                return null;
            var element = uncheckedUrls.entrySet().iterator().next().getValue();
            checkedUrls.add(element);
            uncheckedUrls.remove(element.hashCode());
            return element;
        }
    }

    public static void addUnchecked(String url, int depth) {
        synchronized (locker) {
            var pair = new URLDepthPair(url, depth);
            uncheckedUrls.put(pair.hashCode(), pair);
        }
    }

    public static void addUnchecked(List<String> urls, int depth) {//добавляет новые необработанные ссылки
        for (var url : urls) {
            addUnchecked(url, depth);
        }
    }



}
