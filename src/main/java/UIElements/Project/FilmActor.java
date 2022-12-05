package UIElements.Project;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//@Entity
@IdClass(FilmActorId.class)
@Table(name="film_actor")
public class FilmActor {

    @Id
    @OneToMany(mappedBy = "actor", fetch = FetchType.EAGER, orphanRemoval = false)
    List<Actor> listActor = new ArrayList<>();

    @Id
    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER, orphanRemoval = false)
    List<Film> listFilm = new ArrayList<>();

}
