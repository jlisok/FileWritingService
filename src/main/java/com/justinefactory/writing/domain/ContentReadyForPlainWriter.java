package com.justinefactory.writing.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ContentReadyForPlainWriter {

    private final List<String> content;

    public ContentReadyForPlainWriter() {
        content = new ArrayList<>();
    }

    public ContentReadyForPlainWriter(List<String> rc) throws IllegalArgumentException {
        if (rc == null)
            throw new IllegalArgumentException("Trouble while writing content " + rc + " into Storage. Content is null.");
        for (String item : rc) {
            checkIfContentNull(item);
        }
        content = rc;
    }


    public String getContent(int i) {
        return content.get(i);
    }


    public void addContent(String content) throws IllegalArgumentException {
        checkIfContentNull(content);
        this.content.add(content);
    }

    private void checkIfContentNull(String content) throws IllegalArgumentException {
        if (content == null) {
            throw new IllegalArgumentException("Trouble while writing content " + content + " into Storage. Content is null.");
        }
    }

    public Collection<String> getContent() {
        return content;
    }

    public boolean isEmpty() {
        return content.size() == 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentReadyForPlainWriter that = (ContentReadyForPlainWriter) o;
        if (content.size() != that.content.size()) return false;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

}
