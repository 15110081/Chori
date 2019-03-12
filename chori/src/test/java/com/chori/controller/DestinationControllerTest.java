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

import com.chori.entity.Destination;
import com.chori.model.ColorModel;
import com.chori.model.DestinationModel;
import com.chori.service.ColorService;
import com.chori.service.DestinationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class DestinationControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private DestinationController controller;
	
	@Mock
	DestinationService ser;
	
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
	public void testGetAllDestination() throws Exception  {
		mockMvc.perform(get("/destination/list")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllDestinationException() throws Exception {
		when(ser.getAllDestination()).thenThrow(Exception.class);
		mockMvc.perform(get("/destination/list"));
	}

	@Test
	public void testListDestination() throws Exception {
		mockMvc.perform(get("/listdestination")).andExpect(status().isOk());
		// case #
//		when(roleSer.detectFunc4User(LoginController.currentUser, "FT1"))
//				.thenReturn(true);
		mockMvc.perform(get("/listdestination")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testListDestinationException() throws Exception {
//		when(roleSer.detectFunc4User(LoginController.currentUser, "FT1"))
//				.thenThrow(Exception.class);
		mockMvc.perform(get("/listdestination"));
	}

	@Test
	public void testAddDestination() throws Exception {
		DestinationModel um = new DestinationModel();
		um.setDestinationcode("we");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/destination/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddDestinationException() throws Exception {
		DestinationModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);
		when(ser.addDestination(um, "admin")).thenThrow(Exception.class);
		mockMvc.perform(post("/destination/add").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}

	@Test
	public void testGetDestinationDetail() throws Exception {
		mockMvc.perform(get("/destination/detail/{destinationcode}", "we")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetDestinationDetailException() throws Exception {
		when(ser.findDestinationModelById("CPC")).thenThrow(Exception.class);
		mockMvc.perform(get("/destination/detail/{destinationcode}", "CPC"));
	}


	@Test
	public void testEditDestination() throws Exception {
		DestinationModel um = new DestinationModel();
		um.setDestinationcode("BL");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/destination/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditDestinationException() throws Exception {
		DestinationModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.editDestiantion(um)).thenThrow(Exception.class);

		mockMvc.perform(post("/destination/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}
	
	

	@Test
	public void testDeleteDestination() throws Exception  {
		mockMvc.perform(post("/destination/delete/{destinationcode}", "del1").contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}
	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testDeleteDestinationException() throws Exception {
//		when(ser.deleteDestination("del1")).thenThrow(Exception.class);
//		mockMvc.perform(post("/destination/delete/{destinationcode}", "del1").contentType(MediaType.APPLICATION_JSON));
//	}

	@Test
	public void testIsDestinationExist() throws Exception {
		mockMvc.perform(get("/destination/isExist/{destinationcode}", "we")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsDestinationExistException() throws Exception {
		when(ser.isDestinationExistedById("BLU1")).thenThrow(Exception.class);
		mockMvc.perform(get("/destination/isExist/{destinationcode}", "BLU1"));
	}

}
