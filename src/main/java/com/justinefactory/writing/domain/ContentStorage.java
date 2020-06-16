package com.justinefactory.writing.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ContentStorage<Content> {

    private final List<Content> content;

    public ContentStorage() {
        content = new ArrayList<>();
    }


    public ContentStorage(List<Content> rc) {
        if (rc == null) throw new IllegalArgumentException("Trouble while writing content " + rc + " into Storage. Content is null.");
        for (Content item : rc) {
            checkIfContentNull(item);
        }
        content = rc;
    }

    public ContentStorage(Content rc) {
        checkIfContentNull(rc);
        content = new ArrayList<>();
        content.add(rc);
    }

    private void checkIfContentNull(Content content) throws IllegalArgumentException {
        if (content == null) {
            throw new IllegalArgumentException("Trouble while writing content " + content + " into Storage. Content is null.");
        }
    }

    public Content getContent(int i) {
        return content.get(i);
    }

    public int getContentSize() {
        return content.size();
    }

    public void addContent(Content content) throws IllegalArgumentException {
        checkIfContentNull(content);
        this.content.add(content);
    }

    public Collection<Content> getAllContent() {
        return content;
    }

    public boolean isEmpty() {
        return content.size() == 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentStorage<Content> that = (ContentStorage<Content>) o;
        if (content.size() != that.content.size()) return false;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

}
