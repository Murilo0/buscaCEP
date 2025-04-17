package buscaCEP.methods;

import buscaCEP.modulo.Adress;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

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
        String json = null;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            json = response.body();
            System.out.println("JSON recebido: " + json); // <-- debug útil

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.fromJson(json, Adress.class);

        } catch (IOException | InterruptedException | RuntimeException e) {
            System.out.println("Erro ao consultar o CEP, tente novamente verificando se o CEP esta inserido corretamente");
            return null;
        }
    }
}