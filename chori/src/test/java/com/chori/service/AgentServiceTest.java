//package com.chori.service;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.Date;
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
//import com.chori.model.AgentModel;
//import com.chori.model.UnitModel;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class AgentServiceTest {
//	
//	static AbstractApplicationContext context;
//	static AgentService ser;
//	
//	@Mock
//    SessionFactory sessionFactory;
//	
//	@InjectMocks
//	AgentServiceImpl agentServiceImpl;
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		context = new AnnotationConfigApplicationContext(AppConfig.class);
//		ser= (AgentService) context.getBean("agentService");		
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//		context.close();
//	}
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void testGetAllAgentModel() {
//		assertEquals(true, ser.getAllAgentModel().size()>=0);
//	}
//	
//	 @Test(expected=Exception.class)
//	    public void testGetAllAgentModelException() {
//	
//		 MockitoAnnotations.initMocks(this);
//			SessionFactory sessionFactory = mock(SessionFactory.class);
//			when(sessionFactory.getCurrentSession()).thenReturn(null);
//			assertNull(agentServiceImpl.getAllAgentModel());
//	    }
//
//
//	@Test
//	public void testDeleteAgent() {
//		AgentModel am=ser.findAgentModelById(2);
//		assertTrue(ser.deleteAgent(am.getAgentcode()));
//	}
//	
//	@Test(expected=RuntimeException.class)
//	public void testDeleteAgentException() {	
//		assertFalse(ser.deleteAgent(null));	
//	}
//
//	@Test
//	public void testAddNewAgent() {
//		AgentModel am=new AgentModel();
//		am.setShortname("Chori Agent Japan");
//		am.setLongname("Chori Agent in Japan");
//		am.setCreatedate(new Date());
//		ser.addNewAgent(am, "admin");
//	}
//	
//	@Test(expected=RuntimeException.class)
//	public void testAddNewAgentException() {
//		AgentModel am = null;
//		ser.addNewAgent(am, null);
//		assertNull(ser.findAgentModelById(null));
//	}
//
//	@Test
//	public void testFindAgentModelById() {
//		assertNotNull(ser.findAgentModelById(1));
//	}
//	
//	 @Test(expected=Exception.class)
//	 public void testFindAgentModelByIdException() {
//		assertNotNull(ser.findAgentModelById(null));
//		}
//
//	@Test
//	public void testEditAgent() {
//		AgentModel am=ser.findAgentModelById(1);
//		am.setShortname("chorii");
//		am.setCreatedate(new Date());
//		ser.editAgent(am);
//        assertEquals("chorii", ser.findAgentModelById(1).getShortname());
//	}
//	
//	@Test(expected= RuntimeException.class)
//	public void testEditAgentException() {
//		AgentModel am = null;
//		ser.editAgent(am);
//	}
//
////	@Test
////	public void testIsAgentExistedById() {
////		assertEquals(true,ser.isAgentExistedById(1));
////		assertEquals(false,ser.isAgentExistedById(4));
////	}
//
//	@Test
//	public void testIsAgentExistedByShortname() {
//		assertEquals(true,ser.isAgentExistedByShortname("Chori Agent VietNam"));
//		assertEquals(false,ser.isAgentExistedByShortname("Chori Agent VietNam"));
//	}
//	
//	@Test(expected=Exception.class)
//	public void testIsAgentExistedByShortnameException() {
//		assertEquals(true,ser.isAgentExistedByShortname(null));
//	}
//
//
//}
