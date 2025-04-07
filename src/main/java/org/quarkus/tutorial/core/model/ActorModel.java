package org.quarkus.tutorial.core.model;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorModel {

    private Short id;

    private String firstName;

    private String lastName;

    private Instant lastUpdate;
}
