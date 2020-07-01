package com.justinefactory.stats.calculators;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import com.justinefactory.writing.domain.Content;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class IntegerContentStatsCalculator implements StatsCalculator<Integer> {

    private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Stats<Integer> calculateStats(Content<Integer> content) throws StatsCalculatingException {
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


    private Integer calculateCount(Content<Integer> content) {
        return content.getContentSize();
    }

    private Integer calculateUniqueCount(Content<Integer> content) {
        Set<Integer> uniqueContent = new HashSet<>(content.getContent());
        return uniqueContent.size();
    }

    private Integer calculateMax(Content<Integer> content) {
        return Collections.max(content.getContent());
    }


}
