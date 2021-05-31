package in.stack.boot;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.boolex.Matcher;

import in.stack.boot.controller.PlayerController;
import in.stack.boot.entity.Player;
import in.stack.boot.repository.PlayerRepo;
import in.stack.boot.services.PlayerService;
import in.stack.boot.entity.Player;

@SpringBootTest
@AutoConfigureMockMvc

public class PlayerControllerTest {

	
		
		@MockBean
		 private PlayerRepo repo;
		
		//@Autowired 
		@MockBean
		private PlayerService service;
		private static ObjectMapper mapper=new ObjectMapper();
		 
		@Autowired
		private MockMvc mockMvc;

   //Testing Model class
	  @Test	
	  public void testModel() {
		  Player p= Mockito.mock(Player.class);
		  assertNotNull(p);
	  }
	  
	//Testing controller class 
	  @Test	
	  public void testController() {
		  PlayerController p= Mockito.mock(PlayerController.class);
		  assertNotNull(p);
	  }
	  
	
	 
	  //Testing All Players
	  @Test	
	  public void getAllPlayersTest()throws Exception {
		  
		  List <Player> players=new ArrayList<Player>();
		   players.add(new Player(101, "yuvraj","MI", "Batsmen",4));
		   players.add(new Player(102, "Ishan","MI", "Batsmen",8));
		   
		   // Mocking the service level data
		   Mockito.when(service.getPlayers()).thenReturn(players);
		   
		     // Mocking the Http Call
		   mockMvc.perform(get("/player/all")).andExpect(status().isOk())
		   .andExpect(jsonPath("$",Matchers.hasSize(2)))
		   .andExpect(jsonPath("$[0].name",Matchers.equalTo("yuvraj")))
		   .andExpect(jsonPath("$[1].name",Matchers.equalTo("Ishan")));
		  
		  
	  }
	  // Testing add Player
	  @Test
	  public void testAddPlayer() throws Exception {
		  
		  Player p =new Player(101, "yuvraj","MI", "Batsmen",4);
		  
		  Mockito.when(service.savePlayer(ArgumentMatchers.any())).thenReturn(p);
		  
		  String json_content= mapper.writeValueAsString(p);
		  
		  mockMvc.perform(post("/player/add")
				  .contentType(MediaType.APPLICATION_JSON)
				  .characterEncoding("utf-8").content(json_content)
				  .accept(MediaType.APPLICATION_JSON))
		  .andExpect(status().isOk()).andExpect(jsonPath("$.id",Matchers.equalTo(101)));
		  
	  }
	  
	  // Testing Update Player
	  
	  @Test
	  public void testUpdatePlayer() throws Exception {
		  Player p =new Player(101, "yuvraj","MI", "Batsmen",4);
		  
		
		  
		  Mockito.when(service.updatePlayer(ArgumentMatchers.any())).thenReturn(p);
		  
		  String json_content= mapper.writeValueAsString(p);
		  
		  mockMvc.perform(put("/player/update")
				  .contentType(MediaType.APPLICATION_JSON)
				  .characterEncoding("utf-8").content(json_content)
				  .accept(MediaType.APPLICATION_JSON))
		  .andExpect(status().isOk()).andExpect(jsonPath("$.id",Matchers.equalTo(101)));
		  
	  }
	  
	  //Testing delete player
	  @Test	
	  public void deletePlayersTest()throws Exception {
		  
		  int id=3;
		  
		 Mockito.when(service.deletePlayer(ArgumentMatchers.anyInt())).thenReturn("Player Id : "+id+" Is Removed");
		   
		     // Mocking the Http Call
		  MvcResult res= mockMvc.perform(delete("/player/"+id)).andExpect(status().isOk()).andReturn();
		   
		  String response_result=res.getResponse().getContentAsString();
		  
	     assertEquals(response_result,"Player Id : "+id+" Is Removed");
	  
	  }
	  
	  //Testing delete player Negative
	  @Test	
	  public void deletePlayersTestNeg()throws Exception {
		  
		  int id=3;
		  
		 Mockito.when(service.deletePlayer(ArgumentMatchers.anyInt())).thenReturn("Player Id : "+id+" Is Removed");
		   
		     // Mocking the Http Call
		  MvcResult res= mockMvc.perform(delete("/player/"+id)).andExpect(status().isOk()).andReturn();
		   
		  String response_result=res.getResponse().getContentAsString();

			//POSITIVE TEST CASE
	     assertNotEquals(response_result,"Player Id : "+id+" Is Not Removed");
	  
	  }
	  //Tesing by player Id
	  @Test	
	  public void getPlayerByIdTest()throws Exception {
		  
		  Player p =new Player(101, "yuvraj","MI", "Batsmen",4);
		  
			
		   
		  
		 int id = 101;
		Mockito.when(service.getPlayerById(id)).thenReturn(p);
		     // Mocking the Http Call
		  MvcResult res= mockMvc.perform(get("/player/"+id)).andExpect(status().isOk()).andReturn();
		   
		  String response_result=res.getResponse().getContentAsString();
		  
	     assertEquals(response_result,"{\"id\":101,\"name\":\"yuvraj\",\"team\":\"MI\",\"profile\":\"Batsmen\",\"noOfMatches\":4}");
	  
	  }
	  
	  @Test	
	  public void getPlayerByIdTestNot()throws Exception {
		  
		  Player p =new Player(101, "yuvraj","MI", "Batsmen",4);
		  
			
		   
		  
		 int id = 101;
		Mockito.when(service.getPlayerById(id)).thenReturn(p);
		     // Mocking the Http Call
		  MvcResult res= mockMvc.perform(get("/player/"+id)).andExpect(status().isOk()).andReturn();
		   
		  String response_result=res.getResponse().getContentAsString();
		  
	     assertNotEquals(response_result,"{\"id\":102,\"name\":\"yuvraj\",\"team\":\"MI\",\"profile\":\"Batsmen\",\"noOfMatches\":4}");
	  
	  }
	  
	  
	  @Test	
	  public void getPlayerByNameTest()throws Exception {
		  String name="yuvraj";
		  Player p =new Player(101,"yuvraj","MI","Batsmen",4);
		  
		  Mockito.when(service.getPlayerByName(ArgumentMatchers.anyString())).thenReturn(p);
		  mockMvc.perform(get("/player/"+name)).andExpect(status().isOk());
		 // .andExpect(jsonPath("$.name",Matchers.equalTo("yuvraj")))
		//  .andExpect(jsonPath("$.team",Matchers.equalTo("MI")));
			 
	  }
	
	  
	  

}
