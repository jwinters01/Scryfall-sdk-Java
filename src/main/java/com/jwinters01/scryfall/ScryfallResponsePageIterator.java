package com.jwinters01.scryfall;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScryfallResponsePageIterator implements Iterator<JSONObject> {
    private JSONArray data;
    private int currentIndex;

    public ScryfallResponsePageIterator(JSONArray data) {
        this.data = data;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        if (data.length() > currentIndex)
            return true;
        return false;
    }

    @Override
    public JSONObject next() {
        try {
            return this.data.getJSONObject(this.currentIndex++);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
