package buscaCEP.methods;

import buscaCEP.modulo.Adress;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaCep {
    public Adress consultaCep(String cep) {
        URI link = URI.create("https://viacep.com.br/ws/" + cep + "/json/");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(link)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            System.out.println("JSON recebido: " + json); // <-- debug útil

            // Verifica se o JSON retornado indica erro (como {"erro": true})
            if (json.contains("\"erro\": true")) {
                throw new RuntimeException("CEP não encontrado.");
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.fromJson(json, Adress.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao consultar o CEP", e);
        }
    }
}