package com.justinefactory.server.communication.access;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.justinefactory.sending.exceptions.AwsSecurityCredentialsException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.justinefactory.util.PathToResources.getPathToResource;

public class AwsSecurityCredentials {

    private final String ACCESS_KEY_ID;
    private final String SECRET_ACCESS_KEY;


    public AwsSecurityCredentials() throws AwsSecurityCredentialsException {
        List<String> securityInfo = getAccessKeys();
        ACCESS_KEY_ID = securityInfo.get(0);
        SECRET_ACCESS_KEY = securityInfo.get(1);
    }


    public AWSCredentials getCredentials() {
        return new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
    }


    private List<String> getAccessKeys() throws AwsSecurityCredentialsException {
        try {
            Path path = getPathToResource("AwsRootKey.csv");
            return Files.readAllLines(path);
        } catch (Throwable e) {
            throw new AwsSecurityCredentialsException(e, "Trouble while acquiring security credentials.");
        }
    }

}
