package com.studio.tattoostudio.dao;

import com.studio.tattoostudio.data.DateOfTattoo;

import java.util.List;

public interface DateOfTattooDao {

    List<DateOfTattoo> getAllByArtist(String artistLogin);
    List<DateOfTattoo> getAllByClient(String clientLogin);
    DateOfTattoo save(DateOfTattoo dateOfTattoo);
}