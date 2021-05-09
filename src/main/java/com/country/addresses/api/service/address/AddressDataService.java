package com.country.addresses.api.service.address;

import com.country.addresses.api.service.modal.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

//Data Service
@Service
public class AddressDataService implements AddressService {

    @Autowired
    private JdbcTemplate jtm;

    @Override
    public List<Address> findAll() {
        String sql = "SELECT * FROM addresses";
        List<Address> addresses = jtm.query(sql, new BeanPropertyRowMapper(Address.class));
        return addresses;
    }

    @Override
    public Address findById(Long id) {
        String sql = "SELECT * FROM addresses WHERE id=?";
        return jtm.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Address.class));
    }

    @Override
    public List<Address> findAllByCountryName(String countryname) {
        String sql = "SELECT * FROM Addresses WHERE country=?";
        List<Address> allAddresses = jtm.query(sql, new String[]{countryname.toUpperCase()},
                new BeanPropertyRowMapper(Address.class));
        return allAddresses;
    }

    @Override
    public List<Address> findByCityName(String cityname) {
        String sql = "SELECT * FROM Addresses WHERE city=?";
        List<Address> allAddressesByCity = jtm.query(sql, new String[]{capitalizeFirstLetter(cityname)},
                new BeanPropertyRowMapper(Address.class));
        return allAddressesByCity;
    }

    //Capitalize first letter of a word
    public String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        original = original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase();
        return original;
    }

}