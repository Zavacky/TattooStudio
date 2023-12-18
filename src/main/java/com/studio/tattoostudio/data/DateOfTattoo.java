package com.studio.tattoostudio.data;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class DateOfTattoo {
    private Long id;
    private Client client;
    private TattooArtist tattooArtist;
    private Design design;
    private LocalDateTime dateTime;
    private String notes;

    public DateOfTattoo(Long id, Client client, Design design, LocalDateTime dateTime, String notes) {
        this.id = id;
        this.client = client;
        this.design = design;
        this.dateTime = dateTime;
        this.notes = notes;
    }

    public DateOfTattoo(Long id, TattooArtist tattooArtist, Design design, LocalDateTime dateTime, String notes) {
        this.id = id;
        this.tattooArtist = tattooArtist;
        this.design = design;
        this.dateTime = dateTime;
        this.notes = notes;
    }

    public DateOfTattoo(Client client, TattooArtist tattooArtist, Design design, LocalDateTime dateTime, String notes) {
        this.client = client;
        this.tattooArtist = tattooArtist;
        this.design = design;
        this.dateTime = dateTime;
        this.notes = notes;
    }

    public static DateOfTattoo clone(DateOfTattoo dateOfTattoo) {
        return new DateOfTattoo(dateOfTattoo.getId(), dateOfTattoo.getClient(), dateOfTattoo.getDesign(), dateOfTattoo.getDateTime(), dateOfTattoo.getNotes());
    }

    @Override
    public String toString() {
        return  "tattooArtist: " + tattooArtist +
                ", design: " + design +
                ", dateTime: " + dateTime +
                ", notes: '" + notes + '\'';
    }
}
