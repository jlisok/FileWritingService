package com.justinefactory.deserialization.deserializers;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.reading.exceptions.ContentDeserializationException;
import com.justinefactory.sending.domain.ContentAndStats;
import com.justinefactory.sending.domain.IntegerAndStats;
import com.justinefactory.sending.domain.ThreeElementContentAndStats;
import com.justinefactory.stats.domain.Stats;
import com.justinefactory.writing.converters.ContentAndStatsToJsonConverter;
import com.justinefactory.writing.domain.Content;
import com.justinefactory.writing.domain.Json;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonDeserializerTest {


    @Test
    void deserializeToThreeElemContentAndStats() throws ContentDeserializationException, ContentConversion2ReadyToWriteException {
        // given
        Content<ThreeElemContent> content = new Content<>(new ThreeElemContent(1590147349818750700L, -840762737, "ChristopherRobin"));
        Stats<ThreeElemContent> stats = new Stats<>(1, 1, content.getContent(0));
        ThreeElementContentAndStats expectedStorage = new ThreeElementContentAndStats(content, stats);
        Json json = serializeContentAndStats(expectedStorage);
        JsonDeserializer<ThreeElementContentAndStats> deserializer = new JsonDeserializer<>();
        Class<ThreeElementContentAndStats> classType = ThreeElementContentAndStats.class;

        //when
        ThreeElementContentAndStats actualStorage = deserializer.deserialize(json, classType);

        //then
        assertEquals(expectedStorage, actualStorage);

    }

    @Test
    void deserializeToIntegerContentAndStats() throws ContentDeserializationException, ContentConversion2ReadyToWriteException {
        // given
        Content<Integer> content = new Content<>(8);
        Stats<Integer> stats = new Stats<>(1, 1, 8);
        IntegerAndStats expectedStorage = new IntegerAndStats(content, stats);
        Json json = serializeContentAndStats(expectedStorage);
        JsonDeserializer<IntegerAndStats> deserializer = new JsonDeserializer<>();
        Class<IntegerAndStats> classType = IntegerAndStats.class;

        //when
        IntegerAndStats actualStorage = deserializer.deserialize(json, classType);

        //then
        assertEquals(expectedStorage, actualStorage);

    }


    private static <ContentType> Json serializeContentAndStats(ContentAndStats<ContentType> storage) throws ContentConversion2ReadyToWriteException {
        ContentAndStatsToJsonConverter<ContentType> converter = new ContentAndStatsToJsonConverter<>();
        return converter.convertContent(storage);
    }

}