package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.dao.DesignDao;
import com.studio.tattoostudio.data.Design;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//TODO implement methods
public class PostgresDesignDao implements DesignDao {

    private JdbcTemplate jdbcTemplate;

    public PostgresDesignDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Design> getAllByStudio(Long studioId) {
        return null;
    }

    @Override
    public List<Design> getAllByArtist(Long artistId) {
        return null;
    }

    @Override
    public Design getById(Long id) {
        return null;
    }

    @Override
    public Design save(Design design) {
        return null;
    }
}
