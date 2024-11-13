import java.io.UnsupportedEncodingException;
import com.google.gson.JsonArray;
import java.io.IOException;

/*
* Консольная программа для поиска в Википедии.
*
* @author Платонов Владислав 3353
* @author Коновалов Максим 3353
*
*/

public class Main {
    public static void main(String[] args) {
        ConsoleIO console = new ConsoleIO();
        WikipediaApi wikiApi = new WikipediaApi();
        WikipediaPage wikiPage = new WikipediaPage();
        try {
            String request = console.readRequest();
            if (!(request.isEmpty()))
            {
                String encodedRequest = console.requestEncoder(request);
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
                            wikiPage.showPage(selectedPageId);
                        } else {
                            console.displayMessage("Введён неверный номер, попробуйте снова");
                        }
                    }
                }
            }
            else {
                console.displayMessage("Запрос не может быть пустым");
            }
        } catch (UnsupportedEncodingException e) {
            console.displayMessage("Ошибка кодирования URL: " + e.getMessage());
        } catch (IOException e) {
            console.displayMessage("Произошла ошибка при выполнении запроса: " + e.getMessage());
        }
    }
}

