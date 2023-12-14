package com.studio.tattoostudio.dao;

import com.studio.tattoostudio.data.Design;
import com.studio.tattoostudio.exceptions.EntityNotFoundException;

import java.util.List;

public interface DesignDao {

    List<Design> getAllByStudio(Long studioId);
    List<Design> getAllByArtist(String artistLogin);
    Design getById(Long id);
    Design save(Design design) throws EntityNotFoundException;
    void delete(long idDesign);
}
