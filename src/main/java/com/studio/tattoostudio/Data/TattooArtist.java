package com.studio.tattoostudio.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TattooArtist {
    private Long id;
    private Studio studio;
    private String name;
    private String specialization;
    private String mail;
    private String phoneNumber;
}
