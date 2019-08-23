package by.ps.rstelegrambot.service;

import by.ps.rstelegrambot.entity.City;
import by.ps.rstelegrambot.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link CityService} interface.
 */

@Service
@Slf4j
public class JpaCityService implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public JpaCityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City findById(Long id) {

        log.info("IN JpaCityService findById {}", id);
        return this.cityRepository.findOne(id);
    }

    @Override
    public City findByCityName(String cityName) {

        log.info("IN JpaCityService findByCityName {}", cityName);
        return this.cityRepository.findByCityName(cityName);
    }

    @Override
    public void save(City city) {

        log.info("IN JpaCityService save {}", city);
        this.cityRepository.save(city);
    }

    @Override
    public void delete(Long id) {

        log.info("IN JpaCityService delete {}", id);
        this.cityRepository.delete(id);
    }

    @Override
    public List<City> getAll() {

        log.info("IN JpaCityService getAll");
        return this.cityRepository.findAll();
    }
}
