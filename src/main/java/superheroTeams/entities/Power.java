package superheroTeams.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Power {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
    @Column(nullable = false, unique = true, length=255)
    String name;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn
	PowerType type;
	String description;
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
	public PowerType getType() {
		return type;
	}
	public void setType(PowerType type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
