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
//import com.chori.model.GarmentstyleModel;
//import com.chori.model.WidthModel;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class GarmentstyleServiceImplTest {
//
//	static AbstractApplicationContext context;
//	static GarmentstyleService service;
//	
//	@Mock
//    SessionFactory sessionFactory;
//	
//	@InjectMocks
//	GarmentstyleServiceImpl eS;
//	
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		context = new AnnotationConfigApplicationContext(AppConfig.class);
//		service= (GarmentstyleService) context.getBean("garmentstyleService");
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
//	@Test
//	public void testGetAllGarmentstyleModel() {
//		assertEquals(true,service.getAllGarmentstyleModel().size()>0);	
//	}
//	
//	@Test(expected=RuntimeException.class)
//	public void testGetAllGarmentstyleModelException() {
//	MockitoAnnotations.initMocks(this);
//	sessionFactory = mock(SessionFactory.class);
//	when(sessionFactory.getCurrentSession()).thenReturn(null);
//	assertNull(eS.getAllGarmentstyleModel());
//	}
//
//	@Test
//	public void testAddNewGarmentstyle() {
//		GarmentstyleModel gsm = new GarmentstyleModel();
//		gsm.setGarmentstylecode("tr");
//		gsm.setGarmentkind("hythyt");
//		gsm.setDescription("o");
//		gsm.setCreator("admin");
//		assertEquals(true,service.addNewGarmentstyle(gsm, "admin"));
//	}
//	
//	@Test(expected=RuntimeException.class)
//	public void testAddNewGarmentstyleException() {
//		GarmentstyleModel gsm = new GarmentstyleModel();
//		gsm.setGarmentstylecode("dd");
//		
//		assertEquals(false, service.addNewGarmentstyle(gsm, "admin"));
//	}
//
//	@Test
//	public void testFindGarmentstyleModelById() {
//		assertNotNull(service.findGarmentstyleModelById("tr"));
//	}
//	
//	@Test(expected=RuntimeException.class)
//	public void testFindGarmentstyleModelByIdException() {
//		assertNotNull(service.findGarmentstyleModelById(null));
//	}
//
//	@Test
//	public void testEditGarmentstyle() {
//		GarmentstyleModel gsm = service.findGarmentstyleModelById("srry");
//		gsm.setGarmentstylecode("srry");
//		gsm.setGarmentkind("htghtr");
//		gsm.setDescription("a");
//		
//		service.editGarmentstyle(gsm, "admin");
//		assertEquals("a",service.findGarmentstyleModelById("srry").getDescription());
//	}
//	
//	@Test(expected=RuntimeException.class)
//	public void testEditGarmentstyleException() {
//		GarmentstyleModel gsm = new GarmentstyleModel();
//		gsm.setGarmentstylecode(null);
//		assertEquals(false,service.editGarmentstyle(gsm, ""));
//	}
//
//	@Test
//	public void testDeleteGarmentstyle() {
//		service.deleteGarmentstyle("tr");
//		assertEquals(service.findById("tr"),null);
//	}
//	
//	@Test(expected=RuntimeException.class)
//	public void testDeleteGarmentstyleException() {
//		assertEquals(false,service.deleteGarmentstyle(""));
//	}
//
//	@Test
//	public void testIsGarmentstyleExistedById() {
//		assertEquals(false, service.isGarmentstyleExistedById("tr"));
//		assertEquals(true, service.isGarmentstyleExistedById("srry"));
//	}
//	
//	@Test(expected=RuntimeException.class)
//	public void testIsGarmentstyleExistedByIdException() {
//		service.isGarmentstyleExistedById(null);
//	}
//
//}
