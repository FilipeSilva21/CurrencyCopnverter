package com.CurrencyConverter.services;

import com.CurrencyConverter.DTOs.ConvertResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ConverterService {

    @Value("${api.key}")
    private String apiKey;

    public ConvertResponse convertCurrency(String from, String to, double amount) {
        String urlStr = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", apiKey, from, to);

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder answer = new StringBuilder();
            String linha;

            while ((linha = reader.readLine()) != null) {
                answer.append(linha);
            }

            JsonObject json = JsonParser.parseString(answer.toString()).getAsJsonObject();
            double tax = json.get("conversion_rate").getAsDouble();
            double result = tax * amount;

            return new ConvertResponse(from, to, amount, result);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar API: " + e.getMessage());
        }
    }
}
