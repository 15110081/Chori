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

import com.chori.model.DestinationModel;
import com.chori.model.GarmentkindModel;
import com.chori.service.DestinationService;
import com.chori.service.GarmentkindService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class GarmentkindControllerTest {
	
private MockMvc mockMvc;
	
	@InjectMocks
	private GarmentkindController controller;
	
	@Mock
	GarmentkindService ser;
	
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
	public void testGetAllGarmentkind() throws Exception{
		mockMvc.perform(get("/garmentkind/list")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllGarmentkindException() throws Exception {
		when(ser.getAllGarmentkind()).thenThrow(Exception.class);
		mockMvc.perform(get("/garmentkind/list"));
	}

	@Test
	public void testListGarmentkind() throws Exception {
		mockMvc.perform(get("/listgarmentkind")).andExpect(status().isOk());
		// case #
//		when(roleSer.detectFunc4User(LoginController.currentUser, "FT1"))
//				.thenReturn(true);
		mockMvc.perform(get("/listgarmentkind")).andExpect(status().isOk());

	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testListGarmentkindException() throws Exception {
//		when(roleSer.detectFunc4User(LoginController.currentUser, "FT1"))
//				.thenThrow(Exception.class);
		mockMvc.perform(get("/listgarmentkind"));
	}

	@Test
	public void testIsGarmentkindExist() throws Exception {
		mockMvc.perform(get("/garmentkind/isExist/{gmkCode}", "Shett")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsGarmentkindExistException() throws Exception {
		when(ser.isGarmentkindExistedById("BLU1")).thenThrow(Exception.class);
		mockMvc.perform(get("/garmentkind/isExist/{gmkCode}", "BLU1"));
	}

	@Test
	public void testAddGarmentkind() throws Exception {
		GarmentkindModel um = new GarmentkindModel();
		um.setGarmentkindcode("Shett");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/garmentkind/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddGarmentkindException() throws Exception {
		GarmentkindModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);
		when(ser.addGarmentkind(um, "admin")).thenThrow(Exception.class);
		mockMvc.perform(post("/garmentkind/add").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}

	@Test
	public void testGetGarmentkindDetail() throws Exception {
		mockMvc.perform(get("/garmentkind/detail/{garmentkindCode}", "we")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetGarmentkindDetailException() throws Exception {
		when(ser.findGarmentkindModelById("CPC")).thenThrow(Exception.class);
		mockMvc.perform(get("/garmentkind/detail/{garmentkindCode}", "CPC"));
	}

	@Test
	public void testEditGarmentkind() throws Exception {
		GarmentkindModel um = new GarmentkindModel();
		um.setGarmentkindcode("BL");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/garmentkind/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditGarmentkindException() throws Exception {
		GarmentkindModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.editGarmentkind(um)).thenThrow(Exception.class);

		mockMvc.perform(post("/garmentkind/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}

	@Test
	public void testDeleteGarmentkind() throws Exception {
		mockMvc.perform(post("/garmentkind/delete/{gmkCode}", "del1").contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDeleteGarmentkindException() throws Exception {
		when(ser.deleteGarmentkind("del1")).thenThrow(Exception.class);
		mockMvc.perform(post("/garmentkind/delete/{gmkCode}", "del1").contentType(MediaType.APPLICATION_JSON));
	}

}
