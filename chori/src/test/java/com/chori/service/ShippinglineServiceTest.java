package com.chori.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;

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
import com.chori.entity.Factorycontact;
import com.chori.entity.Shippinglinecontact;
import com.chori.model.FactoryModel;
import com.chori.model.FactorycontactModel;
import com.chori.model.ShippinglineContactModel;
import com.chori.model.ShippinglineModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShippinglineServiceTest {

	static AbstractApplicationContext context;
	static ShippinglineService ser;
	static ShippinglinecontactService splccontactSer;
	
	@Mock
    SessionFactory sessionFactory;
	
	@InjectMocks
	ShippinglineServiceImpl shippingLineServiceImpl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (ShippinglineService) context.getBean("shippinglineService");
		splccontactSer= (ShippinglinecontactService) context.getBean("shippinglinecontactService");	
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
	public void test1GetAllShippinglineModel() {
		assertEquals(true, ser.getAllShippinglineModel().size()>=0);
	}
	
	@Test(expected=Exception.class)
	public void test2GetAllShippingLineModelException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		shippingLineServiceImpl.getAllShippinglineModel();
	}

	@Test
	public void test3AddNewShippingLine() {
		ShippinglineModel spl= new ShippinglineModel();
		spl.setShippinglinecode("SPLXXXX");
		spl.setShortname("XXXX");
		ShippinglineContactModel splcontact = new ShippinglineContactModel();
		splcontact.setName("test");
		splcontact.setEmail("test@gmail.com");
		spl.getShippinglinecontacts().add(splcontact);
		assertEquals(true, ser.addNewShippingLine(spl, "admin"));
	}
	
	@Test(expected=Exception.class)
	public void test4AddNewShippingLineException() {
		assertEquals(false, ser.addNewShippingLine(null, null));
	}
	
	@Test
	public void test5FindShippingLineModelById() {
		assertNotNull(ser.findShippingLineModelById("SPLXXXX"));
	}
	
	@Test(expected=Exception.class)
	public void test6FindShippingLineModelByIdException() {
		assertNotNull(ser.findShippingLineModelById(null));
	}

//	@Test
//	public void testEditShippingLine() {
//		fail("Not yet implemented");
//	}

	@Test
	public void test7EditFactory() {
		//case 1:
		ShippinglineModel spl = ser.findShippingLineModelById("SPLXXXX");
		assertEquals(true, ser.editShippingLine(spl, "admin"));
		
		//case 2:
		ShippinglineModel spl2 = new ShippinglineModel();
		spl2.setShippinglinecode("SPLXXXXXX2");
		spl2.setShortname("SPLXXXX");
		ser.addNewShippingLine(spl2, "admin");
		
		ShippinglineModel spl21= new ShippinglineModel();
		spl21.setShippinglinecode("SPLXXXXXX2");
		spl21.setShortname("SPLXXXXXX2");
		ShippinglineContactModel splc21= new ShippinglineContactModel();
		splc21.setName("test");
		splc21.setEmail("test@gmail.com");
		spl21.getShippinglinecontacts().add(splc21);
		assertEquals(true, ser.editShippingLine(spl21, "admin"));
		ser.delete("SPLXXXXXX2");
		
		//case 3:
		ShippinglineModel spl3 = new ShippinglineModel();
		spl3.setShippinglinecode("SPLXXXXXX2");
		spl3.setShortname("SPLXXXX");
		ShippinglineContactModel splc3= new ShippinglineContactModel();
		splc3.setName("test");
		splc3.setEmail("test@gmail.com");
		spl3.getShippinglinecontacts().add(splc3);
		ser.addNewShippingLine(spl3, "admin");
		
		ShippinglineModel spl31 = new ShippinglineModel();
		spl31.setShippinglinecode("SPLXXXXXX2");
		spl31.setShortname("SPLXXXX");
		assertEquals(true, ser.editShippingLine(spl31, "admin"));
		ser.delete("SPLXXXXXX2");
		
		//case 4:
		ShippinglineModel spl4 = new ShippinglineModel();
		spl4.setShippinglinecode("SPLXXXXXX2");
		spl4.setShortname("SPLXXXX");
		//fc to delete
		ShippinglineContactModel splc4Delete= new ShippinglineContactModel();
		splc4Delete.setName("splcDelete");
		splc4Delete.setEmail("test2@gmail.com");
		//fc to update
		ShippinglineContactModel splc4Update= new ShippinglineContactModel();
		splc4Update.setName("splcUpdate");
		splc4Update.setEmail("test2@gmail.com");
		spl4.getShippinglinecontacts().add(splc4Update);
		spl4.getShippinglinecontacts().add(splc4Delete);
		ser.addNewShippingLine(spl4, "admin");
		//end adding factoryModel, now find fcUpdate
		int splcUpdateID= -1;
		ShippinglineModel splTmp= ser.findShippingLineModelById("SPLXXXXXX2");
		Set<ShippinglineContactModel> lstTmp= splTmp.getShippinglinecontacts();
		for (ShippinglineContactModel splcontactModel : lstTmp) {
			if(splcontactModel.getName().equals("splcUpdate")){
				splcUpdateID= splcontactModel.getShippinglinecontactcode();
			}
		}
		
		ShippinglineModel spl41= new ShippinglineModel();
		spl41.setShippinglinecode("SPLXXXXXX2");
		spl41.setShortname("SPLXXXX");
		
		//add 1 fac contact to update
		Shippinglinecontact splcUpdateTmp = splccontactSer.findById(splcUpdateID);
		ShippinglineContactModel splcmUpdateTmp= new ShippinglineContactModel();
		splcmUpdateTmp.setShippinglinecontactcode(splcUpdateTmp.getShippinglinecontactcode());
		splcmUpdateTmp.setName("splcUpdate");
		splcmUpdateTmp.setEmail("test2@gmail.com");
		spl41.getShippinglinecontacts().add(splcmUpdateTmp);
		
		//add 1 fac contact to add new
		ShippinglineContactModel splcmAddTmp= new ShippinglineContactModel();
		splcmAddTmp.setShippinglinecontactcode(0);
		splcmAddTmp.setName("splcUpdate");
		splcmAddTmp.setEmail("test2@gmail.com");
		spl41.getShippinglinecontacts().add(splcmAddTmp);
		
		assertEquals(true, ser.editShippingLine(spl41, "admin"));
		ser.delete("SPLXXXXXX2");
	}
	
	@Test(expected=Exception.class)
	public void test8EditFactoryException() {
		ser.editShippingLine(ser.findShippingLineModelById("SPLXXXX"), null);
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		shippingLineServiceImpl.editShippingLine(null, null);
	}
	
	
	@Test
	public void test9Delete() {
		assertEquals(true, ser.delete("SPLXXXX"));
		
	}
	
	@Test(expected=Exception.class)
	public void testADeleteException() {
		assertEquals(true, ser.delete("SPLXXXXXXX"));
	}
	


}
