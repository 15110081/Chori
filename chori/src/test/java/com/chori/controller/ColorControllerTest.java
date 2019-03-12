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

import com.chori.model.ColorModel;
import com.chori.model.UserModel;
import com.chori.service.ColorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ColorControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private ColorController controller;
	
	@Mock
	ColorService ser;
	
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
	public void testGetAllColor() throws Exception {
		mockMvc.perform(get("/color/list")).andExpect(status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllColorException() throws Exception {
		when(ser.getAllColor()).thenThrow(Exception.class);
		mockMvc.perform(get("/color/list"));
	}

	@Test
	public void testListColor() throws Exception {
		mockMvc.perform(get("/listcolor")).andExpect(status().isOk());
		// case #
//		when(roleSer.detectFunc4User(LoginController.currentUser, "FT1"))
//				.thenReturn(true);
		mockMvc.perform(get("/listcolor")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testListColorException() throws Exception {
//		when(roleSer.detectFunc4User(LoginController.currentUser, "FT1"))
//				.thenThrow(Exception.class);
		mockMvc.perform(get("/listcolor"));
	}


	@Test
	public void testDeleteColor() throws Exception {
		mockMvc.perform(post("/color/delete/{clId}", "del1").contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}
	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testDeleteColorException() throws Exception {
//		when(ser.deleteColor("del1")).thenThrow(Exception.class);
//		mockMvc.perform(post("/color/delete/{clId}", "del1").contentType(MediaType.APPLICATION_JSON));
//	}

	@Test
	public void testIsColorExist() throws Exception {
		mockMvc.perform(get("/color/isExist/{clId}", "BLU")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsColorExistException() throws Exception {
		when(ser.isColorExistedById("BLU1")).thenThrow(Exception.class);
		mockMvc.perform(get("/color/isExist/{clId}", "BLU1"));
	}

	@Test
	public void testAddColor() throws Exception {
		ColorModel um = new ColorModel();
		um.setColorcode("BLU");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/color/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddColorException() throws Exception {
		ColorModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);
		when(ser.addColor(um, "admin")).thenThrow(Exception.class);
		mockMvc.perform(post("/color/add").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}

	@Test
	public void testGetColorDetail() throws Exception {
		mockMvc.perform(get("/color/detail/{colorCode}", "BLU")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetColorDetailException() throws Exception {
		when(ser.findColorModelById("BLU1")).thenThrow(Exception.class);
		mockMvc.perform(get("/color/detail/{colorCode}", "BLU1"));
	}

	@Test
	public void testEditColor() throws Exception {
		ColorModel um = new ColorModel();
		um.setColorcode("BL");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/color/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditColorException() throws Exception {
		ColorModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.editColor(um)).thenThrow(Exception.class);

		mockMvc.perform(post("/color/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}

}
