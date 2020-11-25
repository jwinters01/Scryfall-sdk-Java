package com.jwinters01.scryfall;

public class ScryfallClientBuilderImpl implements ScryfallClient.Builder{

    @Override
    public ScryfallClient build() {
        return new ScryfallClientImpl();
    }
    
}
