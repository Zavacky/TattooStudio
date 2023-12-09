package com.studio.tattoostudio.DaoImpl;

import com.studio.tattoostudio.DAO.ClientDao;
import com.studio.tattoostudio.Data.Client;
import org.springframework.jdbc.core.JdbcTemplate;

public class PostgresClientDao implements ClientDao {
    private JdbcTemplate jdbcTemplate;
    @Override
    public Client getById(long id) {
        return null;
    }

    @Override
    public void updateClient(long id) {

    }
}
