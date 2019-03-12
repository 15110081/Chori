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
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.chori.configuration.AppConfig;
import com.chori.model.CurrencyModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrencyServiceTest {

	static AbstractApplicationContext context;
	static CurrencyService ser;
	
	@InjectMocks
	static CurrencyServiceImpl curSer;
	
	@Mock
	SessionFactory sessionFactory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (CurrencyService) context.getBean("currencyService");
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
	public void test1GetAllCurrencyModel() {
		assertEquals(true, ser.getAllCurrencyModel().size()>=0);
	}
	
	@Test(expected=Exception.class)
	public void test1GetAllCurrencyModelException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		curSer.getAllCurrencyModel().size();
	}

	@Test
	public void test2AddNewCurrency() {
		CurrencyModel cm= new CurrencyModel();
		cm.setCurrencycode("XD");
		cm.setName("xdddd");
		assertEquals(true, ser.addNewCurrency(cm, "admin"));
	}
	
	@Test(expected=Exception.class)
	public void test2AddNewCurrencyException() {
		ser.addNewCurrency(null, null);
	}

	@Test
	public void test3FindCurrencyModelById() {
		assertNotNull(ser.findCurrencyModelById("YEN"));
		assertNotNull(ser.findCurrencyModelById("USD"));
	}
	
	@Test(expected=Exception.class)
	public void test3FindCurrencyModelByIdException() {
		ser.findCurrencyModelById(null);
	}

	@Test
	public void test4EditCurrency() {
		CurrencyModel cm= ser.findCurrencyModelById("XD");
		cm.setName("xdddd123");
		assertEquals(true, ser.editCurrency(cm, "admin"));
	}
	
	@Test(expected=Exception.class)
	public void test4EditCurrencyException() {
		ser.editCurrency(null, null);
	}

	@Test
	public void test5DeleteCurrency() {
		assertEquals(true, ser.deleteCurrency("XD"));
	}
	
	@Test(expected=Exception.class)
	public void test5DeleteCurrencyException() {
		ser.deleteCurrency(null);
	}

	@Test
	public void test6IsCurrencyExistedById() {
		assertEquals(true, ser.isCurrencyExistedById("YEN"));
		assertEquals(false, ser.isCurrencyExistedById("FabricSup11"));
	}

	@Test(expected=Exception.class)
	public void test6IsCurrencyExistedByIdException() {
		ser.isCurrencyExistedById(null);
	}
}
