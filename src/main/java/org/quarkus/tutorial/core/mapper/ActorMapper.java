package org.quarkus.tutorial.core.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.quarkus.tutorial.core.model.ActorModel;
import org.quarkus.tutorial.persistence.Actor;

@Mapper(componentModel = "jakarta")
public interface ActorMapper {

    ActorModel map(Actor source);

    List<ActorModel> map(List<Actor> source);
}
