package com.justinefactory.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.util.UUID;

public class PathData {

    private final Path filePath;
    private final UUID fileId;
    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public PathData(Path filePath) {
        this.filePath = filePath;
        this.fileId = UUID.randomUUID();
        logger.debug("New file {} has been initialized with path {}", fileId, filePath);
    }

    public Path getFilePath() {
        return filePath;
    }

    public UUID getFileId() {
        return fileId;
    }
}
