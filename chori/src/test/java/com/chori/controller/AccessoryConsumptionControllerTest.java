package com.chori.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import com.chori.entity.AccessoryconsumptionId;
import com.chori.model.AccessoryConsumptionModel;
import com.chori.service.AccessoryConsumptionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import static org.mockito.Mockito.when;

public class AccessoryConsumptionControllerTest {
	
    private MockMvc mockMvc;
    //private MockMvc mockMvcException;
    //isNotAcceptable 406
    //isBadRequest 400
    //isBadGateway 502
    //isNotFound 404 
    //isNotImplemented 501
    @InjectMocks
    AccessoryConsumptionController controller;
    @Mock
    AccessoryConsumptionService accessoryConsumptionService;
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
    public void testShowPageList_001() throws Exception {
       mockMvc.perform(get("/listAccessoryConsumption"))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(view().name("configuration/accessoryconsumption/listAccessoryConsumption"));         
   
    }
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
    public void testShowPageListException() throws Exception {
//       mockMvc.perform(get("/listAccessoryConsumption"))
//           .andExpect(status().isNotFound())
//           .andExpect(view().name("configuration/accessoryconsumption/listAccessoryConsumptions"));         
//    
		when(accessoryConsumptionService.getClass()).thenThrow(Exception.class);
	       mockMvc.perform(get("/listAccessoryConsumption"));     
	}
	
	@Test
	public void testGetAllAccessoryConsumptionUser() throws Exception {
		mockMvc.perform(get("/accessoryconsumption/list")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllAccessoryConsumptionException() throws Exception {
		when(accessoryConsumptionService.getAllAccessoryConsumption()).thenThrow(Exception.class);
		mockMvc.perform(get("/accessoryconsumption/list"));
	}
	
    @Test
    public void testIsExisted_Success() throws Exception {
       mockMvc.perform(get("/accessoryconsumption/isExist/{factorycode}/{accessorycode}","FAC0001", "BTNR"))
           .andDo(print())
           .andExpect(status().isOk());
    }
    
    @SuppressWarnings("unchecked")
	@Test(expected=Exception.class)
    public void testIsExisted_Failed() throws Exception {
    	AccessoryconsumptionId accessoryconsumptionId = new AccessoryconsumptionId();
    	accessoryconsumptionId.setAccessorycode("XXX");
    	accessoryconsumptionId.setFactorycode("XXX");
		when(accessoryConsumptionService.isAccessoryConsumptionExistedById(accessoryconsumptionId)).thenThrow(Exception.class);
		mockMvc.perform(get("/accessoryconsumption/isExist/{factorycode}/{accessorycode}","XXX", "XXX"));
    }
   
    @Test
    public void testFind_Success() throws Exception {
       mockMvc.perform(get("/accessoryconsumption/detail/{factorycode}/{accessorycode}","FAC0001", "BTNR"))
           .andDo(print())
           .andExpect(status().isOk());
    }
    
    @SuppressWarnings("unchecked")
	@Test(expected=Exception.class)
    public void testFind_Failed() throws Exception {
    	AccessoryconsumptionId accessoryconsumptionId = new AccessoryconsumptionId();
    	accessoryconsumptionId.setAccessorycode("XXX");
    	accessoryconsumptionId.setFactorycode("XXX");
		when(accessoryConsumptionService.findAccessoryConsumptionModelById(accessoryconsumptionId)).thenThrow(
				Exception.class);
		mockMvc.perform(get("/accessoryconsumption/detail/{factorycode}/{accessorycode}","XXX", "XXX")).andExpect(status().isOk());
    }  
   
	@Test
	public void testGetAllAccessoryConsumption() throws Exception {
		mockMvc.perform(get("/accessoryconsumption/list"))
        .andExpect(status().isOk());
	}

	
	@Test
	public void testAddNewAccConsump() throws Exception {
		AccessoryConsumptionModel acc = new AccessoryConsumptionModel();
		acc.setAccessorycode("BTNR");
		acc.setFactorycode("FAC0002");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(acc);

		mockMvc.perform(
				post("/accessoryconsumption/add").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddNewAccConsumpException() throws Exception {
		AccessoryConsumptionModel acc = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(acc);

		when(controller.addAccessoryConsumption(acc)).thenThrow(Exception.class);

		mockMvc.perform(post("/accessoryconsumption/add").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}
	
	@Test
	public void testEditAccConsum() throws Exception {
		AccessoryConsumptionModel acc = new AccessoryConsumptionModel();
		acc.setAccessorycode("BTNR");
		acc.setFactorycode("FAC0002");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(acc);

		mockMvc.perform(
				post("/accessoryconsumption/edit").contentType(MediaType.APPLICATION_JSON)
						.content(requestJson)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditAccConsumException() throws Exception {
		AccessoryConsumptionModel acc = null;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(acc);

		when(accessoryConsumptionService.editAccessoryConsumption(acc)).thenThrow(Exception.class);

		mockMvc.perform(post("/accessoryconsumption/edit").contentType(
				MediaType.APPLICATION_JSON).content(requestJson));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void  testdelete() throws Exception {
//			AccessoryconsumptionId acc = new AccessoryconsumptionId();
//			acc.setAccessorycode(null);
//			acc.setFactorycode(null);
//			when(controller.deleteAccessoryConsumption(acc.getFactorycode(), acc.getAccessorycode())).thenThrow(Exception.class);
//			mockMvc.perform(post("/accessoryconsumption/delete/{factorycode}/{accessorycode}", acc.getFactorycode(), acc.getAccessorycode()));
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		mockMvc.perform(
				post("/accessoryconsumption/delete/{factorycode}/{accessorycode}","FAC0001","BTNR").contentType(MediaType.APPLICATION_JSON)
						)
		.andExpect(status().isOk());
	
		}

//	@SuppressWarnings({ "unchecked" })
//	@Test(expected = Exception.class)
//	public void  testdeleteAccConsumException() throws Exception {
//		AccessoryconsumptionId acc = new AccessoryconsumptionId();
//		acc.setAccessorycode(null);
//		acc.setFactorycode(null);
//		when(accessoryConsumptionService.deleteAccessoryConsumption(acc)).thenThrow(Exception.class);
//		mockMvc.perform(post("/accessoryconsumption/delete/{factorycode}/{accessorycode}",null,null));		
//	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDeleteAccessoryConsumptionException() throws Exception {
		AccessoryconsumptionId accessoryconsumptionId =null;
		when(accessoryConsumptionService.deleteAccessoryConsumption(accessoryconsumptionId)).thenThrow(Exception.class);
		mockMvc.perform(
				post("/accessoryconsumption/delete/{factorycode}/{accessorycode}","xx","xx").contentType(MediaType.APPLICATION_JSON)
						)
		.andExpect(status().isOk());}
	
	

}
