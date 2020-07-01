package com.justinefactory.deserialization.deserializers;

import com.justinefactory.reading.exceptions.ContentDeserializationException;

import java.lang.reflect.Type;

public interface Deserializer<Object, ContentType> {

    ContentType deserialize(Object object, Class<ContentType> type) throws ContentDeserializationException;

}
