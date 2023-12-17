package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.dao.StudioDao;
import com.studio.tattoostudio.dao.TattooArtistDao;
import com.studio.tattoostudio.data.Studio;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.factory.Factory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostgresStudioDao implements StudioDao {
    private JdbcTemplate jdbcTemplate;
    private TattooArtistDao tattooArtistDao = Factory.INSTANCE.getTattooArtistDao();

    public PostgresStudioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Studio> studioRowMapper(){
        return new RowMapper<Studio>() {
            @Override
            public Studio mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("idSalon");
                String address = rs.getString("adresa");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String instagram = rs.getString("instagram");
                String facebook = rs.getString("facebook");
                return new Studio(id, address, email, phoneNumber, instagram, facebook, null);
            }
        };
    }

    @Override
    public List<Studio> getAll() {
        String statement = "SELECT * FROM salon";
        List<Studio> studios = jdbcTemplate.query(statement, studioRowMapper());

        for (Studio s : studios){
            s.setArtists(tattooArtistDao.getAllByStudio(s.getId()));
        }
        return studios;
    }
}
