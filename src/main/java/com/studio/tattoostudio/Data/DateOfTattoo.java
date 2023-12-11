package com.studio.tattoostudio.Data;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class DateOfTattoo {
    private Long id;
    private TattooArtist artist;
    private Client client;
    private Design design;
    private LocalDateTime dateTime;
    private String notes;
}
