package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.factory.Factory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostgresDesignDaoTest {
    @Test
    void getAllByArtist() {
        assertEquals(7, Factory.INSTANCE.getDesignDao().getAllByArtist("test").size());
    }

    @Test
    void getById() {
        assertEquals("panther tattoo", Factory.INSTANCE.getDesignDao().getById(7L).getDescription());
    }

    @Test
    void save() {

    }

    @Test
    void delete() {

    }
}