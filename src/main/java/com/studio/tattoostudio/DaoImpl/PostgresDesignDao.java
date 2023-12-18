package com.studio.tattoostudio.daoImpl;

import com.studio.tattoostudio.dao.DesignDao;
import com.studio.tattoostudio.dao.TattooArtistDao;
import com.studio.tattoostudio.data.Design;
import com.studio.tattoostudio.data.TattooArtist;
import com.studio.tattoostudio.exceptions.EntityNotFoundException;
import com.studio.tattoostudio.factory.Factory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

//TODO implement delete
public class PostgresDesignDao implements DesignDao {

    private JdbcTemplate jdbcTemplate;
    private TattooArtistDao tattooArtistDao = Factory.INSTANCE.getTattooArtistDao();

    public PostgresDesignDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Design> designRowMapper(){
        return new RowMapper<Design>() {
            @Override
            public Design mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("idDesign");
                String loginArtist = rs.getString("loginArtist");
                byte[] picture = rs.getBytes("picture");
                int price = (int) rs.getLong("price");
                String description = rs.getString("description");
                return new Design(id, loginArtist, picture, price, description);
            }
        };
    }

    @Override
    public List<Design> getAllByStudio(Long studioId) {
        List<TattooArtist> artists = tattooArtistDao.getAllByStudio(studioId);
        List<Design> designs = new LinkedList<>();
        for (TattooArtist ta : artists){
            designs.addAll(this.getAllByArtist(ta.getLogin()));
        }
        return designs;
    }

    @Override
    public List<Design> getAllByArtist(String artistLogin) {
        String statement = "SELECT idDesign, loginartist, picture, price, description FROM free_design " +
                "WHERE loginArtist = '" + artistLogin + "'";
        return jdbcTemplate.query(statement, designRowMapper());
    }

    @Override
    public Design getById(Long id) {
        String statement = "SELECT idDesign, loginartist, picture, price, description FROM free_design " +
                "WHERE idDesign = " + id;
        return jdbcTemplate.queryForObject(statement, designRowMapper());
    }

    @Override
    public Design save(Design design) throws EntityNotFoundException {
        Objects.requireNonNull(design);

        if (design.getId() == null){
            String statement = "INSERT INTO free_design (loginArtist, picture, price, description) " +
                    "VALUES (?,?,?,?)";

            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement preparedStatement = con.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, design.getLoginArtist());
                    preparedStatement.setBytes(2, design.getPicture());
                    preparedStatement.setInt(3, design.getPrice());
                    preparedStatement.setString(4, design.getDescription());

                    return preparedStatement;
                }
            }, keyHolder);
            long idDesign = Long.parseLong(keyHolder.getKeyList().get(0).get("idDesign").toString());
            Design savedDesign = Design.clone(design);
            design.setId(idDesign);
            return savedDesign;
        }else{
            String query = "UPDATE free_design SET picture=?, price=?, description=? "
                    + "WHERE iddesign = ?";
            int count = jdbcTemplate.update(query, design.getPicture(),
                    design.getPrice(),
                    design.getDescription(),
                    design.getId());
            if (count == 0) {
                throw new EntityNotFoundException(
                        "Design with id " + design.getId() + " does not exist");
            }
            return design;
        }
    }

    @Override
    public void delete(long idDesign) throws EntityNotFoundException {
        String statement = "DELETE FROM free_design WHERE idDesign = ?";
        int count = jdbcTemplate.update(statement, idDesign);
        if (count == 0) {
            throw new EntityNotFoundException(
                    "Design with id " + idDesign + " does not exist");
        }
    }
}
