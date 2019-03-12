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
import com.chori.model.FactoryModel;
import com.chori.model.FactorycontactModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FactoryServiceTest {

	static AbstractApplicationContext context;
	static FactoryService ser;
	static FactorycontactService fcSer;
	
	@Mock
    SessionFactory sessionFactory;
	
	@InjectMocks
	FactoryServiceImpl 	factoryServiceImpl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (FactoryService) context.getBean("factoryService");
		fcSer= (FactorycontactService) context.getBean("factorycontactService");
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
	public void test1GetAllFactoryModel() {
		assertEquals(true, ser.getAllFactoryModel().size()>=0);
	}
	
	@Test(expected=Exception.class)
	public void test1GetAllFactoryModelException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		factoryServiceImpl.getAllFactoryModel();
	}

	@Test
	public void test2AddNewFactory() {
		FactoryModel fm= new FactoryModel();
		fm.setFactorycode("DV");
		fm.setShortname("DaVi");
		FactorycontactModel fc= new FactorycontactModel();
		fc.setName("dude");
		fc.setEmail("dude@gmail.com");
		fm.getFactorycontactModellist().add(fc);
		assertEquals(true, ser.addNewFactory(fm, "admin"));
	}
	
	@Test(expected=Exception.class)
	public void test2AddNewFactoryException() {
		assertEquals(true, ser.addNewFactory(null, null));
	}

	@Test
	public void test3FindFactoryModelById() {
		assertNotNull(ser.findFactoryModelById("Fac1"));
	}
	
	@Test(expected=Exception.class)
	public void test3FindFactoryModelByIdException() {
		ser.findFactoryModelById(null);
	}

	@Test
	public void test4DeleteString() {
		assertEquals(true, ser.delete("DV"));
	}
	
	@Test(expected=Exception.class)
	public void test4DeleteStringException() {
		ser.delete("");
	}

	@Test
	public void test5IsFactoryExistedById() {
		assertEquals(true, ser.isFactoryExistedById("Fac1"));
		assertEquals(false, ser.isFactoryExistedById("Fac"));
	}
	
	@Test(expected=Exception.class)
	public void test5IsFactoryExistedByIdException() {
		ser.isFactoryExistedById(null);
	}

	@Test
	public void test6EditFactory() {
		//case 1:
		FactoryModel fm= ser.findFactoryModelById("Fac2");
		assertEquals(true, ser.editFactory(fm, "admin"));
		
		//case 2:
		FactoryModel fm2= new FactoryModel();
		fm2.setFactorycode("DV2");
		fm2.setShortname("DaVi2");
		ser.addNewFactory(fm2, "admin");
		
		FactoryModel fm21= new FactoryModel();
		fm21.setFactorycode("DV2");
		fm21.setShortname("DaVi21");
		FactorycontactModel fc21= new FactorycontactModel();
		fc21.setName("dude2");
		fc21.setEmail("dude2@gmail.com");
		fm21.getFactorycontactModellist().add(fc21);
		assertEquals(true, ser.editFactory(fm21, "admin"));
		ser.delete("DV2");
		
		//case 3:
		FactoryModel fm3= new FactoryModel();
		fm3.setFactorycode("DV2");
		fm3.setShortname("DaVi21");
		FactorycontactModel fc3= new FactorycontactModel();
		fc3.setName("dude2");
		fc3.setEmail("dude2@gmail.com");
		fm3.getFactorycontactModellist().add(fc3);
		ser.addNewFactory(fm3, "admin");
		
		FactoryModel fm31= new FactoryModel();
		fm31.setFactorycode("DV2");
		fm31.setShortname("DaVi31");
		assertEquals(true, ser.editFactory(fm31, "admin"));
		ser.delete("DV2");
		
		//case 4:
		FactoryModel fm4= new FactoryModel();
		fm4.setFactorycode("DV2");
		fm4.setShortname("DaVi21");
		//fc to delete
		FactorycontactModel fc4Delete= new FactorycontactModel();
		fc4Delete.setName("fcDelete");
		fc4Delete.setEmail("dude2@gmail.com");
		//fc to update
		FactorycontactModel fc4Update= new FactorycontactModel();
		fc4Update.setName("fcUpdate");
		fc4Update.setEmail("dude2@gmail.com");
		fm4.getFactorycontactModellist().add(fc4Update);
		fm4.getFactorycontactModellist().add(fc4Delete);
		ser.addNewFactory(fm4, "admin");
		//end adding factoryModel, now find fcUpdate
		int fcUpdateID= -1;
		FactoryModel fmTmp= ser.findFactoryModelById("DV2");
		Set<FactorycontactModel> lstTmp= fmTmp.getFactorycontactModellist();
		for (FactorycontactModel factorycontactModel : lstTmp) {
			if(factorycontactModel.getName().equals("fcUpdate")){
				fcUpdateID= factorycontactModel.getFactorycontactcode();
			}
		}
		
		FactoryModel fm41= new FactoryModel();
		fm41.setFactorycode("DV2");
		fm41.setShortname("DaVi31");
		
		//add 1 fac contact to update
		Factorycontact fcUpdateTmp = fcSer.findById(fcUpdateID);
		FactorycontactModel fcmUpdateTmp= new FactorycontactModel();
		fcmUpdateTmp.setFactorycontactcode(fcUpdateTmp.getFactorycontactcode());
		fcmUpdateTmp.setName("fcUpdate");
		fcmUpdateTmp.setEmail("dude2@gmail.com");
		fm41.getFactorycontactModellist().add(fcmUpdateTmp);
		
		//add 1 fac contact to add new
		FactorycontactModel fcmAddTmp= new FactorycontactModel();
		fcmAddTmp.setFactorycontactcode(0);
		fcmAddTmp.setName("fcUpdate");
		fcmAddTmp.setEmail("dude2@gmail.com");
		fm41.getFactorycontactModellist().add(fcmAddTmp);
		
		assertEquals(true, ser.editFactory(fm41, "admin"));
		ser.delete("DV2");
	}
	
	@Test(expected=Exception.class)
	public void test6EditFactoryException() {
		ser.editFactory(ser.findFactoryModelById("Fac1"), null);
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		//factoryServiceImpl.getAllFactoryModel();
		factoryServiceImpl.editFactory(null, null);
	}
}
