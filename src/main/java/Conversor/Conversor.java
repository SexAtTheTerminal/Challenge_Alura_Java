package Conversor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import java.util.Map;

public class Conversor {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/7c4d7fc019d160f12fbd0a66/latest/PEN";

    public Map<String, Object> obtenerTasas() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(response.body(), Map.class);
        return data;
    }


    public double convertir(double monto, String monedaDestino) throws Exception {
        Map<String, Object> data = obtenerTasas();
        Map<String, Double> tasas = (Map<String, Double>) data.get("conversion_rates");

        if (!tasas.containsKey(monedaDestino)) {
            throw new IllegalArgumentException("Moneda no válida: " + monedaDestino);
        }

        double tasa = tasas.get(monedaDestino);
        return monto * tasa;
    }
}
