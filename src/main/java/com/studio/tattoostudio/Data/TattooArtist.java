package com.studio.tattoostudio.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TattooArtist {
    private Long id;
    private String name;
    private String specialization;
    private String mail;
    private String phoneNumber;
    private List<Design> designList;
    private List<DateOfTattoo> datesOfTattoos;

    public TattooArtist(Long id, String name, String specialization, String mail, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    public static TattooArtist clone(TattooArtist artist) {
        return new TattooArtist(artist.getId(), artist.getName(), artist.getSpecialization(), artist.getMail(), artist.getPhoneNumber());
    }
}