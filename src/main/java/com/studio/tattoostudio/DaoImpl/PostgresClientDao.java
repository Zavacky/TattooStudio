package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.dao.ClientDao;
import com.studio.tattoostudio.data.Client;
import com.studio.tattoostudio.exceptions.ClientDoesntExistException;
import com.studio.tattoostudio.exceptions.IncorrectLoginOrPasswordException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.Objects;

//TODO set queries for login
public class PostgresClientDao implements ClientDao {
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Client> clientRowMapper() {
        return new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("idClient");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone_number");

                Client client = new Client(id, name, surname, email, phone);
                return client;
            }
        };
    }

    public PostgresClientDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Client getByLogin(String login) throws IncorrectLoginOrPasswordException {
        String statement = "SELECT * FROM client " +
                "WHERE login = " + login;
        return jdbcTemplate.queryForObject(statement, clientRowMapper());
    }

    @Override
    public Client saveClient(Client client) throws ClientDoesntExistException {
        Objects.requireNonNull(client, "Client can't be null");
        if (client.getId() == null){
            String statement = "INSERT INTO client (loginClient, passwordClient, name, surname, email, phone_number) " +
                    "VALUES (?,?,?,?,?,?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator(){
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement statement1 = con.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);

                    statement1.setString(1, client.getName());
                    statement1.setString(2, client.getSurname());
                    statement1.setString(3, client.getMail());
                    statement1.setString(4, client.getPhoneNumber());
                    return statement1;
                }
            }, keyHolder);
            long id = keyHolder.getKey().longValue();
            Client savedClient = Client.clone(client);
            savedClient.setId(id);
            return savedClient;
        }else {
            String statement = "UPDATE student SET name=?, surname=?, email=?, phone_number=? " +
                    "WHERE id = ?";
            int count = jdbcTemplate.update(statement, client.getName(),
                    client.getSurname(),
                    client.getMail(),
                    client.getPhoneNumber(),
                    client.getId());

            if (count == 0){
                throw new ClientDoesntExistException();
            }
            return client;
        }
    }
}
