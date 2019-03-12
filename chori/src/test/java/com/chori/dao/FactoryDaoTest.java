package com.chori.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.chori.configuration.AppConfig;
import com.chori.entity.Factory;

public class FactoryDaoTest {

	static AbstractApplicationContext context;
	static FactoryDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		dao= (FactoryDao) context.getBean("factoryDao");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	public void testFindById() {
		assertNotNull(dao.findById("DaiViet"));
		assertNull(dao.findById("XX"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testFindByIdException() {
		dao.findById(null);
	}

	@Test
	public void testGetAll() {
		assertEquals(true, dao.getAll().size()>=0);
	}

	@Test
	public void testSave() {
		Factory f= new Factory();
		f.setFactorycode("F1");
		f.setShortname("Fac 1");
		dao.save(f);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSaveException() {
		dao.save(null);
	}

	@Test
	public void testUpdate() {
		Factory f= dao.findById("DaiViet");
		f.setShortname("Đại Việt 1");
		dao.update(f);
		assertEquals(true, dao.findById("DaiViet").getShortname().equals("Đại Việt 1"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testUpdateException() {
		dao.update(null);
	}

	@Test
	public void testDelete() {
		dao.delete(dao.findById("f2"));
		assertNull(dao.findById("f2"));
	}
	
	@Test(expected= RuntimeException.class)
	public void testDeleteException() {
		dao.delete(null);
	}
	
	@Test
	public void testDeleteById() {
		dao.deleteById("f3");
		assertNull(dao.findById("f3"));
		dao.deleteById("f3");
	}

	@Test(expected= RuntimeException.class)
	public void testDeleteByIdException() {
		dao.deleteById(null);
	}
}
