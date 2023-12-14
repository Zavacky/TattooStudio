package com.studio.tattoostudio.factory;

import com.studio.tattoostudio.dao.*;
import com.studio.tattoostudio.daoImpl.PostgresClientDao;
import com.studio.tattoostudio.daoImpl.PostgresDesignDao;
import com.studio.tattoostudio.daoImpl.PostgresStudioDao;
import com.studio.tattoostudio.daoImpl.PostgresTattooArtistDao;
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

    public TattooArtistDao getTattooArtistDao(){
        if (tattooArtistDao == null){
            tattooArtistDao = new PostgresTattooArtistDao(jdbcTemplate);
        }
        return tattooArtistDao;
    }

    public StudioDao getStudioDao(){
        if (studioDao == null){
            studioDao = new PostgresStudioDao(jdbcTemplate);
        }
        return studioDao;
    }

    public DesignDao getDesignDao(){
        if (designDao == null){
            designDao = new PostgresDesignDao(jdbcTemplate);
        }
        return designDao;
    }
}
