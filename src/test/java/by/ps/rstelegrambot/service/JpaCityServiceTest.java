package by.ps.rstelegrambot.service;

import by.ps.rstelegrambot.entity.City;
import by.ps.rstelegrambot.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class JpaCityServiceTest {

    @Autowired
    private CityService cityService;

    @MockBean
    private CityRepository cityRepository;

    @Test
    public void saveCityTest() {

        City minsk = new City();
        minsk.setId(1L);
        minsk.setCityName("Minsk");
        minsk.setMessage("Test Minsk Message");

        cityService.save(minsk);

        Mockito.verify(cityRepository, Mockito.times(1)).save(minsk);
    }

    @Test
    public void findByIdTest() {

        City minsk = new City();
        minsk.setId(1L);
        minsk.setCityName("Minsk");
        minsk.setMessage("Test Minsk Message");

        Mockito.when(cityRepository.findOne(1L))
                .thenReturn(minsk);

        City city = cityService.findById(1L);

        Assert.assertEquals("Minsk", city.getCityName());
        Assert.assertEquals("Test Minsk Message", city.getMessage());

        City notFoundCity = cityService.findById(2L);
        Assert.assertNull(notFoundCity);
    }

    @Test
    public void findByCityNameTest() {

        City minsk = new City();
        minsk.setId(1L);
        minsk.setCityName("Minsk");
        minsk.setMessage("Test Minsk Message");

        Mockito.when(cityRepository.findByCityName("Minsk"))
                .thenReturn(minsk);

        City city = cityService.findByCityName("Minsk");

        Assert.assertNotNull(city);
        Assert.assertEquals("Test Minsk Message", city.getMessage());

        City absentCity = cityService.findByCityName("Rome");
        Assert.assertNull(absentCity);
    }

    @Test
    public void deleteCityTest() {

        City minsk = new City();
        minsk.setId(1L);
        minsk.setCityName("Minsk");
        minsk.setMessage("Test Minsk Message");

        cityService.delete(1L);

        Mockito.verify(cityRepository, Mockito.times(1)).delete(1L);
    }

    @Test
    public void getAllCitiesTest() {

        City minsk = new City();
        minsk.setId(1L);
        minsk.setCityName("Minsk");
        minsk.setMessage("Test Minsk Message");

        City berlin = new City();
        minsk.setId(1L);
        minsk.setCityName("Berlin");
        minsk.setMessage("Test Berlin Message");

        List<City> cities = new ArrayList<>();

        cities.add(minsk);
        cities.add(berlin);

        Mockito.when(cityRepository.findAll()).thenReturn(cities);

        List<City> foundCities = cityService.getAll();

        Mockito.verify(cityRepository, Mockito.times(1)).findAll();
        Assert.assertEquals(2, foundCities.size());
    }
}