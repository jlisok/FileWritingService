package com.justinefactory.deserialization.deserializers;

import com.justinefactory.reading.exceptions.ContentDeserializationException;

import java.lang.reflect.Type;

public interface Deserializer<Object, Content> {

    Content deserialize(Object object, Type type) throws ContentDeserializationException;

}
