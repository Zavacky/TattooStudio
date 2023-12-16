package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.data.DateOfTattoo;
import com.studio.tattoostudio.factory.Factory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostgresDateOfTattooDaoTest {

    @Test
    void getAllByArtist() {
       List<DateOfTattoo> result = Factory.INSTANCE.getDateOfTattooDao().getAllByArtist("test");
       assertEquals(0, result.size());
    }

    @Test
    void getAllByClient() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
}