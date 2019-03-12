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
import com.chori.model.UnitModel;




public class UnitServiceImplTest {
	 @InjectMocks
	   UnitServiceImpl umpl;
	  
	    @Mock
	    SessionFactory sessionFactory;
	  static AbstractApplicationContext context;
	    static UnitService ser;
	  
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 context = new AnnotationConfigApplicationContext(AppConfig.class);
	        ser = (UnitService) context.getBean("unitService");
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
	public void testGetAllUnitModel() {
        assertEquals(true, ser.getAllUnitModel().size() > 0);

	}
	 @Test(expected=Exception.class)
	    public void testGetAllException() {
	
		 MockitoAnnotations.initMocks(this);
			SessionFactory sessionFactory = mock(SessionFactory.class);
			when(sessionFactory.getCurrentSession()).thenReturn(null);
			assertNull(umpl.getAllUnitModel());
	    }

	@Test
	public void testFindUnitModelById() {
		assertNotNull(ser.findUnitModelById("pcs"));
	}
	 @Test(expected=Exception.class)
	 public void testFindUnitModelByIdException() {
		assertNotNull(ser.findUnitModelById(null));
		}

	@Test
	public void testEditUnit() {
		UnitModel um=ser.findUnitModelById("44inches");
		um.setName("cross2");
		um.setCreatedate(new Date());
		ser.editUnit(um);
        assertEquals("cross2", ser.findUnitModelById("44inches").getName());

	}

	@Test(expected= RuntimeException.class)
	public void testEditException() {
		UnitModel u = null;
		ser.editUnit(u);
	}

	@Test
	public void testDeleteUnit() {
		UnitModel um=ser.findUnitModelById("crs");
		assertTrue(ser.deleteUnit(um.getUnitcode()));
	}
	@Test(expected=RuntimeException.class)
	public void testDeleteException() {
	
		assertFalse(ser.deleteUnit(""));
	
	}
	

	@Test
	public void testAddUnit() {
		UnitModel um=new UnitModel();
		um.setName("cross");
		um.setUnitcode("crs");
		um.setCreatedate(new Date());
		ser.addUnit(um);
	}
	@Test(expected=RuntimeException.class)
	public void testAddUnitException() {
		UnitModel um2 = null;
		ser.addUnit(um2);
		assertNull(ser.findUnitModelById(um2.getUnitcode()));
	}

	@Test
	public void testIsUnitExistedById() {
		assertEquals(true,ser.isUnitExistedById("pcs"));
		assertEquals(false,ser.isUnitExistedById("3"));

	}

}
