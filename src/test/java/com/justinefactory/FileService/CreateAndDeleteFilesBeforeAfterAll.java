package com.justinefactory.FileService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

class CreateAndDeleteFilesBeforeAfterAll {

    static Path createTemporaryDirectory() throws Exception {
        return Files.createTempDirectory("TEST");
    }

    static void removeAllDirs(Path dir) throws Exception {
        Files.walk(dir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
}
