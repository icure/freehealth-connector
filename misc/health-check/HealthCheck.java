import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HealthCheck {
    public static void main(String[] args) {
        String url = args[0];
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                System.exit(0);
            } else {
                System.err.println("Health check failed: HTTP " + response.statusCode());
                System.exit(1);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Health check failed: " + e.getMessage());
            System.exit(1);
        }
    }
}
