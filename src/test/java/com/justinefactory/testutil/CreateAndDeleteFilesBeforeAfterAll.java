package com.justinefactory.testutil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class CreateAndDeleteFilesBeforeAfterAll {

    public static Path createTemporaryDirectory() throws Exception {
        return Files.createTempDirectory("TEST");
    }

    public static void removeAllDirs(Path dir) throws Exception {
        Files.walk(dir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
}
