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

import com.chori.model.SignatureAddModel;
import com.chori.model.SignatureModel;
import com.chori.model.UserModel;
import com.chori.service.AccessoryordersignatureService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;


public class AccOrSignatureControllerTest {
	
	private MockMvc mockMvc;

	@InjectMocks
	private AccOrSignatureController controller;

	@Mock
	AccessoryordersignatureService ser;

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
	public void testListSignaturePage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllSignature() throws Exception {
		mockMvc.perform(get("/signature/list")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllSignatureException() throws Exception {
		when(ser.getAllSignature()).thenThrow(Exception.class);
		mockMvc.perform(get("/signature/list"));
	}

	@Test
	public void testDelete1Signature() throws Exception {
		mockMvc.perform(post("/signature/delete/{accordersignCode}",13)).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDelete1SignatureException() throws Exception {
		when(ser.deleteSignature(14)).thenThrow(Exception.class);
		mockMvc.perform(post("/signature/delete/{accordersignCode}",14));
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddNewSignatureException() throws Exception {
		SignatureAddModel sm = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sm);

		when(ser.addNewSignature(sm, "admin")).thenThrow(Exception.class);

		mockMvc.perform(post("/signature/addNewSignature").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}

	@Test
	public void testAddNewSignaturePost() throws Exception{
		SignatureModel sm = new SignatureModel();
		sm.setAccordersigncode(13);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sm);

		mockMvc.perform(
				post("/signature/addNewSignature").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddNewSignaturePostException() throws Exception {
		SignatureAddModel sm = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sm);

		when(ser.addNewSignature(sm, "admin")).thenThrow(Exception.class);

		mockMvc.perform(post("/signature/addNewSignature").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}

	@Test
	public void testGet1SignatureModelDetail() throws Exception {
		mockMvc.perform(get("/signature/detail/{accordersigncode}", 13)).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGet1SignatureModelDetailException() throws Exception {
		when(ser.findSignatureModelById(14)).thenThrow(Exception.class);
		mockMvc.perform(get("/signature/detail/{accordersigncode}", 14));
	}

	@Test
	public void testEditSignaturePost() throws Exception {
		SignatureModel sm = new SignatureModel();
		sm.setAccordersigncode(13);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sm);

		mockMvc.perform(
				post("/signature/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditSignaturePostException() throws Exception {
		SignatureAddModel sm = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(sm);

		when(ser.editSignature(sm, "admin")).thenThrow(Exception.class);

		mockMvc.perform(post("/signature/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}

	@Test
	public void testIsSignatureExist() throws Exception {
		mockMvc.perform(get("/signature/isExist/{accordersigncode}", 13)).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsSignatureExistException() throws Exception {
		when(ser.isSignatureExistedById(14)).thenThrow(Exception.class);
		mockMvc.perform(get("/signature/isExist/{accordersigncode}", 14));
	}

}
