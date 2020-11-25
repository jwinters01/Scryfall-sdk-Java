package com.jwinters01.scryfall;

import java.util.Iterator;
import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScryfallResponsePage implements Iterable<JSONObject> {
    private JSONObject raw;
    private boolean hasMore;
    private URI nextPage;
    private JSONArray data;

    public ScryfallResponsePage(JSONObject raw) {
        this.raw = raw;
        try {
            this.data = raw.getJSONArray("data");
        } catch (JSONException e1) {
            System.err.println(e1.getMessage());
        }
        try {
            this.hasMore = raw.getBoolean("has_more");
            this.nextPage = this.hasMore ? new URI(this.raw.getString("next_page")) : null;
        } catch (JSONException | URISyntaxException e) {
            System.err.println(e.getMessage() + " final page of query received.");
            this.hasMore = false;
            this.nextPage = null;
        }
    }

    public boolean getHasMore(){ return this.hasMore; }
    public URI getNextPage(){ return this.nextPage; }


    @Override
    public Iterator<JSONObject> iterator() {
        return new ScryfallResponsePageIterator(this.data);
    }
    
}
