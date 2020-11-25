package com.jwinters01.scryfall;

import org.json.JSONObject;
import java.util.Iterator;

public class ScryfallListResponse implements Iterable<JSONObject>{
    private JSONObject raw;
    private ScryfallResponsePage page;
    
    public ScryfallListResponse(JSONObject raw){
        this.raw = raw;
        this.page = new ScryfallResponsePage(raw);
    }

    public ScryfallResponsePage getPage(){ return this.page; }

    public JSONObject asJsonObject(){
        return this.raw;
    }

    @Override
    public Iterator<JSONObject> iterator() {
        return new ScryfallPaginationIterator(this);
    }
}
