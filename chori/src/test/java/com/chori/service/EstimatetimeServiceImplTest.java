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
import com.chori.entity.Estimatetime;
import com.chori.model.EstimatetimeModel;
import com.chori.model.UnitModel;

public class EstimatetimeServiceImplTest {
	 @InjectMocks
	   EstimatetimeServiceImpl estmpl;
	  
	    @Mock
	    SessionFactory sessionFactory;
	  static AbstractApplicationContext context;
	    static EstimatetimeService ser;
	  

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
        ser = (EstimatetimeService) context.getBean("estimatetimeService");
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
	public void testEstimatetimeServiceImpl() {
		fail("Not yet implemented");
	}



	@Test
	public void testFindEstimatetimeModelById() {
		assertNotNull(ser.findEstimatetimeModelById(1));
	}
	 @Test(expected=Exception.class)
	 public void testFindUnitModelByIdException() {
		assertNotNull(ser.findEstimatetimeModelById(-1));
		}

	@Test
	public void testEditEstimatetime() {
		EstimatetimeModel estm=ser.findEstimatetimeModelById(1);
		estm.setEstimateCode(1);
		estm.setPackingaccdelv(7);
		estm.setManuaccdelv(7);
		estm.setPiconpletion(7);
	
		estm.setCreatedate(new Date());
		ser.editEstimatetime(estm, "admin");
        assertEquals(7, ser.findEstimatetimeModelById(1).getPiconpletion());	}
	
	@Test(expected= RuntimeException.class)
	public void testEditException() {
		EstimatetimeModel est = null;
		ser.editEstimatetime(est, "admin");

	}


	@Test
	public void testGetAllEstimatetimeModel() {
        assertEquals(true, ser.getAllEstimatetimeModel().size() > 0);
	}
	 @Test(expected=Exception.class)
	    public void testGetAllException() {
	
		 MockitoAnnotations.initMocks(this);
			SessionFactory sessionFactory = mock(SessionFactory.class);
			when(sessionFactory.getCurrentSession()).thenReturn(null);
			assertNull(estmpl.getAllEstimatetimeModel());
	    }

}
