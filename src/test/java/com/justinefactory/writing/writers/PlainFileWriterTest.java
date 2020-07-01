package com.justinefactory.writing.writers;

import com.justinefactory.domain.PathInfo;
import com.justinefactory.testutil.CreateAndDeleteFilesBeforeAfterAll;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.writers.file.writers.PlainFileWriter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class PlainFileWriterTest {

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
    void write2FileWhenWritingRandomInteger() throws Exception {
        //given
        Path filePath = dir.resolve("doc.csv");
        PathInfo file2writeData = new PathInfo(filePath);
        ContentStorage<String> readyToWriteContent = new ContentStorage<>(List.of("1","2"));

        //when
        PlainFileWriter writer = new PlainFileWriter();
        writer.writeContent(readyToWriteContent, file2writeData);

        //then
        assertTrue(Files.exists(filePath));
        assertTrue(Files.size(filePath) > 0);
    }


    @Test
    void write2FileWhenWritingJSON() throws Exception {
        //given
        Path filePath = dir.resolve("doc.json");
        PathInfo file2writeData = new PathInfo(filePath);
        ContentStorage<String> readyToWriteContent = new ContentStorage<>();
        readyToWriteContent.addContent("\"{\\n\" +\n" +
                "                \"  \\\"content\\\": {\\n\" +\n" +
                "                \"    \\\"content\\\": [\\n\" +\n" +
                "                \"      {\\n\" +\n" +
                "                \"        \\\"timeStamp\\\": 1590147349818750700,\\n\" +\n" +
                "                \"        \\\"randomInt\\\": -840762737,\\n\" +\n" +
                "                \"        \\\"randomString\\\": \\\"ChristopherRobin\\\"\\n\" +\n" +
                "                \"      }\\n\" +\n" +
                "                \"    ]\\n\" +\n" +
                "                \"  },\\n\" +\n" +
                "                \"  \\\"stats\\\": {\\n\" +\n" +
                "                \"    \\\"count\\\": 1,\\n\" +\n" +
                "                \"    \\\"distinctCount\\\": 1,\\n\" +\n" +
                "                \"    \\\"max\\\": {\\n\" +\n" +
                "                \"      \\\"timeStamp\\\": 1590147349818750700,\\n\" +\n" +
                "                \"      \\\"randomInt\\\": -840762737,\\n\" +\n" +
                "                \"      \\\"randomString\\\": \\\"ChristopherRobin\\\"\\n\" +\n" +
                "                \"    }\\n\" +\n" +
                "                \"  }\\n\" +\n" +
                "                \"}\";");

        //when
        PlainFileWriter writer = new PlainFileWriter();
        writer.writeContent(readyToWriteContent, file2writeData);

        //then
        assertTrue(Files.exists(filePath));
        assertTrue(Files.size(filePath) > 0);
    }
}