package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.factory.Factory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostgresStudioDaoTest {

    @Test
    void getAll() {
        assertEquals(2, Factory.INSTANCE.getStudioDao().getAll().size());
    }
}