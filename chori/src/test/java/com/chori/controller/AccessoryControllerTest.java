package com.chori.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import com.chori.model.AccessoryAddModel;
import com.chori.model.FabricsupplierModel;
import com.chori.model.FileBucket;
import com.chori.service.AccessoryService;
import com.chori.service.FabricsupplierService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class AccessoryControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private AccessoryController controller;
	
	@Mock
	AccessoryService ser;

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
	public void testListAccessoryPage() throws Exception {
		mockMvc.perform(get("/listAccessory")).andExpect(status().isOk());
	}

	@Test
	public void testGetAllAccessory() throws Exception {
		mockMvc.perform(get("/accessory/list")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetAllAccessoryException() throws Exception {
		when(ser.getAllAccessory()).thenThrow(Exception.class);
		mockMvc.perform(get("/accessory/list"));
	}

	@Test
	public void testDelete1Accessory() throws Exception {
		mockMvc.perform(post("/accessory/delete/{accessoryCode}", "fsc1")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testDelete1AccessoryException() throws Exception {
		when(ser.deleteAccessory("fsc1")).thenThrow(Exception.class);
		mockMvc.perform(post("/accessory/delete/{accessoryCode}", "fsc1"));
	}

	@Test
	public void testAddNewAccessoryPost() throws Exception {
		AccessoryAddModel fsm = new AccessoryAddModel();
		fsm.setAccessorycode("FS01");
		fsm.setColorcode("black");
		fsm.setUnitcode("pcs");
		fsm.setUserName("admin");
		fsm.setName("AAM");
		fsm.setKind("Internal");
		fsm.setDimension("3x4");
		fsm.setMode("Packing");
		fsm.setImgurl1("a.jpg");
		fsm.setImgurl2("a.jpg");
		fsm.setCreatedate(new Date());
		
		///
		List<FileBucket> lst= new ArrayList<FileBucket>();
		
		FileBucket f= new FileBucket();
		f.setFile(new MultipartFile() {
			
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
		});
		
		FileBucket f1= new FileBucket();
		f1.setFile(new MultipartFile() {
			
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
				return "b.jpg";
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
		});
		
		f.getFile().getOriginalFilename();
		f1.getFile().getOriginalFilename();
		
		lst.add(f);
		lst.add(f1);
		
		fsm.getFiles().addAll(lst);
		///

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(fsm);

		
		
		
//		fsm.setFiles(files);
		
//		mockMvc.perform(
//				post("/accessory/addNewAccessory")).
		this.mockMvc.perform(post("/accessory/addNewAccessory")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(requestJson))
		        .andExpect(status().isOk());
	}

	@Test
	public void testGet1AccessoryModelDetail() throws Exception {
		mockMvc.perform(get("/accessory/detail/{accessorycode}", "fsc")).andExpect(
				status().isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGet1AccessoryModelDetailException() throws Exception {
		when(ser.findAccessoryModelById("fsc")).thenThrow(Exception.class);
		mockMvc.perform(get("/accessory/detail/{accessorycode}", "fsc")).andExpect(
				status().isOk());
	}

//	@Test
//	public void testEditAccessoryPost() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testIsAccessoryExist() throws Exception {
		mockMvc.perform(get("/accessory/isExist/{accessorycode}", "admin")).andExpect(
				status().isOk());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testIsAccessoryExistException() throws Exception {
		when(ser.isAccessoryExistedById("admin")).thenThrow(Exception.class);
		mockMvc.perform(get("/accessory/isExist/{accessorycode}", "admin")).andExpect(
				status().isOk());
	}
}
