package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.data.DateOfTattoo;
import com.studio.tattoostudio.data.Design;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.factory.Factory;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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
        try{
        Client client = Factory.INSTANCE.getClientDao().getByLogin("test");
        TattooArtist tattooArtist = Factory.INSTANCE.getTattooArtistDao().getByLogin("test");
        Design design = Factory.INSTANCE.getDesignDao().getById(1L);
        DateOfTattoo dateOfTattoo = new DateOfTattoo(50L, client, tattooArtist, design, LocalDateTime.now(), "test");
        assertEquals(dateOfTattoo, Factory.INSTANCE.getDateOfTattooDao().save(dateOfTattoo));
        }catch (Exception e){
            fail("Failed to save dateOfTattoo " + e.getMessage());
        }
    }

    @Test
    void delete() {
        try {
            Factory.INSTANCE.getDateOfTattooDao().delete(2L);
            assertFalse(Factory.INSTANCE.getDateOfTattooDao().getAllByArtist("test").contains(2L));
        }catch (Exception e){
            fail("Failed to delete dateOfTattoo " + e.getMessage());
        }
    }
}