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

import com.chori.model.GarmentConsumptionModel;
import com.chori.model.SizeModel;
import com.chori.service.GarmentConsumptionService;
import com.chori.service.SizeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SizeControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    SizeController controller;
    @Mock
    SizeService sizeService;
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
	       mockMvc.perform(get("/listSize"))
	           .andDo(print())
	           .andExpect(status().isOk())
	           .andExpect(view().name("configuration/size/list"));           
	    }
		
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
	    public void testShowPageListException() throws Exception {
			when(sizeService.getClass()).thenThrow(Exception.class);
		       mockMvc.perform(get("/list"));     
		}
		
		@Test
		public void testGetAllSizeUser() throws Exception {
			mockMvc.perform(get("/size/list")).andExpect(status().isOk());
		}
		
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
	    public void testGetAllSizeByCustomer() throws Exception {
			when(sizeService.findSizeCodeByCustomerCode(null)).thenThrow(Exception.class);
		       mockMvc.perform(get("/size/list/{customerCode}",null));     
		}
		
		@Test
		public void testGetAllSizeByCustomer2() throws Exception {
			when(sizeService.findSizeCodeByCustomerCode("Asafa")).thenThrow(Exception.class);
		       mockMvc.perform(get("/size/list/{customerCode}","Asafa"));   
		}
		
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
		public void testGetAllSizeException() throws Exception {
			when(sizeService.getAllSizeModel()).thenThrow(Exception.class);
			mockMvc.perform(get("/size/list"));
		}
		
	    @Test
	    public void testIsExisted_Success() throws Exception {
			SizeModel sizeModel = new SizeModel();
			sizeModel.setCustomer("Saquiri");
			sizeModel.setGarmentkind("Jacket");
			sizeModel.setSizename("L");
			sizeModel.setType("Boy");

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(sizeModel);

			mockMvc.perform(
					post("/size/isExist").contentType(MediaType.APPLICATION_JSON)
							.content(requestJson)).andExpect(status().isOk());
	    }
	    
	    @SuppressWarnings("unchecked")
		@Test(expected=Exception.class)
	    public void testIsExisted_Failed() throws Exception {
	    	SizeModel sizeModel = null;

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(sizeModel);

			when(sizeService.isSizeExisted(sizeModel)).thenThrow(Exception.class);

			mockMvc.perform(post("/size/isExist").contentType(
					MediaType.APPLICATION_JSON).content(requestJson));
	    }
	   
	    @Test
	    public void testFind_Success() throws Exception {
	       mockMvc.perform(get("/size/detail/{sizecode}",1))
	           .andDo(print())
	           .andExpect(status().isOk());
	    }
	    
	    @SuppressWarnings("unchecked")
		@Test(expected=Exception.class)
	    public void testFind_Failed() throws Exception {
	    	SizeModel sizeModel = null;

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(sizeModel);

			when(sizeService.isSizeExisted(sizeModel)).thenThrow(Exception.class);

			mockMvc.perform(post("/size/detail/{sizecode}",sizeModel.getSizecode()).contentType(
					MediaType.APPLICATION_JSON).content(requestJson));
	    }  
	   
		@Test
		public void testGetAllSize() throws Exception {
			mockMvc.perform(get("/size/list"))
	        .andExpect(status().isOk());
		}

		
		@Test
		public void testAddNewSize() throws Exception {
			SizeModel sizeModel = new SizeModel();
			sizeModel.setCustomer("Saquiri");
			sizeModel.setGarmentkind("Jacket");
			sizeModel.setSizename("XXXX");
			sizeModel.setType("Boy");

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(sizeModel);

			mockMvc.perform(
					post("/size/add").contentType(MediaType.APPLICATION_JSON)
							.content(requestJson)).andExpect(status().isOk());
		}
		
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
		public void testAddNewAccConsumpException() throws Exception {
			SizeModel sizeModel = null;

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(sizeModel);

			when(sizeService.addSize(sizeModel, null)).thenThrow(Exception.class);

			mockMvc.perform(post("/size/add").contentType(
					MediaType.APPLICATION_JSON).content(requestJson));
		}
		
		@Test
		public void testEditAccConsum() throws Exception {
			SizeModel sizeModel = new SizeModel();
			sizeModel.setSizecode(1);
			sizeModel.setSizename("sadas");
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(sizeModel);

			mockMvc.perform(
					post("/size/edit").contentType(MediaType.APPLICATION_JSON)
							.content(requestJson)).andExpect(status().isOk());
		}
		
		@SuppressWarnings("unchecked")
		@Test(expected = Exception.class)
		public void testEditAccConsumException() throws Exception {
			SizeModel sizeModel = null;

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(sizeModel);

			when(sizeService.editSize(sizeModel)).thenThrow(Exception.class);

			mockMvc.perform(post("/size/edit").contentType(
					MediaType.APPLICATION_JSON).content(requestJson));
		}
		
		@SuppressWarnings("unchecked")
		@Test
		public void  testdelete() throws Exception {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			mockMvc.perform(
					post("/size/delete/{sizecode}",1).contentType(MediaType.APPLICATION_JSON)
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
