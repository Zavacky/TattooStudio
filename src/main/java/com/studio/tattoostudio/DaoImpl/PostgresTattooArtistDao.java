package com.studio.tattoostudio.DaoImpl;

import com.studio.tattoostudio.DAO.TattooArtistDao;
import com.studio.tattoostudio.Data.TattooArtist;
import com.studio.tattoostudio.Exceptions.TattooArtistDoesntExistException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.Objects;

public class PostgresTattooArtistDao implements TattooArtistDao {
    private JdbcTemplate jdbcTemplate;
//  private DesignDao designDao = Factory.INSTANCE.getDesignDao;
//  private DateOfTattooDao DateOfTattooDao = Factory.INSTANCE.getDateOfTattooDao;

    //TODO implement into getByLogin
    private RowMapper<TattooArtist> tattooArtistRowMapper(){
        return new RowMapper<TattooArtist>() {
            @Override
            public TattooArtist mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("idArtist");
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");
                String mail = rs.getString("email");
                String phone = rs.getString("phone_number");

                TattooArtist artist = new TattooArtist(id, name, specialization, mail, phone);
                return artist;
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
    public TattooArtist saveTattooArtist(TattooArtist artist) throws TattooArtistDoesntExistException {
        Objects.requireNonNull(artist);

        if (artist.getId() == null){
            String statement = "INSERT INTO tattoo_artist (name, specialization, email, phone_number) " +
                    "VALUES (?,?,?,?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement preparedStatement = con.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, artist.getName());
                    preparedStatement.setString(2, artist.getSpecialization());
                    preparedStatement.setString(3, artist.getMail());
                    preparedStatement.setString(4, artist.getPhoneNumber());

                    return preparedStatement;
                }
            }, keyHolder);
            long id = keyHolder.getKey().longValue();
            TattooArtist savedArtist = TattooArtist.clone(artist);
            savedArtist.setId(id);
            return savedArtist;
        }else {
            String statement = "UPDATE tattoo_artist SET name=?, specialization=?, email=?, phone_number=? " +
                    "WHERE idartist = ?";
            int count = jdbcTemplate.update(statement, artist.getName(),
                    artist.getSpecialization(),
                    artist.getMail(),
                    artist.getPhoneNumber(),
                    artist.getId());
            if (count == 0){
                throw new TattooArtistDoesntExistException();
            }

            return artist;
        }
    }
}
