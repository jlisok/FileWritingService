package com.justinefactory.writing.writers.tofile;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.testutil.CreateAndDeleteFilesBeforeAfterAll;
import com.justinefactory.writing.domain.JsonContent;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonFileWriterTest {

    static Path dir;

    @BeforeAll
    static void createDirsBeforeAll() throws Exception {
        dir = CreateAndDeleteFilesBeforeAfterAll.createTemporaryDirectory();
    }

    @AfterAll
    static void removeDirsAfterAll() throws Exception {
        CreateAndDeleteFilesBeforeAfterAll.removeAllDirs(dir);
    }

    @Test
    void write2FileWhenWritingJSON() throws Exception {
        //given
        Path filePath = dir.resolve("doc.json");
        PathInfo file2writeData = new PathInfo(filePath);
        JsonContent readyToWriteContent = new JsonContent("{\n" +
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
                "}");

        //when
        JsonFileWriter writer = new JsonFileWriter();
        writer.writeContent(readyToWriteContent, file2writeData);

        //then
        assertTrue(Files.exists(filePath));
        assertTrue(Files.size(filePath) > 0);
    }

}