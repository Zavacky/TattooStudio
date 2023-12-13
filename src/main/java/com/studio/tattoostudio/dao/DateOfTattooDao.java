package com.studio.tattoostudio.DAO;

import com.studio.tattoostudio.Data.DateOfTattoo;

import java.util.List;

public interface DateOfTattooDao {

    List<DateOfTattoo> getAllByArtist(String artistLogin);
    List<DateOfTattoo> getAllByClient(String clientLogin);
    DateOfTattoo save(DateOfTattoo dateOfTattoo);
}
