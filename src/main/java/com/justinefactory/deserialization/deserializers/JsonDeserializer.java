package com.justinefactory.deserialization.deserializers;

import com.google.gson.Gson;
import com.justinefactory.reading.exceptions.ContentDeserializationException;
import com.justinefactory.writing.domain.Json;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonDeserializer<ContentType> implements Deserializer<Json, ContentType> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ContentType deserialize(Json json, Class<ContentType> classTypeToDeserializeJson) throws ContentDeserializationException {
        Gson gson = new Gson();
        try {
            return gson.fromJson(json.getContent(), classTypeToDeserializeJson);
        } catch (Throwable e) {
            logger.warn("Trouble while performing deserialization of json: {}", json.getContent(), e);
            throw new ContentDeserializationException(e, "Trouble while performing deserialization of json: " + json.getContent());
        }
    }

}
