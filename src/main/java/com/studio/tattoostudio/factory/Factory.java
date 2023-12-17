package com.studio.tattoostudio.factory;

import com.studio.tattoostudio.dao.*;
import com.studio.tattoostudio.daoImpl.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum Factory {
    INSTANCE;

    private ClientDao clientDao;
    private DateOfTattooDao dateOfTattooDao;
    private DesignDao designDao;
    private StudioDao studioDao;
    private TattooArtistDao tattooArtistDao;
    private JdbcTemplate jdbcTemplate;

    private JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
           DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost:5432/tattoo_studio");
            dataSource.setUsername("postgres");
            dataSource.setPassword("odjednapoosem");
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }

    public ClientDao getClientDao(){
        if (clientDao == null){
            clientDao = new PostgresClientDao(getJdbcTemplate());
        }
        return clientDao;
    }

    public TattooArtistDao getTattooArtistDao(){
        if (tattooArtistDao == null){
            tattooArtistDao = new PostgresTattooArtistDao(getJdbcTemplate());
        }
        return tattooArtistDao;
    }

    public StudioDao getStudioDao(){
        if (studioDao == null){
            studioDao = new PostgresStudioDao(getJdbcTemplate());
        }
        return studioDao;
    }

    public DesignDao getDesignDao(){
        if (designDao == null){
            designDao = new PostgresDesignDao(getJdbcTemplate());
        }
        return designDao;
    }

    public DateOfTattooDao getDateOfTattooDao() {
        if (dateOfTattooDao == null) {
            dateOfTattooDao = new PostgresDateOfTattooDao(getJdbcTemplate());
        }
        return dateOfTattooDao;
    }
}
