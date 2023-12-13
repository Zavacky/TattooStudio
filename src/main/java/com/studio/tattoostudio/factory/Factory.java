package com.studio.tattoostudio.Factory;

import com.studio.tattoostudio.DAO.*;
import com.studio.tattoostudio.DaoImpl.PostgresClientDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public enum Factory {
    INSTANCE;

    private ClientDao clientDao;
    private DateOfTattooDao dateOfTattooDao;
    private DesignDao designDao;
    private StudioDao studioDao;
    private TattooArtistDao tattooArtistDao;
    private JdbcTemplate jdbcTemplate;

    private JdbcTemplate getJdbcTemplate(){
        if (jdbcTemplate == null){
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost:5432/your_database");
            dataSource.setUsername("postgres");
            dataSource.setPassword("odjednapoosem");
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }

    public ClientDao getClientDao(){
        if (clientDao == null){
            clientDao = new PostgresClientDao(jdbcTemplate);
        }
        return clientDao;
    }
}
