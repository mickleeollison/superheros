package superheroTeams.services;

import java.util.List;

import superheroTeams.entities.Power;
import superheroTeams.entities.PowerType;
import superheroTeams.entities.Search;
import superheroTeams.entities.Superhero;
import superheroTeams.entities.SuperheroTeam;

public interface SuperheroServices {
	List<SuperheroTeam> getTeams();
	SuperheroTeam getTeam(int id);
	String addTeam(SuperheroTeam team);
	void deleteTeam(int id);
	String updateTeam(SuperheroTeam team); 
	List<Superhero> getHeros();
	Superhero getHero(int id);
	String addHero(Superhero hero);
	String deleteHero(int id);
	String updateHero(Superhero hero);
	List<Power> getPowers();
	Power getPower(int id);
	String addPower(Power power);
	String deletePower(int id);
	String updatePower(Power power);
	List<PowerType> getPowerTypes();
	<T> List<T> search(Search search);
}
