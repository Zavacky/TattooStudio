package com.studio.tattoostudio.DAO;

import com.studio.tattoostudio.Data.Client;
import com.studio.tattoostudio.Exceptions.ClientDoesntExistException;
import com.studio.tattoostudio.Exceptions.IncorrectLoginOrPasswordException;

public interface ClientDao {
    Client getByLogin(String login) throws IncorrectLoginOrPasswordException;
    Client saveClient(Client client) throws ClientDoesntExistException;
}
