package UIElements.Project;

import jakarta.persistence.*;

@Entity
@IdClass(FilmCategoryId.class)
@Table(name="film_category")
public class FilmCategory {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    Film filmId;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    Category categoryId;

    public FilmCategory(){}

    public Film getFilmId() {
        return filmId;
    }

    public void setFilmId(Film filmId) {
        this.filmId = filmId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }
}
