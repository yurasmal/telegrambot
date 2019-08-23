package by.ps.rstelegrambot.repository;

import by.ps.rstelegrambot.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link City} class.
 */

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City c where c.cityName = :cityName")
    City findByCityName(@Param("cityName") String cityName);
}
