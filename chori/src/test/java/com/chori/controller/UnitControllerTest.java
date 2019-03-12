package com.chori.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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

import com.chori.model.UnitModel;
import com.chori.model.UserModel;
import com.chori.service.UnitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class UnitControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	private UnitController controller;

	@Mock
	UnitService ser;


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
	public void testGetAllUnit() throws Exception {
		mockMvc.perform(get("/unit/list")).andExpect(status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllUnitException() throws Exception {
		when(ser.getAllUnitModel()).thenThrow(Exception.class);
		mockMvc.perform(get("/unit/list"));
	}

	@Test
	public void testListUnit() throws Exception {
		mockMvc.perform(get("/listunit")).andExpect(status().isOk());
		
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testListUnitException() throws Exception {
		
		mockMvc.perform(get("/listunit")).andExpect(status().isNotFound());
	}

	

	@Test
	public void testGetUnitDetail() throws Exception {
		mockMvc.perform(get("/unit/detail/{unitCode}","pcs")).andExpect(status().isOk());

	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetUnitDetailException() throws Exception {
		when(ser.findUnitModelById("spc")).thenThrow(Exception.class);
		mockMvc.perform(get("/unit/detail/{unitCode}","spc"));
	}


	@Test
	public void testAddUnit() throws Exception {
		UnitModel um = new UnitModel();
		um.setUnitcode("grs");
		um.setName("Gross");
		um.setCreatedate(new Date());

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/unit/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddUnitException() throws Exception {
		UnitModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.addUnit(um)).thenThrow(Exception.class);

		mockMvc.perform(post("/unit/add").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}


	@Test
	public void testIsUnitExist() throws Exception {
		mockMvc.perform(get("/unit/isExist/{unitCode}", "pcs")).andExpect(
				status().isOk());	
		}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsUnitExistException() throws Exception {
		when(ser.isUnitExistedById("unitCode")).thenThrow(Exception.class);
		mockMvc.perform(get("/unit/isExist/{unitCode}", "unitCode"));
	}
	@Test
	public void testEditUnit() throws Exception {
		UnitModel um = new UnitModel();
		um.setName("Pieces");
		um.setCreatedate(new Date());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/unit/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditUnitException() throws Exception {
		UnitModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.editUnit(um)).thenThrow(Exception.class);

		mockMvc.perform(post("/unit/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}
	
	@Test
	public void testDeleteUnit() throws Exception {
		mockMvc.perform(post("/unit/delete/{unitCode}", "a").contentType(
				MediaType.APPLICATION_JSON));
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDeleteUnitxception() throws Exception {
		when(ser.deleteUnit("user1")).thenThrow(Exception.class);
		mockMvc.perform(post("/unit/delete/{unitCode}", "user1").contentType(
				MediaType.APPLICATION_JSON));
	}

}
