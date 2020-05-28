package com.justinefactory.stats.calculators;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class ThreeElementContentStatsCalculator implements StatsCalculator<ThreeElemContent> {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Stats<ThreeElemContent> calculateStats(Collection<ThreeElemContent> content) throws StatsCalculatingException {
        logger.debug("Calculating stats from ThreeElementContent content.");
        if (content == null || content.isEmpty()) {
            logger.warn("Calculating stats from ThreeElementContent content - failed. Collection {} was empty.", content);
            throw new StatsCalculatingException("Calculating stats from ThreeElementContent content - failed. Inserted input Collection " + content + " is empty.");
        }
        Integer count = calculateCount(content);
        Integer uniqueCount = calculateUniqueCount(content);
        ThreeElemContent max = calculateMax(content);
        Stats<ThreeElemContent> stats = new Stats<>(count, uniqueCount, max);
        logger.info("Calculating stats from ThreeElementContent content - success.");
        return stats;
    }

    private Integer calculateCount(Collection<ThreeElemContent> content) {
        return content.size();
    }


    private Integer calculateUniqueCount(Collection<ThreeElemContent> content) {
        List<StringIntContent> stringIntContent = new ArrayList<>(content.size());
        for (ThreeElemContent item : content) {
            stringIntContent.add(new StringIntContent(item));
        }
        Set<StringIntContent> uniqueContent = new HashSet<>(stringIntContent);
        return uniqueContent.size();
    }


    private ThreeElemContent calculateMax(Collection<ThreeElemContent> content) {
        return Collections.max(content, new ThreeElementContentComparatorByRandomInt());
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


    private class ThreeElementContentComparatorByRandomInt implements Comparator<ThreeElemContent> {

        public int compare(ThreeElemContent tec1, ThreeElemContent tec2) {
            ThreeElemContent threeElemContent1 = tec1;
            ThreeElemContent threeElemContent2 = tec2;

            if (threeElemContent1.getRandomInt() == threeElemContent2.getRandomInt())
                return 0;
            else if (threeElemContent1.getRandomInt() > threeElemContent2.getRandomInt())
                return 1;
            else
                return -1;
        }
    }

}
