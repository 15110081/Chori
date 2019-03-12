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
import com.chori.model.CtnrtypeModel;
import com.chori.model.WidthModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CtnrtypeServiceImplTest {
	
	static AbstractApplicationContext context;
	static CtnrtypeService service;
	@Mock
    SessionFactory sessionFactory;
	@InjectMocks
	CtnrtypeServiceImpl eS;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		service= (CtnrtypeService) context.getBean("ctnrtypeService");
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
//	public void testCtnrtypeServiceImpl() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testCtnrtypeServiceImplAbstractDaoOfCtnrtypeString() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetAllCtnrtypeModel() {
		assertEquals(true,service.getAllCtnrtypeModel().size()>0);		
	}
	@Test(expected=RuntimeException.class)
	public void testGetAllCtnrtypeModelException() {
	MockitoAnnotations.initMocks(this);
	sessionFactory = mock(SessionFactory.class);
	when(sessionFactory.getCurrentSession()).thenReturn(null);
	assertNull(eS.getAllCtnrtypeModel());
	}

	@Test
	public void testFindCtnrtypeEntityById() {
		assertNotNull(service.findCtnrtypeEntityById("1"));
	}

	@Test(expected=RuntimeException.class)
	public void testFindCtnrtypeEntityByIdException() {
		assertNotNull(service.findCtnrtypeEntityById(null));
	}
	
	@Test
	public void testDeleteCtnrtype() {
		service.deleteCtnrtype("4");
		assertEquals(service.findById("4"),null);
	}
	@Test(expected=RuntimeException.class)
	public void testDeleteCtnrtypeException() {
		assertEquals(false,service.deleteCtnrtype(""));
	}

	@Test
	public void testAddCtnrtype() {
		CtnrtypeModel ctnr = new CtnrtypeModel();
		ctnr.setCtnrcode("7");
		ctnr.setDescription("very nice");
		
		assertEquals(true,service.addCtnrtype(ctnr, "admin"));
	}
	@Test(expected=RuntimeException.class)
	public void testAddException() {
		assertEquals(true, service.addCtnrtype(null, null));
	}

	@Test
	public void testIsCtnrtypeExistedById() {
		assertEquals(true, service.isCtnrtypeExistedById("1"));
		assertEquals(false, service.isCtnrtypeExistedById("ZZ"));
	}
	@Test(expected=RuntimeException.class)
	public void testIsCtnrtypeExistedByIdhException() {
		service.isCtnrtypeExistedById(null);
	}
	@Test
	public void testEditCtnrtype() {
		CtnrtypeModel ctnr = new CtnrtypeModel();
		ctnr.setCtnrcode("5");
		ctnr.setDescription("very nice");
		
		assertEquals(true,service.editCtnrtype(ctnr, "admin"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testEditCtnrtypeException() {
		CtnrtypeModel ctnr = new CtnrtypeModel();
		ctnr.setCtnrcode(null);
		assertEquals(false,service.editCtnrtype(ctnr, null));
	}

}
