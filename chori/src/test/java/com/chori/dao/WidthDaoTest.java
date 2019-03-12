package com.chori.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.chori.configuration.AppConfig;
import com.chori.entity.Unit;
import com.chori.entity.Width;
import com.chori.model.WidthModel;


public class WidthDaoTest {
	
	static AbstractApplicationContext context;
	static WidthDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		dao= (WidthDao) context.getBean("widthDao");
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
		assertNotNull(dao.findById("2"));
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
//		dao= null;
//		dao.getAll();
//	}
	
//	@Test
//	public void testSearch() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testSave() {
		Width wid = new Width();
		wid.setWidthcode("1");
		wid.setWidthvalues("10");
		
		dao.save(wid);
		assertNotNull(dao.findById("1"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testSaveException() {
		Width wid = null;
		
		dao.save(wid);
	}

	@Test
	public void testUpdate() {
		Width wid = dao.findById("3");
		wid.setWidthvalues("3");
		dao.update(wid);
		assertEquals(true,dao.findById("3").getWidthvalues().equals("3"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testUpdateException() {
		Width wid = null;
		dao.update(wid);
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
	public void testDelete() {
		dao.delete(dao.findById("bbbb"));
		assertEquals(dao.findById("bbbb"), null);
	}
	@Test(expected=RuntimeException.class)
	public void testDeleteException() {
		dao.delete(null);
	}
	@Test
	public void testDeleteById() {
		dao.deleteById("5");
		assertEquals(dao.findById("5"), null);
	}
	@Test(expected=RuntimeException.class)
	public void testDeleteByIdException() {
		dao.deleteById(null);
	}
}
