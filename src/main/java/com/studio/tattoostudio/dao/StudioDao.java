package com.studio.tattoostudio.dao;

import com.studio.tattoostudio.data.Studio;

import java.util.List;

public interface StudioDao {

    /**
     * Method to get all studios
     * @author Martin Zavacky
     * @return
     */
    List<Studio> getAll();
}
