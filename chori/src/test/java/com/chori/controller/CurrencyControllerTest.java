package com.chori.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import com.chori.service.CurrencyService;

public class CurrencyControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
	private CurrencyController controller;
	
	@Mock
	CurrencyService ser;

	@Mock
	View mockView;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(mockView).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testListCurrency() throws Exception {
		mockMvc.perform(get("/listCurrency")).andExpect(status().isOk());
	}

	@Test
	public void testGetAllCurrency() throws Exception {
		mockMvc.perform(get("/currency/list")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllCurrencyException() throws Exception {
		when(ser.getAllCurrencyModel()).thenThrow(Exception.class);
		mockMvc.perform(get("/currency/list"));
	}

//	@Test
//	public void testAddNewCurrency() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGet1CurrencyDetail() throws Exception {
		mockMvc.perform(get("/currency/detail/{currencyCode}", "fsc")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGet1CurrencyDetailException() throws Exception {
		when(ser.findCurrencyModelById("fsc1")).thenThrow(Exception.class);
		mockMvc.perform(get("/currency/detail/{currencyCode}", "fsc1"));
	}

//	@Test
//	public void testEditCurrency() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testDelete1Currency() throws Exception {
		mockMvc.perform(post("/currency/delete/{currencyCode}", "fsc1")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDelete1CurrencyException() throws Exception {
		when(ser.deleteCurrency("fsc1")).thenThrow(Exception.class);
		mockMvc.perform(post("/currency/delete/{currencyCode}", "fsc1"));
	}

	@Test
	public void testIsCurrencyExist() throws Exception {
		mockMvc.perform(get("/currency/isExist/{currencyCode}", "xd")).andExpect(
				status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsCurrencyExistException() throws Exception {
		when(ser.isCurrencyExistedById("xd")).thenThrow(Exception.class);
		mockMvc.perform(get("/currency/isExist/{currencyCode}", "xd"));
	}
}
