package com.justinefactory.writing.converters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.justinefactory.sending.domain.ContentAndStatsStorage;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;

public class ContentAndStatsToJsonConverter<Content> implements ContentConverter<ContentAndStatsStorage<Content>, ContentStorage<String>> {

    @Override
    public ContentStorage<String> convertContent(ContentAndStatsStorage<Content> content) throws ContentConversion2ReadyToWriteException {
        checkIfIsNull(content);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(content);
        ContentStorage<String> storage = new ContentStorage<>();
        storage.addContent(json);
        return storage;
    }


    private void checkIfIsNull(ContentAndStatsStorage<Content> content) throws ContentConversion2ReadyToWriteException {
        if (content == null || content.getContent() == null || content.getContent().isEmpty() || content.getStats() == null) {
            throw new ContentConversion2ReadyToWriteException("Converting ThreeElemContent object " + content + " into csvFile line - failed. Content was empty.");
        }
    }
}
