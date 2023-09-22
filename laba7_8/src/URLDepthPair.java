import java.util.Objects;

public class URLDepthPair {
    private String url;//ссылка
    private int depth;//глубина
    public URLDepthPair(String url, int depth) {
        this.url = url;
        this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URLDepthPair that = (URLDepthPair) o;
        return depth == that.depth && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, depth);
    }

    public String getUrl() {
        return url;
    }

    public int getDepth() {
        return depth;
    }
}
