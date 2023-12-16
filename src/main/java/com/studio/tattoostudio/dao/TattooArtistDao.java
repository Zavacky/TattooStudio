package com.studio.tattoostudio.dao;

import com.studio.tattoostudio.data.TattooArtist;

import java.util.List;

public interface TattooArtistDao {
    TattooArtist getByLogin(String login);
    List<TattooArtist> getAllByStudio(Long idStudio);
}
