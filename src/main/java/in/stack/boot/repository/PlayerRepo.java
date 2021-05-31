package in.stack.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.stack.boot.entity.Player;

public interface PlayerRepo extends JpaRepository<Player,Integer> {
	
    Player findByName(String name);


}
