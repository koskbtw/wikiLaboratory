import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class WikipediaApi {
    private static final String searchUrl = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=";
    public JsonObject makeServerConnection(String request) {
        HttpURLConnection connection = null;
        InputStreamReader reader = null;
        try {
            String requestUrl = searchUrl + request;
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new Gson();
            return gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            System.err.println("Ошибка при соединении или чтении данных: " + e.getMessage());
            return null;
        } finally {
            try {
                if (reader != null) reader.close();
                if (connection != null) connection.disconnect();
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии ресурсов: " + e.getMessage());
            }
        }
    }

    public boolean checkConnection(String query)
    {
        if ( makeServerConnection(query) == null)
        {
            return false;

        }
        else return true;
    }

    public JsonArray getSearchResults(String query)
    {
        JsonObject jsonResponse = makeServerConnection(query);
        JsonObject queryObject = jsonResponse.getAsJsonObject("query");
        return queryObject.getAsJsonArray("search");
    }

    public int getSelectedArticle(int choice, JsonArray searchResults)
    {
        JsonObject selectedArticle = searchResults.get(choice - 1).getAsJsonObject();
        return selectedArticle.get("pageid").getAsInt();
    }
}