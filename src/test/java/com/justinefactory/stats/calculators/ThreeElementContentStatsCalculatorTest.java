package com.justinefactory.stats.calculators;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.reading.exceptions.ContentStoringException;
import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import com.justinefactory.writing.domain.ContentStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ThreeElementContentStatsCalculatorTest {

    @Test
    void calculateStatsWhenCollectionIsNull() {
        //when
        ThreeElementContentStatsCalculator calculator = new ThreeElementContentStatsCalculator();

        //then
        assertThrows(StatsCalculatingException.class, () -> calculator.calculateStats(null));
    }

    @Test
    void calculateStatsWhenCollectionIsEmpty() {
        //given
        ContentStorage<ThreeElemContent> content = new ContentStorage<>();

        //when
        ThreeElementContentStatsCalculator calculator = new ThreeElementContentStatsCalculator();

        //then
        assertThrows(StatsCalculatingException.class, () -> calculator.calculateStats(content));
    }

    @Test
    void calculateStatsWhen1Element() throws StatsCalculatingException, ContentStoringException {
        //given
        ThreeElemContent maxThreeElemContent = new ThreeElemContent(1590147349818750700L, -840762737, "ChristopherRobin");
        ContentStorage<ThreeElemContent> content = new ContentStorage<>();
        content.addContent(maxThreeElemContent);
        Stats<ThreeElemContent> expectedStats = new Stats<>(1, 1, maxThreeElemContent);

        //when
        ThreeElementContentStatsCalculator calculator = new ThreeElementContentStatsCalculator();

        //then
        calculator.calculateStats(content);
        assertEquals(expectedStats, calculator.calculateStats(content));
    }

    @Test
    void calculateStatsWhen2DistinctElements() throws StatsCalculatingException, ContentStoringException {
        //given
        ThreeElemContent maxThreeElemContent = new ThreeElemContent(1590147349818750700L, 1345882450, "Owl");
        ContentStorage<ThreeElemContent> content = new ContentStorage<>();
        content.addContent(new ThreeElemContent(1590147349818750700L, -840762737, "ChristopherRobin"));
        content.addContent(maxThreeElemContent);
        Stats<ThreeElemContent> expectedStats = new Stats<>(2, 2, maxThreeElemContent);

        //when
        ThreeElementContentStatsCalculator calculator = new ThreeElementContentStatsCalculator();

        //then
        calculator.calculateStats(content);
        assertEquals(expectedStats, calculator.calculateStats(content));
    }

    @Test
    void calculateStatsWhen3ElementsBut2Distinct() throws StatsCalculatingException, ContentStoringException {
        //given
        ThreeElemContent maxThreeElemContent = new ThreeElemContent(1590147349818750700L, 1345882450, "Owl");
        ContentStorage<ThreeElemContent> content = new ContentStorage<>();
        content.addContent(new ThreeElemContent(1590147349818750700L, -840762737, "ChristopherRobin"));
        content.addContent(maxThreeElemContent);
        content.addContent(maxThreeElemContent);
        Stats<ThreeElemContent> expectedStats = new Stats<>(3, 2, maxThreeElemContent);

        //when
        ThreeElementContentStatsCalculator calculator = new ThreeElementContentStatsCalculator();

        //then
        calculator.calculateStats(content);
        assertEquals(expectedStats, calculator.calculateStats(content));
    }

    @Test
    void calculateStatsWhen2ElementsBut1Distinct_MakingSureTimeStampIsExcludedFromDefiningDistinct() throws StatsCalculatingException, ContentStoringException {
        //given
        ThreeElemContent maxThreeElemContent = new ThreeElemContent(1590147349818750700L, 1434010513, "Owl");
        ContentStorage<ThreeElemContent> content = new ContentStorage<>();
        content.addContent(maxThreeElemContent);
        content.addContent(new ThreeElemContent(1590147349818750888L, 1434010513, "Owl"));

        Stats<ThreeElemContent> expectedStats = new Stats<>(2, 1, maxThreeElemContent);

        //when
        ThreeElementContentStatsCalculator calculator = new ThreeElementContentStatsCalculator();

        //then
        calculator.calculateStats(content);
        assertEquals(expectedStats, calculator.calculateStats(content));
    }

    @Test
    void calculateStatsWhen2DistinctElements_MakingSureACombinationOfStringAndRandomIntIsTakenToDefineDistinct() throws StatsCalculatingException, ContentStoringException {
        //given
        ThreeElemContent maxThreeElementContent = new ThreeElemContent(1590147349818750800L, 1434010513, "ChristopherRobin");
        ContentStorage<ThreeElemContent> content = new ContentStorage<>();
        content.addContent(new ThreeElemContent(1590147349818750700L, -840762737, "ChristopherRobin"));
        content.addContent(maxThreeElementContent);

        Stats<ThreeElemContent> expectedStats = new Stats<>(2, 2, maxThreeElementContent);

        //when
        ThreeElementContentStatsCalculator calculator = new ThreeElementContentStatsCalculator();

        //then
        calculator.calculateStats(content);
        assertEquals(expectedStats, calculator.calculateStats(content));
    }

}

