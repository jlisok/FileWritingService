package com.justinefactory.sending.service;

import com.justinefactory.reading.exceptions.ContentReadingException;
import com.justinefactory.reading.service.ContentReadingService;
import com.justinefactory.sending.domain.ContentAndStatsStorage;
import com.justinefactory.stats.calculators.StatsCalculator;
import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.writers.ContentWritingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class ContentSendingService<RawContent, Content, ReadyToSendContent> {

    private final ContentReadingService<RawContent, Content> contentReadingService;
    private final StatsCalculator<Content> statsCalculator;
    private final ContentWritingService<ContentAndStatsStorage<Content>, ReadyToSendContent> contentWritingService;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    public ContentSendingService(ContentReadingService<RawContent, Content> crs, StatsCalculator<Content> sc, ContentWritingService<ContentAndStatsStorage<Content>, ReadyToSendContent> cw) {
        logger.info("Content sending service - initialization.");
        contentReadingService = crs;
        statsCalculator = sc;
        contentWritingService = cw;
    }


    public void sendContent() throws ContentReadingException, StatsCalculatingException, ContentWritingException {
        ContentStorage<Content> content = contentReadingService.processContent();
        Stats<Content> stats = statsCalculator.calculateStats(content);
        ContentAndStatsStorage<Content> contentAndStats = new ContentAndStatsStorage<>(content, stats);
        contentWritingService.writeContent(contentAndStats);
        logger.info("Content sending service - success.");
    }

}
