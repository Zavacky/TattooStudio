package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.dao.TattooArtistDao;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.exceptions.TattooArtistDoesntExistException;
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
                String login = rs.getString("login");
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");
                String mail = rs.getString("email");
                String phone = rs.getString("phone_number");

                return new TattooArtist(id, login, name, specialization, mail, phone);
            }
        };
    }

    @Override
    public TattooArtist getByLogin(String login) {
        String statement = "SELECT idArtist, loginArtist, name, specialization, email, phone_number FROM tattoo_artist " +
                "WHERE login = '" + login + "'";
        return jdbcTemplate.queryForObject(statement, tattooArtistRowMapper());
    }

    @Override
    public List<TattooArtist> getAllByStudio(Long idStudio) {
        String statement = "SELECT idArtist, loginArtist, name, specialization, email, phone_number " +
                "WHERE idSalon = " + idStudio;
        return jdbcTemplate.query(statement, tattooArtistRowMapper());
    }
}
