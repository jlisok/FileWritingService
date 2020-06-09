package com.justinefactory.stats.calculators;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import com.justinefactory.writing.domain.ContentStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class ThreeElementContentStatsCalculator implements StatsCalculator<ThreeElemContent> {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ThreeElementContentComparatorByRandomInt comparator = new ThreeElementContentComparatorByRandomInt();

    @Override
    public Stats<ThreeElemContent> calculateStats(ContentStorage<ThreeElemContent> content) throws StatsCalculatingException {
        logger.debug("Calculating stats from ThreeElementContent content.");
        if (content == null || content.getContentSize() == 0) {
            logger.warn("Calculating stats from ThreeElementContent content - failed. Storage class {} is empty or does not exist.", content);
            throw new StatsCalculatingException("Calculating stats from ThreeElementContent content - failed. Inserted input storage class " + content + " is empty or does not exist.");
        }
        Integer count = calculateCount(content);
        Integer uniqueCount = calculateUniqueCount(content);
        ThreeElemContent max = calculateMax(content);
        Stats<ThreeElemContent> stats = new Stats<>(count, uniqueCount, max);
        logger.info("Calculating stats from ThreeElementContent content - success.");
        return stats;
    }

    private Integer calculateCount(ContentStorage<ThreeElemContent> content) {
        return content.getContentSize();
    }


    private Integer calculateUniqueCount(ContentStorage<ThreeElemContent> content) {
        Set<StringIntContent> uniqueContent = new HashSet<>();
        for (ThreeElemContent item : content.getAllContent()) {
            uniqueContent.add(new StringIntContent(item));
        }
        return uniqueContent.size();
    }


    private ThreeElemContent calculateMax(ContentStorage<ThreeElemContent> content) {
        return Collections.max(content.getAllContent(), comparator);
    }


    private class StringIntContent {

        private final String randomString;
        private final Integer randomInteger;

        StringIntContent(ThreeElemContent tec) {
            randomInteger = tec.getRandomInt();
            randomString = tec.getRandomString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StringIntContent that = (StringIntContent) o;
            return Objects.equals(randomString, that.randomString) &&
                    Objects.equals(randomInteger, that.randomInteger);
        }

        @Override
        public int hashCode() {
            return Objects.hash(randomInteger, randomString);
        }
    }


    private static class ThreeElementContentComparatorByRandomInt implements Comparator<ThreeElemContent> {

        public int compare(ThreeElemContent threeElemContent1, ThreeElemContent threeElemContent2) {
            return (threeElemContent2.getRandomInt() - threeElemContent1.getRandomInt());
        }
    }

}
