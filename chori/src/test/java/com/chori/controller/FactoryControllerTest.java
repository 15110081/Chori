package com.chori.controller;

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

import com.chori.model.FactoryModel;
import com.chori.service.FactoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class FactoryControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private FactoryController controller;
	
	@Mock
	FactoryService ser;

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
	public void testListFactory() throws Exception {
		mockMvc.perform(get("/listFactory")).andExpect(status().isOk());
	}

	@Test
	public void testGetAllFactory() throws Exception {
		mockMvc.perform(get("/factory/list")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllFactoryException() throws Exception {
		when(ser.getAllFactoryModel()).thenThrow(Exception.class);
		mockMvc.perform(get("/factory/list")).andExpect(status().isOk());
	}

	@Test
	public void testAddNewFactory() throws Exception {
		FactoryModel fsm = new FactoryModel();
		fsm.setFactorycode("FS01");
		fsm.setShortname("FabSup01");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(fsm);

		mockMvc.perform(
				post("/factory/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddNewFactoryException() throws Exception {
		FactoryModel fsm = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(fsm);
		
		when(ser.addNewFactory(fsm, "admin")).thenThrow(Exception.class);

		mockMvc.perform(
				post("/factory/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}

	@Test
	public void testGet1FactoryDetail() throws Exception {
		mockMvc.perform(get("/factory/detail/{factoryCode}", "fsc")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGet1FactoryDetailException() throws Exception {
		when(ser.findFactoryModelById("fsc")).thenThrow(Exception.class);
		mockMvc.perform(get("/factory/detail/{factoryCode}", "fsc")).andExpect(
				status().isOk());
	}

	@Test
	public void testEditFactory() throws Exception {
		FactoryModel fsm = new FactoryModel();
		fsm.setFactorycode("FS01");
		fsm.setShortname("FabSup01");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(fsm);

		mockMvc.perform(
				post("/factory/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditFactoryException() throws Exception {
		FactoryModel fsm = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(fsm);

		when(ser.editFactory(fsm, "admin")).thenThrow(Exception.class);
		
		mockMvc.perform(
				post("/factory/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}

	@Test
	public void testDelete1Factory() throws Exception {
		mockMvc.perform(post("/factory/delete/{factoryCode}", "fsc1")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDelete1FactoryException() throws Exception {
		when(ser.delete("fsc1")).thenThrow(Exception.class);
		mockMvc.perform(post("/factory/delete/{factoryCode}", "fsc1")).andExpect(
				status().isOk());
	}

	@Test
	public void testIsFactoryExist() throws Exception {
		mockMvc.perform(get("/factory/isExist/{factoryCode}", "admin")).andExpect(
				status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsFactoryExistException() throws Exception {
		when(ser.isFactoryExistedById("admin")).thenThrow(Exception.class);
		mockMvc.perform(get("/factory/isExist/{factoryCode}", "admin")).andExpect(
				status().isOk());
	}
}
