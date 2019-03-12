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

import com.chori.model.CtnrtypeModel;
import com.chori.model.WidthModel;
import com.chori.service.CtnrtypeService;
import com.chori.service.UnitService;
import com.chori.service.WidthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CtnrtypeControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private CtnrtypeController controller;

	@Mock
	CtnrtypeService ser;

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
	public void testGetAllStatus() throws Exception {
		mockMvc.perform(get("/ctnrtype/list")).andExpect(status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllStatusException() throws Exception {
		when(ser.getAllCtnrtypeModel()).thenThrow(Exception.class);
		mockMvc.perform(get("/ctnrtype/list"));
	}
	
	@Test
	public void testListCtnrtype() throws Exception {
		mockMvc.perform(get("/listctnrtype")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testListCtnrtypeException() throws Exception {
		mockMvc.perform(get("/listctnrtype"));
	}
	
	@Test
	public void testGetCtnrtypeDetail() throws Exception {
		mockMvc.perform(get("/ctnrtype/detail/{ctnrtypeCode}","5")).andExpect(status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetCtnrtypeDetailException() throws Exception {
		when(ser.findCtnrtypeEntityById("9")).thenThrow(
				Exception.class);
		mockMvc.perform(get("/ctnrtype/detail/{ctnrtypeCode}","9")).andExpect(status().isOk());
	}

	@Test
	public void testEditCtnrtype() throws Exception {
		CtnrtypeModel wm = new CtnrtypeModel();
		wm.setCtnrcode("3");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(wm);
		
		mockMvc.perform(
				post("/ctnrtype/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditCtnrtypeException() throws Exception {
		CtnrtypeModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.editCtnrtype(um, "admin")).thenThrow(Exception.class);

		mockMvc.perform(post("/ctnrtype/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}

	@Test
	public void testIsCtnrtypeExist() throws Exception {
		mockMvc.perform(get("/ctnrtype/isExist/{ctnrtypeCode}", "1")).andExpect(
				status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsCtnrtypeExistException() throws Exception {
		mockMvc.perform(get("/ctnrtype/isExist/{ctnrtypeCode}", "4"));
	}
	

	@Test
	public void testAddCtnrtype() throws Exception {
		CtnrtypeModel wm = new CtnrtypeModel();
		wm.setCtnrcode("8");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(wm);
		
		mockMvc.perform(
				post("/ctnrtype/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddCtnrtypeException() throws Exception {
		CtnrtypeModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.addCtnrtype(um, "admin")).thenThrow(Exception.class);

		mockMvc.perform(post("/ctnrtype/add").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}

	@Test
	public void testDeleteCtnrtype() throws Exception {
		mockMvc.perform(post("/ctnrtype/delete/{ctnrtypeCode}", "bobo").contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDeleteCtnrtypeException() throws Exception {
		when(ser.deleteCtnrtype("9")).thenThrow(Exception.class);
		mockMvc.perform(post("/ctnrtype/delete/{ctnrtypeCode}", "9").contentType(MediaType.APPLICATION_JSON));
	}

}
