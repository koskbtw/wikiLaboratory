import java.io.UnsupportedEncodingException;
import com.google.gson.JsonArray;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ConsoleIO console = new ConsoleIO();
        WikipediaApi wikiApi = new WikipediaApi();
        UrlPe urlParser = new UrlPe();
        try {
            String encodedRequest = console.readRequest();
            if(wikiApi.checkConnection(encodedRequest))
            {
                JsonArray searchResults = wikiApi.getSearchResults(encodedRequest);

                if (searchResults.isEmpty()) {
                    console.displayMessage("Нет результатов для поиска.");
                }
                else
                {
                    console.displayResults(searchResults);
                    int choice = console.readInt();
                    if (choice > 0 && choice <= searchResults.size()) {
                        int selectedPageId = wikiApi.getSelectedArticle(choice, searchResults);
                        urlParser.showPage(selectedPageId);

                    } else {
                        console.displayMessage("Введён неверный номер, попробуйте снова");
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            console.displayMessage("Ошибка кодирования URL: " + e.getMessage());
        } catch (IOException e) {
            console.displayMessage("Произошла ошибка при выполнении запроса: " + e.getMessage());
        }
    }
}

