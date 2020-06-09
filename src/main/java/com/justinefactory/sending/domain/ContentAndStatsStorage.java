package com.justinefactory.sending.domain;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.writing.domain.ContentStorage;

import java.util.Objects;

public class ContentAndStatsStorage<Content> {

    private final ContentStorage<Content> content;
    private final Stats<Content> stats;

    public ContentAndStatsStorage(ContentStorage<Content> ct, Stats<Content> st) {
        content = ct;
        stats = st;
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
