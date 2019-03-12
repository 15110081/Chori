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
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.chori.configuration.AppConfig;
import com.chori.model.AccessoryPriceModel;

public class AccessoryPriceServiceImplTest {
	static AbstractApplicationContext context;
	static AccessoryPriceService service;
	@Mock
    SessionFactory sessionFactory;
	@InjectMocks
	AccessoryPriceServiceImpl eS;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		service= (AccessoryPriceService) context.getBean("accessorypriceService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void testAccessoryPriceServiceImpl() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetAllAccessoryPrice() {
		assertEquals(true,service.getAllAccessoryPrice().size()>0);		
	}
	@Test(expected=RuntimeException.class)
	public void testGetAllAccessoryPriceException() {
	MockitoAnnotations.initMocks(this);
	sessionFactory = mock(SessionFactory.class);
	when(sessionFactory.getCurrentSession()).thenReturn(null);
	assertNull(eS.getAllAccessoryPrice());
	}

	@Test
	public void testAddAccessoryPrice() {
		AccessoryPriceModel acc = new AccessoryPriceModel();
		acc.setAccessorycode("BX3");
		acc.setAccessorysuppliercode("AS0002");
		acc.setFromdate(new Date());
		acc.setTodate(new Date());
		acc.setUnitpriceperunit((float) 300);
		acc.setCurrencycode("VND");
		acc.setCreatedate(new Date());
		assertEquals(true,service.addAccessoryPrice(acc, "admin"));
	}
	@Test(expected=RuntimeException.class)
	public void testAddAccessoryPriceException() {
		AccessoryPriceModel acc = new AccessoryPriceModel();
		acc.setAccessorycode("XX");
		acc.setAccessorysuppliercode("XX");
		acc.setCurrencycode("XX");
		acc.setCreatedate(new Date());
		assertEquals(true,service.addAccessoryPrice(acc, "admin"));
	}

	@Test
	public void testFindAccessoryPriceModelById() {
		assertNotNull(service.findAccessoryPriceModelById(275));
	}
	@Test(expected=RuntimeException.class)
	public void testFindAccessoryPriceModelByIdException() {
		assertNotNull(service.findAccessoryPriceModelById(null));
	}
	@Test
	public void testEditAccessoryPrice() {
		AccessoryPriceModel acc = service.findAccessoryPriceModelById(276);
		acc.setAccessorypricecode(276);
		acc.setAccessorycode("BTNR");
		acc.setAccessorysuppliercode("AS0002");
		acc.setFromdate(new Date());
		acc.setTodate(new Date());
		acc.setUnitpriceperunit((float) 300);
		acc.setCurrencycode("VND");
		acc.setCreatedate(new Date());
		assertEquals(true,service.editAccessoryPrice(acc));
	}
	@Test(expected= RuntimeException.class)
	public void testEditAccessoryPriceException() {
		AccessoryPriceModel acc = new AccessoryPriceModel();
		acc.setAccessorypricecode(-1);;
		assertEquals(false,service.editAccessoryPrice(acc));
	}

//	@Test
//	public void testEditAccessoryPriceValidate() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testDeleteAccessoryprice() {
		service.deleteAccessoryprice(275);
		assertEquals(service.findAccessoryPriceModelById(275),null);
	}
	@Test(expected=RuntimeException.class)
	public void testDeleteAccessorypriceException() {
		assertEquals(false,service.deleteAccessoryprice(-1));
	}
}
