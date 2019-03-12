package com.chori.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import com.chori.model.FileBucket;
import com.chori.model.GarmentstyleModel;
import com.chori.model.GarmentstyleaccessorydetailModel;
import com.chori.service.GarmentstyleService;
import com.chori.service.GarmentstyleaccessorydetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class GarmentstyleControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private GarmentstyleController controller;
	
	@Mock
	GarmentstyleService service;
	
	@Mock
	GarmentstyleaccessorydetailService garmentstyleaccessorydetailService;
	
	@Mock
	@Qualifier("garmentstyleValidator")
	private Validator garmentstyleValidator;
	
	@Mock
	@Qualifier("garmentstyleaccessorydetailValidator")
	private Validator garmentstyleaccessorydetailValidator;
	
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
	public void testGetAllGarmentstyle() throws Exception {
		mockMvc.perform(get("/garmentstyle/list")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllGarmentstyleException() throws Exception {
		when(service.getAllGarmentstyleModel()).thenThrow(Exception.class);
		mockMvc.perform(get("/garmentstyle/list"));
	}

	@Test
	public void testListGarmentstyle() throws Exception {
		mockMvc.perform(get("/listGarmentstyle")).andExpect(status().isOk());
	}

	@Test
	public void testGetGarmentstyleDetail() throws Exception {
		mockMvc.perform(get("/garmentstyle/detail/{garmentstyleCode}", "gsc")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetGarmentstyleDetailException() throws Exception {
		when(service.findGarmentstyleModelById("gsc")).thenThrow(Exception.class);
		mockMvc.perform(get("/garmentstyle/detail/{garmentstyleCode}", "gsc"));
	}

	@Test(expected = Exception.class)
	public void testEditGarmentstyle() throws Exception {
		
		MultipartFile mpf= new MultipartFile() {
			
			@Override
			public void transferTo(File dest) throws IOException, IllegalStateException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public long getSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getOriginalFilename() {
				// TODO Auto-generated method stub
				return "a.jpg";
			}
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public InputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public byte[] getBytes() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		FileBucket fb= new FileBucket();
		fb.setFile(mpf);
		
		GarmentstyleModel newGarmentstyleModel =new GarmentstyleModel();
		newGarmentstyleModel.setGarmentstylecode("gsc");
		newGarmentstyleModel.getFiles().add(fb);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(newGarmentstyleModel);
		
		mockMvc.perform(post("/garmentstyle/edit").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(
				status().isOk());
	}

	@Test
	public void testIsGarmentstyleExist() throws Exception {
		mockMvc.perform(get("/garmentstyle/isExist/{garmentstyleCode}", "gsc")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsGarmentstyleExistException() throws Exception {
		when(service.isGarmentStyleExistedById("gsc")).thenThrow(Exception.class);
		mockMvc.perform(get("/garmentstyle/isExist/{garmentstyleCode}", "gsc")).andExpect(
				status().isOk());
	}

	@Test(expected = Exception.class)
	public void testAddGarmentstyle() throws Exception {
		GarmentstyleModel newGarmentstyleModel =new GarmentstyleModel();
		newGarmentstyleModel.setGarmentstylecode("gsc");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(newGarmentstyleModel);
		
		mockMvc.perform(post("/garmentstyle/add").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(
				status().isOk());
	}

	@Test
	public void testDeleteGarmentstyle() throws Exception {
		mockMvc.perform(post("/garmentstyle/delete/{garmentstyleCode}", "gsc").contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDeleteGarmentstyleException() throws Exception {
		when(service.deleteGarmentstyle("gsc")).thenThrow(Exception.class);
		mockMvc.perform(post("/garmentstyle/delete/{garmentstyleCode}", "gsc").contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testCopyGarmentstyle() throws Exception {
		GarmentstyleModel newGarmentstyleModel =new GarmentstyleModel();
		newGarmentstyleModel.setGarmentstylecode("gsc");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(newGarmentstyleModel);
		
		mockMvc.perform(post("/garmentstyle/copy/{oldGarmentstyleCode}", "gsc").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testCopyGarmentstyleException() throws Exception {
		GarmentstyleModel newGarmentstyleModel = null;
//		newGarmentstyleModel.setGarmentstylecode("gsc");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(newGarmentstyleModel);
		
		when(service.copyGarmentstyle(newGarmentstyleModel, "gsc", "admin")).thenThrow(Exception.class);
		mockMvc.perform(post("/garmentstyle/copy/{oldGarmentstyleCode}", "gsc").contentType(MediaType.APPLICATION_JSON).content(requestJson));
	}

	@Test
	public void testGetListAccessoryForGarmentstyle() throws Exception {
		mockMvc.perform(get("/garmentstyle/listAccessoryForGarment/{garmentStyleName}", "gsc")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetListAccessoryForGarmentstyleException() throws Exception {
		when(garmentstyleaccessorydetailService.getListAccessoryForGarmentStyle("gsc")).thenThrow(Exception.class);
		mockMvc.perform(get("/garmentstyle/listAccessoryForGarment/{garmentStyleName}", "gsc")).andExpect(
				status().isOk());
	}

	@Test
	public void testGetListSizeByGarmentAccessoryCustomer() throws Exception {
		mockMvc.perform(get("/garmentstyle/listSizeByGarmentAccessoryCustomer/{garmentStyleCode}/{accessoryCode}/{customerCode}", "gsc", "gsc", "gsc")).andExpect(
				status().isOk());
	}
	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testGetListSizeByGarmentAccessoryCustomerException() throws Exception {
//		when(garmentstyleaccessorydetailService.getListSizeByGarmentAccessoryCustomer("gsc", "gsc", "gsc")).thenThrow(Exception.class);
//		mockMvc.perform(get("/garmentstyle/listSizeByGarmentAccessoryCustomer/{garmentStyleCode}/{accessoryCode}/{customerCode}", "gsc", "gsc", "gsc"));
//	}

	@Test
	public void testGetListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName() throws Exception {
		mockMvc.perform(get("/garmentstyle/listGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName/{garmentStyleCode}/{accessoryCode}", "gsc", "gsc")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryNameException() throws Exception {
		when(garmentstyleaccessorydetailService.getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName("gsc", "gsc")).thenThrow(Exception.class);
		mockMvc.perform(get("/garmentstyle/listGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryName/{garmentStyleCode}/{accessoryCode}", "gsc", "gsc"));
	}

	@Test
	public void testGetListGarmentstyleaccessorydetailByGarmentStyleName() throws Exception {
		mockMvc.perform(get("/garmentstyle/listGarmentstyleaccessorydetailByGarmentStyleName/{garmentStyleCode}", "gsc")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetListGarmentstyleaccessorydetailByGarmentStyleNameException() throws Exception {
		when(garmentstyleaccessorydetailService.getListGarmentstyleaccessorydetailByGarmentStyleName("gsc")).thenThrow(Exception.class);
		mockMvc.perform(get("/garmentstyle/listGarmentstyleaccessorydetailByGarmentStyleName/{garmentStyleCode}", "gsc")).andExpect(
				status().isOk());
	}

	@Test
	public void testAddNewGarmentstyleaccessorydetail() throws Exception {
		GarmentstyleaccessorydetailModel GSADmodel = new GarmentstyleaccessorydetailModel();
		GSADmodel.setGarmentstyleaccessorydetailcode(1);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(GSADmodel);
		
		mockMvc.perform(post("/garmentstyleaccessorydetail/add").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testAddNewGarmentstyleaccessorydetailException() throws Exception {
		GarmentstyleaccessorydetailModel GSADmodel = new GarmentstyleaccessorydetailModel();
		GSADmodel.setGarmentstyleaccessorydetailcode(1);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(GSADmodel);
		
		when(garmentstyleaccessorydetailService.addNewGarmentstyleaccessorydetail(GSADmodel, "admin")).thenThrow(Exception.class);
		
		mockMvc.perform(post("/garmentstyleaccessorydetail/add").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(
				status().isOk());
	}

	@Test
	public void testIsAllSizeAlreadyAssignedForAdd() throws Exception {
		mockMvc.perform(get("/garmentstyleaccessorydetail/isAllSizeAlreadyAssigned/{garmentCode}/{accessoryCode}/{customerCode}", "gsc", "gsc", "gsc")).andExpect(
				status().isOk());
	}
	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testIsAllSizeAlreadyAssignedForAddException() throws Exception {
//		when(garmentstyleaccessorydetailService.isAllSizeAlreadyAssigned("gsc", "gsc", "gsc")).thenThrow(Exception.class);
//		mockMvc.perform(get("/garmentstyleaccessorydetail/isAllSizeAlreadyAssigned/{garmentCode}/{accessoryCode}/{customerCode}", "gsc", "gsc", "gsc")).andExpect(
//				status().isOk());
//	}

	@Test
	public void testEditGarmentstyleaccessorydetail() throws Exception {
		GarmentstyleaccessorydetailModel GSADmodel = new GarmentstyleaccessorydetailModel();
		GSADmodel.setGarmentstyleaccessorydetailcode(1);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(GSADmodel);
		
		mockMvc.perform(post("/garmentstyleaccessorydetail/edit").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testEditGarmentstyleaccessorydetailException() throws Exception {
		GarmentstyleaccessorydetailModel GSADmodel = new GarmentstyleaccessorydetailModel();
		GSADmodel.setGarmentstyleaccessorydetailcode(1);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(GSADmodel);
		
		when(garmentstyleaccessorydetailService.editGarmentstyleAccessoryDetail(GSADmodel, "admin")).thenThrow(Exception.class);
		
		mockMvc.perform(post("/garmentstyleaccessorydetail/edit").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(
				status().isOk());
	}

	@Test
	public void testDeleteGarmentstyleaccessorydetail() throws Exception {
		mockMvc.perform(post("/garmentstyleaccessorydetail/delete/{id}", 1).contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDeleteGarmentstyleaccessorydetailException() throws Exception {
		when(garmentstyleaccessorydetailService.deleteGSAD(1)).thenThrow(Exception.class);
		mockMvc.perform(post("/garmentstyleaccessorydetail/delete/{id}", 1).contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}

	@Test
	public void testGetListAccessoryGroupByGarmentStyleName() throws Exception {
		mockMvc.perform(get("/garmentstyle/getListAccessoryGroupByGarmentStyleName/{garmentStyleCode}", "gsc").contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetListAccessoryGroupByGarmentStyleNameexception() throws Exception {
		when(garmentstyleaccessorydetailService.getAccessoryGroupByGarmentStyleCode("gsc")).thenThrow(Exception.class);
		mockMvc.perform(get("/garmentstyle/getListAccessoryGroupByGarmentStyleName/{garmentStyleCode}", "gsc").contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}

	@Test
	public void testGetListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName() throws Exception {
		mockMvc.perform(get("/garmentstyle/listGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName/{garmentStyleCode}/{accessoryGroupCode}", "gsc", "gsc").contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
		mockMvc.perform(get("/garmentstyle/listGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName/{garmentStyleCode}/{accessoryGroupCode}", "gsc", "All").contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupNameException() throws Exception {
		when(garmentstyleaccessorydetailService.getListGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName("gsc", "gsc")).thenThrow(Exception.class);
		mockMvc.perform(get("/garmentstyle/listGarmentstyleaccessorydetailByGarmentStyleNameAndAccessoryGroupName/{garmentStyleCode}/{accessoryGroupCode}", "gsc", "gsc").contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}
}
