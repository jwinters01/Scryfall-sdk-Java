package com.jwinters01.scryfall;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;

import org.json.JSONObject;

public abstract class ScryfallClient {
    protected ScryfallClient(){}

    public static ScryfallClient newScryfallClient(){
        return newBuilder().build();
    }

    public static Builder newBuilder(){
        return new ScryfallClientBuilderImpl();
    }

    public interface Builder{
        public ScryfallClient build();
    }

    public ScryfallListResponse getCardsWithQueryString(String qString){
        try{
            String encodedString = URLEncoder.encode(qString, "UTF-8");
            String uriString = String.format("https://api.scryfall.com/cards/search?q=%s", encodedString);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uriString))
                .GET()
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject obj = new JSONObject(response.body());
            return new ScryfallListResponse(obj);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
