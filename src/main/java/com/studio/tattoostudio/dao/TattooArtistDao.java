package com.studio.tattoostudio.DAO;

import com.studio.tattoostudio.Data.TattooArtist;
import com.studio.tattoostudio.Exceptions.TattooArtistDoesntExistException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Objects;

public interface TattooArtistDao {
    TattooArtist getByLogin(String login);
    List<TattooArtist> getAllByStudio(Long idStudio);
}
