package by.ps.rstelegrambot.service;

import by.ps.rstelegrambot.entity.City;

import java.util.List;

/**
 * Service interface for {@link City} class.
 */

public interface CityService {

    City findById(Long id);

    City findByCityName(String cityName);

    void save(City city);

    void delete(Long id);

    List<City> getAll();
}
