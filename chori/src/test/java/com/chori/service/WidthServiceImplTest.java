//package com.chori.service;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import org.hibernate.SessionFactory;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runners.MethodSorters;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.AbstractApplicationContext;
//
//import com.chori.configuration.AppConfig;
//import com.chori.dao.WidthDao;
//
//import com.chori.model.WidthModel;
//
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class WidthServiceImplTest {
//	
//	static AbstractApplicationContext context;
//	static WidthService service;
//	@Mock
//    SessionFactory sessionFactory;
//	@InjectMocks
//	WidthServiceImpl eS;
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		context = new AnnotationConfigApplicationContext(AppConfig.class);
//		service= (WidthService) context.getBean("widthService");
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//		context.close();
//	}
//
////	@Before
////	public void setUp() throws Exception {
////	}
////
////	@After
////	public void tearDown() throws Exception {
////	}
//
////	@Test
////	public void testWidthServiceImpl() {
////		fail("Not yet implemented");
////	}
//
////	@Test
////	public void testWidthServiceImplAbstractDaoOfWidthString() {
////		fail("Not yet implemented");
////	}
//
//	@Test
//	public void testGetAllWidthModel() {
//		assertEquals(true,service.getAllWidthModel().size()>0);		
//	}
//	
//	@Test(expected=RuntimeException.class)
//	public void testGetAllWidthModelException() {
//	MockitoAnnotations.initMocks(this);
//	sessionFactory = mock(SessionFactory.class);
//	when(sessionFactory.getCurrentSession()).thenReturn(null);
//	assertNull(eS.getAllWidthModel());
//	}
//
//	@Test
//	public void testFindWidthEntityById() {
//		assertNotNull(service.findWidthEntityById("2"));
//	}
//	@Test(expected=RuntimeException.class)
//	public void testFindWidthEntityByIdException() {
//		assertNotNull(service.findWidthEntityById(null));
//	}
//	@Test
//	public void testDeleteWidth() {
//		service.deleteWidth("bbbb");
//		assertEquals(service.findById("bbbb"),null);
//	}
//	@Test(expected=RuntimeException.class)
//	public void testDeleteWidthException() {
//		assertEquals(false,service.deleteWidth(""));
//	}
//	
//	@Test
//	public void testAddWidth() {
//		WidthModel wid = new WidthModel();
//		wid.setWidthcode("1");
//		wid.setWidthvalues("10");
//		wid.setUnitcode("pcs");
//		wid.setUnitname("piece");
//		assertEquals(true,service.addWidth(wid, "admin"));
//	
//	}
//
//	@Test(expected=RuntimeException.class)
//	public void testSaveException() {
//		WidthModel wid = new WidthModel();
//		wid.setWidthcode("ZZ");
//		
//		assertEquals(false, service.addWidth(wid, "admin"));
//	}
//
//	@Test
//	public void testIsWidthExistedById() {
//		assertEquals(true, service.isWidthExistedById("4"));
//		assertEquals(false, service.isWidthExistedById("ZZ"));
//	}
//	@Test(expected=RuntimeException.class)
//	public void testIsWidthExistedByIdhException() {
//		service.isWidthExistedById(null);
//	}
//
//	@Test
//	public void testEditWidth() {
//		WidthModel wid = service.findWidthEntityById("3");
//		wid.setWidthcode("3");
//		wid.setWidthvalues("7");
//		wid.setUnitcode("pcs");
//		wid.setUnitname("piece");
//		service.editWidth(wid, "admin");
//		assertEquals("7",service.findWidthEntityById("3").getWidthvalues());
//	}
//	@Test(expected=RuntimeException.class)
//	public void testEditWidthException() {
//		WidthModel wid = new WidthModel();
//		wid.setUnitcode("7");
//		assertEquals(false,service.editWidth(wid, ""));
//	}
//}
