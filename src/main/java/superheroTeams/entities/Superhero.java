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
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Type;

@Entity
public class Superhero {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
    @Column(nullable = false, unique = true, length=255)
    String heroName;
    @Column(nullable = false, unique = true, length=255)
    String mutantName;
    @Column(nullable = false, length=255)
    String originType;
    @Type(type="text")
    @Column(nullable = false, length=255)
    String costumeImage;
    @Column(nullable = false)
    int height;
    @Column(nullable = false)
    int weight;
    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "superhero_id")},
    inverseJoinColumns = {@JoinColumn(name = "power_id")})
    List<Power> powers;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHeroName() {
		return heroName;
	}
	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}
	public String getMutantName() {
		return mutantName;
	}
	public void setMutantName(String mutantName) {
		this.mutantName = mutantName;
	}
	public String getOriginType() {
		return originType;
	}
	public void setOriginType(String originType) {
		this.originType = originType;
	}
	public String getCostumeImage() {
		return costumeImage;
	}
	public void setCostumeImage(String costumeImage) {
		this.costumeImage = costumeImage;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public List<Power> getPowers() {
		return powers;
	}
	public void setPowers(List<Power> powers) {
		this.powers = powers;
	}
    
}
