package com.justinefactory.testutil;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathToResourcesGetter {

    public static Path getPathToResource(String name) throws Exception {
        if (name.startsWith("/")) {
            return getPath(name);
        } else {
            return getPath("/" + name);
        }
    }

    private static Path getPath(String name) throws Exception {
        return Paths.get(PathToResourcesGetter.class.getResource(name).toURI());
    }
}
