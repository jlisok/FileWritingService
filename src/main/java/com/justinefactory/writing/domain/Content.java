package com.justinefactory.writing.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Content<ContentType> {

    private final List<ContentType> content;

    public Content() {
        content = new ArrayList<>();
    }


    public Content(List<ContentType> rc) throws IllegalArgumentException {
        if (rc == null)
            throw new IllegalArgumentException("Trouble while writing content " + rc + " into Storage. Content is null.");
        for (ContentType item : rc) {
            checkIfContentNull(item);
        }
        content = rc;
    }

    public Content(ContentType rc) throws IllegalArgumentException {
        checkIfContentNull(rc);
        content = new ArrayList<>();
        content.add(rc);
    }

    private void checkIfContentNull(ContentType content) throws IllegalArgumentException {
        if (content == null) {
            throw new IllegalArgumentException("Trouble while writing content " + content + " into Storage. Content is null.");
        }
    }

    public ContentType getContent(int i) {
        return content.get(i);
    }

    public int getContentSize() {
        return content.size();
    }

    public void addContent(ContentType content) throws IllegalArgumentException {
        checkIfContentNull(content);
        this.content.add(content);
    }


    public Collection<ContentType> getContent() {
        return content;
    }

    public boolean isEmpty() {
        return content.size() == 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content<ContentType> that = (Content<ContentType>) o;
        if (content.size() != that.content.size()) return false;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

}
