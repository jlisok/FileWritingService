package com.justinefactory.writing.domain;

public class JsonStorage {

    private final String json;

    public JsonStorage(String json) {
        ensureJsonNotEmptyAndNotNull(json);
        this.json = json;
    }

    private void ensureJsonNotEmptyAndNotNull(String json) throws IllegalArgumentException {
        if (json == null || json.isEmpty()) {
            throw new IllegalArgumentException("Trouble while writing json: " + json + ". The argument cannot be empty or null.");
        }
    }

    public String getContent() {
        return json;
    }

}
