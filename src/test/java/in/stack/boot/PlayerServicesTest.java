package in.stack.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.stack.boot.entity.Player;
import in.stack.boot.repository.PlayerRepo;
import in.stack.boot.services.PlayerService;


@SpringBootTest
@AutoConfigureMockMvc
public class PlayerServicesTest {

	@MockBean
	 private PlayerRepo repo;
	
	@Autowired 
	private PlayerService service;
	
	private static ObjectMapper mapper=new ObjectMapper();
	 
	@Autowired
	private MockMvc mockMvc;
	
	
	//POSITIVE TEAT CASE
	//Testing Model class
	  @Test	
	  public void testModel() {
		  Player p= Mockito.mock(Player.class);
		  assertNotNull(p);
	  }
	  

		//POSITIVE TEST CASE
	  //Testing Srvice class
	@Test
	public void serviceTest() {
		PlayerService service=Mockito.mock(PlayerService.class);
		assertNotNull(service);
	}
	

	//POSITIVE TEST CASE
	//Test for adding Player  {Save method}
	@Test
	public void addPlayerTest() {
		Player p=new Player(101,"yuvraj","MI","Bowler",8);
		Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(p);
		  
		 
		
		 
		 Player test=service.savePlayer(p);
		 assertEquals(101,test.getId());
		 
	}

	//NEGATIVE TEST CASE
	@Test
	public void addPlayerTestNeg() {
		Player p=new Player(101,"yuvraj","MI","Bowler",8);
		Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(p);
		  
		 
		
		 
		 Player test=service.savePlayer(p);
		 assertNotEquals(102,test.getId());
		 
	}
	

	//POSITIVE TEST CASE
	//Test for add players {SaveAll method}
	 @Test
	 public void addPlayerAllTest() {
		 List<Player> players=new ArrayList<>();
		 players.add(new Player(101,"yuvraj","MI","Bowler",8));
		 players.add(new Player(102,"virat","MI","Bowler",5));
		 
		 when(repo.saveAll(ArgumentMatchers.any())).thenReturn(players);
		 
		 List<Player> plist=service.saveAllPlayer(players);
				 assertTrue(plist.size()==2);
		 
	 }

		//POSITIVE TEST CASE
	 // Test for getting all players (FINDALL METHOD)
	 
	 @Test	
	  public void getAllPlayersTest(){
		  
		  List <Player> players=new ArrayList<Player>();
		   players.add(new Player(101, "yuvraj","MI", "Batsmen",4));
		   players.add(new Player(102, "Ishan","MI", "Batsmen",8));
		   
		   // Mocking the service level data
		   Mockito.when(repo.findAll()).thenReturn(players);
		   
		     // Mocking the Http Call
		   assertEquals(2,service.getPlayers().size());
		  
	  }
	 //NEGATIVE TEST CASE
	 @Test	
	  public void getAllPlayersTestNeg(){
		  
		  List <Player> players=new ArrayList<Player>();
		   players.add(new Player(101, "yuvraj","MI", "Batsmen",4));
		   players.add(new Player(102, "Ishan","MI", "Batsmen",8));
		   
		   // Mocking the service level data
		   Mockito.when(repo.findAll()).thenReturn(players);
		   
		     // Mocking the Http Call
		   assertNotEquals(3,service.getPlayers().size());
		  
	  }
	 //PSITIVE TEST CASE
	 //Test finfbyid method
	 @Test	
	  public void getAllPlayersByIdTest(){
		  
		  
		  Optional<Player> p=Optional.ofNullable(new Player(101, "yuvraj","MI", "Batsmen",4));
		   //players.add(new Player(102, "Ishan","MI", "Batsmen",8));
		   
		   // Mocking the service level data
		  Mockito.when(repo.findById(ArgumentMatchers.any())).thenReturn(p);
		 // Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(p);
		  
		     // Mocking the Http Call
		  Player test=service.getPlayerById(101);
		  assertEquals(test.getName(),p.get().getName());
		  
	  }
	 
	 //NEGATIVE TEST CASE
	 //Test findbyid method negative
	 @Test	
	  public void getAllPlayersByIdTestNeg(){
		  
		  
		  Optional<Player> p=Optional.ofNullable(new Player(101, "yuvraj","MI", "Batsmen",4));
		   //players.add(new Player(102, "Ishan","MI", "Batsmen",8));
		   
		   // Mocking the service level data
		  Mockito.when(repo.findById(ArgumentMatchers.any())).thenReturn(p);
		 // Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(p);
		  
		     // Mocking the Http Call
		  Player test=service.getPlayerById(102);
		  assertNotEquals(test.getName(),"dhoni");
		  
	  }
	 
	/* @Test	
	  public void getAllPlayersByNameTest(){
		  
		 List <Player> players=new ArrayList<Player>();
		   players.add(new Player(101, "yuvraj","MI", "Batsmen",4));
		   players.add(new Player(102, "Ishan","MI", "Batsmen",8));
		   String name="yuvraj";
		   
		   // Mocking the service level data
		   Mockito.when(repo.findByName("yuvraj"));
		   
		     // Mocking the Http Call
		 
		  assertEquals("yuvraj",service.getPlayerByName("yuvraj"));
		  
	  }
	 */
	 
	 
}
