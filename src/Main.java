import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    private static final String apiurl = "https://68d275ddcc7017eec543f161.mockapi.io/api/users/users";
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.print("So'rovlardan birini tanlang (1.GET 2.PUT 3.POST 4.DELETE 0.EXIT): ");
            int tanlov = sc.nextInt();
            sc.nextLine(); // buffer tozalash

            if (tanlov == 1) {
                GetSurovi();
            } else if (tanlov == 2) {
                GetSurovi();
                PutSurovi();
            } else if (tanlov == 3) {
                PostSurovi();
            } else if (tanlov == 4) {
                GetSurovi();
                DeleteSurovi();
            } else if (tanlov == 0) {
                System.out.println("Chiqish...");
                break;
            } else {
                System.out.println("Noto'g'ri tanlov!");
            }
        }
    }

    public static void GetSurovi() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiurl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status kodi: " + response.statusCode());
            System.out.println("Natija:");
            System.out.println(response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void PutSurovi() {
        System.out.print("Qaysi ID ni o'zgartirasiz: ");
        String id = sc.nextLine(); // String bo'lishi kerak

        System.out.print("Boshqa nomni kiriting: ");
        String nom = sc.nextLine();

        String body = "{ \"name\": \"" + nom + "\" }";

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiurl + "/" + id))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status kodi: " + response.statusCode());
            System.out.println("O'zgarish kiritildi:");
            System.out.println(response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void PostSurovi() {
        System.out.print("Nom yozing: ");
        String nom = sc.nextLine();

        String body = "{ \"name\": \"" + nom + "\" }";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiurl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status kodi: " + response.statusCode());
            System.out.println("Yangi foydalanuvchi qo'shildi:");
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DeleteSurovi() {
        System.out.print("ID yozing: ");
        String id = sc.nextLine();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiurl + "/" + id))
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status kodi: " + response.statusCode());
            System.out.println("Foydalanuvchi o'chirildi!");
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
