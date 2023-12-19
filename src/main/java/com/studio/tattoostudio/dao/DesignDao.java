package com.studio.tattoostudio.dao;

import com.studio.tattoostudio.data.Design;
import com.studio.tattoostudio.exceptions.EntityNotFoundException;

import java.util.List;

public interface DesignDao {

    /**
     * Method to get all designs by artist
     * @author Martin Zavacky
     * @param artistLogin
     * @return
     */
    List<Design> getAllByArtist(String artistLogin);

    /**
     * Method to get design by id
     * @author Martin Zavacky
     * @param id
     * @return
     */
    Design getById(Long id);

    /**
     * Method to save new design or update existing one
     * @author Martin Zavacky
     * @param design
     * @return
     * @throws EntityNotFoundException
     */
    Design save(Design design) throws EntityNotFoundException;

    /**
     * Method to delete design
     * @author Martin Zavacky
     * @param idDesign
     */
    void delete(long idDesign) throws EntityNotFoundException;
}
