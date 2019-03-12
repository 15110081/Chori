package com.chori.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.chori.configuration.AppConfig;
import com.chori.model.SignatureAddModel;


public class AccessoryordersignatureServiceTest {
	
	static AbstractApplicationContext context;
	static AccessoryordersignatureService ser;
	
	@Mock
    SessionFactory sessionFactory;
	@InjectMocks
	AccessoryordersignatureServiceImpl asi;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (AccessoryordersignatureService) context.getBean("accessoryordersignatureService");
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
	@Test
	public void testGetAllSignature() {
		assertEquals(true, ser.getAllSignature().size()>=0);	
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetAllSignatureException() {
	MockitoAnnotations.initMocks(this);
	sessionFactory = mock(SessionFactory.class);
	when(sessionFactory.getCurrentSession()).thenReturn(null);
	assertNull(asi.getAllSignature());
	
	}

	@Test
	public void testDeleteSignature() {
		assertEquals(true, ser.deleteSignature(4));
	}
	
	@Test(expected=RuntimeException.class)
	public void testDeleteSignatureException() {
		assertEquals(true,ser.deleteSignature(null));
	}

	@Test
	public void testAddNewSignature() {
		SignatureAddModel sm= new SignatureAddModel();
		sm.setName("");
		sm.setText1("10");
		sm.setText2("10");
		sm.setImgurl("");
		assertEquals(true,ser.addNewSignature(sm, "admin"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testSaveException() {
		SignatureAddModel sm=null;
	
		assertEquals(true ,ser.addNewSignature(sm,"admin"));
	}

	@Test
	public void testFindSignatureModelById() {
		assertNotNull(ser.findSignatureModelById(4));
	}
	
	@Test(expected=RuntimeException.class)
	public void testFindWidthEntityByIdException() {
		assertNotNull(ser.findSignatureModelById(null));
	}

	@Test
	public void testEditSignature() {
		SignatureAddModel sm = new SignatureAddModel();
		sm.setAccordersigncode(14);
		sm.setName("");
		sm.setText1("");
		sm.setText2("");
		sm.setImgurl("");
		ser.editSignature(sm, "admin");
		assertEquals("admin",ser.findSignatureModelById(14).getAccordersigncode());
	}
	
	@Test(expected=RuntimeException.class)
	public void testEditSignatureException() {
		SignatureAddModel sm = new SignatureAddModel();
		sm.setAccordersigncode(4);
		assertEquals(false,ser.editSignature(sm, ""));
	}

	@Test
	public void testIsSignatureExistedById() {
		assertEquals(true, ser.isSignatureExistedById(13));
		assertEquals(false, ser.isSignatureExistedById(-1));
	}
	
	@Test(expected=RuntimeException.class)
	public void testIsSignatureExistedByIdException() {
		ser.isSignatureExistedById(null);
	}

}
