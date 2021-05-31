package in.stack.boot.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

public class Favorites {
@Id
@GeneratedValue
	private int id;

     public Favorites(int id) {
	super();
	this.id = id;
	
}

	public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}



	@OneToMany(targetEntity=Player.class,cascade=CascadeType.ALL)
     @JoinColumn(name="xp_fk",referencedColumnName="id")
	private List<Player> fplayers;
	
}
