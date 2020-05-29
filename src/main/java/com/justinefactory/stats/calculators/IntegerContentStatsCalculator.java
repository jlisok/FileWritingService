package com.justinefactory.stats.calculators;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class IntegerContentStatsCalculator implements StatsCalculator<Integer> {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Stats<Integer> calculateStats(Collection<Integer> content) throws StatsCalculatingException {
        logger.debug("Calculating stats from Integer content.");
        if (content == null || content.isEmpty()) {
            logger.warn("Calculating stats from Integer content - failed. Collection {} was empty.", content);
            throw new StatsCalculatingException("Calculating stats from Integer content - failed. Collection " + content + " was empty.");
        }
        Integer count = calculateCount(content);
        Integer uniqueCount = calculateUniqueCount(content);
        Integer max = calculateMax(content);
        Stats<Integer> stats = new Stats<>(count, uniqueCount, max);
        logger.debug("Calculating stats from Integer content - success.");
        return stats;
    }


    private Integer calculateCount(Collection<Integer> content) {
        return content.size();
    }

    private Integer calculateUniqueCount(Collection<Integer> content) {
        Set<Integer> uniqueContent = new HashSet<>(content);
        return uniqueContent.size();
    }

    private Integer calculateMax(Collection<Integer> content) {
        return Collections.max(content);
    }


}
