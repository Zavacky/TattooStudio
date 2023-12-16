package com.studio.tattoostudio.dao;

import com.studio.tattoostudio.data.TattooArtist;

import java.util.List;

public interface TattooArtistDao {
    /**
     * Method to get artist by login
     * @author Martin Zavacky
     * @param login
     * @return
     */
    TattooArtist getByLogin(String login);

    /**
     * Method to get all artists by studio
     * @author Martin Zavacky
     * @param idStudio
     * @return
     */
    List<TattooArtist> getAllByStudio(Long idStudio);
}
