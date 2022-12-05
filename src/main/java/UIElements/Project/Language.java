package UIElements.Project;


import jakarta.persistence.*;

@Entity
@Table(name="language")
public class Language {

    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int languageId;

    @Column(name = "name")
    String name;

    public Language(String name){
        this.name =  name;
    }

    public Language(){}

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
