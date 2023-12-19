package com.studio.tattoostudio.dao;

import com.studio.tattoostudio.data.DateOfTattoo;
import com.studio.tattoostudio.exceptions.EntityNotFoundException;

import java.util.List;

public interface DateOfTattooDao {

    /**
     * Method to get all dates of tattoo by artist
     * @author Martin Zavacky
     * @param artistLogin
     * @return
     */
    List<DateOfTattoo> getAllByArtist(String artistLogin);

    /**
     * Method to get all dates of tattoo by client
     * @author Martin Zavacky
     * @param clientLogin
     * @return
     */
    List<DateOfTattoo> getAllByClient(String clientLogin);

    /**
     * Method to save new date of tattoo or update existing one
     * @author Martin Zavacky
     * @param dateOfTattoo
     * @return
     */
    DateOfTattoo save(DateOfTattoo dateOfTattoo);

    /**
     * Method to delete date of tattoo
     * @author Martin Zavacky
     * @param idDate
     * @throws EntityNotFoundException
     */
    void delete(long idDate) throws EntityNotFoundException;
}
