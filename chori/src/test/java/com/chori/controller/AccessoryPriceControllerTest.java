package com.chori.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hibernate.SessionFactory;
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

import com.chori.model.AccessoryPriceModel;
import com.chori.model.AgentModel;
import com.chori.model.WidthModel;
import com.chori.service.AccessoryPriceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class AccessoryPriceControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private AccessoryPriceController controller;
	
	@Mock
	AccessoryPriceService ser;
	
	@Mock
	View mockView;

	@Mock
	SessionFactory sessionFactory;

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
	public void testListAccessoryPrice() throws Exception {
		mockMvc.perform(get("/listAccessoryPrice")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testListAccessoryPriceException() throws Exception {
		when(ser.getClass()).thenThrow(Exception.class);
	       mockMvc.perform(get("/listAccessoryPrice"));  
	}
	
	@Test
	public void testGetAllAccessoryPrice() throws Exception {
		mockMvc.perform(get("/accessoryprice/list")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllAccessoryPriceException() throws Exception {
		when(ser.getAllAccessoryPrice()).thenThrow(Exception.class);
		mockMvc.perform(get("/accessoryprice/list"));
	}
	
	

	@Test
	public void testAddAccessoryPrice() throws Exception {
		AccessoryPriceModel acc = new AccessoryPriceModel();
		acc.setAccessorypricecode(24);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(acc);
		
		mockMvc.perform(
				post("/accessoryprice/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddAccessoryPriceException() throws Exception {
		AccessoryPriceModel acc = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(acc);
		when(controller.addAccessoryPrice(acc)).thenThrow(Exception.class);
		mockMvc.perform(post("/accessoryprice/add").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}
	

	@Test
	public void testGetAccessoryPriceDetail() throws Exception {
		mockMvc.perform(get("/accessoryprice/detail/{accessorypriceCode}",24)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAccessoryPriceDetailException() throws Exception {
		when(ser.findAccessoryPriceModelById(5)).thenThrow(
				Exception.class);
		mockMvc.perform(get("/accessoryprice/detail/{accessorypriceCode}",5)).andExpect(status().isOk());
	}

	@Test
	public void testEditAccessoryPrice() throws Exception {
		AccessoryPriceModel acc = new AccessoryPriceModel();
		acc.setAccessorypricecode(25);;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(acc);
		
		mockMvc.perform(
				post("/accessoryprice/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditAccessoryPriceException() throws Exception {
		AccessoryPriceModel acc = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(acc);

		when(ser.editAccessoryPrice(acc)).thenThrow(Exception.class);

		mockMvc.perform(post("/accessoryprice/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}
	

	@Test
	public void testDeleteAccessoryPrice() throws Exception {
		mockMvc.perform(post("/accessoryprice/delete/{accessorypriceCode}",23).contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDeleteAccessoryPriceException() throws Exception {
		when(ser.deleteAccessoryprice(24)).thenThrow(Exception.class);
		mockMvc.perform(post("/accessoryprice/delete/{accessorypriceCode}",24).contentType(MediaType.APPLICATION_JSON));
	}

}
