package com.justinefactory.writing.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContentStorageTest {

    @Test
    void createContentStorageWhenInstertingContentAsNull() {
        //given
        String string = null;

        //then
        assertThrows(IllegalArgumentException.class, () -> new Content<>(string));
    }

    @Test
    void createContentStorageWhenInstertingContentThatMeetsConditions() {
        //given
        String string = "Tiger";

        //when
        Content<String> contentStorage = new Content<>(string);

        //then
        assertEquals(string, contentStorage.getContent(0));
    }


    @Test
    void createContentStorageWhenInstertingListOfContentWithNullItem() {
        //given
        List<String> list = new ArrayList<>();
        list.add(null);

        //then
        assertThrows(IllegalArgumentException.class, () -> new Content<>(list));
    }

    @Test
    void createContentStorageWhenInstertingListOfContentNull() {
        //given
        List<String> list = null;

        //then
        assertThrows(IllegalArgumentException.class, () -> new Content<>(list));
    }

    @Test
    void createContentStorageWhenInstertingListOfContentThatMeetsConditions() {
        //given
        List<Integer> list = new ArrayList<>(List.of(1, 15, 32));

        //when
        Content<Integer> contentStorage = new Content<>(list);

        //then
        assertEquals(list, contentStorage.getContent());
    }


    @Test
    void createContentStorageWhenAddingNull() {
        //given
        Content<String> content = new Content<>();

        //then
        assertThrows(IllegalArgumentException.class, () -> content.addContent(null));
    }


    @Test
    void createContentStorageWhenAddingContentThatMeetsConditions() {
        //given
        Content<String> content = new Content<>();
        String string = "Tiger";

        //when
        content.addContent(string);

        //then
        assertEquals(string, content.getContent(0));
    }

}