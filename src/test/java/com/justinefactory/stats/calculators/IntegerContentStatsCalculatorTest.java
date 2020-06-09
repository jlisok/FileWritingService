package com.justinefactory.stats.calculators;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import com.justinefactory.writing.domain.ContentStorage;
import org.junit.jupiter.api.Test;

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
    void calculateStatsWhenContentHas1Element() throws StatsCalculatingException {
        //given
        ContentStorage<Integer> content = new ContentStorage<>(10);
        content.addContent(10);
        Stats<Integer> expectedStats = new Stats<>(1, 1, 10);
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();

        //when
        Stats<Integer> stats = calculator.calculateStats(content);

        //then
        assertEquals(expectedStats, stats);
    }


    @Test
    void calculateStatsWhenContentHasMoreElementsAndAllUnique() throws StatsCalculatingException {
        //given
        ContentStorage<Integer> content = new ContentStorage<>(10);
        content.addContent(10);
        content.addContent(50);
        content.addContent(40);
        content.addContent(20);
        content.addContent(70);
        content.addContent(130);
        content.addContent(1);
        Stats<Integer> expectedStats = new Stats<>(7, 7, 130);
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();

        //when
        Stats<Integer> stats = calculator.calculateStats(content);

        //then
        assertEquals(expectedStats, stats);
    }


    @Test
    void calculateStatsWhenContentHasMoreNonUniqueElements() throws StatsCalculatingException {
        //given
        ContentStorage<Integer> content = new ContentStorage<>(10);
        content.addContent(10);
        content.addContent(20);
        content.addContent(14);
        content.addContent(20);
        content.addContent(20);
        content.addContent(12);
        content.addContent(1);
        Stats<Integer> expectedStats = new Stats<>(7, 5, 20);
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();

        //when
        Stats<Integer> stats = calculator.calculateStats(content);

        //then
        assertEquals(expectedStats, stats);
    }


}