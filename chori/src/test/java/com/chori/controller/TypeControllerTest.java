package com.chori.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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

import com.chori.model.TypeModel;
import com.chori.model.UnitModel;
import com.chori.service.TypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TypeControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	private TypeController controller;

	@Mock
	TypeService ser;


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
	public void testGetAllType() throws Exception {
		mockMvc.perform(get("/type/list")).andExpect(status().isOk());
		}
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
		public void testGetAllException() throws Exception {
			when(ser.getAllTypeModel()).thenThrow(Exception.class);
			mockMvc.perform(get("/type/list"));
		}

	@Test
	public void testListType() throws Exception {
		mockMvc.perform(get("/listtype")).andExpect(status().isOk());
	}

	@Test
	public void testGetTypeDetail() throws Exception {
		mockMvc.perform(get("/type/detail/{typeCode}","Youth")).andExpect(status().isOk());

		}
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
		public void testGetDetailException() throws Exception {
			when(ser.findTypeModelById("spc")).thenThrow(Exception.class);
			mockMvc.perform(get("/type/detail/{typeCode}","spc"));

		}


	@Test
	public void testEditType() throws Exception {
		TypeModel um = new TypeModel();
		um.setDescription("zxzxa1");
		um.setCreatedDate(new Date());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/type/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditException() throws Exception {
		TypeModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.editType(um, "admin")).thenThrow(Exception.class);

		mockMvc.perform(post("/type/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}
	

	@Test
	public void testDeleteType() throws Exception {
		mockMvc.perform(post("/type/delete/{typeCode}", "Youth").contentType(
				MediaType.APPLICATION_JSON));
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDeletexception() throws Exception {
		when(ser.deleteType("user1")).thenThrow(Exception.class);
		mockMvc.perform(post("/type/delete/{typeCode}", "user1").contentType(
				MediaType.APPLICATION_JSON));
	}

	@Test
	public void testAddType() throws Exception {
		TypeModel um = new TypeModel();
		um.setTypeCode("grs");
		um.setDescription("Gross");
		um.setCreatedDate(new Date());

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/type/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddException() throws Exception {
		TypeModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.addType(um,"admin")).thenThrow(Exception.class);

		mockMvc.perform(post("/type/add").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}


	@Test
	public void testIsTypeExist() throws Exception {
		mockMvc.perform(get("/type/isExist/{typeCode}", "Youth")).andExpect(
				status().isOk());	
		}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsExistException() throws Exception {
		when(ser.isTypeExistedById("typeCode")).thenThrow(Exception.class);
		mockMvc.perform(get("/type/isExist/{typeCode}", "typeCode"));
	}

}
