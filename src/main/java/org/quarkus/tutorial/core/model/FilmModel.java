package org.quarkus.tutorial.core.model;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmModel {

    private Short id;

    private String title;

    private String description;

    private Integer releaseYear;

    private Short rentalDuration;

    private BigDecimal rentalRate;

    private Short length;

    private BigDecimal replacementCost;

    private String rating;

    private String specialFeatures;

    private Instant lastUpdate;
}
