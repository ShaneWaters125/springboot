package UIElements.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Query(value = "SELECT * FROM actor LIMIT 1", nativeQuery = true)
    Actor findFirst();

    @Query(value = "SELECT * FROM actor ORDER BY actor_id DESC LIMIT 1", nativeQuery = true)
    Actor findLast();

    @Query(value = "SELECT actor.first_name, film.title FROM actor " +
            "INNER JOIN film_actor ON actor.actor_id = film_actor.actor_id " +
            "INNER JOIN film ON film_actor.film_id = film.film_id", nativeQuery = true)
    Iterable<Object> matchFilm();

}
