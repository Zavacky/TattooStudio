package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.dao.StudioDao;
import com.studio.tattoostudio.data.Studio;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//TODO implement methods
public class PostgresStudioDao implements StudioDao {
    private JdbcTemplate jdbcTemplate;

    public PostgresStudioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Studio> getAll() {
        return null;
    }
}
