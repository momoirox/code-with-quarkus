package org.quarkus.tutorial.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.quarkus.tutorial.core.ActingJobService;
import org.quarkus.tutorial.core.model.ActorModel;
import org.quarkus.tutorial.persistence.Actor;

@Path("/acting/job")
public class ActingJobController {

    @Inject
    public ActingJobService actingJobService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Check if actor has been in any movies", description = "Check if actor has been in any movies")
    public boolean isActing(@PathParam("id") Long actorId) {
        return actingJobService.isActing(actorId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Search actors", description = "Get a list of every Actors based on search query")
    public List<ActorModel> searchActors(@RequestBody SearchQuery query) {
        return actingJobService.search(query.getQuery());
    }
}
