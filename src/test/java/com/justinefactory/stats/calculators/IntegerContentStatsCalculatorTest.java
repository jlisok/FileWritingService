package com.justinefactory.stats.calculators;

import com.justinefactory.reading.exceptions.ContentStoringException;
import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import com.justinefactory.writing.domain.ContentStorage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegerContentStatsCalculatorTest {

    @Test
    void calculateStatsWhenContentIsNull() {
        //when
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();

        //then
        assertThrows(StatsCalculatingException.class, () -> calculator.calculateStats(null));
    }

    @Test
    void calculateStatsWhenContentEmpty() {
        //given
        ContentStorage<Integer> content = new ContentStorage<>();

        //when
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();

        //then
        assertThrows(StatsCalculatingException.class, () -> calculator.calculateStats(content));
    }


    @Test
    void calculateStatsWhenContentHas1Element() throws StatsCalculatingException, ContentStoringException {
        //given
        ContentStorage<Integer> content = new ContentStorage<>(10);
        Stats<Integer> expectedStats = new Stats<>(1, 1, 10);
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();

        //when
        Stats<Integer> stats = calculator.calculateStats(content);

        //then
        assertEquals(expectedStats, stats);
    }


    @Test
    void calculateStatsWhenContentHasMoreElementsAndAllUnique() throws StatsCalculatingException, ContentStoringException {
        //given
        ContentStorage<Integer> content = new ContentStorage<>(List.of(10, 50, 40, 20, 70, 130, 1));
        Stats<Integer> expectedStats = new Stats<>(7, 7, 130);
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();

        //when
        Stats<Integer> stats = calculator.calculateStats(content);

        //then
        assertEquals(expectedStats, stats);
    }


    @Test
    void calculateStatsWhenContentHasMoreNonUniqueElements() throws StatsCalculatingException, ContentStoringException {
        //given
        ContentStorage<Integer> content = new ContentStorage<>(List.of(10, 20, 14, 20, 20, 12, 1));
        Stats<Integer> expectedStats = new Stats<>(7, 5, 20);
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();

        //when
        Stats<Integer> stats = calculator.calculateStats(content);

        //then
        assertEquals(expectedStats, stats);
    }


}