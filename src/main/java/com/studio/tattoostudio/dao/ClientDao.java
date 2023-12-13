package com.studio.tattoostudio.dao;

import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.exceptions.ClientDoesntExistException;
import com.studio.tattoostudio.exceptions.IncorrectLoginOrPasswordException;

public interface ClientDao {
    Client getByLogin(String login) throws IncorrectLoginOrPasswordException;
    Client saveClient(Client client) throws ClientDoesntExistException;
}
