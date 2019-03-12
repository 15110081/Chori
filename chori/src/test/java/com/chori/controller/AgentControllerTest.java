//package com.chori.controller;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.hibernate.SessionFactory;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.View;
//
//import com.chori.model.AgentModel;
//import com.chori.model.DestinationModel;
//import com.chori.service.AgentService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.fasterxml.jackson.databind.SerializationFeature;
//
//public class AgentControllerTest {
//	
//	private MockMvc mockMvc;
//	
//	@InjectMocks
//	private AgentController controller;
//	
//	@Mock
//	AgentService ser;
//	
//	@Mock
//	View mockView;
//
//	@Mock
//	SessionFactory sessionFactory;
//
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(controller)
//				.setSingleView(mockView).build();
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void testGetAllAgent() throws Exception {
//		mockMvc.perform(get("/agent/list")).andExpect(status().isOk());
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testGetAllAgentException() throws Exception {
//		when(ser.getAllAgentModel()).thenThrow(Exception.class);
//		mockMvc.perform(get("/agent/list"));
//	}
//
//
//	@Test
//	public void testListAgent() throws Exception{
//		mockMvc.perform(get("/listAgent")).andExpect(status().isOk());
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testListAgentException() throws Exception {
//		when(ser.getClass()).thenThrow(Exception.class);
//	       mockMvc.perform(get("/listAgent"));     
//	}
//
//	@Test
//	public void testAddNewAgent() throws Exception{
//		AgentModel am = new AgentModel();
//		am.setShortname("Choriiii");
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//		String requestJson = ow.writeValueAsString(am);
//
//		mockMvc.perform(
//				post("/agent/add").contentType(MediaType.APPLICATION_JSON)
//						.content(requestJson)).andExpect(status().isOk());
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testAddNewAgentException() throws Exception {
//		AgentModel am = null;
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//		String requestJson = ow.writeValueAsString(am);
//		when(controller.addNewAgent(am)).thenThrow(Exception.class);
//		mockMvc.perform(post("/agent/add").contentType(
//				MediaType.APPLICATION_JSON).content(requestJson));
//	}
//
//	@Test
//	public void testGetAgentDetail() throws Exception{
//		mockMvc.perform(get("/agent/detail/{agentCode}", 1)).andExpect(
//				status().isOk());
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testGetAgentDetailException() throws Exception {
//		when(ser.findAgentModelById(null)).thenThrow(Exception.class);
//		mockMvc.perform(get("/agent/detail/{agentCode}", null));
//	}
//
//	@Test
//	public void testEditAgent() throws Exception {
//		AgentModel am = new AgentModel();
//		am.setShortname("Thai");
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//		String requestJson = ow.writeValueAsString(am);
//
//		mockMvc.perform(
//				post("/agent/edit").contentType(MediaType.APPLICATION_JSON)
//						.content(requestJson)).andExpect(status().isOk());
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testEditAgentException() throws Exception {
//		AgentModel am = null;
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//		String requestJson = ow.writeValueAsString(am);
//
//		when(ser.editAgent(am)).thenThrow(Exception.class);
//
//		mockMvc.perform(post("/agent/edit").contentType(
//				MediaType.APPLICATION_JSON).content(requestJson));
//	}
//
//	@Test
//	public void testDeleteAgent() throws Exception{
//		mockMvc.perform(post("/agent/delete/{agentCode}", 2).contentType(MediaType.APPLICATION_JSON)).andExpect(
//				status().isOk());
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testDeleteAgentException() throws Exception {
//		when(ser.deleteAgent(4)).thenThrow(Exception.class);
//		mockMvc.perform(post("/agent/delete/{agentCode}", 4).contentType(MediaType.APPLICATION_JSON));
//	}
//
//	@Test
//	public void testIsAgentExist() throws Exception {
//		mockMvc.perform(get("/agent/isExist/{agentCode}", 1)).andExpect(
//				status().isOk());
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test(expected = Exception.class)
//	public void testIsAgentExistException() throws Exception {
//		when(ser.isAgentExistedByShortname("aaaa")).thenThrow(Exception.class);
//		mockMvc.perform(get("/agent/isExist/shortName", "aaaa"));
//	}
//
//}
