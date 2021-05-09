package com.country.addresses.api.service.address;

import com.country.addresses.api.service.modal.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    Address findById(Long id);

    List<Address> findAllByCountryName(String countryname);

    List<Address> findByCityName(String cityname);
}