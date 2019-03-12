//package com.chori.service;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.Set;
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
//import com.chori.entity.Accessorysuppliercontact;
//import com.chori.entity.Factorycontact;
//import com.chori.model.AccessorySupplierModel;
//import com.chori.model.AccessorySuppliercontactModel;
//import com.chori.model.FactoryModel;
//import com.chori.model.FactorycontactModel;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//
//public class AccessorySupplierServiceImplTest {
//
//	static AbstractApplicationContext context;
//	static AccessorySupplierService ser;
//	static AccessorySuppliercontactService acSer;
//	
//	@Mock
//    SessionFactory sessionFactory;
//	
//	@InjectMocks
//	AccessorySupplierServiceImpl 	accessorysupplierServiceImpl;
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		context = new AnnotationConfigApplicationContext(AppConfig.class);
//		ser= (AccessorySupplierService) context.getBean("accessorysupplierService");
//		acSer= (AccessorySuppliercontactService) context.getBean("accessorysuppliercontactService");
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
////	@Test
////	public void testAccessorySupplierServiceImpl() {
////		fail("Not yet implemented");
////	}
//
////	@Test
////	public void testAccessorySupplierServiceImplAbstractDaoOfAccessorysupplierString() {
////		fail("Not yet implemented");
////	}
//
//	@Test
//	public void test1GetAllAccessorySupplierModel() {
//		assertEquals(true, ser.getAllAccessorySupplierModel().size()>=0);
//	}
//	
//	@Test(expected=Exception.class)
//	public void test1GetAllAccessorySupplierModelException() {
//		MockitoAnnotations.initMocks(this);
//		sessionFactory = mock(SessionFactory.class);
//		when(sessionFactory.getCurrentSession()).thenReturn(null);
//		accessorysupplierServiceImpl.getAllAccessorySupplierModel();
//	}
//
//	@Test
//	public void test2AddNewAccSup() {
//		AccessorySupplierModel access = new AccessorySupplierModel();
//		access.setAccessorysuppliercode("B_B");
//		access.setShortname("Bad Boy");
//		AccessorySuppliercontactModel ac = new AccessorySuppliercontactModel();
//		ac.setName("jikarintu");
//		ac.setEmail("jikarintu@gmail.com");
//		access.getAccsupcontactModellist().add(ac);
//		assertEquals(true,ser.addNewAccSup(access, "admin"));
//	}
//	
//	@Test(expected=Exception.class)
//	public void test2AddNewAccSupException() {
//		assertEquals(true, ser.addNewAccSup(null, null));
//	}
//
//	@Test
//	public void test3FindAccessorySupModelById() {
//		assertNotNull(ser.findAccessorySupModelById("AC"));
//	}
//	
//	@Test(expected=Exception.class)
//	public void test3FindAccessorySupModelByIdException() {
//		ser.findAccessorySupModelById(null);
//	}
//
//	@Test
//	public void test6EditAccSup() {
//		//case 1:
//		AccessorySupplierModel access= ser.findAccessorySupModelById("DV");
//				assertEquals(true, ser.editAccSup(access, "admin"));
//				
//				//case 2:
//				AccessorySupplierModel access2= new AccessorySupplierModel();
//				access2.setAccessorysuppliercode("DV2");
//				access2.setShortname("DaVi2");
//				ser.addNewAccSup(access2, "admin");
//				
//				AccessorySupplierModel access3= new AccessorySupplierModel();
//				access3.setAccessorysuppliercode("DV2");
//				access3.setAccessorysuppliercode("DaVi21");
//				AccessorySuppliercontactModel ac21= new AccessorySuppliercontactModel();
//				ac21.setName("dude2");
//				ac21.setEmail("dude2@gmail.com");
//				access3.getAccsupcontactModellist().add(ac21);
//				assertEquals(true, ser.editAccSup(access3, "admin"));
//				ser.delete("DV2");
//				
//				//case 3:
//				AccessorySupplierModel access4= new AccessorySupplierModel();
//				access4.setAccessorysuppliercode("DV2");
//				access4.setShortname("DaVi21");
//				AccessorySuppliercontactModel ac3= new AccessorySuppliercontactModel();
//				ac3.setName("dude2");
//				ac3.setEmail("dude2@gmail.com");
//				access4.getAccsupcontactModellist().add(ac3);
//				ser.addNewAccSup(access4, "admin");
//				
//				AccessorySupplierModel access5= new AccessorySupplierModel();
//				access5.setAccessorysuppliercode("DV2");
//				access5.setShortname("DaVi21");
//				assertEquals(true, ser.editAccSup(access5, "admin"));
//				ser.delete("DV2");
//				
//				//case 4:
//				AccessorySupplierModel access6= new AccessorySupplierModel();
//				access6.setAccessorysuppliercode("DV2");
//				access6.setShortname("DaVi21");
//				//fc to delete
//				AccessorySuppliercontactModel ac4Delete= new AccessorySuppliercontactModel();
//				ac4Delete.setName("fcDelete");
//				ac4Delete.setEmail("dude2@gmail.com");
//				//fc to update
//				AccessorySuppliercontactModel ac4Update= new AccessorySuppliercontactModel();
//				ac4Update.setName("fcUpdate");
//				ac4Update.setEmail("dude2@gmail.com");
//				access6.getAccsupcontactModellist().add(ac4Update);
//				access6.getAccsupcontactModellist().add(ac4Update);
//				ser.addNewAccSup(access6, "admin");
//				//end adding accessModel, now find fcUpdate
//				int acUpdateID= -1;
//				AccessorySupplierModel acTmp= ser.findAccessorySupModelById("DV2");
//				Set<AccessorySuppliercontactModel> lstTmp= acTmp.getAccsupcontactModellist();
//				for (AccessorySuppliercontactModel accessorysuppliercontactModel : lstTmp) {
//					if(accessorysuppliercontactModel.getName().equals("fcUpdate")){
//						acUpdateID= accessorysuppliercontactModel.getAccessorycontact();
//					}
//				}
//				
//				AccessorySupplierModel access7= new AccessorySupplierModel();
//				access7.setAccessorysuppliercode("DV2");
//				access7.setShortname("DaVi31");
//				
//				//add 1 access contact to update
//				Accessorysuppliercontact acUpdateTmp = acSer.findById(acUpdateID);
//				AccessorySuppliercontactModel acmUpdateTmp= new AccessorySuppliercontactModel();
//				acmUpdateTmp.setAccessorycontact(acmUpdateTmp.getAccessorycontact());
//				acmUpdateTmp.setName("fcUpdate");
//				acmUpdateTmp.setEmail("dude2@gmail.com");
//				access7.getAccsupcontactModellist().add(acmUpdateTmp);
//				
//				//add 1 fac contact to add new
//				AccessorySuppliercontactModel acmAddTmp= new AccessorySuppliercontactModel();
//				acmAddTmp.setAccessorycontact(0);
//				acmAddTmp.setName("fcUpdate");
//				acmAddTmp.setEmail("dude2@gmail.com");
//				access7.getAccsupcontactModellist().add(acmAddTmp);
//				
//				assertEquals(true, ser.editAccSup(access7, "admin"));
//				ser.delete("DV2");
//	}
//	
////	@Test(expected=Exception.class)
////	public void test6EditAccSupException() {
////		ser.editAccSup(ser.findAccessorySupModelById("Acc1"), null);
////		MockitoAnnotations.initMocks(this);
////		sessionFactory = mock(SessionFactory.class);
////		when(sessionFactory.getCurrentSession()).thenReturn(null);
////		accessorysupplierServiceImpl.editAccSup(null, null);
////	}
////
////	@Test
////	public void test4DeleteString() {
////		assertEquals(true, ser.delete("Haha"));
////	}
////	
////	@Test(expected=Exception.class)
////	public void test4DeleteStringException() {
////		ser.delete("");
////	}
////
////	@Test
////	public void test5IsAccSupExistedById() {
////		assertEquals(true, ser.isAccSupExistedById("Hihi"));
////		assertEquals(false, ser.isAccSupExistedById("Hihi2"));
////	}
////	@Test(expected=Exception.class)
////	public void test5IsAccSupExistedByIdException() {
////		ser.isAccSupExistedById(null);
////	}
//
//}
