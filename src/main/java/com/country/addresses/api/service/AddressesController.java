package com.country.addresses.api.service;

import com.country.addresses.api.service.address.AddressService;
import com.country.addresses.api.service.modal.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressesController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AddressService addressService;

    // Select All Addresses
    @RequestMapping("/api/v1/addresses")
    public List<Address> findAddresses() {
        log.info("Get all addresses. " + addressService.findAll());
        return addressService.findAll();
    }

    // Select an Address by id
    @RequestMapping(value = "/api/v1/address/{addressid}", method = RequestMethod.GET)
    Address getAddresses(@PathVariable Long addressid) {
        log.info("Get address by id. " + addressid + "." + addressService.findById(addressid));
        return addressService.findById(addressid);
    }

    // Select an Address by city name
    @RequestMapping(value = "/api/v1/addresses/cities/{cityName}", method = RequestMethod.GET)
    List<Address> getAddresses(@PathVariable String cityName) {
        log.info("Get addresses by city name : " + cityName.toUpperCase() + ".");
        return addressService.findByCityName(cityName);
    }

    // Select All Addresses By Country
    @RequestMapping(value = "/api/v1/addresses/{country}", method = RequestMethod.GET)
    public List<Address> FindAllByCountryName(@PathVariable String country) {
        List<Address> addressesbycountry = addressService.findAllByCountryName(country);
        log.info("Get addresses by country name. " + country.toUpperCase() + " : " + addressesbycountry);
        return addressesbycountry;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> noAddressFound(EmptyResultDataAccessException e) {
        log.info("No Address found. ");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Address found");
    }

}
