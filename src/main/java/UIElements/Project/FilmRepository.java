package UIElements.Project;

import UIElements.Project.ReturnInterfaces.CategoryWithFilmInterface;
import UIElements.Project.ReturnInterfaces.FilmWithActorInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query(value = "SELECT film.title, category.name FROM film " +
            "INNER JOIN film_category ON film.film_id = film_category.film_id " +
            "INNER JOIN category ON film_category.category_id = category.category_id" +
            " WHERE category.name = :catname", nativeQuery = true)
    Iterable<CategoryWithFilmInterface> matchCategoryWithFilms(@Param("catname") String catname);

    @Query(value = "SELECT actor.actor_id, actor.first_name, actor.last_name FROM film " +
            "INNER JOIN film_actor ON film.film_id = film_actor.film_id " +
            "INNER JOIN actor ON film_actor.actor_id = actor.actor_id " +
            "WHERE film.film_id = :filmid", nativeQuery = true)
    Iterable<FilmWithActorInterface> matchFilmWithActors(@Param("filmid") int filmid);

    @Query(value = "SELECT * FROM film WHERE film.title LIKE :filmname%", nativeQuery = true)
    Iterable<Film> findFilmWithName(@Param("filmname") String filmname);

    @Query(value = "SELECT * FROM film WHERE film.description LIKE %:filmdesc%", nativeQuery = true)
    Iterable<Film> findFilmWithDescription(@Param("filmdesc") String filmdesc);

    @Query(value = "SELECT * FROM film WHERE film.length < :filmlength", nativeQuery = true)
    Iterable<Film> findFilmsWithLessLengthThan(@Param("filmlength") int filmlength);

    @Query(value = "SELECT * FROM film WHERE film.rating = :filmrating", nativeQuery = true)
    Iterable<Film> findFilmsWithRating(@Param("filmrating") String filmrating);

}

