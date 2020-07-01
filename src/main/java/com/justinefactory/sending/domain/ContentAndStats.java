package com.justinefactory.sending.domain;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.writing.domain.Content;

import java.util.Objects;

public class ContentAndStats<ContentType> {


    private final Content<ContentType> content;
    private final Stats<ContentType> stats;

    public ContentAndStats(Content<ContentType> ct, Stats<ContentType> st) throws IllegalArgumentException {
        checkIfIsNullOrEmpty(ct, st);
        content = ct;
        stats = st;
    }


    private void checkIfIsNullOrEmpty(Content<ContentType> content, Stats<ContentType> stats) throws IllegalArgumentException {
        if (content == null || content.getContent() == null || content.getContent().isEmpty() || stats == null) {
            throw new IllegalArgumentException("Trouble while writing content: " + content + " and stats: " + stats + " to ContentStorage. Content or stats are empty or null.");
        }
    }


    public Content<ContentType> getContent() {
        return content;
    }

    public Stats<ContentType> getStats() {
        return stats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentAndStats<ContentType> that = (ContentAndStats<ContentType>) o;
        return Objects.equals(content, that.content) &&
                Objects.equals(stats, that.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, stats);
    }


}
