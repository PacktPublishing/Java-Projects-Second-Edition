package packt.java9.by.example.http2;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.net.http.HttpRequest.noBody;
import static java.net.http.HttpResponse.asString;

public class GetPacktHomePage {

    public String getHttp1_1() throws URISyntaxException, IOException, InterruptedException {
        HttpResponse response = HttpRequest
                .create(new URI("https://www.packtpub.com/"))
                .body(noBody())
                .GET().response();
        int responseCode = response.statusCode();
        String responseBody = response.body(asString());
        return responseBody;
    }

    public String getHttpAsync() throws URISyntaxException, IOException, InterruptedException, ExecutionException {
        CompletableFuture<HttpResponse> futureResponse = HttpRequest
                .create(new URI("https://www.packtpub.com/"))
                .body(noBody())
                .GET().responseAsync();
        int i = 0;
        while (!futureResponse.isDone()) {
            i++;
            System.out.println(i + ". Waiting...");
        }

        String responseBody = futureResponse.get().body(asString());
        return responseBody;
    }

    public String getHttp2() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.getDefault().create().version(HttpClient.Version.HTTP_2).build();
        HttpResponse response = client.request(new URI("https://www.packtpub.com/")).body(noBody()).GET().response();
        String responseBody = response.body(asString());
        return responseBody;
    }

}
