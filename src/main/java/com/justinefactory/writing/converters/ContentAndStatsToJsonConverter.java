package com.justinefactory.writing.converters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.justinefactory.sending.domain.ContentAndStats;
import com.justinefactory.writing.domain.JsonContent;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;

public class ContentAndStatsToJsonConverter<ContentType> implements ContentConverter<ContentAndStats<ContentType>, JsonContent> {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();


    @Override
    public JsonContent convertContent(ContentAndStats<ContentType> content) throws ContentConversion2ReadyToWriteException {
        if (content == null) {
            throw new ContentConversion2ReadyToWriteException("Converting content " + content + " into line - failed. Content is empty or does not exist.");
        }
        String json = gson.toJson(content);
        return new JsonContent(json);
    }

}
