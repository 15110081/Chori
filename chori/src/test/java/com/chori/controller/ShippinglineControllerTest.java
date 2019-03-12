package com.chori.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hibernate.SessionFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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

import com.chori.entity.AccessoryconsumptionId;
import com.chori.model.AccessoryConsumptionModel;
import com.chori.model.ShippinglineModel;
import com.chori.service.ShippinglineService;
import com.chori.validator.ShippingLineValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ShippinglineControllerTest {
	
    private MockMvc mockMvc;
    @InjectMocks
    ShippinglineController controller;
    @Mock
    ShippinglineService ser;
	@Mock
	View mockView;
    @Mock
    ShippingLineValidator validator;
    
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
		 when(validator.supports(Class.class)).thenReturn(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testListShippingline() throws Exception {
//	       mockMvc.perform(get("/listShippingline"))
//           .andDo(print())
//           .andExpect(status().isOk())
//           .andExpect(view().name("configuration/shippingline/listShippingline"));         
	       mockMvc.perform(get("/listShippingline")).andExpect(status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testShowListShippinglineException() throws Exception {
		when(controller.listShippingline()).thenThrow(Exception.class);
		mockMvc.perform(get("/listShippingline"));
	}
	

	@Test
	public void testGetAllShippingLine() throws Exception {
		mockMvc.perform(get("/shippingline/list")).andExpect(status().isOk());

	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllShippingLineException() throws Exception {
		when(controller.getAllShippingLine()).thenThrow(Exception.class);
		mockMvc.perform(get("/shippingline/list"));
	}
	
	@Test
	public void testGetShippingLineDetail() throws Exception {
	       mockMvc.perform(get("/shippingline/detail/{shippinglineCode}","SPL0001"))
           .andDo(print())
           .andExpect(status().isOk());
	}
    
    @SuppressWarnings("unchecked")
	@Test(expected=Exception.class)
    public void testGetShippingLineDetailException() throws Exception {
		when(ser.findShippingLineModelById("SPLXXXX")).thenThrow(
				Exception.class);
		mockMvc.perform(get("/shippingline/detail/{shippinglineCode}","SPLXXXX")).andExpect(status().isOk());
    }  

	@Test
	public void testAddNewShippingLine() throws Exception {
		ShippinglineModel spl = new ShippinglineModel();

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(spl);

		mockMvc.perform(
				post("/shippingline/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());	
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddNewShippingLineException() throws Exception {
		ShippinglineModel spl = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(spl);

		when(controller.addNewShippingLine(spl)).thenThrow(Exception.class);

		mockMvc.perform(post("/shippingline/add").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}



	@Test
	public void testEditShippingline() throws Exception {
		ShippinglineModel spl = new ShippinglineModel();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(spl);

		mockMvc.perform(
				post("/shippingline/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditShippinglineException() throws Exception {
		ShippinglineModel spl = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(spl);

		when(controller.editShippingline(spl)).thenThrow(Exception.class);

		mockMvc.perform(post("/shippingline/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}
	
	@Test
	public void testDeleteShippingline() throws Exception {
		String shippinglineCode = "SPL0001";
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(shippinglineCode);

		mockMvc.perform(
				post("/shippingline/delete/{shippinglineCode}",shippinglineCode).contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	
	}
	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testDeleteShippinglineException() throws Exception {
////		String shippinglineCode = null;
////
////		ObjectMapper mapper = new ObjectMapper();
////		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
////		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
////		String requestJson = ow.writeValueAsString(shippinglineCode);
////
////		when(controller.deleteShippingline(shippinglineCode)).thenThrow(Exception.class);
////
////		mockMvc.perform(post("/shippingline/delete/{shippinglineCode}",213).contentType(
////				MediaType.APPLICATION_JSON).content(requestJson));
////	
//		when(controller.deleteShippingline("wqewqe")).thenThrow(Exception.class);
//		mockMvc.perform(post("/shippingline/delete/{shippinglineCode}", "wqewqe"));
//	}

}
