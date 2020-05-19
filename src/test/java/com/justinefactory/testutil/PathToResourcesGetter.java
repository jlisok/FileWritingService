package com.justinefactory.testutil;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathToResourcesGetter {

    public static Path getPath(String name) throws Exception {
        if (name.startsWith("/")) {
            return getPath2(name);
        } else {
            return getPath2("/" + name);
        }
    }

    private static Path getPath2(String name) throws Exception {
        return Paths.get(PathToResourcesGetter.class.getResource(name).toURI());
    }
}
