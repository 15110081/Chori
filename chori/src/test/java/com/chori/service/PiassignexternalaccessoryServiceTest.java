package com.chori.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

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
import com.chori.model.PiassignexternalaccessoryModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PiassignexternalaccessoryServiceTest {
	
	static AbstractApplicationContext context;
	static PiassignexternalaccessoryService ser;
	
	@Mock
    SessionFactory sessionFactory;
	
	@InjectMocks
	PiassignexternalaccessoryServiceImpl piassignexternalaccessoryServiceImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		ser= (PiassignexternalaccessoryService) context.getBean("piassignexternalaccessoryService");
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
	public void test1GetListPiassignexternalaccessoryModelWhenPressAssign() {
		assertEquals(true, ser.getListPiassignexternalaccessoryModelWhenPressAssign("LOT1").size()>=0);
	}
	
	@Test(expected=Exception.class)
	public void test1GetListPiassignexternalaccessoryModelWhenPressAssignException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		piassignexternalaccessoryServiceImpl.getListPiassignexternalaccessoryModelWhenPressAssign("LOT1");
	}

	@Test
	public void test2Add1stTimePiAssignExternalAccessory() {
		PiassignexternalaccessoryModel p= new PiassignexternalaccessoryModel();
		p.setPiassignexternalaccessorycode(1);
		ArrayList<PiassignexternalaccessoryModel> lstNotAssign = new ArrayList<PiassignexternalaccessoryModel>();
		lstNotAssign.add(p);
		
		assertEquals(true,ser.add1stTimePiAssignExternalAccessory(lstNotAssign,"admin"));
	}
	
	@Test(expected=Exception.class)
	public void test2Add1stTimePiAssignExternalAccessoryException() {
		ser.add1stTimePiAssignExternalAccessory(null,null);
	}

	@Test
	public void test3IsPiAssignedExternalAccessory() {
		assertEquals(true,ser.isPiAssignedExternalAccessory("LOT1"));
	}
	
	@Test(expected=Exception.class)
	public void test3IsPiAssignedExternalAccessoryException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		piassignexternalaccessoryServiceImpl.isPiAssignedExternalAccessory(null);
	}

	@Test
	public void test4GetListPiassignexternalaccessoryByLotNumber() {
		assertEquals(true, ser.getListPiassignexternalaccessoryByLotNumber("LOT1").size()>=0);
	}
	
	@Test(expected=Exception.class)
	public void test4GetListPiassignexternalaccessoryByLotNumberException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		piassignexternalaccessoryServiceImpl.getListPiassignexternalaccessoryByLotNumber("LOT1");
	}

	@Test
	public void test5GetListPiassignexternalaccessoryForEditByLotNumber() {
		assertEquals(true, ser.getListPiassignexternalaccessoryForEditByLotNumber("LOT1").size()>=0);
	}
	
	@Test(expected=Exception.class)
	public void test5GetListPiassignexternalaccessoryForEditByLotNumberException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		piassignexternalaccessoryServiceImpl.getListPiassignexternalaccessoryForEditByLotNumber("LOT1");
	}

	@Test
	public void test6EditPiAssignExternalAccessory() {
		PiassignexternalaccessoryModel p= new PiassignexternalaccessoryModel();
		p.setPiassignexternalaccessorycode(28);//fix here
		p.setAssign(true);
		PiassignexternalaccessoryModel p1= new PiassignexternalaccessoryModel();
		p1.setPiassignexternalaccessorycode(29);//fix here
		p1.setAssign(false);
		PiassignexternalaccessoryModel p2= new PiassignexternalaccessoryModel();
		p2.setPiassignexternalaccessorycode(30);//fix here
		p2.setAssign(true);
		ArrayList<PiassignexternalaccessoryModel> lstNotAssign = new ArrayList<PiassignexternalaccessoryModel>();
		lstNotAssign.add(p);
		lstNotAssign.add(p1);
		lstNotAssign.add(p2);
		
		assertEquals(true,ser.editPiAssignExternalAccessory(lstNotAssign,"admin"));
	}
	
	@Test(expected=Exception.class)
	public void test6EditPiAssignExternalAccessoryException() {
		MockitoAnnotations.initMocks(this);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(null);
		piassignexternalaccessoryServiceImpl.editPiAssignExternalAccessory(null,"admin");
	}

}
