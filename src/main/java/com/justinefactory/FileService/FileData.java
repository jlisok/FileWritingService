package com.justinefactory.FileService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.UUID;

class FileData {

    private final Path filePath;
    private final UUID fileId;
    private static final Logger logger = LogManager.getLogger(FileData.class);

    FileData(Path filePath) {
        this.filePath = filePath;
        this.fileId = UUID.randomUUID();
        logger.debug("New file {} has been initialized with path {}", fileId.toString(), filePath.toString());
    }

    Path getFilePath() {
        return filePath;
    }

    UUID getFileId() {
        return fileId;
    }
}
