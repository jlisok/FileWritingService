package com.justinefactory.writing.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

interface ContentReader<RawContent> {

    public List<RawContent> readContent() throws SourceFileIsEmptyException, IOException, ReadingContentFromFileException;

}
