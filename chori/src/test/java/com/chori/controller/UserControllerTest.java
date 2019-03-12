package com.chori.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.chori.model.UserModel;
import com.chori.service.RoleService;
import com.chori.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UserControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private UserController controller;

	@Mock
	UserService ser;

	@Mock
	RoleService roleSer;

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
	public void test0TestHandleRequest() throws Exception {
		mockMvc.perform(get("/userProfile")).andExpect(status().isOk());
	}

	@Test
	public void test1GetUserDetail() throws Exception {
		mockMvc.perform(get("/user/getUserDetail")).andExpect(status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void test1GetUserDetailException() throws Exception {
		when(ser.findUserModelById(LoginController.currentUser)).thenThrow(
				Exception.class);
		mockMvc.perform(get("/user/getUserDetail")).andExpect(status().isOk());
	}

	@Test
	public void test2ListUser() throws Exception {
		mockMvc.perform(get("/listUser")).andExpect(status().isOk());
		// case #
		when(roleSer.detectFunc4User(LoginController.currentUser, "FT1"))
				.thenReturn(true);
		mockMvc.perform(get("/listUser")).andExpect(status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void test2ListUserException() throws Exception {
		when(roleSer.detectFunc4User(LoginController.currentUser, "FT1"))
				.thenThrow(Exception.class);
		mockMvc.perform(get("/listUser"));
	}

	@Test
	public void test3GetAllUser() throws Exception {
		mockMvc.perform(get("/user/list")).andExpect(status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void test3GetAllUserException() throws Exception {
		when(ser.getAllUserModel()).thenThrow(Exception.class);
		mockMvc.perform(get("/user/list"));
	}

	@Test
	public void testAddNewUser() throws Exception {
		UserModel um = new UserModel();
		um.setUsername("user1");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/user/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddNewUserException() throws Exception {
		UserModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.addNewUser(um, "admin")).thenThrow(Exception.class);

		mockMvc.perform(post("/user/add").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}

	@Test
	public void testGet1UserDetail() throws Exception {
		mockMvc.perform(get("/user/detail/{username}", "admin")).andExpect(
				status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGet1UserDetailException() throws Exception {
		when(ser.findUserModelById("admin1")).thenThrow(Exception.class);
		mockMvc.perform(get("/user/detail/{username}", "admin1"));
	}

	@Test
	public void testEditUser() throws Exception {
		UserModel um = new UserModel();
		um.setUsername("user1");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		mockMvc.perform(
				post("/user/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditUserException() throws Exception {
		UserModel um = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(um);

		when(ser.editUser(um, "admin")).thenThrow(Exception.class);

		mockMvc.perform(post("/user/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}

	@Test
	public void testDelete1User() throws Exception {
		mockMvc.perform(post("/user/delete/{username}", "user1")).andExpect(
				status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDelete1UserException() throws Exception {
		when(ser.delete("user1")).thenThrow(Exception.class);
		mockMvc.perform(post("/user/delete/{username}", "user1"));
	}

	@Test
	public void testIsUserExist() throws Exception {
		mockMvc.perform(get("/user/isExist/{username}", "admin")).andExpect(
				status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsUserExistException() throws Exception {
		when(ser.isUserExistedById("admin1")).thenThrow(Exception.class);
		mockMvc.perform(get("/user/isExist/{username}", "admin1"));
	}
}
