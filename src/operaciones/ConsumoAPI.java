package operaciones;

import com.google.gson.Gson;
import modelos.Conversion;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    public Conversion conversion(String monedaDe, String monedaA, double cantidad){
        URI url = URI.create("https://v6.exchangerate-api.com/v6/d6918a72c52caa6bc4832a00/pair/"
                + monedaDe + "/" + monedaA + "/" + cantidad);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new Gson().fromJson(response.body(), Conversion.class);
    }
}
