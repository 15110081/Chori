package com.chori.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import com.chori.model.CustomerModel;
import com.chori.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomerControllerTest {
	
	private MockMvc mockMvc;

	@InjectMocks
	private CustomerController controller;
	
	@Mock
	CustomerService ser;

	@Mock
	View mockView;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(mockView).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testListCustomer() throws Exception {
		mockMvc.perform(get("/listCustomer")).andExpect(status().isOk());
	}

	@Test
	public void testGetAllCustomer() throws Exception {
		mockMvc.perform(get("/customer/list")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllCustomerException() throws Exception {
		when(ser.getAllCustomerModel()).thenThrow(Exception.class);
		mockMvc.perform(get("/customer/list")).andExpect(status().isOk());
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		mockMvc.perform(
				post("/customer/delete/{customerCode}", "fdhdfh").contentType(MediaType.APPLICATION_JSON)					)
		.andExpect(status().isOk());
	}
	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testDeleteCustomerException() throws Exception {
//		when(ser.delete("fdhdfh")).thenThrow(Exception.class);
//		mockMvc.perform(post("/customer/delete/{customerCode}", "fdhdfh")).andExpect(
//				status().isOk());
//	}

	@Test
	public void testAddNewCustomer() throws Exception{
		CustomerModel cm = new CustomerModel();
		cm.setCustomercode("cuso2");
		cm.setShortname("son pho");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(cm);

		mockMvc.perform(
				post("/customer/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddNewCustomerException() throws Exception {
		CustomerModel cm = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(cm);
		
		when(ser.addNewCustomer(cm, "admin")).thenThrow(Exception.class);

		mockMvc.perform(
				post("/customer/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}


	@Test
	public void testGetCustomerDetail() throws Exception {
		mockMvc.perform(get("/customer/detail/{customerCode}", "cus02")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetCustomerDetailException() throws Exception {
		when(ser.findCustomerModelById(null)).thenThrow(Exception.class);
		mockMvc.perform(get("/customer/detail/{customerCode}", null)).andExpect(
				status().isOk());
	}

	@Test
	public void testEditCustomer() throws Exception{
		CustomerModel cm = new CustomerModel();
		cm.setCustomercode("cus02");
		cm.setShortname("son pho");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(cm);

		mockMvc.perform(
				post("/customer/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());	
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditCustomerException() throws Exception {
		CustomerModel cm = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(cm);

		when(ser.editCustomer(cm, "admin")).thenThrow(Exception.class);
		
		mockMvc.perform(
				post("/customer/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}

	@Test
	public void testIsCustomerExist() throws Exception {
		mockMvc.perform(get("/customer/isExist/{customerCode}", "admin")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsCustomerExistException() throws Exception {
		when(ser.isCustomerExistedById("")).thenThrow(Exception.class);
		mockMvc.perform(get("/customer/isExist/{customerCode}", "")).andExpect(
				status().isOk());
	}

}
