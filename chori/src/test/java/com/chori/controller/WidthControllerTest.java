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

import com.chori.model.UserModel;
import com.chori.model.WidthModel;
import com.chori.service.RoleService;
import com.chori.service.UnitService;
import com.chori.service.UserService;
import com.chori.service.WidthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class WidthControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private WidthController controller;

	@Mock
	WidthService ser;

	@Mock
	UnitService unitSer;

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
		mockMvc.perform(get("/width/list")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllStatusException() throws Exception {
		when(ser.getAllWidthModel()).thenThrow(Exception.class);
		mockMvc.perform(get("/width/list"));
	}

	@Test
	public void testListWidth() throws Exception {
		mockMvc.perform(get("/listwidth")).andExpect(status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testListWidthException() throws Exception {
		mockMvc.perform(get("/listwidth"));
	}

	@Test
	public void testGetWidthDetail() throws Exception {
		mockMvc.perform(get("/width/detail/{widthCode}","2")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetWidthDetailException() throws Exception {
		when(ser.findWidthEntityById("6")).thenThrow(
				Exception.class);
		mockMvc.perform(get("/width/detail/{widthCode}","6")).andExpect(status().isOk());
	}
	
	@Test
	public void testEditWidth() throws Exception {
		WidthModel wm = new WidthModel();
		wm.setWidthcode("3");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(wm);
		
		mockMvc.perform(
				post("/width/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditWidthException() throws Exception {
		WidthModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.editWidth(um, "admin")).thenThrow(Exception.class);

		mockMvc.perform(post("/width/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}

	@Test
	public void testIsWidthExist() throws Exception {
		mockMvc.perform(get("/width/isExist/{widthcode}", "1")).andExpect(
				status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsWidthExistException() throws Exception {
		mockMvc.perform(get("/width/isExist/{widthcode}", "6"));
	}
	
	@Test
	public void testAddWidth() throws Exception {
		WidthModel wm = new WidthModel();
		wm.setWidthcode("7");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(wm);
		
		mockMvc.perform(
				post("/width/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddWidthException() throws Exception {
		WidthModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.addWidth(um, "admin")).thenThrow(Exception.class);

		mockMvc.perform(post("/width/add").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}
	
	@Test
	public void testDeleteWidth() throws Exception {
		mockMvc.perform(post("/width/delete/{widthcode}", "4").contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}
	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testDeleteWidthException() throws Exception {
//		when(ser.deleteWidth("9")).thenThrow(Exception.class);
//		mockMvc.perform(post("/width/delete/{widthcode}", "9").contentType(MediaType.APPLICATION_JSON));
//	}
//	
}
