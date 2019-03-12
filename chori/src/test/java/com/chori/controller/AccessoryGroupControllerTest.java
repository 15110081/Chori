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

import com.chori.model.AccessoryGroupModel;
import com.chori.model.UnitModel;
import com.chori.service.AccessoryGroupService;
import com.chori.service.UnitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class AccessoryGroupControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	private AccessoryGroupController controller;

	@Mock
	AccessoryGroupService ser;


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
	public void testGetAllAccessoryGroup() throws Exception {
		mockMvc.perform(get("/accessorygroup/list")).andExpect(status().isOk());
		}
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
		public void testGetAllException() throws Exception {
			when(ser.getAllAccessoryGroup()).thenThrow(Exception.class);
			mockMvc.perform(get("/accessorygroup/list"));
		}
	@Test
	public void testListAccessoryGroup() throws Exception {
		mockMvc.perform(get("/listAccessoryGroup")).andExpect(status().isOk());
	}

	@Test
	public void testDeleteAccessoryGroup() throws Exception{
		mockMvc.perform(post("/accessorygroup/delete/{accessorygroupCode}", "Box2").contentType(
				MediaType.APPLICATION_JSON));
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDeletexception() throws Exception {
		when(ser.deleteAccessoryGroup("a")).thenThrow(Exception.class);
		mockMvc.perform(post("/accessorygroup/delete/{accessorygroupCode}", "a").contentType(
				MediaType.APPLICATION_JSON));
	}

	

	@Test
	public void testIsAccessoryGroupExist() throws Exception {
		mockMvc.perform(get("/accessorygroup/isExist/{accessorygroupCode}", "BOX")).andExpect(
				status().isOk());	
		}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsExistException() throws Exception {
		when(ser.isAccessoryGroupExistedById("Code")).thenThrow(Exception.class);
		mockMvc.perform(get("/accessorygroup/isExist/{accessorygroupCode}", "a"));
	}

	@Test
	public void testAddAccessoryGroup() throws Exception {
		AccessoryGroupModel um = new AccessoryGroupModel();
		um.setAccessorygroupCode("Box3");
		um.setDescription("Desss");
		um.setCreatedDate(new Date());

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/accessorygroup/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddException() throws Exception {
		AccessoryGroupModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.addAccessoryGroup(um, "admin")).thenThrow(Exception.class);

		mockMvc.perform(post("/accessorygroup/add").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}


	@Test
	public void testGetAccessoryGroupDetail() throws Exception {
		mockMvc.perform(get("/accessorygroup/detail/{accessorygroupCode}","BOX")).andExpect(status().isOk());

		}
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
		public void testGetDetailException() throws Exception {
			when(ser.findAccessoryGroupById("BOX")).thenThrow(Exception.class);
			mockMvc.perform(get("/accessorygroup/detail/{accessorygroupCode}","BOX"));
		}
	@Test
	public void testEditAccessoryGroup() throws Exception {
		AccessoryGroupModel um = new AccessoryGroupModel();
		um.setDescription("BOX");
		um.setCreatedDate(new Date());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/accessorygroup/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditException() throws Exception {
		AccessoryGroupModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.editAccessoryGroup(um)).thenThrow(Exception.class);

		mockMvc.perform(post("/accessorygroup/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}
	
}
