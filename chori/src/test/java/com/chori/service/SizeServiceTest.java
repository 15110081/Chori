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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.chori.configuration.AppConfig;
import com.chori.model.SizeModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SizeServiceTest {

	static AbstractApplicationContext context;
	static SizeService ser;
	
	@Mock
    SessionFactory sessionFactory;
	@InjectMocks
	SizeServiceImpl sizeServiceImpl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (SizeService) context.getBean("sizeService");	
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
	public void test1GetAllSize() {
		assertEquals(true, ser.getAllSizeModel().size()>=0);
	}
	
	@Test(expected=RuntimeException.class)
	public void test1GetAllSizeException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		assertNull(sizeServiceImpl.getAllSizeModel());
	}
	
	@Test
	public void test2AddSize() {
		SizeModel sizeModel = new SizeModel();
		sizeModel.setCustomer("Asafa");
		sizeModel.setGarmentkind("Shirt");
		sizeModel.setSizename("XXX");
		sizeModel.setType("Boy");
		assertEquals(true, ser.addSize(sizeModel, "admin"));
	}
	
	@Test(expected=Exception.class)
	public void test3AddSizeException() {
		assertEquals(true, ser.addSize(null, null));
	}
	
	@Test
	public void test4FindSizeModelById() {
		assertNotNull(ser.findSizeModelById(1));
	}
	
	@Test(expected=Exception.class)
	public void test5FindSizeModelByIdException() {
		assertEquals(true, ser.findSizeModelById(null));
	}
	
	@Test
	public void test6EditSize() {

		List<SizeModel> a = ser.getAllSizeModel();
		int max = a.get(0).getSizecode();
		for(SizeModel x : a)
		{
			if(x.getSizecode() > max)
			{
				max = x.getSizecode();
			}
		}
		SizeModel sizeModel = ser.findSizeModelById(1);
		sizeModel.setSizename("XXXXXX");
		assertEquals(true, ser.editSize(sizeModel));
	}
	
	@Test(expected=Exception.class)
	public void test7EditSizeException() {
		assertEquals(true, ser.editSize(null));
	}
	
	@Test
	public void test8IsSizeExistedById() {
		SizeModel sizeModel = new SizeModel();
		sizeModel.setCustomer("Asafa");
		sizeModel.setGarmentkind("Shirt");
		sizeModel.setSizename("XXX");
		sizeModel.setType("Boy");
		assertEquals(true,ser.isSizeExisted(sizeModel));
	}
	
	@Test(expected=Exception.class)
	public void test9IsSizeExistedByIdException() {
		SizeModel sizeModel = null;
		assertEquals(false,ser.isSizeExisted(sizeModel));
	}
	
	@Test
	public void testAFindSizeCodeByCustomerCode() {
		assertNotNull(ser.findSizeCodeByCustomerCode("Asafa"));
	}
	
	@Test(expected=Exception.class)
	public void testAFindSizeCodeByCustomerCodeException() {
		assertEquals(true, ser.findSizeCodeByCustomerCode(null));
	}
	
	@Test
	public void testBDeleteSize() {
		List<SizeModel> a = ser.getAllSizeModel();
		int max = a.get(0).getSizecode();
		for(SizeModel sizeModel : a)
		{
			if(sizeModel.getSizecode() > max)
			{
				max = sizeModel.getSizecode();
			}
		}
		assertEquals(true, ser.deleteSizeById(max));
	}
	
	
	@Test(expected=Exception.class)
	public void testBDeleteSizeException() {
		assertEquals(true, ser.deleteSizeById(null));
	}

}
