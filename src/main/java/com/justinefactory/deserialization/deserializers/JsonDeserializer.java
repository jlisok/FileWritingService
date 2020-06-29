package com.justinefactory.deserialization.deserializers;

import com.google.gson.Gson;
import com.justinefactory.reading.exceptions.ContentDeserializationException;
import com.justinefactory.writing.domain.JsonStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;

public class JsonDeserializer<Content> implements Deserializer<JsonStorage, Content> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public Content deserialize(JsonStorage jsonStorage, Type classTypeToDeserializeJson) throws ContentDeserializationException {
        Gson gson = new Gson();
        try {
            return gson.fromJson(jsonStorage.getAllContent(), classTypeToDeserializeJson);
        } catch (Throwable e) {
            logger.warn("Trouble while performing deserialization of json: {}", jsonStorage.getAllContent(), e);
            throw new ContentDeserializationException(e, "Trouble while performing deserialization of json: " + jsonStorage.getAllContent());
        }
    }

}
