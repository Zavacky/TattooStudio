package com.studio.tattoostudio.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Design {
    private Long id;
    private String pictureOfDesign;
    private int price;
    private String description;
}
