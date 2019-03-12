package com.chori.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

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

import com.chori.model.PiassignexternalaccessoryModel;
import com.chori.service.PiassignexternalaccessoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class AssignExternalAccessoryForPiControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private AssignExternalAccessoryForPiController controller;
	
	@Mock
	PiassignexternalaccessoryService ser;
	
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
	public void testListAssignExternalAccessoryForPi() throws Exception {
		mockMvc.perform(get("/listAssignExternalAccessoryForPi")).andExpect(status().isOk());
	}

	@Test
	public void testGet1ListAssignExternalAccessory1stTime() throws Exception {
		mockMvc.perform(get("/listAssignExternalAccessory1stTime/{lotNumber}", "LOT1")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGet1ListAssignExternalAccessory1stTimeException() throws Exception {
		when(ser.getListPiassignexternalaccessoryModelWhenPressAssign("LOT1")).thenThrow(Exception.class);
		mockMvc.perform(get("/listAssignExternalAccessory1stTime/{lotNumber}", "LOT1"));
	}

	@Test
	public void testIsPiAssignedExternalAccessory() throws Exception {
		mockMvc.perform(get("/assignExternalAccessory/isAssigned/{lotNumber}", "LOT1")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsPiAssignedExternalAccessoryException() throws Exception {
		when(ser.isPiAssignedExternalAccessory("LOT1")).thenThrow(Exception.class);
		mockMvc.perform(get("/assignExternalAccessory/isAssigned/{lotNumber}", "LOT1"));
	}

	@Test
	public void testAdd1stTimePiAssignExternalAccessory() throws Exception {
		ArrayList<PiassignexternalaccessoryModel> lstNotAssign= new ArrayList<PiassignexternalaccessoryModel>();

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(lstNotAssign);
		
		mockMvc.perform(
				post("/assignExternalAccessory/add1stTime").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAdd1stTimePiAssignExternalAccessoryException() throws Exception {
		ArrayList<PiassignexternalaccessoryModel> lstNotAssign= new ArrayList<PiassignexternalaccessoryModel>();

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(lstNotAssign);
		
		when(ser.add1stTimePiAssignExternalAccessory(lstNotAssign, "admin")).thenThrow(Exception.class);
		
		mockMvc.perform(
				post("/assignExternalAccessory/add1stTime").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson));
	}

	@Test
	public void testGetListPiassignexternalaccessoryByLotNumber() throws Exception {
		mockMvc.perform(get("/listPiassignexternalaccessoryByLotNumber/{lotNumber}", "LOT1")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetListPiassignexternalaccessoryByLotNumberException() throws Exception {
		when(ser.getListPiassignexternalaccessoryByLotNumber("LOT1")).thenThrow(Exception.class);
		mockMvc.perform(get("/listPiassignexternalaccessoryByLotNumber/{lotNumber}", "LOT1"));
	}

	@Test
	public void testGetListPiassignexternalaccessoryForEditByLotNumber() throws Exception {
		mockMvc.perform(get("/listPiassignexternalaccessoryForEditByLotNumber/{lotNumber}", "LOT1")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetListPiassignexternalaccessoryForEditByLotNumberException() throws Exception {
		when(ser.getListPiassignexternalaccessoryForEditByLotNumber("LOT1")).thenThrow(Exception.class);
		mockMvc.perform(get("/listPiassignexternalaccessoryForEditByLotNumber/{lotNumber}", "LOT1")).andExpect(
				status().isOk());
	}

	@Test
	public void testEditPiAssignExternalAccessory() throws Exception {
		ArrayList<PiassignexternalaccessoryModel> lstAssignOrNot= new ArrayList<PiassignexternalaccessoryModel>();

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(lstAssignOrNot);
		
		mockMvc.perform(
				post("/assignExternalAccessory/editPiAssignExternalAccessory").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditPiAssignExternalAccessoryException() throws Exception {
		ArrayList<PiassignexternalaccessoryModel> lstAssignOrNot= new ArrayList<PiassignexternalaccessoryModel>();

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(lstAssignOrNot);
		
		when(ser.editPiAssignExternalAccessory(lstAssignOrNot, "admin")).thenThrow(Exception.class);
		
		mockMvc.perform(
				post("/assignExternalAccessory/editPiAssignExternalAccessory").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson));
	}
}
