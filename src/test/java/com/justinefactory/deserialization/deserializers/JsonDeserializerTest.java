package com.justinefactory.deserialization.deserializers;

import com.google.gson.reflect.TypeToken;
import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.reading.exceptions.ContentDeserializationException;
import com.justinefactory.sending.domain.ContentAndStatsStorage;
import com.justinefactory.stats.domain.Stats;
import com.justinefactory.writing.converters.ContentAndStatsToJsonConverter;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.domain.JsonStorage;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonDeserializerTest {


    @Test
    void deserializeToThreeElemContentAndStats() throws ContentDeserializationException, ContentConversion2ReadyToWriteException {
        // given
        ContentAndStatsStorage<ThreeElemContent> expectedStorage = mockContentAndStatsStorage(new ThreeElemContent(1590147349818750700L, -840762737, "ChristopherRobin"));
        JsonStorage json = serializeContentAndStats(expectedStorage);
        JsonDeserializer<ContentAndStatsStorage<ThreeElemContent>> deserializer = new JsonDeserializer<>();
        Type classType = new TypeToken<ContentAndStatsStorage<ThreeElemContent>>() {
        }.getType();

        //when
        ContentAndStatsStorage<ThreeElemContent> actualStorage = deserializer.deserialize(json, classType);

        //then
        assertEquals(expectedStorage, actualStorage);

    }

    @Test
    void deserializeToIntegerContentAndStats() throws ContentDeserializationException, ContentConversion2ReadyToWriteException {
        // given
        ContentAndStatsStorage<Integer> expectedStorage = mockContentAndStatsStorage(8);
        JsonStorage json = serializeContentAndStats(expectedStorage);
        JsonDeserializer<ContentAndStatsStorage<Integer>> deserializer = new JsonDeserializer<>();
        Type classType = new TypeToken<ContentAndStatsStorage<Integer>>() {
        }.getType();

        //when
        ContentAndStatsStorage<Integer> actualStorage = deserializer.deserialize(json, classType);

        //then
        assertEquals(expectedStorage, actualStorage);

    }


    private static <Content> ContentAndStatsStorage<Content> mockContentAndStatsStorage(Content content) {
        Stats<Content> stats = new Stats<>(1, 1, content);
        return new ContentAndStatsStorage<>(new ContentStorage<>(content), stats);
    }


    private static <Content> JsonStorage serializeContentAndStats(ContentAndStatsStorage<Content> storage) throws ContentConversion2ReadyToWriteException {
        ContentAndStatsToJsonConverter<Content> converter = new ContentAndStatsToJsonConverter<>();
        return converter.convertContent(storage);
    }

}