package com.chori.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.chori.configuration.AppConfig;
import com.chori.entity.Destination;
import com.chori.model.DestinationModel;

public class DestinationDaoTest {
	
	static AbstractApplicationContext context;
	 static DestinationDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		  dao= (DestinationDao) context.getBean("destinationDao");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
	
	@Test
	public void testFindById() {
		assertNotNull(dao.findById("fr"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testFindByIdException() {
		assertNotNull(dao.findById(null));
	}

	@Test
	public void testGetAll() {
		assertEquals(true,dao.getAll().size()>=1);
	}
	
//	@Test(expected=RuntimeException.class)
//	public void testGetAllException() {
//		dao=null;
//		dao.getAll();
//	}

//	@Test
//	public void testSearch() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testSave() {
		Destination des = new Destination();
		des.setDestinationcode("add1");
		des.setDescription("viet nam");
		dao.save(des);
		assertNotNull(dao.findById("add1"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testSaveException() {
		Destination des= null;
		dao.save(des);
	}

	@Test
	public void testUpdate() {
		Destination des = dao.findById("fr");
		des.setDescription("franc");
		dao.update(des);
		
		assertEquals(true,dao.findById("fr").getDescription().equals("franc"));
	}
	
	@Test(expected= RuntimeException.class)
	public void testUpdateException() {
		Destination des = null;
		dao.update(des);
	}
//
//	@Test
//	public void testMerge() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDelete() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testDeleteById() {
		dao.deleteById("del1");
		assertEquals(dao.findById("del1") , null);
	}
	@Test(expected= RuntimeException.class)
	public void testDeleteByIdException() {
		dao.deleteById(null);
	}
	@Test
	public void testDelete(){
		dao.delete(dao.findById("del2"));
		assertEquals(dao.findById("del2"), null);
	}
	@Test(expected=RuntimeException.class)
	public void testDeleteException() {
		dao.delete(null);
	}

}
