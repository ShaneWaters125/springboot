package UIElements.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query(value = "SELECT address.address, country.country FROM address INNER JOIN city ON address.city_id = city.city_id INNER JOIN country ON city.country_id = country.country_id", nativeQuery = true)
    Iterable<Object> addressWithCountry();

}
