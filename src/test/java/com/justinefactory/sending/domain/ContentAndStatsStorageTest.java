package com.justinefactory.sending.domain;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.writing.domain.ContentStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContentAndStatsStorageTest {

    @Test
    void createContentAndStatsStorageWhenContentMeetsConditions() {
        // given
        ContentStorage<Integer> content = new ContentStorage<>(50);
        Stats<Integer> stats = new Stats<>(1, 1, 50);

        //when
        ContentAndStatsStorage<Integer> contentAndStatsStorage = new ContentAndStatsStorage<>(content, stats);

        //then
        assertEquals(content, contentAndStatsStorage.getAllContent());
        assertEquals(stats, contentAndStatsStorage.getStats());
    }

    @Test
    void createContentAndStatsStorageWhenContentEmpty() {
        // given
        ContentStorage<Integer> content = new ContentStorage<>();
        Stats<Integer> stats = new Stats<>(1, 1, 50);

        //then
        assertThrows(IllegalArgumentException.class, () -> new ContentAndStatsStorage<>(content, stats));
    }

    @Test
    void createContentAndStatsStorageWhenContentIsNull() {
        // given
        Stats<Integer> stats = new Stats<>(1, 1, 50);

        //then
        assertThrows(IllegalArgumentException.class, () -> new ContentAndStatsStorage<>(null, stats));
    }

    @Test
    void createContentAndStatsStorageWhenStatsAreNull() {
        // given
        ContentStorage<Integer> content = new ContentStorage<>(50);

        //then
        assertThrows(IllegalArgumentException.class, () -> new ContentAndStatsStorage<>(content, null));
    }
}