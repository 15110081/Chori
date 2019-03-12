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

import com.chori.model.EstimatetimeModel;
import com.chori.model.UnitModel;
import com.chori.service.EstimatetimeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class EstimatetimeControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	private EstimatetimeController controller;

	@Mock
	EstimatetimeService ser;


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
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setSingleView(mockView).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEstimatetimeDetail() throws Exception {
		mockMvc.perform(get("/estimatetime/detail/{EstCode}",1)).andExpect(status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetEstimatetimeDetailException() throws Exception {
		when(ser.findEstimatetimeModelById(2)).thenThrow(Exception.class);
		mockMvc.perform(get("/estimatetime/detail/{EstCode}",2));
	}

	@Test
	public void testEditEstimatetime() throws Exception {
		EstimatetimeModel um = new EstimatetimeModel();
		um.setManuaccdelv(6);
		um.setPackingaccdelv(6);
		um.setPiconpletion(6);
		um.setCreatedate(new Date());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/estimatetime/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditEstimatetException() throws Exception {
		EstimatetimeModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.editEstimatetime(um, "admin")).thenThrow(Exception.class);

		mockMvc.perform(post("/estimatetime/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}
	
	@Test
	public void testGetAllEstimatetime() throws Exception {
		mockMvc.perform(get("/estimatetime/list")).andExpect(status().isOk());

	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllEstimatetimeException() throws Exception {
		when(ser.getAllEstimatetimeModel()).thenThrow(Exception.class);
		mockMvc.perform(get("/estimatetime/list"));
	}
	@Test
	public void testListEstimatetime() throws Exception {
		mockMvc.perform(get("/listEstimatetime")).andExpect(status().isOk());

	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testListEstimatetimeException() throws Exception {
		
	}

}
