package com.chori.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.chori.configuration.AppConfig;
import com.chori.entity.AccessoryconsumptionId;
import com.chori.model.AccessoryConsumptionModel;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccessoryConsumptionServiceTest {
	
	static AbstractApplicationContext context;
	static AccessoryConsumptionService ser;
	
	@Mock
    SessionFactory sessionFactory;
	@InjectMocks
	AccessoryConsumptionServiceImpl accessoryConsumptionServiceImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (AccessoryConsumptionService) context.getBean("accessoryconsumptionService");	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1GetAllAccessoryConsumption() {
		assertEquals(true, ser.getAllAccessoryConsumption().size()>=0);
	}

	@Test(expected=RuntimeException.class)
	public void test1GetAllAccessoryConsumptionException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		assertNull(accessoryConsumptionServiceImpl.getAllAccessoryConsumption());
	}
	
	@Test
	public void test2AddAccessoryConsumption() {
		AccessoryConsumptionModel accessoryConsumptionModel = new AccessoryConsumptionModel();
		accessoryConsumptionModel.setFactorycode("FAC0002");
		accessoryConsumptionModel.setAccessorycode("BTNR");
		accessoryConsumptionModel.setConsumption((float)1.2);
		assertEquals(true, ser.addAccessoryConsumption(accessoryConsumptionModel, "admin"));
	}
	
	@Test(expected=Exception.class)
	public void test3AddAccessoryConsumptionException() {
		assertEquals(true, ser.addAccessoryConsumption(null, null));
	}

	@Test
	public void test4FindAccessoryConsumptionModelById() {
		AccessoryconsumptionId accessoryconsumptionId = new AccessoryconsumptionId("FAC0002","BTNR");
		assertNotNull(ser.findAccessoryConsumptionModelById(accessoryconsumptionId));
	}
	
	@Test(expected=Exception.class)
	public void test5FindAccessoryConsumptionModelByIdException() {
		assertEquals(true, ser.findAccessoryConsumptionModelById(null));
	}
	
	@Test
	public void test6EditAccessoryConsumption() {
		AccessoryConsumptionModel accessoryConsumptionModel = new AccessoryConsumptionModel();
		accessoryConsumptionModel.setFactorycode("FAC0002");
		accessoryConsumptionModel.setAccessorycode("BTNR");
		accessoryConsumptionModel.setConsumption((float)1.6);
		assertEquals(true, ser.editAccessoryConsumption(accessoryConsumptionModel));
	}
	
	@Test(expected=Exception.class)
	public void test7EditAccessoryConsumptionException() {
		assertEquals(true, ser.editAccessoryConsumption(null));
	}
	
	@Test
	public void test8IsAccessoryConsumptionExistedById() {
		AccessoryconsumptionId accessoryconsumptionId = new AccessoryconsumptionId("FAC0002","BTNR");
		assertEquals(true,ser.isAccessoryConsumptionExistedById(accessoryconsumptionId));
	}
	
	@Test(expected=Exception.class)
	public void test9IsAccessoryConsumptionExistedByIdException() {
		AccessoryconsumptionId accessoryconsumptionId = new AccessoryconsumptionId("Test","Test");
		assertEquals(false,ser.isAccessoryConsumptionExistedById(accessoryconsumptionId));
	}
	
	@Test
	public void testADeleteAccessoryConsumption() {
		AccessoryconsumptionId accessoryconsumptionId = new AccessoryconsumptionId();
		accessoryconsumptionId.setFactorycode("FAC0002");
		accessoryconsumptionId.setAccessorycode("BTNR");
//		Accessoryconsumption acc = new Accessoryconsumption();
//		acc.setFactorycode("FAC0002");
//		acc.setAccessorycode("BTNR");
		assertEquals(true, ser.deleteAccessoryConsumption(accessoryconsumptionId));
	}
	
	
	@Test(expected=Exception.class)
	public void testBDeleteAccessoryConsumptionException() {
		assertEquals(true, ser.deleteAccessoryConsumption(null));
	}
}
