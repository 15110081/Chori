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

public class DestinationServiceImplTest {
	
	static AbstractApplicationContext context;
	static DestinationService ser;
	
	  @Mock
	    SessionFactory sessionFactory;
	  @InjectMocks
	   DestinationServiceImpl dS;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (DestinationService) context.getBean("destinationService");
		
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
//	public void testDestinationServiceImpl() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetAllDestination() {
		assertEquals(true, ser.getAllDestination().size()>=0);
	}
	@Test(expected=RuntimeException.class)
	public void testGetAllDestinationException(){
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		assertNull(dS.getAllDestination());
	}
	

	@Test
	public void testAddDestination() {
		DestinationModel des = new DestinationModel();
		des.setDestinationcode("add1");
		des.setDescription("add");
		assertEquals(true, ser.addDestination(des, "admin"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testAddDestinationException() {
		DestinationModel des= new DestinationModel();
		des.setDestinationcode(null);
		//des.setDescription("Lop Pho");
		assertEquals(false, ser.addDestination(des, "admin"));
	}


	@Test
	public void testFindDestinationModelById() {
		DestinationModel des= ser.findDestinationModelById("we");
		assertEquals(true, des.getDestinationcode().equals("we"));
		des= ser.findDestinationModelById("fr");
	}
	@Test(expected=Exception.class)
	public void testFindDestinationModelByIdException() {
		DestinationModel des= ser.findDestinationModelById(null);
		assertNull(des);
	}

	@Test
	public void testEditDestiantion() {
	DestinationModel des = ser.findDestinationModelById("fr");
	des.setDescription("update1");
	assertEquals(true, ser.editDestiantion(des));
	}
	@Test(expected=Exception.class)
	public void testEditDestiantionException() {
		DestinationModel des = new DestinationModel();
		des.setDescription("7");
		assertEquals(false, ser.editDestiantion(des));
	}

	@Test
	public void testIsDestinationExistedById() {
		assertEquals(true, ser.isDestinationExistedById("fr"));
		assertEquals(false, ser.isDestinationExistedById("T1"));
	}
	@Test(expected=Exception.class)
	public void testIsDestinationExistedByIdException() {
		ser.isDestinationExistedById(null);
	}
	
//	@Test
//	public void testDeleteDestination() {
//		ser.deleteDestination("vn");
//		assertEquals(ser.findById("BL"),null);
//	}
//	@Test(expected=RuntimeException.class)
//	public void testDeleteDestinationException() {
//		assertEquals(false,ser.deleteDestination(""));
//	}

}
