package superheroTeams.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import superheroTeams.entities.*;
import superheroTeams.dao.SuperheroDao;
import superheroTeams.validation.Validation;
	@Service
public class SuperheroServicesImpl implements SuperheroServices{
	@Autowired
	Validation valid;
	public void setValidation(Validation valid) {
		this.valid = valid;
	}
	@Autowired
	SuperheroDao dao;

	public void setDao(SuperheroDao dao) {
		this.dao = dao;
	}

	@Override
	public List<SuperheroTeam> getTeams() {
		return dao.getTeams();
	}

	@Override
	public SuperheroTeam getTeam(int id) {
		return dao.getTeam(id);
	}

	@Override
	public String addTeam(SuperheroTeam team) {
		if(heroOnTeamAlready(team)){
			return "Error: Cannot add hero to two or more teams";
		}
		else{
		dao.addTeam(team);
		return"";
		}
	}

	@Override
	public void deleteTeam(int id) {
		dao.deleteTeam(id);
	}

	@Override
	public String updateTeam(SuperheroTeam team) {
		if(heroOnTeamAlready(team)){
			return "Error: Cannot add hero to two or more teams";
		}
		else{
		dao.updateTeam(team);
		return"";
		}
	}

	@Override
	public List<Superhero> getHeros() {
		return dao.getHeros();
	}

	@Override
	public Superhero getHero(int id) {
		return dao.getHero(id);
	}

	@Override
	public String addHero(Superhero hero) {
		List<Superhero> heros = getHeros();
		for(Superhero h: heros){
			if((h.getHeroName().equals(hero.getHeroName()) || (h.getMutantName().equals(hero.getMutantName())))){
				return "This hero name or mutant name is already in use";
			}
		}
		dao.addHero(hero);
		return null;
	}

	@Override
	public String deleteHero(int id) {
		if(heroInUse(id)){
			return "Error: Hero is currently on team and cannot be deleted.";
				}
		else{
		dao.deleteHero(id);
		return"";
		}
	}

	@Override
	public String updateHero(Superhero hero) {
		List<Superhero> heros = getHeros();
		for(Superhero h: heros){
			if((h.getHeroName().equals(hero.getHeroName()) || (h.getMutantName().equals(hero.getMutantName())))){
				if(hero.getId() != h.getId()){
					return "This hero name or mutant name is already in use";
				}
			}
		}
		dao.updateHero(hero);
		return null;
	}

	@Override
	public List<Power> getPowers() {
		return dao.getPowers();
	}

	@Override
	public Power getPower(int id) {
		return dao.getPower(id);
	}

	@Override
	public String addPower(Power power) {
		if(uniqueConstraintPower(power)){
			return "Error: enter a unique power name.";
		}
		else{
		dao.addPower(power);
		return "";
		}
	}

	@Override
	public String deletePower(int id) {
		if(powerInUse(id)){
			return "Error: Power is currently usedby a hero and cannot be deleted.";
		}
	else{
		dao.deletePower(id);
		return "";
		}
	}

	@Override
	public String updatePower(Power power) {
		if(uniqueConstraintPower(power)){
			return "Error: enter a unique power name.";
		}
		else{
		dao.updatePower(power);
		return "";
		}
	}

	@Override
	public List<PowerType> getPowerTypes() {
		return dao.getPowerTypes();
	}
	public boolean heroOnTeamAlready(SuperheroTeam teamNew){
		List<SuperheroTeam> teams = dao.getTeams();
		for(SuperheroTeam teamOld: teams){
			List<Superhero> herosOld = teamOld.getTeam();
			herosOld.add(teamOld.getTeamLeader());
			for(Superhero heroOld: herosOld){
				for(Superhero heroNew: teamNew.getTeam()){
					if(heroNew.getId()==heroOld.getId() && teamOld.getId()!=teamNew.getId()){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean powerInUse(int id){
		List<Superhero> heros = dao.getHeros();
		for(Superhero hero: heros){
			List<Power> powers = hero.getPowers();
			for(Power power: powers){
				if(power.getId()==id){
					return true;
				}
			}
		}
		return false;
	}
	public boolean heroInUse(int id){
		List<SuperheroTeam> teams = dao.getTeams();
		for(SuperheroTeam team: teams){
			List<Superhero> heros = team.getTeam();
			heros.add(team.getTeamLeader());
			for(Superhero hero: heros){
				if(hero.getId()==id){
					return true;
				}
			}
		}
		return false;
	}
	public boolean uniqueConstraintPower(Power p){
		List<Power> powers = dao.getPowers();
		for(Power power: powers){
			if(power.getName().equals(p.getName())){
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> search(Search search){
		if(search.getType().equals("power")){
			return (List<T>) dao.searchPowers(search);
		}
		if(search.getType().equals("hero")){
			return (List<T>) dao.searchHeros(search);
		}
		if(search.getType().equals("team")){
			return (List<T>) dao.searchTeams(search);
		}
		else{
			return null;
		}
	}


}
