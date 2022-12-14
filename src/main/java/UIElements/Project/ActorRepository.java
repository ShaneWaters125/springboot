package UIElements.Project;
import UIElements.Project.ReturnInterfaces.FilmsWithActorInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Query(value = "SELECT * FROM actor LIMIT 1", nativeQuery = true)
    Actor findFirst();

    @Query(value = "SELECT * FROM actor ORDER BY actor_id DESC LIMIT 1", nativeQuery = true)
    Actor findLast();

    @Query(value = "SELECT film.film_id, film.title FROM actor " +
            "INNER JOIN film_actor ON actor.actor_id = film_actor.actor_id " +
            "INNER JOIN film ON film_actor.film_id = film.film_id " +
            "WHERE actor.actor_id = :actorid", nativeQuery = true)
    Iterable<FilmsWithActorInterface> getFilmsWithActor(@Param("actorid") int actorid);

    @Query(value = "SELECT * FROM actor WHERE actor.first_name LIKE %:actorfirstname% " +
            "AND actor.last_name LIKE %:actorlastname%", nativeQuery = true)
    Iterable<Actor> findActorWithName(@Param("actorfirstname") String actorfirstname, @Param("actorlastname") String actorlastname);

}
