package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.dao.DateOfTattooDao;
import com.studio.tattoostudio.data.DateOfTattoo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//TODO implement methods
public class PostgresDateOfTattooDao implements DateOfTattooDao {

    private JdbcTemplate jdbcTemplate;

    public PostgresDateOfTattooDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DateOfTattoo> getAllByArtist(String artistLogin) {
        
        return null;
    }

    @Override
    public List<DateOfTattoo> getAllByClient(String clientLogin) {

        return null;
    }

    @Override
    public DateOfTattoo save(DateOfTattoo dateOfTattoo) {
        return null;
    }

    @Override
    public void delete(long idDate) {

    }
}
