package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.dao.TattooArtistDao;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.exceptions.TattooArtistDoesntExistException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class PostgresTattooArtistDao implements TattooArtistDao {
    private JdbcTemplate jdbcTemplate;

    public PostgresTattooArtistDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<TattooArtist> tattooArtistRowMapper(){
        return new RowMapper<TattooArtist>() {
            @Override
            public TattooArtist mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("idArtist");
                String login = rs.getString("loginArtist");
                String password = rs.getString("passwordArtist");
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");
                String mail = rs.getString("email");
                String phone = rs.getString("phone_number");

                return new TattooArtist(id, login, password, name, specialization, mail, phone);
            }
        };
    }

    @Override
    public TattooArtist getByLogin(String login) throws TattooArtistDoesntExistException {
        String statement = "SELECT idArtist, loginArtist, passwordArtist, name, specialization, email, phone_number FROM tattoo_artist " +
                "WHERE loginArtist = '" + login + "'";
        try {
            return jdbcTemplate.queryForObject(statement, tattooArtistRowMapper());
        }catch (EmptyResultDataAccessException e){
            throw new TattooArtistDoesntExistException();
        }

    }

    @Override
    public List<TattooArtist> getAllByStudio(Long idStudio) {
        String statement = "SELECT idArtist, loginArtist, passwordArtist, name, specialization, email, phone_number FROM tattoo_artist " +
                "WHERE idSalon = " + idStudio;
        return jdbcTemplate.query(statement, tattooArtistRowMapper());
    }
}
