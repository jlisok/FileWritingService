package com.justinefactory.server.communication.distribution;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.justinefactory.domain.AwsInfo;
import com.justinefactory.util.TimeCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class ServerObjectDownloadByUrlCreator {

    private final TimeCalculator timeCalculator;
    private final PresignedUrlRequest urlRequest;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public ServerObjectDownloadByUrlCreator(TimeCalculator timeCalculator, PresignedUrlRequest urlRequest) {
        this.timeCalculator = timeCalculator;
        this.urlRequest = urlRequest;

    }


    public URL createAccessWithPresignedUrl(AmazonS3 client, AwsInfo info, Duration duration) {
        try {
            Date expiration = timeCalculator.calculateExpirationTime(Instant.now(), duration);
            URL url = client.generatePresignedUrl(urlRequest.generateRequest(info, expiration));
            logger.debug("Creating URL {} to the object {} - success", url, info.getURI());
            return url;
        } catch (AmazonServiceException e) {
            logger.warn("Trouble while creating URL to the object {} due to an error in the request.", info.getURI(), e);
            throw new AmazonServiceException("Trouble while creating URL to the object " + info.getURI() + " due to an error in the request.", e);
        } catch (SdkClientException e) {
            logger.warn("Trouble while creating URL to the object {} due to an error in service connection.", info.getURI(), e);
            throw new SdkClientException("Trouble while creating URL to the object " + info.getURI() + " due to an error in service connection.", e);
        }
    }
}

