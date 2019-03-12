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
import com.chori.model.CurrencyexchangeModel;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrencyExchangeServiceImplTest {
	
	static AbstractApplicationContext context;
	static CurrencyexchangeService service;
	@Mock
    SessionFactory sessionFactory;
	@InjectMocks
	CurrencyexchangServiceImpl eS;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		service= (CurrencyexchangeService) context.getBean("currencyexchangeService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}

//	@Test
//	public void testWidthServiceImpl() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testWidthServiceImplAbstractDaoOfWidthString() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testgetAllCurrencyexchangeModel() {
		assertEquals(true,service.getAllCurrencyexchangeModel().size()>0);		
	}
	
	@Test(expected=RuntimeException.class)
	public void testgetAllCurrencyexchangeModelException() {
	MockitoAnnotations.initMocks(this);
	sessionFactory = mock(SessionFactory.class);
	when(sessionFactory.getCurrentSession()).thenReturn(null);
	assertNull(eS.getAllCurrencyexchangeModel());
	}

	@Test
	public void testfindCurrencyexchangeById() {
		assertNotNull(service.findCurrencyexchangeById(2));
	}
	@Test(expected=RuntimeException.class)
	public void testFindCurrencyexchangeById() {
		assertNotNull(service.findCurrencyexchangeById(-1));
	}
	@Test
	public void testDeleteCurrencyexchange() {
		service.deleteCurrencyexchange(2);
		assertEquals(service.findById(2),null);
	}
	@Test(expected=RuntimeException.class)
	public void testDeleteWidthException() {
		assertEquals(false,service.deleteCurrencyexchange(-1));
	}
	
//	@Test
//	public void testAddWidth() {
//		CurrencyexchangeModel cur = new CurrencyexchangeModel();
//		cur.setCurrencyexchangecode(9);
//		cur.setAmount(200000);
//		cur.setCurrencycodedestination("USD");
//		cur.setCurrencycodesource("VND");
//		cur.setExchangedate(new Date());
//		assertEquals(true,service.addCurrencyexchange(cur, "admin"));
//	
//	}

	@Test(expected=RuntimeException.class)
	public void testSaveException() {
		CurrencyexchangeModel cur = new CurrencyexchangeModel();
		cur.setCurrencyexchangecode(-1);
		
		assertEquals(false, service.addCurrencyexchange(cur, "admin"));
	}

	@Test
	public void testIsCurrencyexchangeExistedById() {
		assertEquals(true, service.isCurrencyexchangeExistedById(7));
		assertEquals(false, service.isCurrencyexchangeExistedById(-1));
	}
	@Test(expected=RuntimeException.class)
	public void testIsCurrencyexchangeExistedByIdException() {
		service.isCurrencyexchangeExistedById(-1);
	}

//	@Test
//	public void testEditCurrencyExchange() {
//		CurrencyexchangeModel cur = service.findCurrencyexchangeById(3);
//		cur.setCurrencyexchangecode(3);
//		cur.setAmount(200000);
//		cur.setCurrencycodedestination("USD");
//		cur.setCurrencycodesource("VND");
//		cur.setExchangedate(new Date());
//		assertEquals(200000,service.findCurrencyexchangeById(3).getAmount());
//	}
	@Test(expected=RuntimeException.class)
	public void testEditCurrencyExchangeException() {
		CurrencyexchangeModel cur = new CurrencyexchangeModel();
		cur.setCurrencyexchangecode(-1);
		assertEquals(false,service.editCurrencyexchange(cur, ""));
	}
}
