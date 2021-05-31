package in.stack.boot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_PLAYER")

public class Player {
	
	
	@Id
	@GeneratedValue

	private int id;
	private String name;
	private String team;
	private String profile;
	private int noOfMatches;
	
	
	
	
	
	public Player(String name, String team, String profile, int noOfMatches) {
		super();
		this.name = name;
		this.team = team;
		this.profile = profile;
		this.noOfMatches = noOfMatches;
	}
	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Player(int id, String name, String team, String profile, int noOfMatches) {
		super();
		this.id = id;
		this.name = name;
		this.team = team;
		this.profile = profile;
		this.noOfMatches = noOfMatches;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public int getNoOfMatches() {
		return noOfMatches;
	}
	public void setNoOfMatches(int noOfMatches) {
		this.noOfMatches = noOfMatches;
	}
	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", team=" + team + ", profile=" + profile + ", noOfMatches="
				+ noOfMatches + "]";
	}
	
	

}
