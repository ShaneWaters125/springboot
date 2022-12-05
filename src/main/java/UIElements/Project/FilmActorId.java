package UIElements.Project;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

class FilmActorId implements Serializable {

    private int filmId;

    private int actorId;

    public FilmActorId(int filmId, int actorId){
        this.filmId = filmId;
        this.actorId = actorId;
    }
    public FilmActorId(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmActorId that = (FilmActorId) o;
        return filmId == that.filmId && actorId == that.actorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, actorId);
    }
}
