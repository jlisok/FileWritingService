package com.justinefactory.server.communication.distribution.service;

import com.amazonaws.services.s3.AmazonS3;
import com.justinefactory.domain.AwsInfo;
import com.justinefactory.reading.exceptions.ContentReadingException;
import com.justinefactory.sending.service.ContentSendingService;
import com.justinefactory.server.communication.distribution.ServerObjectDownloadByUrlCreator;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.exceptions.ContentWritingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.time.Duration;

public class ServerObjectsManagementService<RawContent, Content> {

    ContentSendingService<RawContent, Content, ContentStorage<String>> sendingService;
    ServerObjectDownloadByUrlCreator urlCreator;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public ServerObjectsManagementService(ContentSendingService<RawContent, Content, ContentStorage<String>> sendingService, ServerObjectDownloadByUrlCreator urlCreator) {
        logger.debug("Creating, sending and management of server objects - initialization");
        this.sendingService = sendingService;
        this.urlCreator = urlCreator;
    }

    public void manageContent(AmazonS3 client, AwsInfo info, Duration duration) throws ContentWritingException, StatsCalculatingException, ContentReadingException {
        sendingService.sendContent();
        urlCreator.createAccessWithPresignedUrl(client, info, duration);
        logger.debug("Creating, sending and management of server objects - success");
    }
}
