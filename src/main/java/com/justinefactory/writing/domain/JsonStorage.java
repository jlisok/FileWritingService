package com.justinefactory.writing.domain;

import com.justinefactory.domain.Storage;

public class JsonStorage implements Storage<String> {

    private final String json;

    public JsonStorage(String json) {
        assureJsonNotEmptyAndNotNull(json);
        this.json = json;
    }

    private void assureJsonNotEmptyAndNotNull(String json) throws IllegalArgumentException {
        if (json == null || json.isEmpty()) {
            throw new IllegalArgumentException("Trouble while writing json: " + json + ". The argument cannot be empty or null.");
        }
    }

    public String getAllContent() {
        return json;
    }

}
