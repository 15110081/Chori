package com.chori.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
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
import com.chori.entity.Garmentstyleaccessorydetail;
import com.chori.model.GarmentstyleModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GarmentstyleServiceTest {

	static AbstractApplicationContext context;
	static GarmentstyleService ser;
	static GarmentstyleaccessorydetailService gsadSer;

//	@Autowired
//	private GarmentkindDao gkDao;
//
//	@Autowired
//	private CustomerDao cusDao;
//	
//	@Autowired
//	private FactoryDao facDao;
	
	@Mock
    SessionFactory sessionFactory;
	
	@InjectMocks
	GarmentstyleServiceImpl garmentstyleServiceImpl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (GarmentstyleService) context.getBean("garmentstyleService");
		gsadSer = (GarmentstyleaccessorydetailService) context.getBean("garmentstyleaccessorydetailService");
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
	public void test1GetAllGarmentstyleModel() {
		assertEquals(true, ser.getAllGarmentstyleModel().size()>=0);
	}
	
	@Test(expected=Exception.class)
	public void test1GetAllGarmentstyleModelException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		garmentstyleServiceImpl.getAllGarmentstyleModel();
	}

	@Test
	public void test6IsGarmentStyleExistedById() {
		assertEquals(true, ser.isGarmentStyleExistedById("Middle East"));
		assertEquals(false, ser.isGarmentStyleExistedById("Middle Eastxxx"));
	}
	
	@Test(expected=Exception.class)
	public void test6IsGarmentStyleExistedByIdException() {
		ser.isGarmentStyleExistedById(null);
	}

	@Test
	public void test3AddNewGarmentstyle() {
		GarmentstyleModel garmentstyleModel= new GarmentstyleModel();
		garmentstyleModel.setGarmentstylecode("xxx2");
		garmentstyleModel.setCustomercode("Asafa");
		garmentstyleModel.setFactorycode("FAC0001");
		garmentstyleModel.setGarmentkindcode("Shirt");
		garmentstyleModel.setCreator("admin");
		garmentstyleModel.setDescription("xxx2");
		garmentstyleModel.setCreatedate(new Date());
		garmentstyleModel.setImgurl1("a.jpg");
		garmentstyleModel.setImgurl2("a.jpg");
		garmentstyleModel.setImgurl3("a.jpg");
		garmentstyleModel.setImgurl4("a.jpg");
		garmentstyleModel.setImgurl5("a.jpg");
		
		assertEquals(true, ser.addNewGarmentstyle(garmentstyleModel, "admin"));
	}
	
	@Test(expected=Exception.class)
	public void test3AddNewGarmentstyleException() {
		ser.addNewGarmentstyle(null, "admin");
	}

	@Test
	public void test2FindGarmentstyleModelById() {
		assertNotNull(ser.findGarmentstyleModelById("Middle East"));
		assertNotNull(ser.findGarmentstyleModelById("xxx"));
	}
	
	@Test(expected=Exception.class)
	public void test2FindGarmentstyleModelByIdException() {
		assertNotNull(ser.findGarmentstyleModelById(null));
	}

	@Test
	public void test5DeleteGarmentstyle() {
		assertEquals(true, ser.deleteGarmentstyle("xxx2"));
	}
	
	@Test(expected=Exception.class)
	public void test5DeleteGarmentstyleException() {
		ser.deleteGarmentstyle(null);
	}

	@Test
	public void test4EditGarmentstyle() {
		GarmentstyleModel garmentstyleModel= ser.findGarmentstyleModelById("xxx2");
		assertEquals(true, ser.editGarmentstyle(garmentstyleModel, "admin"));
		GarmentstyleModel garmentstyleModel1= ser.findGarmentstyleModelById("xxx");
		assertEquals(true, ser.editGarmentstyle(garmentstyleModel1, "admin"));
	}
	
	@Test(expected=Exception.class)
	public void test4EditGarmentstyleException() {
		ser.editGarmentstyle(null, "admin");
	}

	@Test
	public void test6CopyGarmentstyle() {
		GarmentstyleModel garmentstyleModel= new GarmentstyleModel();
		garmentstyleModel.setGarmentstylecode("xxx3");
		ser.copyGarmentstyle(garmentstyleModel, "xxx", "admin");
		Set<Garmentstyleaccessorydetail> lst = ser.findById("xxx3").getGarmentstyleaccessorydetails();
		for (Garmentstyleaccessorydetail garmentstyleaccessorydetail : lst) {
			gsadSer.deleteGSAD(garmentstyleaccessorydetail.getGarmentstyleaccessorydetailcode());
		}
		ser.deleteGarmentstyle("xxx3");
	}

	@Test(expected=Exception.class)
	public void test6CopyGarmentstyleException() {
		ser.copyGarmentstyle(null, null, "admin");
	}
}
