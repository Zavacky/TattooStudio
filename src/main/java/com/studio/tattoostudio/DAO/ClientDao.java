package com.studio.tattoostudio.DAO;

import com.studio.tattoostudio.Data.Client;

public interface ClientDao {
    public Client getById(long id);
    public void updateClient(long id);
}
