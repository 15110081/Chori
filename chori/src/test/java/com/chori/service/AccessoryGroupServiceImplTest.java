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
import com.chori.model.AccessoryGroupModel;
import com.chori.model.UnitModel;

public class AccessoryGroupServiceImplTest {
	 @InjectMocks
	   AccessoryGroupServiceImpl umpl;
	  
	    @Mock
	    SessionFactory sessionFactory;
	  static AbstractApplicationContext context;
	    static AccessoryGroupService ser;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 context = new AnnotationConfigApplicationContext(AppConfig.class);
	        ser = (AccessoryGroupService) context.getBean("accessoryGroupService");
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
	public void testIsAccessoryGroupExistedById() {
		assertEquals(true,ser.isAccessoryGroupExistedById("BOX"));
		assertEquals(false,ser.isAccessoryGroupExistedById("3"));

	}

	@Test
	public void testGetAllAccessoryGroup() {
        assertEquals(true, ser.getAllAccessoryGroup().size() > 0);

	}
	 @Test(expected=Exception.class)
	    public void testGetAllException() {
	
		 MockitoAnnotations.initMocks(this);
			SessionFactory sessionFactory = mock(SessionFactory.class);
			when(sessionFactory.getCurrentSession()).thenReturn(null);
			assertNull(umpl.getAllAccessoryGroup());
	    }


	@Test
	public void testAddAccessoryGroup() {
		AccessoryGroupModel um=new AccessoryGroupModel();
		um.setAccessorygroupCode("Button2");
		um.setDescription("DES 2");
		um.setCreatedDate(new Date());
		
		ser.addAccessoryGroup(um, "admin1");
		assertNull(ser.findAccessoryGroupById(um.getAccessorygroupCode()));

	}
	@Test(expected=RuntimeException.class)
	public void testAddException() {
		AccessoryGroupModel um2 = null;
		ser.addAccessoryGroup(um2, "username");
		assertNull(ser.findAccessoryGroupById(um2.getAccessorygroupCode()));
	}

	@Test
	public void testFindAccessoryGroupById() {
		assertNotNull(ser.findAccessoryGroupById("BOX"));
		}
		 @Test(expected=Exception.class)
		 public void testFindAccessoryModelByIdException() {
			assertNotNull(ser.findAccessoryGroupById(null));
			}


	@Test
	public void testEditAccessoryGroup() {
		AccessoryGroupModel um=ser.findAccessoryGroupById("Button2");
		um.setDescription("B2");
		um.setCreatedDate(new Date());
		ser.editAccessoryGroup(um);
        assertEquals("B2", ser.findAccessoryGroupById("Button2").getDescription());

	}

	@Test(expected= RuntimeException.class)
	public void testEditException() {
		AccessoryGroupModel u = null;
		ser.editAccessoryGroup(u);
	}

	@Test
	public void testDeleteAccessoryGroup() {
		AccessoryGroupModel um=ser.findAccessoryGroupById("aaa");
		assertTrue(ser.deleteAccessoryGroup(um.getAccessorygroupCode()));
	}
	@Test(expected=RuntimeException.class)
	public void testDeleteException() {
	
		assertFalse(ser.deleteAccessoryGroup(""));
	
	}
}
