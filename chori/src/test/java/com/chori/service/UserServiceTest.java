package com.chori.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.chori.configuration.AppConfig;
import com.chori.model.UserModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

	static AbstractApplicationContext context;
	static UserService ser;
	
	@Mock
    SessionFactory sessionFactory;
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (UserService) context.getBean("userService");	
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	@Test
	public void test1GetAllUserModel() {
		assertEquals(true, ser.getAllUserModel().size()>=0);
	}
	
	@Test(expected=Exception.class)
	public void test1GetAllUserModelException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		assertNull(userServiceImpl.getAllUserModel());
	}

	@Test
	public void test2AddNewUser() {
		UserModel userModel= new UserModel("user0002", 1, "", "AD", "", "admin", "0002", "first", "last", "0002@gmail.com", "xd", "0909789456", "Active");
		assertEquals(true, ser.addNewUser(userModel, "admin"));
	}
	
	@Test(expected= Exception.class)
	public void test2AddNewUserException() {
		UserModel userModel= null;
		ser.addNewUser(userModel, "admin");
	}

	@Test
	public void test3FindUserModelById() {
		assertNotNull(ser.findUserModelById("admin"));
		assertNotNull(ser.findUserModelById("user0001"));
	}
	
	@Test(expected= Exception.class)
	public void test3FindUserModelByIdException() {
		ser.findUserModelById(null);
	}

	@Test
	public void test4EditUser() {
		UserModel userModel= new UserModel("user0002", 1, "", "AD", "", "admin", "0002", "first", "last", "0002@gmail.com", "xd", "0909789456", "Active");
		assertEquals(true, ser.editUser(userModel, "admin"));
	}
	
	@Test(expected= Exception.class)
	public void test4EditUserException() {
		ser.editUser(null, null);
	}

	@Test
	public void test5DeleteString() {
		assertEquals(true, ser.delete("user0002"));
	}
	
	@Test(expected= Exception.class)
	public void test5DeleteStringException() {
		ser.delete("");
	}

	@Test
	public void test6IsUserExistedById() {
		assertEquals(true, ser.isUserExistedById("admin"));
		assertEquals(false, ser.isUserExistedById("admin1"));
	}

	@Test(expected= Exception.class)
	public void test6IsUserExistedByIdException() {
		ser.isUserExistedById(null);
	}
}
