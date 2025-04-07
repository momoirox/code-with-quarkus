package org.quarkus.tutorial.core;

import java.util.List;
import org.quarkus.tutorial.core.model.ActorModel;

public interface ActingJobService {

    boolean isActing(Long actorId);

    List<ActorModel> search(String query);
}
