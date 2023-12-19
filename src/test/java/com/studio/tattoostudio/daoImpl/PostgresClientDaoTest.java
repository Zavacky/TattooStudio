package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.exceptions.ClientDoesntExistException;
import com.studio.tattoostudio.exceptions.IncorrectLoginOrPasswordException;
import com.studio.tattoostudio.factory.Factory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostgresClientDaoTest {

    @Test
    void getByLogin() {
        Client client = new Client(5L, "test", "testPassword", "test", "test", "testMail", "testPhone");
        try {
            assertEquals(client, Factory.INSTANCE.getClientDao().getByLogin("test"));
        } catch (IncorrectLoginOrPasswordException e) {
            fail("Failed to get client by login " + e.getMessage());
        }
    }

    @Test
    void saveClient() {
        try {
            Client client = new Client(5L, "test", "testPassword", "test", "test", "testMail", "testPhone");
            assertEquals(client, Factory.INSTANCE.getClientDao().saveClient(client));
        } catch (ClientDoesntExistException e) {
            fail("Failed to save client " + e.getMessage());
        }
    }
}