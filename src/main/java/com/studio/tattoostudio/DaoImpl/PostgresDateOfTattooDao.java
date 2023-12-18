package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.dao.DateOfTattooDao;
import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.data.DateOfTattoo;
import com.studio.tattoostudio.data.Design;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.exceptions.EntityNotFoundException;
import com.studio.tattoostudio.exceptions.IncorrectLoginOrPasswordException;
import com.studio.tattoostudio.exceptions.TattooArtistDoesntExistException;
import com.studio.tattoostudio.factory.Factory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PostgresDateOfTattooDao implements DateOfTattooDao {

    private JdbcTemplate jdbcTemplate;

    public PostgresDateOfTattooDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DateOfTattoo> getAllByArtist(String artistLogin) {
        String statement = "SELECT idDate, loginClient, idDesign, date, time, description FROM tattoo_date " +
                "WHERE loginArtist = '" + artistLogin + "' " +
                "ORDER BY date, time";

        return jdbcTemplate.query(statement, new ResultSetExtractor<>() {
            @Override
            public List<DateOfTattoo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<DateOfTattoo> dates = new ArrayList<>();
                while (rs.next()) {
                    long id = rs.getLong("idDate");
                    Client client;
                    try {
                        client = Factory.INSTANCE.getClientDao().getByLogin(rs.getString("loginClient"));
                    } catch (IncorrectLoginOrPasswordException e) {
                        throw new RuntimeException(e);
                    }
                    Design design = Factory.INSTANCE.getDesignDao().getById(rs.getLong("idDesign"));
                    Date date = rs.getDate("date");
                    Time time = rs.getTime("time");
                    LocalDate localDate = date.toLocalDate();
                    LocalTime localTime = time.toLocalTime();
                    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

                    String description = rs.getString("description");

                    DateOfTattoo dateOfTattoo = new DateOfTattoo(id, client, design, localDateTime, description);
                    dates.add(dateOfTattoo);
                }
                return dates;
            }
        });
    }

    @Override
    public List<DateOfTattoo> getAllByClient(String clientLogin) {
        String statement = "SELECT idDate, loginArtist, idDesign, date, time, description FROM tattoo_date " +
                "WHERE loginClient = '" + clientLogin + "' " +
                "ORDER BY date, time";

        return jdbcTemplate.query(statement, new ResultSetExtractor<>() {
            @Override
            public List<DateOfTattoo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<DateOfTattoo> dates = new ArrayList<>();
                while (rs.next()) {
                    long id = rs.getLong("idDate");
                    TattooArtist artist;
                    try {
                        artist = Factory.INSTANCE.getTattooArtistDao().getByLogin(rs.getString("loginArtist"));
                    } catch (TattooArtistDoesntExistException e) {
                        throw new RuntimeException(e);
                    }
                    Design design = Factory.INSTANCE.getDesignDao().getById(rs.getLong("idDesign"));
                    Date date = rs.getDate("date");
                    Time time = rs.getTime("time");
                    LocalDate localDate = date.toLocalDate();
                    LocalTime localTime = time.toLocalTime();
                    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

                    String description = rs.getString("description");

                    DateOfTattoo dateOfTattoo = new DateOfTattoo(id, artist, design, localDateTime, description);
                    dates.add(dateOfTattoo);
                }
                return dates;
            }
        });
    }

    @Override
    public DateOfTattoo save(DateOfTattoo dateOfTattoo) {
        Objects.requireNonNull(dateOfTattoo);
        if (dateOfTattoo.getId() == null) {
            String statement = "INSERT INTO tattoo_date (loginClient, loginArtist, idDesign, date, time, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(statement, new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, dateOfTattoo.getClient().getLogin());
                    ps.setString(2, dateOfTattoo.getTattooArtist().getLogin());
                    ps.setLong(3, dateOfTattoo.getDesign().getId());
                    ps.setDate(4, Date.valueOf(dateOfTattoo.getDateTime().toLocalDate()));
                    ps.setTime(5, Time.valueOf(dateOfTattoo.getDateTime().toLocalTime()));
                    ps.setString(6, dateOfTattoo.getNotes());

                    return ps;
                }
            }, keyHolder);
            long id = Long.parseLong(keyHolder.getKeyList().get(0).get("idDate").toString());
            DateOfTattoo date = DateOfTattoo.clone(dateOfTattoo);
            date.setId(id);
            return date;
        }else{
            String statement = "UPDATE tattoo_date SET loginClient = ?, loginArtist = ?, idDesign = ?, date = ?, time = ?, description = ? WHERE idDate = ?";
            jdbcTemplate.update(statement, dateOfTattoo.getClient().getLogin(),
                    dateOfTattoo.getTattooArtist().getLogin(),
                    dateOfTattoo.getDesign().getId(),
                    dateOfTattoo.getDateTime().toLocalDate(),
                    dateOfTattoo.getDateTime().toLocalTime(),
                    dateOfTattoo.getNotes(), dateOfTattoo.getId());
            return dateOfTattoo;
        }
    }

    @Override
    public void delete(long idDate) throws EntityNotFoundException {
        String statement = "DELETE FROM tattoo_date WHERE idDate = " + idDate;
        int count = jdbcTemplate.update(statement);
        if (count == 0) {
            throw new EntityNotFoundException("Date with id " + idDate + " not found");
        }
    }
}
