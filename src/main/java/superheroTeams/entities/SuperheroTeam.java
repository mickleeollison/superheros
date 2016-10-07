package superheroTeams.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
	@Entity
public class SuperheroTeam {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

    @Column(nullable = false, unique = true, length=255)
    String name;
    @Column(nullable = false, unique = true, length=255)
	String headquarters;
    @Column(nullable = false, unique = true, length=255)
	String publicSupportLevel;
    
    @OneToOne
    @JoinColumn
    Superhero teamLeader;
    
    @OneToMany
    @JoinTable
    List<Superhero> team;
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
	public String getHeadquarters() {
		return headquarters;
	}
	public void setHeadquarters(String headquarters) {
		this.headquarters = headquarters;
	}
	public String getPublicSupportLevel() {
		return publicSupportLevel;
	}
	public void setPublicSupportLevel(String publicSupportLevel) {
		this.publicSupportLevel = publicSupportLevel;
	}
	public Superhero getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(Superhero teamLeader) {
		this.teamLeader = teamLeader;
	}
	public List<Superhero> getTeam() {
		return team;
	}
	public void setTeam(List<Superhero> team) {
		this.team = team;
	}
    
	
	
}
