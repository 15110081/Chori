package com.chori.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;







import java.util.Date;

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
import com.chori.model.TypeModel;
import com.chori.model.UnitModel;

public class TypeServiceImplTest {
	@InjectMocks
	   TypeServiceImpl umpl;
	  
	    @Mock
	    SessionFactory sessionFactory;
	  static AbstractApplicationContext context;
	    static TypeService ser;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 context = new AnnotationConfigApplicationContext(AppConfig.class);
	        ser = (TypeService) context.getBean("typeService");
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
	public void testGetAllTypeModel() {
        assertEquals(true, ser.getAllTypeModel().size() > 0);
	}
	 @Test(expected=Exception.class)
	    public void testGetAllException() {
	
		 MockitoAnnotations.initMocks(this);
			SessionFactory sessionFactory = mock(SessionFactory.class);
			when(sessionFactory.getCurrentSession()).thenReturn(null);
			assertNull(umpl.getAllTypeModel());
	    }
	@Test
	public void testFindTypeModelById() {
		assertNotNull(ser.findById("Boy"));
		}
		 @Test(expected=Exception.class)
		 public void testFindUnitModelByIdException() {
			assertNotNull(ser.findTypeModelById(null));
			}
	@Test
	public void testEditType() {
		TypeModel um=ser.findTypeModelById("Boy");
		um.setDescription("asda1");
		um.setCreatedDate(new Date());
		ser.editType(um, "admin");
        assertEquals("asda1", ser.findTypeModelById("Boy").getDescription());
	}
	@Test(expected= RuntimeException.class)
	public void testEditException() {
		TypeModel u = null;
		ser.editType(u, null);
	}

	@Test
	public void testDeleteType() {
		TypeModel um=ser.findTypeModelById("Boy");
		assertTrue(ser.deleteType(um.getTypeCode()));
	}
	@Test(expected=RuntimeException.class)
	public void testDeleteException() {
	
		assertFalse(ser.deleteType(""));
	
	}
	@Test
	public void testAddType() {
		TypeModel um=new TypeModel();
		um.setDescription("description");
		um.setTypeCode("Uniform");
		um.setCreatedDate(new Date());
		
		ser.addType(um, "admin");
	}
	@Test(expected=RuntimeException.class)
	public void testAddUnitException() {
		TypeModel um2 = null;
		ser.addType(um2,null);
		assertNull(ser.findTypeModelById(um2.getTypeCode()));
	}
	@Test
	public void testIsTypeExistedById() {
		assertEquals(true,ser.isTypeExistedById("Child"));
		assertEquals(false,ser.isTypeExistedById("1"));	}

}
