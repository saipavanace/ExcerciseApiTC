package com.country.addresses.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.country.addresses.api.service.address.AddressService;
import com.country.addresses.api.service.modal.Address;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@MockBean
	private AddressService addressService;

	@Autowired
	WebApplicationContext webApplicationContext;
	
	Address createAddressMockData()
	{
		Address addressMock = new Address();
		addressMock.setId(1L);
		addressMock.setAddress1("451 W 51 ST");
		addressMock.setAddress2("254 address2");
		addressMock.setCity("Columbia");
		addressMock.setCountry("USA");
		
		return addressMock;
	}

	protected void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	// Select All Addresses
	@Test
	public void findAllAddresses() throws Exception {

		Address addressMock = createAddressMockData();

		List<Address> addresListMock = new ArrayList<>();
		addresListMock.add(addressMock);

		when(addressService.findAll()).thenReturn(addresListMock);

		String uri = "/api/v1/addresses";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Address[] addresslist = mapFromJson(content, Address[].class);
		assertTrue(addresslist.length > 0);
	}

	// Select an Address by id
	@Test
	public void getAddressById() throws Exception {
		Address addressMock = createAddressMockData();

		List<Address> addresListMock = new ArrayList<>();
		addresListMock.add(addressMock);

		when(addressService.findById(1L)).thenReturn(addressMock);

		String uri = "/api/v1/address/1";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Address address = mapFromJson(content, Address.class);
		assertTrue(address.getId() == 1);
	}

	// Select an Address by id
	@Test
	public void getAddressByIdNotFound() throws Exception {
		
		when(addressService.findById(1L)).thenThrow(EmptyResultDataAccessException.class);

		String uri = "/api/v1/address/1";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(404, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "No Address found");
	}

	// Select an Address by city name
	@Test
	public void getAddressByCityName() throws Exception {
		Address addressMock = createAddressMockData();

		List<Address> addresListMock = new ArrayList<>();
		addresListMock.add(addressMock);

		when(addressService.findByCityName("Columbia")).thenReturn(addresListMock);

		String uri = "/api/v1/addresses/cities/Columbia";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Address[] addresslist = mapFromJson(content, Address[].class);
		assertTrue(addresslist.length > 0);
	}

	// Select All Addresses By Country
	@Test
	public void getAllAddressByCountry() throws Exception {
		Address addressMock = createAddressMockData();

		List<Address> addresListMock = new ArrayList<>();
		addresListMock.add(addressMock);

		when(addressService.findAllByCountryName("usa")).thenReturn(addresListMock);

		String uri = "/api/v1/addresses/usa";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Address[] addresslist = mapFromJson(content, Address[].class);
		assertTrue(addresslist.length > 0);
	}


}
