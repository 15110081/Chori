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
import com.chori.entity.Factory;
import com.chori.entity.Factorycontact;

public class FactorycontactDaoTest {

	static AbstractApplicationContext context;
	static FactorycontactDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		dao= (FactorycontactDao) context.getBean("factorycontactDao");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	public void testFindById() {
		assertNotNull(dao.findById(1));
		assertNull(dao.findById(0));
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
		Factorycontact fc= new Factorycontact();
		int i=dao.save(fc);
		dao.deleteById(i);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSaveException() {
		dao.save(null);
	}

	@Test
	public void testUpdate() {
		Factorycontact f= dao.findById(1);
		f.setName("Nguyễn Văn A");
		dao.update(f);
		assertEquals(true, dao.findById(1).getName().equals("Nguyễn Văn A"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testUpdateException() {
		dao.update(null);
	}
	
	@Test
	public void testDelete() {
		dao.delete(dao.findById(dao.save(new Factorycontact())));
		//assertNull(dao.findById("f2"));
	}
}
