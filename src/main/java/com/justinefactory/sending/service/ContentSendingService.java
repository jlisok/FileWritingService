package com.justinefactory.sending.service;

import com.justinefactory.reading.exceptions.ContentReadingException;
import com.justinefactory.reading.service.ContentReadingService;
import com.justinefactory.sending.domain.ContentAndStats;
import com.justinefactory.stats.calculators.StatsCalculator;
import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import com.justinefactory.writing.domain.Content;
import com.justinefactory.writing.exceptions.ContentWritingException;
import com.justinefactory.writing.service.ContentWritingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class ContentSendingService<RawContent, ContentType, ReadyToSendContent> {

    private final ContentReadingService<RawContent, ContentType> contentReadingService;
    private final StatsCalculator<ContentType> statsCalculator;
    private final ContentWritingService<ContentAndStats<ContentType>, ReadyToSendContent> contentWritingService;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    public ContentSendingService(ContentReadingService<RawContent, ContentType> crs, StatsCalculator<ContentType> sc, ContentWritingService<ContentAndStats<ContentType>, ReadyToSendContent> cw) {
        logger.info("Content sending service - initialization.");
        contentReadingService = crs;
        statsCalculator = sc;
        contentWritingService = cw;
    }


    public void sendContent() throws ContentReadingException, StatsCalculatingException, ContentWritingException {
        Content<ContentType> content = contentReadingService.processContent();
        Stats<ContentType> stats = statsCalculator.calculateStats(content);
        ContentAndStats<ContentType> contentAndStats = new ContentAndStats<>(content, stats);
        contentWritingService.writeContent(contentAndStats);
        logger.info("Content sending service - success.");
    }

}
