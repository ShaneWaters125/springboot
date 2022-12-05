package UIElements.Project;

import java.io.Serializable;

public class FilmActorId implements Serializable {

    private int filmId;

    private int actorId;

    public FilmActorId(int filmId, int actorId){
        this.filmId = filmId;
        this.actorId = actorId;
    }

}
