//package com.chori.controller;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.View;
//
//import com.chori.model.AccessorySupplierModel;
//import com.chori.model.FactoryModel;
//import com.chori.service.AccessorySupplierService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.fasterxml.jackson.databind.SerializationFeature;
//
//public class AccessorySupplierControllerTest {
//
//	private MockMvc mockMvc;
//
//	@InjectMocks
//	private AccessorySupplierController controller;
//	
//	@Mock
//	AccessorySupplierService ser;
//
//	@Mock
//	View mockView;
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(controller)
//				.setSingleView(mockView).build();
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void testListAccessorySupplier() throws Exception {
//		mockMvc.perform(get("/listaccessorysupplier")).andExpect(status().isOk());
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testListAccessorySupplierException() throws Exception {
//		when(ser.getAllAccessorySupplierModel()).thenThrow(Exception.class);
//		mockMvc.perform(get("/accessorysupplier/list")).andExpect(status().isOk());
//	}
//
//	@Test
//	public void testGetAllAccessorySupplier() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddNewAccessorySupplier() throws Exception {
//		AccessorySupplierModel fsm = new AccessorySupplierModel();
//		fsm.setAccessorysuppliercode("FS01");
//		fsm.setShortname("FabSup01");
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//		String requestJson = ow.writeValueAsString(fsm);
//
//		mockMvc.perform(
//				post("/accessorysupplier/add").contentType(MediaType.APPLICATION_JSON)
//						.content(requestJson)).andExpect(status().isOk());
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testAddNewAccessorySupplierException() throws Exception {
//		AccessorySupplierModel fsm = null;
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//		String requestJson = ow.writeValueAsString(fsm);
//		
//		when(ser.addNewAccSup(fsm, "admin")).thenThrow(Exception.class);
//
//		mockMvc.perform(
//				post("/accessorysupplier/add").contentType(MediaType.APPLICATION_JSON)
//						.content(requestJson)).andExpect(status().isOk());
//	}
//	
//	@Test
//	public void testGetAccessorySupplierDetail() throws Exception {
//		mockMvc.perform(get("/accessorysupplier/detail/{accsupplierCode}", "fsc")).andExpect(
//				status().isOk());
//	}
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testGetAccessorySupplierDetailException() throws Exception {
//		when(ser.findAccessorySupModelById("fsc")).thenThrow(Exception.class);
//		mockMvc.perform(get("/accessorysupplier/detail/{accsupplierCode}", "fsc")).andExpect(
//				status().isOk());
//	}
//
//	@Test
//	public void testEditAccessorySupplier() throws Exception {
//		AccessorySupplierModel fsm = new AccessorySupplierModel();
//		fsm.setAccessorysuppliercode("FS01");
//		fsm.setShortname("FabSup01");
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//		String requestJson = ow.writeValueAsString(fsm);
//
//		mockMvc.perform(
//				post("/accessorysupplier/edit").contentType(MediaType.APPLICATION_JSON)
//						.content(requestJson)).andExpect(status().isOk());
//	}
//
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testEditAccessorySupplierException() throws Exception {
//		AccessorySupplierModel fsm = null;
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//		String requestJson = ow.writeValueAsString(fsm);
//
//		when(ser.editAccSup(fsm, "admin")).thenThrow(Exception.class);
//		
//		mockMvc.perform(
//				post("/accessorysupplier/edit").contentType(MediaType.APPLICATION_JSON)
//						.content(requestJson)).andExpect(status().isOk());
//	}
//	
//	@Test
//	public void testDeleteAccessorySupplier() throws Exception {
//		mockMvc.perform(post("/accessorysupplier/delete/{accsupplierCode}", "fsc1")).andExpect(
//				status().isOk());
//	}
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testDeleteAccessoryException() throws Exception {
//		when(ser.delete("fsc1")).thenThrow(Exception.class);
//		mockMvc.perform(post("/accessorysupplier/delete/{accsupplierCode}", "fsc1")).andExpect(
//				status().isOk());
//	}
//
//	@Test
//	public void testIsAccSupExist() throws Exception {
//		mockMvc.perform(get("/accessorysupplier/isExist/{accsupplierCode}", "admin")).andExpect(
//				status().isOk());
//	}
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testIsAccSupExistException() throws Exception {
//		when(ser.isAccSupExistedById("admin")).thenThrow(Exception.class);
//		mockMvc.perform(get("/accessorysupplier/isExist/{accsupplierCode}", "admin")).andExpect(
//				status().isOk());
//	}
//	
//}
