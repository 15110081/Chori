package com.chori.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import com.chori.model.GarmentConsumptionModel;
import com.chori.service.AccessoryConsumptionService;
import com.chori.service.GarmentConsumptionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class GarmentConsumptionControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    GarmentConsumptionController controller;
    @Mock
    GarmentConsumptionService garmentConsumptionService;
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
	       mockMvc.perform(get("/listGarmentConsumption"))
	           .andDo(print())
	           .andExpect(status().isOk())
	           .andExpect(view().name("configuration/garmentconsumption/listGarmentConsumption"));         
	   
	    }
		
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
	    public void testShowPageListException() throws Exception {
//	       mockMvc.perform(get("/listGarmentConsumption"))
//	           .andExpect(status().isNotFound())
//	           .andExpect(view().name("configuration/GarmentConsumption/listGarmentConsumptions"));         
	//    
			when(garmentConsumptionService.getClass()).thenThrow(Exception.class);
		       mockMvc.perform(get("/listGarmentConsumption"));     
		}
		
		@Test
		public void testGetAllGarmentConsumptionUser() throws Exception {
			mockMvc.perform(get("/garmentconsumption/list")).andExpect(status().isOk());
		}
		
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
		public void testGetAllGarmentConsumptionException() throws Exception {
			when(garmentConsumptionService.getAllGarmentConsumptionModel()).thenThrow(Exception.class);
			mockMvc.perform(get("/garmentconsumption/list"));
		}
		
	    @Test
	    public void testIsExisted_Success() throws Exception {
//	       mockMvc.perform(post("/garmentconsumption/isExist"))
//	           .andDo(print())
//	           .andExpect(status().isOk());   
			GarmentConsumptionModel garmentConsumptionModel = new GarmentConsumptionModel();
			garmentConsumptionModel.setGarmentstyle("Northen West");
			garmentConsumptionModel.setSize(3);
			garmentConsumptionModel.setCustomer("Saquiri");

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(garmentConsumptionModel);

			mockMvc.perform(
					post("/garmentconsumption/isExist").contentType(MediaType.APPLICATION_JSON)
							.content(requestJson)).andExpect(status().isOk());
	    }
	    
	    @SuppressWarnings("unchecked")
		@Test(expected=Exception.class)
	    public void testIsExisted_Failed() throws Exception {
			GarmentConsumptionModel acc = null;

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(acc);

			when(garmentConsumptionService.isGarmentConsumptionExisted(acc)).thenThrow(Exception.class);

			mockMvc.perform(post("/garmentconsumption/isExist").contentType(
					MediaType.APPLICATION_JSON).content(requestJson));
	    }
	   
	    @Test
	    public void testFind_Success() throws Exception {
	       mockMvc.perform(get("/garmentconsumption/detail/{garconCode}",2))
	           .andDo(print())
	           .andExpect(status().isOk());
	    }
	    
	    @SuppressWarnings("unchecked")
		@Test(expected=Exception.class)
	    public void testFind_Failed() throws Exception {
			when(garmentConsumptionService.findGarmentConsumptionModelById(2)).thenThrow(
					Exception.class);
			mockMvc.perform(get("/garmentconsumption/detail/{garconCode}",2)).andExpect(status().isOk());
	    }  
	   
		@Test
		public void testGetAllGarmentConsumption() throws Exception {
			mockMvc.perform(get("/garmentconsumption/list"))
	        .andExpect(status().isOk());
		}

		
		@Test
		public void testAddNewAccConsump() throws Exception {
			GarmentConsumptionModel garmentConsumptionModel = new GarmentConsumptionModel();
			garmentConsumptionModel.setGarmentstyle("South");
			garmentConsumptionModel.setSize(1);
			garmentConsumptionModel.setCustomer("Asafa");
			garmentConsumptionModel.setDescription("Description");

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(garmentConsumptionModel);

			mockMvc.perform(
					post("/garmentconsumption/add2").contentType(MediaType.APPLICATION_JSON)
							.content(requestJson)).andExpect(status().isOk());
		}
		
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
		public void testAddNewAccConsumpException() throws Exception {
			GarmentConsumptionModel acc = null;

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(acc);

			when(garmentConsumptionService.addNewGarmentConsumption2(acc, null)).thenThrow(Exception.class);

			mockMvc.perform(post("/garmentconsumption/add2").contentType(
					MediaType.APPLICATION_JSON).content(requestJson));
		}
		
		@Test
		public void testEditAccConsum() throws Exception {
			GarmentConsumptionModel garmentConsumptionModel = new GarmentConsumptionModel();
			garmentConsumptionModel.setGarmentstyle("Northen West");
			garmentConsumptionModel.setSize(3);
			garmentConsumptionModel.setCustomer("Saquiri");

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(garmentConsumptionModel);

			mockMvc.perform(
					post("/garmentconsumption/edit").contentType(MediaType.APPLICATION_JSON)
							.content(requestJson)).andExpect(status().isOk());
		}
		
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
		public void testEditAccConsumException() throws Exception {
			GarmentConsumptionModel acc = null;

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(acc);

			when(garmentConsumptionService.editGarmentConsumption(acc)).thenThrow(Exception.class);

			mockMvc.perform(post("/garmentconsumption/edit").contentType(
					MediaType.APPLICATION_JSON).content(requestJson));
		}
		
		@SuppressWarnings("unchecked")
		@Test
		public void  testdelete() throws Exception {
//				GarmentConsumptionId acc = new GarmentConsumptionId();
//				acc.setAccessorycode(null);
//				acc.setFactorycode(null);
//				when(controller.deleteGarmentConsumption(acc.getFactorycode(), acc.getAccessorycode())).thenThrow(Exception.class);
//				mockMvc.perform(post("/GarmentConsumption/delete/{factorycode}/{accessorycode}", acc.getFactorycode(), acc.getAccessorycode()));
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			mockMvc.perform(
					post("/garmentconsumption/delete/{garmentconsumptionCode}",2).contentType(MediaType.APPLICATION_JSON)
							)
			.andExpect(status().isOk());		
		}
		
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
		public void testDeleteGarmentConsumptionException() throws Exception {
//			GarmentConsumptionId GarmentConsumptionId =null;
//			when(GarmentConsumptionService.deleteGarmentConsumption(GarmentConsumptionId)).thenThrow(Exception.class);
//			mockMvc.perform(
//					post("/GarmentConsumption/delete/{factorycode}/{accessorycode}","xx","xx").contentType(MediaType.APPLICATION_JSON)
//							)
//			.andExpect(status().isOk());
			}

}
