package com.studio.tattoostudio.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Design {
    private Long id;
    private String loginArtist;
    private byte[] picture;
    private int price;
    private String description;

    public static Design clone(Design design){
        return new Design(null, design.getLoginArtist(), design.getPicture(), design.getPrice(), design.getDescription());
    }
}
