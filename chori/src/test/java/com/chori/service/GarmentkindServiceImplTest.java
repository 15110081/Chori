package com.chori.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.chori.configuration.AppConfig;
import com.chori.model.DestinationModel;
import com.chori.model.GarmentkindModel;

public class GarmentkindServiceImplTest {
	
	static AbstractApplicationContext context;
	static GarmentkindService ser;
	
	  @Mock
	    SessionFactory sessionFactory;
	  @InjectMocks
	   GarmentkindServiceImpl gMK;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (GarmentkindService) context.getBean("garmentkindService");
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
//
//	@Test
//	public void testGarmentkindServiceImpl() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGarmentkindServiceImplAbstractDaoOfGarmentkindStringGarmentkindDao() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testIsGarmentkindExistedById() {
		assertEquals(true, ser.isGarmentkindExistedById("Shett"));
		assertEquals(false, ser.isGarmentkindExistedById("T1"));
	}
	@Test(expected=RuntimeException.class)
	public void testIsGarmentkindExistedByIdException() {
		ser.isGarmentkindExistedById(null);
	}

	@Test
	public void testGetAllGarmentkind() {
		assertEquals(true, ser.getAllGarmentkind().size()>=0);
	}
	@Test(expected=RuntimeException.class)
	public void testGetAllGarmentkindException(){
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		assertNull(gMK.getAllGarmentkind());
	}

	@Test
	public void testAddGarmentkind() {
		GarmentkindModel gmk = new GarmentkindModel();
		gmk.setGarmentkindcode("add1");
		gmk.setDescription("add");
		
		assertEquals(true, ser.addGarmentkind(gmk, "admin"));
	}
	@Test(expected=RuntimeException.class)
	public void testAddGarmentkindException() {
		GarmentkindModel gmk = new GarmentkindModel();
		gmk.setGarmentkindcode(null);
		//gmk.setDescription("add");
		
		assertEquals(false, ser.addGarmentkind(gmk, "admin"));
	}

	@Test
	public void testFindGarmentkindModelById() {
		GarmentkindModel gmk= ser.findGarmentkindModelById("Shett");
		assertEquals(true, gmk.getGarmentkindcode().equals("Shett"));
		gmk= ser.findGarmentkindModelById("T-Shirt");
	}
	@Test(expected=RuntimeException.class)
	public void testFindGarmentkindModelByIdException() {
		GarmentkindModel gmk= ser.findGarmentkindModelById(null);
		assertNull(gmk);
	}

	@Test
	public void testEditGarmentkind() {
		GarmentkindModel gmk = ser.findGarmentkindModelById("Shett");
		gmk.setDescription("updateGarmentkind");
		assertEquals(true, ser.editGarmentkind(gmk));
	}
	@Test(expected=Exception.class)
	public void testEditGarmentkindException() {
		GarmentkindModel gmk = new GarmentkindModel();
		gmk.setDescription("update Failed");
		assertEquals(false, ser.editGarmentkind(gmk));
	}
	
	@Test
	public void testDeleteGarmentkind() {
		ser.deleteGarmentkind("vn");
		assertEquals(ser.findById("BL"),null);
	}
	@Test(expected=RuntimeException.class)
	public void testDeleteGarmentkindException() {
		assertEquals(false,ser.deleteGarmentkind(""));
	}
}
