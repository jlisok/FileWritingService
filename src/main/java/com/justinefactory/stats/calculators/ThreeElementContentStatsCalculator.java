package com.justinefactory.stats.calculators;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Collection;

import static com.justinefactory.util.DistinctByKeyPredicate.distinctByKey;

public class ThreeElementContentStatsCalculator implements StatsCalculator<ThreeElemContent, Integer> {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Stats<Integer> calculateStats(Collection<ThreeElemContent> content) throws StatsCalculatingException {
        logger.debug("Calculating stats from ThreeElementContent content.");
        if (content.isEmpty()) {
            logger.warn("Calculating stats from ThreeElementContent content - failed. Collection {} was empty.", content);
            throw new StatsCalculatingException("Calculating stats from ThreeElementContent content - failed. Inserted input Collection " + content + " is empty.");
        }
            Integer count = calculateCount(content);
            Integer uniqueCount = calculateUniqueCount(content);
            Integer max = calculateMax(content);
            logger.info("Calculating stats from ThreeElementContent content - success.");
            return new Stats<>(count, uniqueCount, max);
    }

    private Integer calculateCount(Collection<ThreeElemContent> content) {
        return content.size();
    }

    private Integer calculateUniqueCount(Collection<ThreeElemContent> content) {
        Long uniqueCount = content.stream()
                .filter(distinctByKey(ThreeElemContent::getDistinctStats))
                .count();
        return uniqueCount.intValue();
    }

    private Integer calculateMax(Collection<ThreeElemContent> content) {
        Integer max = null;
        for (ThreeElemContent item : content) {
            max = returnGreaterNumberOfTwoInsertedIntegers(max, item.getRandomInt());
        }
        return max;
    }

    private Integer returnGreaterNumberOfTwoInsertedIntegers(Integer max, Integer newNumber) {
        if (max == null) {
            return newNumber;
        }
        if (max >= newNumber) {
            return max;
        } else {
            return newNumber;
        }
    }


}
