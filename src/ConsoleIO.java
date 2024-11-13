import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;

public class ConsoleIO {
    private Scanner scanner;

    public String readRequest() throws UnsupportedEncodingException {
        System.out.println("Введите запрос для поиска статей на Википедии: ");
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String requestEncoder(String req) throws UnsupportedEncodingException {
        req = URLEncoder.encode(req, "UTF-8");
        return req;
    }
    public int readInt()
    {
        scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public void displayMessage(String message) {
        System.out.println(message);
    }
    public void displayResults(JsonArray searchResults)
    {
        System.out.println("Найденные статьи:");
        for (int i = 0; i < searchResults.size(); i++) {
            JsonObject article = searchResults.get(i).getAsJsonObject();
            String title = article.get("title").getAsString();
            System.out.println((i + 1) + ":" + title);
        }
    }
    public void close() {
        scanner.close();
    }
}
