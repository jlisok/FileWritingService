package com.justinefactory.writing.converters;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.sending.domain.ContentAndStatsStorage;
import com.justinefactory.stats.domain.Stats;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;
import com.justinefactory.writing.exceptions.ContentWritingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ContentAndStatsToJsonConverterTest {


    @Test
    void createContentWhenStorageIsNull(){
        //given
        ContentAndStatsToJsonConverter<ThreeElemContent> converter = new ContentAndStatsToJsonConverter<>();

        //then
        assertThrows(ContentConversion2ReadyToWriteException.class, ()->  converter.convertContent(null));
    }

    @Test
    void createContentWhenStatsAreNull(){
        //given
        ContentStorage<ThreeElemContent> threeElemContentContentStorage = new ContentStorage<>();
        ThreeElemContent threeElemContentContent = new ThreeElemContent(1590147349818750700L, -840762737, "ChristopherRobin");
        threeElemContentContentStorage.addContent(threeElemContentContent);

        ContentAndStatsStorage<ThreeElemContent> content = new ContentAndStatsStorage<>(threeElemContentContentStorage, null);
        ContentAndStatsToJsonConverter<ThreeElemContent> converter = new ContentAndStatsToJsonConverter<>();

        //then
        assertThrows(ContentConversion2ReadyToWriteException.class, ()->  converter.convertContent(content));
    }

    @Test
    void createContentWhenContentIsNull(){
        //given
        ThreeElemContent threeElemContentContent = new ThreeElemContent(1590147349818750700L, -840762737, "ChristopherRobin");
        Stats<ThreeElemContent> stats = new Stats<>(1, 1, threeElemContentContent);

        ContentAndStatsStorage<ThreeElemContent> content = new ContentAndStatsStorage<>(null, stats);
        ContentAndStatsToJsonConverter<ThreeElemContent> converter = new ContentAndStatsToJsonConverter<>();

        //then
        assertThrows(ContentConversion2ReadyToWriteException.class, ()->  converter.convertContent(content));
    }

    @Test
    void createContentWhenContentIsEmpty(){
        //given
        ContentStorage<ThreeElemContent> threeElemContentContentStorage = new ContentStorage<>();
        ThreeElemContent threeElemContentContent = new ThreeElemContent(1590147349818750700L, -840762737, "ChristopherRobin");
        Stats<ThreeElemContent> stats = new Stats<>(1, 1, threeElemContentContent);

        ContentAndStatsStorage<ThreeElemContent> content = new ContentAndStatsStorage<>(threeElemContentContentStorage, stats);
        ContentAndStatsToJsonConverter<ThreeElemContent> converter = new ContentAndStatsToJsonConverter<>();

        //then
        assertThrows(ContentConversion2ReadyToWriteException.class, ()->  converter.convertContent(content));
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
        ContentStorage<ThreeElemContent> threeElemContentContentStorage = new ContentStorage<>();
        ThreeElemContent threeElemContentContent = new ThreeElemContent(1590147349818750700L, -840762737, "ChristopherRobin");
        threeElemContentContentStorage.addContent(threeElemContentContent);

        Stats<ThreeElemContent> stats = new Stats<>(1, 1, threeElemContentContent);
        ContentAndStatsStorage<ThreeElemContent> content = new ContentAndStatsStorage<>(threeElemContentContentStorage, stats);

        ContentAndStatsToJsonConverter<ThreeElemContent> converter = new ContentAndStatsToJsonConverter<>();

        //when
        ContentStorage<String> json = converter.convertContent(content);

        //then
        assertEquals(expectedContent, json.getContent(0));
    }


}