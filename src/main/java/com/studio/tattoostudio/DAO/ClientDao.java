package com.studio.tattoostudio.DAO;

import com.studio.tattoostudio.Data.Client;
import com.studio.tattoostudio.Exceptions.ClientDoesntExistException;
import com.studio.tattoostudio.Exceptions.IncorrectLoginOrPasswordException;

public interface ClientDao {
    public Client getByLogin(String login) throws IncorrectLoginOrPasswordException;
    public Client saveClient(Client client) throws ClientDoesntExistException;
}
