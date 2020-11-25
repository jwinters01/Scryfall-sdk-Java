package com.jwinters01.scryfall;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;

import org.json.JSONObject;

public class ScryfallPaginationIterator implements Iterator<JSONObject>{
    private ScryfallResponsePage currentPage;
    private Iterator<JSONObject> currentItemIterator;

    public ScryfallPaginationIterator(ScryfallListResponse response){
        this.currentPage = response.getPage();
        this.currentItemIterator = this.currentPage.iterator();
    }

    private ScryfallResponsePage getNextPage(){
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(this.currentPage.getNextPage())
                .GET()
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject obj = new JSONObject(response.body());
            return new ScryfallResponsePage(obj);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        return (this.currentItemIterator.hasNext() || this.currentPage.getHasMore());
    }

    @Override
    public JSONObject next() {
        if(this.currentItemIterator.hasNext())
            return currentItemIterator.next();
        this.currentPage = this.getNextPage();
        this.currentItemIterator = this.currentPage.iterator();
        return this.currentItemIterator.next();
    }
    
}
