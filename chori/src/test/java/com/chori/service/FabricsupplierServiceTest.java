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
import com.chori.entity.Fabricsuppliercontact;
import com.chori.model.FabricsupplierModel;
import com.chori.model.FabricsuppliercontactModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FabricsupplierServiceTest {

	static AbstractApplicationContext context;
	static FabricsupplierService ser;
	static FabricsuppliercontactService fscSer;
	
	@Mock
	SessionFactory sessionFactory;
	
	@InjectMocks
	FabricsupplierServiceImpl fabricsupplierServiceImpl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (FabricsupplierService) context.getBean("fabricsupplierService");
		fscSer= (FabricsuppliercontactService) context.getBean("fabricsuppliercontactService");
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
	public void test1GetAllFabricsupplierModel() {
		assertEquals(true, ser.getAllFabricsupplierModel().size()>=0);
	}

	@Test(expected=Exception.class)
	public void test1GetAllFabricsupplierModelException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		fabricsupplierServiceImpl.getAllFabricsupplierModel();
	}
	
	@Test
	public void test2AddNewFabricSupplier() {
		FabricsupplierModel fsm= new FabricsupplierModel();
		fsm.setFabricsupcode("FS");
		fsm.setShortname("FabSup");
		FabricsuppliercontactModel fsc= new FabricsuppliercontactModel();
		fsc.setName("dude");
		fsc.setEmail("dude@gmail.com");
		fsm.getFabricsuppliercontactModelList().add(fsc);
		assertEquals(true, ser.addNewFabricSupplier(fsm, "admin"));
	}
	
	@Test(expected=Exception.class)
	public void test2AddNewFabricSupplierException() {
		ser.addNewFabricSupplier(null, null);
	}

	@Test
	public void test3FindFabricsupplierModelById() {
		assertNotNull(ser.findFabricsupplierModelById("FabricSup1"));
		assertNotNull(ser.findFabricsupplierModelById("FabricSup2"));
	}
	
	@Test(expected=Exception.class)
	public void test3FindFabricsupplierModelByIdException() {
		ser.findFabricsupplierModelById(null);
	}

	@Test
	public void test6EditFabricSupplier() {
		//case 1
		FabricsupplierModel fsm= ser.findFabricsupplierModelById("FabricSup2");
		assertEquals(true, ser.editFabricSupplier(fsm, "admin"));
		
		//case 2:
		FabricsupplierModel fsm2= new FabricsupplierModel();
		fsm2.setFabricsupcode("FS2");
		fsm2.setShortname("FabSup");
		ser.addNewFabricSupplier(fsm2, "admin");
		
		FabricsupplierModel fsm21= new FabricsupplierModel();
		fsm21.setFabricsupcode("FS2");
		fsm21.setShortname("FabSup21");
		FabricsuppliercontactModel fsc21= new FabricsuppliercontactModel();
		fsc21.setName("dude");
		fsc21.setEmail("dude@gmail.com");
		fsm21.getFabricsuppliercontactModelList().add(fsc21);
		assertEquals(true, ser.editFabricSupplier(fsm21, "admin"));
		ser.delete("FS2");
		
		//case 3:
		FabricsupplierModel fsm3= new FabricsupplierModel();
		fsm3.setFabricsupcode("FS2");
		fsm3.setShortname("FabSup21");
		FabricsuppliercontactModel fsc3= new FabricsuppliercontactModel();
		fsc3.setName("dude");
		fsc3.setEmail("dude@gmail.com");
		fsm3.getFabricsuppliercontactModelList().add(fsc3);
		ser.addNewFabricSupplier(fsm3, "admin");
		
		FabricsupplierModel fsm31= new FabricsupplierModel();
		fsm31.setFabricsupcode("FS2");
		fsm31.setShortname("FabSup");
		assertEquals(true, ser.editFabricSupplier(fsm31, "admin"));
		ser.delete("FS2");
		
		//case 4:
		FabricsupplierModel fm4= new FabricsupplierModel();
		fm4.setFabricsupcode("DV2");
		fm4.setShortname("DaVi21");
		//fc to delete
		FabricsuppliercontactModel fc4Delete= new FabricsuppliercontactModel();
		fc4Delete.setName("fcDelete");
		fc4Delete.setEmail("dude2@gmail.com");
		//fc to update
		FabricsuppliercontactModel fc4Update= new FabricsuppliercontactModel();
		fc4Update.setName("fcUpdate");
		fc4Update.setEmail("dude2@gmail.com");
		fm4.getFabricsuppliercontactModelList().add(fc4Update);
		fm4.getFabricsuppliercontactModelList().add(fc4Delete);
		ser.addNewFabricSupplier(fm4, "admin");
		//end adding factoryModel, now find fcUpdate
		int fcUpdateID= -1;
		FabricsupplierModel fmTmp= ser.findFabricsupplierModelById("DV2");
		Set<FabricsuppliercontactModel> lstTmp= fmTmp.getFabricsuppliercontactModelList();
		for (FabricsuppliercontactModel factorycontactModel : lstTmp) {
			if(factorycontactModel.getName().equals("fcUpdate")){
				fcUpdateID= factorycontactModel.getFabricsuppliercontactcode();
			}
		}
		
		FabricsupplierModel fm41= new FabricsupplierModel();
		fm41.setFabricsupcode("DV2");
		fm41.setShortname("DaVi31");
		
		//add 1 fac contact to update
		Fabricsuppliercontact fcUpdateTmp = fscSer.findById(fcUpdateID);
		FabricsuppliercontactModel fcmUpdateTmp= new FabricsuppliercontactModel();
		fcmUpdateTmp.setFabricsuppliercontactcode(fcUpdateTmp.getFabricsuppliercontactcode());
		fcmUpdateTmp.setName("fcUpdate");
		fcmUpdateTmp.setEmail("dude2@gmail.com");
		fm41.getFabricsuppliercontactModelList().add(fcmUpdateTmp);
		
		//add 1 fac contact to add new
		FabricsuppliercontactModel fcmAddTmp= new FabricsuppliercontactModel();
		fcmAddTmp.setFabricsuppliercontactcode(0);
		fcmAddTmp.setName("fcUpdate");
		fcmAddTmp.setEmail("dude2@gmail.com");
		fm41.getFabricsuppliercontactModelList().add(fcmAddTmp);
		
		assertEquals(true, ser.editFabricSupplier(fm41, "admin"));
		ser.delete("DV2");
	}
	
	@Test(expected=Exception.class)
	public void test6EditFabricSupplierException() {
		ser.editFabricSupplier(null, null);
	}

	@Test
	public void test4DeleteString() {
		assertEquals(true, ser.delete("FS"));
	}
	
	@Test(expected=Exception.class)
	public void test4DeleteStringException() {
		ser.delete("");
	}

	@Test
	public void test5IsFabricSupplierExistedById() {
		assertEquals(true, ser.isFabricSupplierExistedById("FabricSup1"));
		assertEquals(false, ser.isFabricSupplierExistedById("FabricSup11"));
	}

	@Test(expected=Exception.class)
	public void test5IsFabricSupplierExistedByIdException() {
		ser.isFabricSupplierExistedById(null);
	}
}
