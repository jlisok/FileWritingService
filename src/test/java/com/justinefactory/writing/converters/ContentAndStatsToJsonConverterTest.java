package com.justinefactory.writing.converters;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.sending.domain.ContentAndStats;
import com.justinefactory.stats.domain.Stats;
import com.justinefactory.writing.domain.Content;
import com.justinefactory.writing.domain.Json;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;
import com.justinefactory.writing.exceptions.ContentWritingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ContentAndStatsToJsonConverterTest {


    @Test
    void createContentWhenStorageIsNull() {
        //given
        ContentAndStatsToJsonConverter<ThreeElemContent> converter = new ContentAndStatsToJsonConverter<>();

        //then
        assertThrows(ContentConversion2ReadyToWriteException.class, () -> converter.convertContent(null));
    }


    @Test
    void createContentWhenThreeElemContentConvertedAndMeetsConditions() throws ContentWritingException {
        //given
        String expectedContent = "{\n" +
                "  \"content\": {\n" +
                "    \"content\": [\n" +
                "      {\n" +
                "        \"timeStamp\": 1590147349818750700,\n" +
                "        \"randomInt\": -840762737,\n" +
                "        \"randomString\": \"ChristopherRobin\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"stats\": {\n" +
                "    \"count\": 1,\n" +
                "    \"distinctCount\": 1,\n" +
                "    \"max\": {\n" +
                "      \"timeStamp\": 1590147349818750700,\n" +
                "      \"randomInt\": -840762737,\n" +
                "      \"randomString\": \"ChristopherRobin\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Content<ThreeElemContent> threeElemContentContentStorage = new Content<>();
        ThreeElemContent threeElemContentContent = new ThreeElemContent(1590147349818750700L, -840762737, "ChristopherRobin");
        threeElemContentContentStorage.addContent(threeElemContentContent);

        Stats<ThreeElemContent> stats = new Stats<>(1, 1, threeElemContentContent);
        ContentAndStats<ThreeElemContent> content = new ContentAndStats<>(threeElemContentContentStorage, stats);

        ContentAndStatsToJsonConverter<ThreeElemContent> converter = new ContentAndStatsToJsonConverter<>();

        //when
        Json json = converter.convertContent(content);

        //then
        assertEquals(expectedContent, json.getContent());
    }


}