package com.studio.tattoostudio.dao;

import com.studio.tattoostudio.data.Design;

import java.util.List;

public interface DesignDao {

    List<Design> getAllByStudio(Long studioId);
    List<Design> getAllByArtist(Long artistId);
    Design getById(Long id);
    Design save(Design design);
}
