package com.justinefactory.stats.calculators;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ThreeElementContentStatsCalculatorTest {

    @Test
    void calculateStatsWhenCollectionIsEmpty() {
        //given
        List<ThreeElemContent> content = new ArrayList<>();

        //when
        ThreeElementContentStatsCalculator calculator = new ThreeElementContentStatsCalculator();

        //then
        assertThrows(StatsCalculatingException.class, () -> calculator.calculateStats(content));
    }

    @Test
    void calculateStatsWhen1Element() throws StatsCalculatingException {
        //given
        List<ThreeElemContent> content = new ArrayList<>();
        content.add(new ThreeElemContent(Long.parseLong("1590147349818750700"), -840762737, "ChristopherRobin"));
        Stats<Integer> expectedStats = new Stats<>(1,1,-840762737);

        //when
        ThreeElementContentStatsCalculator calculator = new ThreeElementContentStatsCalculator();

        //then
        calculator.calculateStats(content);
        assertEquals(calculator.calculateStats(content), expectedStats);
    }

    @Test
    void calculateStatsWhen2DistinctElements() throws StatsCalculatingException {
        //given
        List<ThreeElemContent> content = new ArrayList<>();
        content.add(new ThreeElemContent(Long.parseLong("1590147349818750700"), -840762737, "ChristopherRobin"));
        content.add(new ThreeElemContent(Long.parseLong("1590147349818750700"), 1345882450, "Owl"));
        Stats<Integer> expectedStats = new Stats<>(2,2,1345882450);

        //when
        ThreeElementContentStatsCalculator calculator = new ThreeElementContentStatsCalculator();

        //then
        calculator.calculateStats(content);
        assertEquals(calculator.calculateStats(content), expectedStats);
    }

    @Test
    void calculateStatsWhen3ElementsBut2Distinct() throws StatsCalculatingException {
        //given
        List<ThreeElemContent> content = new ArrayList<>();
        content.add(new ThreeElemContent(Long.parseLong("1590147349818750700"), -840762737, "ChristopherRobin"));
        content.add(new ThreeElemContent(Long.parseLong("1590147349818750700"), 1345882450, "Owl"));
        content.add(new ThreeElemContent(Long.parseLong("1590147349818750800"), 1345882450, "Owl"));

        Stats<Integer> expectedStats = new Stats<>(3,2,1345882450);

        //when
        ThreeElementContentStatsCalculator calculator = new ThreeElementContentStatsCalculator();

        //then
        calculator.calculateStats(content);
        assertEquals(calculator.calculateStats(content), expectedStats);
    }

    @Test
    void calculateStatsWhen2ElementsBut1Distinct_MakingSureTimeStampIsExcludedFromDefiningDistinct() throws StatsCalculatingException {
        //given
        List<ThreeElemContent> content = new ArrayList<>();
        content.add(new ThreeElemContent(Long.parseLong("1590147349818750700"), 1434010513, "Owl"));
        content.add(new ThreeElemContent(Long.parseLong("1590147349818750888"), 1434010513, "Owl"));

        Stats<Integer> expectedStats = new Stats<>(2,1,1434010513);

        //when
        ThreeElementContentStatsCalculator calculator = new ThreeElementContentStatsCalculator();

        //then
        calculator.calculateStats(content);
        assertEquals(calculator.calculateStats(content), expectedStats);
    }

    @Test
    void calculateStatsWhen2DistinctElements_MakingSureACombinationOfStringAndRandomIntIsTakenToDefineDistinct() throws StatsCalculatingException {
        //given
        List<ThreeElemContent> content = new ArrayList<>();
        content.add(new ThreeElemContent(Long.parseLong("1590147349818750700"), -840762737, "ChristopherRobin"));
        content.add(new ThreeElemContent(Long.parseLong("1590147349818750800"), 1434010513, "ChristopherRobin"));

        Stats<Integer> expectedStats = new Stats<>(2,2,1434010513);

        //when
        ThreeElementContentStatsCalculator calculator = new ThreeElementContentStatsCalculator();

        //then
        calculator.calculateStats(content);
        assertEquals(calculator.calculateStats(content), expectedStats);
    }

}

