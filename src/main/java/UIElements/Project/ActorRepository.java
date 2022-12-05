package UIElements.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Query(value = "SELECT * FROM actor LIMIT 1", nativeQuery = true)
    Actor findFirst();

    @Query(value = "SELECT * FROM actor ORDER BY actor_id DESC LIMIT 1", nativeQuery = true)
    Actor findLast();

    @Query(value = "SELECT film.title FROM actor " +
            "INNER JOIN film_actor ON actor.actor_id = film_actor.actor_id " +
            "INNER JOIN film ON film_actor.film_id = film.film_id " +
            "WHERE actor.actor_id = :actorid", nativeQuery = true)
    Iterable<Object> getFilmsWithActor(@Param("actorid") int actorid);

}
