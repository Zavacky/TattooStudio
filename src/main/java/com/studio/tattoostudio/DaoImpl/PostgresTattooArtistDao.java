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

//TODO implement interface methods
public class PostgresTattooArtistDao implements TattooArtistDao {
    private JdbcTemplate jdbcTemplate;
//  private DesignDao designDao = Factory.INSTANCE.getDesignDao;
//  private DateOfTattooDao DateOfTattooDao = Factory.INSTANCE.getDateOfTattooDao;

    private RowMapper<TattooArtist> tattooArtistRowMapper(){
        return new RowMapper<TattooArtist>() {
            @Override
            public TattooArtist mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("idArtist");
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");
                String mail = rs.getString("email");
                String phone = rs.getString("phone_number");

                return new TattooArtist(id, name, specialization, mail, phone);
            }
        };
    }

    @Override
    public TattooArtist getByLogin(String login) {
        String statement = "SELECT * FROM tattoo_artist " +
                "WHERE login = " + login;
        return jdbcTemplate.queryForObject(statement, tattooArtistRowMapper());
    }

    @Override
    public List<TattooArtist> getAllByStudio(Long idStudio) {
        return null;
    }
}
