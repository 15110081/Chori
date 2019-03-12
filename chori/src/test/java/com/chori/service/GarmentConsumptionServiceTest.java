package com.chori.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.chori.configuration.AppConfig;
import com.chori.model.GarmentConsumptionModel;
import com.chori.model.SizeModel;

public class GarmentConsumptionServiceTest {

	static AbstractApplicationContext context;
	static GarmentConsumptionService ser;
	
	@Mock
    SessionFactory sessionFactory;
	@InjectMocks
	GarmentConsumptionServiceImpl GarmentConsumptionServiceImpl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (GarmentConsumptionService) context.getBean("garmentconsumptionService");	
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
	public void test1GetAllGarmentConsumption() {
		assertEquals(true, ser.getAllGarmentConsumptionModel().size()>=0);
	}
	
	@Test(expected=RuntimeException.class)
	public void test1GetAllGarmentConsumptionException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		assertNull(GarmentConsumptionServiceImpl.getAllGarmentConsumptionModel());
	}
	
	@Test
	public void test2AddGarmentConsumption() {
		GarmentConsumptionModel garmentConsumptionModel = new GarmentConsumptionModel();
		garmentConsumptionModel.setGarmentstyle("South");
		garmentConsumptionModel.setSize(1);
		garmentConsumptionModel.setCustomer("Asafa");
		garmentConsumptionModel.setDescription("Description");
		assertEquals(true, ser.addNewGarmentConsumption2(garmentConsumptionModel, "admin"));
	}
	
	@Test(expected=Exception.class)
	public void test3AddGarmentConsumptionException() {
		assertEquals(true, ser.addNewGarmentConsumption2(null, null));
	}
	
	@Test
	public void test4FindGarmentConsumptionModelById() {
		assertNotNull(ser.findGarmentConsumptionModelById(2));
	}
	
	@Test(expected=Exception.class)
	public void test5FindGarmentConsumptionModelByIdException() {
		assertEquals(true, ser.findGarmentConsumptionModelById(null));
	}
	
	@Test
	public void test6EditGarmentConsumption() {

		List<GarmentConsumptionModel> a = ser.getAllGarmentConsumptionModel();
		int max = a.get(0).getGarmentconsumptioncode();
		for(GarmentConsumptionModel x : a)
		{
			if(x.getGarmentconsumptioncode() > max)
			{
				max = x.getGarmentconsumptioncode();
			}
		}
		GarmentConsumptionModel GarmentConsumptionModel = ser.findGarmentConsumptionModelById(max);
		GarmentConsumptionModel.setDescription("XXXXXX");
		assertEquals(true, ser.editGarmentConsumption(GarmentConsumptionModel));
	}
	
	@Test(expected=Exception.class)
	public void test7EditGarmentConsumptionException() {
		assertEquals(true, ser.editGarmentConsumption(null));
	}
	
	@Test
	public void test8IsGarmentConsumptionExistedById() {
		GarmentConsumptionModel garmentConsumptionModel = new GarmentConsumptionModel();
		garmentConsumptionModel.setGarmentstyle("South");
		garmentConsumptionModel.setSize(1);
		garmentConsumptionModel.setCustomer("Asafa");
		assertEquals(true,ser.isGarmentConsumptionExisted(garmentConsumptionModel));
	}
	
	@Test(expected=Exception.class)
	public void test9IsGarmentConsumptionExistedByIdException() {
		GarmentConsumptionModel garmentConsumptionModel = null;
		assertEquals(false,ser.isGarmentConsumptionExisted(garmentConsumptionModel));
	}
	
	@Test
	public void testAFindGarmentConsumptionCode() {
		List<GarmentConsumptionModel> a = ser.getAllGarmentConsumptionModel();
		int max = a.get(0).getGarmentconsumptioncode();
		for(GarmentConsumptionModel x : a)
		{
			if(x.getGarmentconsumptioncode() > max)
			{
				max = x.getGarmentconsumptioncode();
			}
		}
		assertNotNull(ser.findGarmentConsumptionModelById(max));
	}
	
	@Test(expected=Exception.class)
	public void testAFindGarmentConsumptionCodeException() {
		assertEquals(true, ser.findGarmentConsumptionModelById(null));
	}
	
	@Test
	public void testBDeleteGarmentConsumption() {
		List<GarmentConsumptionModel> a = ser.getAllGarmentConsumptionModel();
		int max = a.get(0).getGarmentconsumptioncode();
		for(GarmentConsumptionModel x : a)
		{
			if(x.getGarmentconsumptioncode() > max)
			{
				max = x.getGarmentconsumptioncode();
			}
		}
		assertEquals(true, ser.delete(max));
	}
	
	
	@Test(expected=Exception.class)
	public void testBDeleteGarmentConsumptionException() {
		assertEquals(true, ser.delete(null));
	}


}
