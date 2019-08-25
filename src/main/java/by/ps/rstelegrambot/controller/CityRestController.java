package by.ps.rstelegrambot.controller;

import by.ps.rstelegrambot.entity.City;
import by.ps.rstelegrambot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CityRestController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<City> getCityById(@PathVariable("id") Long cityId) {

        if (cityId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        City city = this.cityService.findById(cityId);

        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<City> getByName(@RequestParam("name") String cityName){

        City city = this.cityService.findByCityName(cityName);

        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<City> saveCity(@RequestBody City city) {

        HttpHeaders headers = new HttpHeaders();

        if (city == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.cityService.save(city);
        return new ResponseEntity<>(city, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<City> updateCity(@RequestBody City city) {

        HttpHeaders headers = new HttpHeaders();

        if (city == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.cityService.save(city);
        return new ResponseEntity<>(city, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<City> deleteCity(@PathVariable("id") Long cityId) {

        City city = this.cityService.findById(cityId);

        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.cityService.delete(cityId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<City>> getAllCities() {

        List<City> cities = this.cityService.getAll();

        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
