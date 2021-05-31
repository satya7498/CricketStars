package in.stack.boot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.stack.boot.entity.Player;
import in.stack.boot.repository.PlayerRepo;
//import in.stackroute.ms.repository.PlayerRepository;

@Service

public class PlayerService {
	
	
	@Autowired
	private PlayerRepo repo;
	
	
	// Create single player
	public Player savePlayer(Player player) {
		return repo.save(player);
	}
	
	// Create list of players
		public List<Player> saveAllPlayer(List<Player> players) {
			return repo.saveAll(players);
		}

    //To get all the players
		public List<Player> getPlayers(){
			return repo.findAll();
		}

		//To get player by Id
		
		public Player getPlayerById(int id){
			return repo.findById(id).orElse(null);
		}
		
		//To get player by Name
		public Player getPlayerByName(String name){
			return repo.findByName(name);
		}
		
		//To remove player by Id
		 public String deletePlayer(int id) {
			 repo.deleteById(id);
			 return "Player Id : "+id+" Is Removed";
		 }
		
		//To update the player
		 public Player updatePlayer(Player Player) {
			 
			 Player existingPlayer=repo.findById(Player.getId()).orElse(null);
			 
			 existingPlayer.setName(Player.getName());
			 existingPlayer.setTeam(Player.getTeam());
			 existingPlayer.setProfile(Player.getProfile());
			 existingPlayer.setNoOfMatches(Player.getNoOfMatches());
			 
			 return repo.save( existingPlayer);
		 }
		

		

}
