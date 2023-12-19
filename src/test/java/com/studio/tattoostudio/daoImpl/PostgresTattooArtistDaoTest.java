package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.exceptions.TattooArtistDoesntExistException;
import com.studio.tattoostudio.factory.Factory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostgresTattooArtistDaoTest {

    @Test
    void getByLogin() {
        try {
            assertEquals("testPassword", Factory.INSTANCE.getTattooArtistDao().getByLogin("test").getPassword());
        } catch (TattooArtistDoesntExistException e) {
            fail("Tattoo artist doesn't exist");
        }
    }

    @Test
    void getAllByStudio() {
        assertEquals(5, Factory.INSTANCE.getTattooArtistDao().getAllByStudio(1L).size());
    }
}