package com.justinefactory.sending.domain;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.writing.domain.ContentStorage;

import java.util.Objects;

public class ContentAndStatsStorage<Content> {

    private final ContentStorage<Content> content;
    private final Stats<Content> stats;

    public ContentAndStatsStorage(ContentStorage<Content> ct, Stats<Content> st) throws IllegalArgumentException {
        checkIfIsNullOrEmpty(ct, st);
        content = ct;
        stats = st;
    }

    /*
    public ContentAndStatsStorage(){
        content = null;
        stats = null;
    }

     */


    private void checkIfIsNullOrEmpty(ContentStorage<Content> content, Stats<Content> stats) throws IllegalArgumentException {
        if (content == null || content.getContent() == null || content.getContent().isEmpty() || stats == null) {
            throw new IllegalArgumentException("Trouble while writing content: " + content + " and stats: " + stats + " to ContentStorage. Content or stats are empty or null.");
        }
    }


    public ContentStorage<Content> getContent() {
        return content;
    }

    public Stats<Content> getStats() {
        return stats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentAndStatsStorage<Content> that = (ContentAndStatsStorage<Content>) o;
        return Objects.equals(content, that.content) &&
                Objects.equals(stats, that.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, stats);
    }


}
