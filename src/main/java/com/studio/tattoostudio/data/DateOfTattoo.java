package com.studio.tattoostudio.data;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class DateOfTattoo {
    private Long id;
    private Client client;
    private Design design;
    private LocalDateTime dateTime;
    private String notes;
}
