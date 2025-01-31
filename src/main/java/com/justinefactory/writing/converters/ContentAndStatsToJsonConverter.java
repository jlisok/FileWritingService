package com.justinefactory.writing.converters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.justinefactory.sending.domain.ContentAndStatsStorage;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;

public class ContentAndStatsToJsonConverter<Content> implements ContentConverter<ContentAndStatsStorage<Content>, ContentStorage<String>> {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();


    @Override
    public ContentStorage<String> convertContent(ContentAndStatsStorage<Content> content) throws ContentConversion2ReadyToWriteException {
        if (content == null) {
            throw new ContentConversion2ReadyToWriteException("Converting content " + content + " into line - failed. Content is empty or does not exist.");
        }
        String json = gson.toJson(content);
        return new ContentStorage<>(json);
    }

}
