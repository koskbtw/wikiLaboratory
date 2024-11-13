import java.io.IOException;

public class WikipediaPage
{
    private static final String harvesting = "https://ru.wikipedia.org/w/index.php?curid=";
    public void showPage(int pageId) throws IOException {
        String pageUrl = harvesting + pageId;
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(pageUrl));
    }
}
