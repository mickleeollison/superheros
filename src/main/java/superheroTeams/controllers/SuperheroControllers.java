package superheroTeams.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import superheroTeams.entities.*;
import superheroTeams.services.SuperheroServices;
@RestController
public class SuperheroControllers {

	@Autowired
	SuperheroServices service;

	public void setServices(SuperheroServices service) {
		this.service = service;
	}
	@RequestMapping(value="/teams", method=RequestMethod.GET)
	public List<SuperheroTeam> getTeams() {
			return	service.getTeams();
	}

	@RequestMapping(value="/teams/{id}", method=RequestMethod.GET)
	public SuperheroTeam getTeam(@PathVariable Integer id) {
		return service.getTeam(id);
	}

	@RequestMapping(value="/teams", method=RequestMethod.POST)
	public String addTeam(@RequestBody SuperheroTeam team) {
		return service.addTeam(team);
	}

	@RequestMapping(value="/teams/{id}", method=RequestMethod.DELETE)
	public void deleteBig(@PathVariable Integer id) {
		service.deleteTeam(id);
	}

	@RequestMapping(value="/teams/{id}", method=RequestMethod.PUT)
	public String updateTeam(@PathVariable Integer id, @RequestBody SuperheroTeam team) {
		team.setId(id);
		return service.updateTeam(team);
	}

	@RequestMapping(value="/heros", method=RequestMethod.GET)
	public List<Superhero> getHeros() {
		return service.getHeros();
	}

	@RequestMapping(value="/heros/{id}", method=RequestMethod.GET)
	public Superhero getHero(@PathVariable Integer id) {
		return service.getHero(id);
	}

	@RequestMapping(value="/heros", method=RequestMethod.POST)
	public String addHero(@RequestBody Superhero hero) {
		return service.addHero(hero);
	}
	
	@RequestMapping(value="/heros/{id}", method=RequestMethod.PUT)
	public String updateHero(@PathVariable Integer id, @RequestBody Superhero hero) {
		hero.setId(id);
		return service.updateHero(hero);
	}

	@RequestMapping(value="/heros/{id}", method=RequestMethod.DELETE)
	public String deleteHero(@PathVariable Integer id) {
		String ret = service.deleteHero(id);
		System.out.println(ret);
		
		return ret;	
	}
	
	@RequestMapping(value="/powers", method=RequestMethod.GET)
	public List<Power> getPowers() {
		return service.getPowers();
	}

	@RequestMapping(value="/powers/{id}", method=RequestMethod.GET)
	public Power getPower(@PathVariable Integer id) {
		return service.getPower(id);
	}

	@RequestMapping(value="/powers", method=RequestMethod.POST)
	public String addPower(@RequestBody Power power) {
		return service.addPower(power);
	}
	
	@RequestMapping(value="/powers/{id}", method=RequestMethod.PUT)
	public void updatePower(@PathVariable Integer id, @RequestBody Power power) {
		power.setId(id);
		service.updatePower(power);
	}

	@RequestMapping(value="/powers/{id}", method=RequestMethod.DELETE)
	public String deletePower(@PathVariable Integer id) {
		return service.deletePower(id);		
	}
	
	@RequestMapping(value="/powertypes", method=RequestMethod.GET)
	public List<PowerType> getPowersTypes() {
		return service.getPowerTypes();
	}
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public <T> List<T> Search(@RequestBody Search search) {
		return service.search(search);
	}

}
