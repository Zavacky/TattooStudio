package com.studio.tattoostudio.DAO;

import com.studio.tattoostudio.Data.Design;

import java.util.List;

public interface DesignDao {

    List<Design> getAllByStudio(Long studioId);
    List<Design> getAllByArtist(Long artistId);
    Design getById(Long id);
    Design save(Design design);
}
