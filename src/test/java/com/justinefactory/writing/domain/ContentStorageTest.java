package com.justinefactory.writing.domain;

import com.justinefactory.reading.exceptions.ContentStoringException;
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
        assertThrows(ContentStoringException.class, () -> new ContentStorage<>(string));
    }

    @Test
    void createContentStorageWhenInstertingContentThatMeetsConditions() throws ContentStoringException {
        //given
        String string = "Tiger";

        //when
        ContentStorage<String> contentStorage = new ContentStorage<>(string);

        //then
        assertEquals(string, contentStorage.getContent(0));
    }


    @Test
    void createContentStorageWhenInstertingListOfContentAsNull() {
        //given
        List<String> list = new ArrayList<>();
        list.add(null);

        //then
        assertThrows(ContentStoringException.class, () -> new ContentStorage<>(list));
    }

    @Test
    void createContentStorageWhenInstertingListOfContentThatMeetsConditions() throws ContentStoringException {
        //given
        List<Integer> list = new ArrayList<>(List.of(1, 15, 32));

        //when
        ContentStorage<Integer> contentStorage = new ContentStorage<>(list);

        //then
        assertEquals(list, contentStorage.getAllContent());
    }


    @Test
    void createContentStorageWhenAddingNull() {
        //given
        ContentStorage<String> content = new ContentStorage<>();

        //then
        assertThrows(ContentStoringException.class, () -> content.addContent(null));
    }


    @Test
    void createContentStorageWhenAddingContentThatMeetsConditions() throws ContentStoringException {
        //given
        ContentStorage<String> content = new ContentStorage<>();
        String string = "Tiger";

        //when
        content.addContent(string);

        //then
        assertEquals(string, content.getContent(0));
    }

}