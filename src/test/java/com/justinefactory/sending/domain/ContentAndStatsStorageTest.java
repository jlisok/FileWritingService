package com.justinefactory.sending.domain;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.writing.domain.Content;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContentAndStatsStorageTest {

    @Test
    void createContentAndStatsStorageWhenContentMeetsConditions() {
        // given
        Content<Integer> content = new Content<>(50);
        Stats<Integer> stats = new Stats<>(1, 1, 50);

        //when
        ContentAndStats<Integer> contentAndStatsStorage = new ContentAndStats<>(content, stats);

        //then
        assertEquals(content, contentAndStatsStorage.getContent());
        assertEquals(stats, contentAndStatsStorage.getStats());
    }

    @Test
    void createContentAndStatsStorageWhenContentEmpty() {
        // given
        Content<Integer> content = new Content<>();
        Stats<Integer> stats = new Stats<>(1, 1, 50);

        //then
        assertThrows(IllegalArgumentException.class, () -> new ContentAndStats<>(content, stats));
    }

    @Test
    void createContentAndStatsStorageWhenContentIsNull() {
        // given
        Stats<Integer> stats = new Stats<>(1, 1, 50);

        //then
        assertThrows(IllegalArgumentException.class, () -> new ContentAndStats<>(null, stats));
    }

    @Test
    void createContentAndStatsStorageWhenStatsAreNull() {
        // given
        Content<Integer> content = new Content<>(50);

        //then
        assertThrows(IllegalArgumentException.class, () -> new ContentAndStats<>(content, null));
    }
}