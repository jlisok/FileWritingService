package com.justinefactory.domain;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class PathInfo implements WritingInfo {

    private final Path path;
    private final URI uri;
    private final UUID id;

    public PathInfo(Path filePath) {
        path = filePath;
        uri = path.toUri();
        id = UUID.randomUUID();
    }

    public PathInfo(URI u) {
        uri = u;
        path = Paths.get(uri);
        id = UUID.randomUUID();
    }

    @Override
    public URI getURI() {
        return uri;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public Path getPath() {
        return path;
    }
}
