package UIElements.Project;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@IdClass(FilmActorId.class)
@Table(name="film_actor")
public class FilmActor {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "actor_id", referencedColumnName = "actor_id")
    Actor actorId;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    Film filmId;

    public FilmActor(){}

    public Actor getActorId() {
        return actorId;
    }

    public void setActorId(Actor actorId) {
        this.actorId = actorId;
    }

    public Film getFilmId() {
        return filmId;
    }

    public void setFilmId(Film filmId) {
        this.filmId = filmId;
    }
}
