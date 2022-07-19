import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_7d6xiku7"; // IMDB
        String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=adeb2d66af45e3fde467051268add432"; // themoviedb
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        // extrair só os dados que interessa (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("backdrop_path"));
            System.out.println(filme.get("vote_average"));
            System.out.println();
        }

    }
}
