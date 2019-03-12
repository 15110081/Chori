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
import com.chori.entity.Ctnrtype;
import com.chori.entity.Width;

public class CtnrtypeDaoTest {

	static AbstractApplicationContext context;
	static CtnrtypeDao dao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		dao= (CtnrtypeDao) context.getBean("ctnrtypeDao");
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
		assertNotNull(dao.findById("1"));
	}
	@Test(expected=RuntimeException.class)
	public void testFindByIdException() {
		assertNotNull(dao.findById(null));
	}
	@Test
	public void testGetAll() {
		assertEquals(true,dao.getAll().size()>=1);
	}

//	@Test
//	public void testSearch() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testSave() {
		Ctnrtype ctnr = new Ctnrtype();
		ctnr.setCtnrcode("2");
		
		dao.save(ctnr);
		assertNotNull(dao.findById("2"));
	}
	@Test(expected=RuntimeException.class)
	public void testSaveException() {
		Ctnrtype ctnr = null;
		
		dao.save(ctnr);
	}

	@Test
	public void testUpdate() {
		Ctnrtype ctnr = dao.findById("bobo");
		ctnr.setDescription("23");
		dao.update(ctnr);
		assertEquals(true,dao.findById("bobo").getDescription().equals("23"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testUpdateException() {
		Ctnrtype ctnr = null;
		dao.update(ctnr);
	}

//	@Test
//	public void testMerge() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testDelete() {
		dao.delete(dao.findById("4"));
		assertEquals(dao.findById("4"), null);
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
