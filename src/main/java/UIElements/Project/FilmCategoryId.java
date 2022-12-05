package UIElements.Project;

import java.io.Serializable;
import java.util.Objects;

public class FilmCategoryId implements Serializable {

    private int filmId;

    private int categoryId;

    public FilmCategoryId(int filmId, int categoryId){
        this.filmId = filmId;
        this.categoryId = categoryId;
    }

    public FilmCategoryId(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCategoryId that = (FilmCategoryId) o;
        return filmId == that.filmId && categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, categoryId);
    }
}
