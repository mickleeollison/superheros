package superheroTeams.dao;

import java.util.List;

import superheroTeams.entities.Power;
import superheroTeams.entities.PowerType;
import superheroTeams.entities.Search;
import superheroTeams.entities.Superhero;
import superheroTeams.entities.SuperheroTeam;

public interface SuperheroDao {
	List<SuperheroTeam> getTeams();
	SuperheroTeam getTeam(int id);
	void addTeam(SuperheroTeam team);
	void deleteTeam(int id);
	void updateTeam(SuperheroTeam team); 
	List<Superhero> getHeros();
	Superhero getHero(int id);
	void addHero(Superhero hero);
	void deleteHero(int id);
	void updateHero(Superhero hero);
	List<Power> getPowers();
	Power getPower(int id);
	void addPower(Power power);
	void deletePower(int id);
	void updatePower(Power power);
	List<PowerType> getPowerTypes();
	List<Power> searchPowers(Search search);
	List<Superhero> searchHeros(Search search);
	List<SuperheroTeam> searchTeams(Search search);

}
