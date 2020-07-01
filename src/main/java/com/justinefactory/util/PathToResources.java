package com.justinefactory.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathToResources {

    public static Path getPathToResource(String name) throws Exception {
        if (name.startsWith("/")) {
            return getPath(name);
        } else {
            return getPath("/" + name);
        }
    }

    private static Path getPath(String name) throws Exception {
        return Paths.get(PathToResources.class.getResource(name).toURI());
    }
}
