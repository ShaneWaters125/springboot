package UIElements.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query(value = "SELECT film.title, category.name FROM film " +
            "INNER JOIN film_category ON film.film_id = film_category.film_id " +
            "INNER JOIN category ON film_category.category_id = category.category_id" +
            " WHERE category.name = :catname", nativeQuery = true)
    Iterable<Object> matchCategoryWithFilms(@Param("catname") String catname);

    @Query(value = "SELECT actor.actor_id, actor.first_name FROM film " +
            "INNER JOIN film_actor ON film.film_id = film_actor.film_id " +
            "INNER JOIN actor ON film_actor.actor_id = actor.actor_id " +
            "WHERE film.film_id = :filmid", nativeQuery = true)
    Iterable<Object> matchFilmWithActors(@Param("filmid") int filmid);

}

