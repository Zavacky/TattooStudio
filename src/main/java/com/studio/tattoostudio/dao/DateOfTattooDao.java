package com.studio.tattoostudio.dao;

import com.studio.tattoostudio.data.DateOfTattoo;
import com.studio.tattoostudio.exceptions.EntityNotFoundException;

import java.util.List;

public interface DateOfTattooDao {

    List<DateOfTattoo> getAllByArtist(String artistLogin);
    List<DateOfTattoo> getAllByClient(String clientLogin);
    DateOfTattoo save(DateOfTattoo dateOfTattoo);
    void delete(long idDate) throws EntityNotFoundException;
}
