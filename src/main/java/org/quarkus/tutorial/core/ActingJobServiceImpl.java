package org.quarkus.tutorial.core;

import static java.util.Objects.nonNull;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.quarkus.tutorial.core.mapper.ActorMapper;
import org.quarkus.tutorial.core.model.ActorModel;
import org.quarkus.tutorial.persistence.Actor;
import org.quarkus.tutorial.persistence.Actor$;
import org.quarkus.tutorial.persistence.Film;
import org.quarkus.tutorial.persistence.Film$;

@ApplicationScoped
public class ActingJobServiceImpl implements ActingJobService {

    @Inject
    EntityManager entityManager;

    @Inject
    ActorMapper actorMapper;

    @Override
    public boolean isActing(Long actorId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);

        Root<Film> filmRoot = query.from(Film.class);
        Join<Film, Actor> actorsJoin = filmRoot.join(Film$.actors.columnName());

        query.select(criteriaBuilder.count(filmRoot))
            .where(criteriaBuilder.equal(actorsJoin.get(Actor$.id.columnName()), actorId));

        Long count = entityManager.createQuery(query).getSingleResult();

        return count != null && count > 0;
    }

    @Override
    public List<ActorModel> search(String query) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Actor> searchQuery = criteriaBuilder.createQuery(Actor.class);

        Root<Actor> actorRoot = searchQuery.from(Actor.class);

        if (nonNull(query)) {
            Predicate searchPredicate = criteriaBuilder.or(
                criteriaBuilder.like(actorRoot.get(Actor$.firstName.columnName()), "%" + query + "%"),
                criteriaBuilder.like(actorRoot.get(Actor$.lastName.columnName()), "%" + query + "%")
            );
            searchQuery.where(searchPredicate);
        }

        TypedQuery<Actor> typedQuery = entityManager.createQuery(searchQuery);
        List<Actor> resultList = typedQuery.getResultList();
        return actorMapper.map(resultList);
    }
}
