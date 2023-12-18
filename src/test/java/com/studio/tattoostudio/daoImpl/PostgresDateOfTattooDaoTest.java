package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.data.DateOfTattoo;
import com.studio.tattoostudio.data.Design;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.factory.Factory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostgresDateOfTattooDaoTest {

    @Test
    void getAllByArtist() {
       List<DateOfTattoo> result = Factory.INSTANCE.getDateOfTattooDao().getAllByArtist("test");
       assertEquals(5, result.size());
    }

    @Test
    void getAllByClient() {
        List<DateOfTattoo> result = Factory.INSTANCE.getDateOfTattooDao().getAllByClient("test");
        assertEquals(5, result.size());
    }

    @Test
    void save() {
      /* DateOfTattoo dateOfTattoo = new DateOfTattoo(1, new Client(1L, "test", "test", "testMail", "testPhone"),
               new TattooArtist(1L, "test", "test", "test", "test", "test"),
               new Design(1L, ), "test", "test"), ;*/
    }

    @Test
    void delete() {
    }
}