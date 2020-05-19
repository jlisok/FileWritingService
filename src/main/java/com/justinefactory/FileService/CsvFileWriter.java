package com.justinefactory.FileService;

import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

public class CsvFileWriter<T extends ContentToCsvLine> implements ContentWriter<T> {

    private final FileData fileData;
    private static final Logger logger = LogManager.getLogger(CsvFileWriter.class);


    CsvFileWriter(FileData fp) {
        fileData = fp;
    }

    @Override
    public void writeContent(Collection<T> content) throws IOException, CouldNotWrite2FileAlreadyExists {
        logger.debug("Initializing writing content to file {}.", fileData.getFileId().toString());

        if (checkIfFileAlreadyExists(fileData.getFilePath())) {
            logger.info("Could not create file {}. File {} already exists.", fileData.getFileId().toString(), fileData.getFilePath().toString());
            throw new CouldNotWrite2FileAlreadyExists();
        }

        createNonExistingDirs(fileData.getFilePath());

        try (BufferedWriter writer = Files.newBufferedWriter(fileData.getFilePath());
             CSVWriter csvWriter = new CSVWriter(writer)
        ) {
            for (T items : content) {
                String[] newStringLine = items.varsToCsvLine();
                csvWriter.writeNext(newStringLine);
            }
            logger.info("File {} has been created and appended successfully.", fileData.getFileId().toString());
        } // catch i logger??

    }


    private void createNonExistingDirs(Path fileData) throws IOException {
        Files.createDirectories(fileData.getParent());
    }


    private boolean checkIfFileAlreadyExists(Path fileData) {
        return Files.exists(fileData);
    }

}
