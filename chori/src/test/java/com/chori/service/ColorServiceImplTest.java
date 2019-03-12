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
import com.chori.model.ColorModel;

public class ColorServiceImplTest {

	static AbstractApplicationContext context;
	static ColorService ser;
	
	  @Mock
	    SessionFactory sessionFactory;
	  @InjectMocks
	   ColorServiceImpl cLS;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (ColorService) context.getBean("colorService");
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
//	public void testColorServiceImpl() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testIsColorExistedById() {
		assertEquals(true, ser.isColorExistedById("BR"));
		assertEquals(false, ser.isColorExistedById("T1"));
	}
	@Test(expected=Exception.class)
	public void testIsColorExistedByIdException() {
		ser.isColorExistedById(null);
	}

	@Test
	public void testGetAllColor() {
		assertEquals(true, ser.getAllColor().size()>=0);
	}
	@Test(expected=RuntimeException.class)
	public void testGetAllColorException(){
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		assertNull(cLS.getAllColor());
	}

	@Test
	public void testAddColor() {
		ColorModel cl = new ColorModel();
		cl.setColorcode("add1");
		cl.setDescription("add");
		assertEquals(true, ser.addColor(cl, "admin"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testAddColorException() {
		ColorModel cl= new ColorModel();
		cl.setColorcode(null);
		//des.setDescription("Lop Pho");
		assertEquals(false, ser.addColor(cl, "admin"));
	}

	@Test
	public void testFindColorModelById() {
		ColorModel cl= ser.findColorModelById("BLU");
		assertEquals(true, cl.getColorcode().equals("BLU"));
		cl= ser.findColorModelById("BR");
	}
	
	@Test(expected=Exception.class)
	public void testFindColorModelByIdException() {
		ColorModel cl= ser.findColorModelById(null);
		assertNull(cl);
	}

	@Test
	public void testEditColor() {
		ColorModel cl = ser.findColorModelById("BR");
		cl.setDescription("updateRed");
		assertEquals(true, ser.editColor(cl));
	}
	@Test(expected=Exception.class)
	public void testEditColorException() {
		ColorModel cl = new ColorModel();
		cl.setDescription("7");
		assertEquals(false, ser.editColor(cl));
	}
	
//	@Test
//	public void testDeleteColor() {
//		ser.deleteColor("BL");
//		assertEquals(ser.findById("BL"),null);
//	}
//	@Test(expected=RuntimeException.class)
//	public void testDeleteColorException() {
//		assertEquals(false,ser.deleteColor(""));
//	}
}
