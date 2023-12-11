package com.studio.tattoostudio.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Studio {
    private Long id;
    private String address;
    private String phoneNumber;
    private String mail;
    private String instagram;
    private String Facebook;
    private List<TattooArtist> artists;
}
