package com.chori.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
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
import com.chori.model.AccessoryAddModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccessoryServiceTest {

	static AbstractApplicationContext context;
	static AccessoryService ser;
	
	@Mock
    SessionFactory sessionFactory;
	
	@InjectMocks
	AccessoryServiceImpl accessoryServiceImpl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (AccessoryService) context.getBean("accessoryService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1GetAllAccessory() {
		assertEquals(true, ser.getAllAccessory().size()>=0);
	}

	@Test(expected=Exception.class)
	public void test1GetAllAccessoryException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		accessoryServiceImpl.getAllAccessory();
	}
	
	@Test
	public void test3DeleteAccessory() {
		assertEquals(true, ser.deleteAccessory("BTN2"));
	}
	
	@Test(expected=Exception.class)
	public void test3DeleteAccessoryException() {
		ser.deleteAccessory(null);
	}

	@Test
	public void test2AddNewAccessory() {
		AccessoryAddModel aam= new AccessoryAddModel();
		
		aam.setAccessorycode("BTN2");
		aam.setColorcode("Black");
		aam.setUnitcode("Pcs");
		aam.setName("Button 2");
		aam.setKind("Internal");
		aam.setDimension("2x3");
		aam.setMode("Packing");
		aam.setImgurl1("a.jpg");
		aam.setImgurl2("s.jpg");
		aam.setCreatedate(new Date());
		
		assertEquals(true, ser.addNewAccessory(aam, "admin"));
	}
	
	@Test(expected=Exception.class)
	public void test2AddNewAccessoryException() {
		ser.addNewAccessory(null, null);
	}

	@Test
	public void test4FindAccessoryModelById() {
		assertNotNull(ser.findAccessoryModelById("BTW1"));
	}
	
	@Test(expected=Exception.class)
	public void test4FindAccessoryModelByIdException() {
		ser.findAccessoryModelById(null);
	}

	@Test
	public void test5EditAccessory() {
		//case image not null
		AccessoryAddModel aam= new AccessoryAddModel();
		
		aam.setAccessorycode(ser.findAccessoryModelById("BTW1").getAccessorycode());
		aam.setColorcode("Black");
		aam.setUnitcode("Pcs");
		aam.setName("Button White 1");
		aam.setKind("Internal");
		aam.setDimension("2x3");
		aam.setMode("Packing");
		aam.setImgurl1("a.jpg");
		aam.setImgurl2("s.jpg");
		
		assertEquals(true, ser.editAccessory(aam, "admin"));
		
		//case img null
		AccessoryAddModel aam1= new AccessoryAddModel();
		
		aam1.setAccessorycode("BTN2");
		aam1.setColorcode("Black");
		aam1.setUnitcode("Pcs");
		aam1.setCreatedate(new Date());
		
		assertEquals(true, ser.addNewAccessory(aam1, "admin"));
		aam1.setImgurl1(null);
		aam1.setImgurl2(null);
		assertEquals(true, ser.editAccessory(aam1, "admin"));
		ser.deleteAccessory("BTN2");
	}
	
	@Test(expected=Exception.class)
	public void test5EditAccessoryException() {
		ser.editAccessory(null, null);
	}

	@Test
	public void test6IsAccessoryExistedById() {
		assertEquals(true, ser.isAccessoryExistedById("BTW1"));
		assertEquals(false, ser.isAccessoryExistedById("BTW11"));
	}

	@Test(expected=Exception.class)
	public void test6IsAccessoryExistedByIdException() {
		ser.isAccessoryExistedById(null);
	}
}
