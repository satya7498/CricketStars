package in.stack.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.stack.boot.entity.Player;
import in.stack.boot.services.PlayerService;


@RestController

//Basic URL:localhost:9191/players/
@RequestMapping("/player")


public class PlayerController {
	
	
	@Autowired
	private PlayerService service;
 
	
	//Adding single player
	@PostMapping("/add")
	public Player addPlayer(@RequestBody Player player) {
		
		return service.savePlayer(player);
	}
	
	//adding list of players
	@PostMapping("/all")
	public List<Player> addPlayers(@RequestBody List<Player> players){
		return service.saveAllPlayer(players);
	}
	
	
	//Retrieving List of players

	@GetMapping("/all")
	public List<Player> getAllPlayers(){
		return service.getPlayers();
	}
     
	
	//Retrieving player by id
	
	@GetMapping("/{id}")
	public Player getPlayerByID(@PathVariable int id){
		return service.getPlayerById(id);
	}
	
	
	//deleting player by id
	
	@DeleteMapping("/{id}")
	public String deletePlayer(@PathVariable int id){
		
		return service.deletePlayer(id);
	}
	
	//updating the player details
	
	@PutMapping("/update")
	public Player updatePlayer(@RequestBody Player player) {
		
		return service.updatePlayer(player);
	}



}
