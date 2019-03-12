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

import com.chori.model.FabricsupplierModel;
import com.chori.service.FabricsupplierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class FabricsupplierControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private FabricsupplierController controller;
	
	@Mock
	private FabricsupplierController controller1;

	@Mock
	FabricsupplierService ser;

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
	public void testListFabricSupplier() throws Exception {
		mockMvc.perform(get("/listFabricSupplier")).andExpect(status().isOk());
	}
	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testListFabricSupplierException() throws Exception {
//		when(controller1.listFabricSupplier()).thenThrow(Exception.class);
//		mockMvc.perform(get("/listFabricSupplier"));
//	}

	@Test
	public void testGetAllFabricSupplier() throws Exception {
		mockMvc.perform(get("/fabricSupplier/list")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllFabricSupplierException() throws Exception {
		when(ser.getAllFabricsupplierModel()).thenThrow(Exception.class);
		mockMvc.perform(get("/fabricSupplier/list")).andExpect(status().isOk());
	}

	@Test
	public void testAddNewFabricSupplier() throws Exception {
		FabricsupplierModel fsm = new FabricsupplierModel();
		fsm.setFabricsupcode("FS01");
		fsm.setShortname("FabSup01");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(fsm);

		mockMvc.perform(
				post("/fabricSupplier/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddNewFabricSupplierException() throws Exception {
		FabricsupplierModel fsm = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(fsm);
		
		when(ser.addNewFabricSupplier(fsm, "admin")).thenThrow(Exception.class);

		mockMvc.perform(
				post("/fabricSupplier/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}

	@Test
	public void testGet1FabricSupplierDetail() throws Exception {
		mockMvc.perform(get("/fabricSupplier/detail/{fabricSupplierCode}", "fsc")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGet1FabricSupplierDetailException() throws Exception {
		when(ser.findFabricsupplierModelById("fsc1")).thenThrow(Exception.class);
		mockMvc.perform(get("/fabricSupplier/detail/{fabricSupplierCode}", "fsc1"));
	}

	@Test
	public void testEditFabricSupplier() throws Exception {
		FabricsupplierModel fsm = new FabricsupplierModel();
		fsm.setFabricsupcode("FS01");
		fsm.setShortname("FabSup01");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(fsm);

		mockMvc.perform(
				post("/fabricSupplier/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditFabricSupplierException() throws Exception {
		FabricsupplierModel fsm = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(fsm);
		
		when(ser.editFabricSupplier(fsm, "admin")).thenThrow(Exception.class);

		mockMvc.perform(
				post("/fabricSupplier/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson));
	}

	@Test
	public void testDelete1FabricSupplier() throws Exception {
		mockMvc.perform(post("/fabricSupplier/delete/{fabricSupplierCode}", "fsc1")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDelete1FabricSupplierException() throws Exception {
		when(ser.delete("fsc1")).thenThrow(Exception.class);
		mockMvc.perform(post("/fabricSupplier/delete/{fabricSupplierCode}", "fsc1")).andExpect(
				status().isOk());
	}

	@Test
	public void testIsFabricSupplierExist() throws Exception {
		mockMvc.perform(get("/fabricSupplier/isExist/{fabricSupplierCode}", "admin")).andExpect(
				status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsFabricSupplierExistException() throws Exception {
		when(ser.isFabricSupplierExistedById("admin")).thenThrow(Exception.class);
		mockMvc.perform(get("/fabricSupplier/isExist/{fabricSupplierCode}", "admin")).andExpect(
				status().isOk());
	}
}
