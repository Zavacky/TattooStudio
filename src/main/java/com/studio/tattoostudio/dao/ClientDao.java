package com.studio.tattoostudio.dao;

import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.exceptions.ClientDoesntExistException;
import com.studio.tattoostudio.exceptions.IncorrectLoginOrPasswordException;

public interface ClientDao {
    /**
     * Method to get client by login
     * @author Martin Zavacky
     * @param login
     * @return
     * @throws IncorrectLoginOrPasswordException
     */
    Client getByLogin(String login) throws IncorrectLoginOrPasswordException;

    /**
     * Method to save new client or update existing one
     * @author Martin Zavacky
     * @param client
     * @return
     * @throws ClientDoesntExistException
     */
    Client saveClient(Client client) throws ClientDoesntExistException;
}
