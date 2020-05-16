package com.justinefactory.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

class RandomStringGeneratorFromFile implements ContentGenerator<TwoElemContent> {

    private Random newNumber;
    private final Path stringFilePath;

    RandomStringGeneratorFromFile(Random newNumber, Path pathToFileWithRandomStrings) {
        this.newNumber = newNumber;
        this.stringFilePath = pathToFileWithRandomStrings;
    }


    @Override
    public ArrayList<TwoElemContent> generateContent(int nLines) throws IOException {

        ArrayList<String> stringList = readStringsFromFile();
        ArrayList<TwoElemContent> randomContent = new ArrayList<>(nLines);


        for (int i = 0; i < nLines; i++) {
            int nString = newNumber.nextInt(stringList.size());

            // simulating a "long"-running operation
            try {
                Thread.sleep(0, new Random().nextInt(999));
            } catch (Exception e) {
            }

            Instant now = Instant.now();
            Long timeStamp = recalculateInstantToNanoSeconds(now);

            TwoElemContent generatedData = new TwoElemContent(timeStamp, stringList.get(nString));
            randomContent.add(generatedData);
        }

        return randomContent;
    }


    private ArrayList<String> readStringsFromFile() throws IOException {

        ArrayList<String> list = (ArrayList<String>) Files.readAllLines(stringFilePath);
        return list;

    }

    private Long recalculateInstantToNanoSeconds(Instant tstmp) {

        Long timeStamp = TimeUnit.SECONDS.toNanos(tstmp.getEpochSecond()) + tstmp.getNano();
        return timeStamp;

    }


}
