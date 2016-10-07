package superheroTeams.servicesTesting;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



import superheroTeams.dao.SuperheroDaoImpl;
import superheroTeams.entities.Power;
import superheroTeams.entities.Superhero;
import superheroTeams.entities.SuperheroTeam;
import superheroTeams.services.SuperheroServicesImpl;
import superheroTeams.validation.Validation;

public class ServiceTests {
	
	SuperheroServicesImpl service;
	SuperheroDaoImpl mockDao;
	Validation  mockValid;
	  

	    @Before
	    public void setup() {
	        service = new SuperheroServicesImpl();
	        mockDao = mock(SuperheroDaoImpl.class);
	        service.setDao(mockDao);
	        mockValid = mock(Validation.class);  
	        service.setValidation(mockValid);
	    }
	    

		@Test
		public void testPowerInUseHappy() {
			int id = 1;
			List<Superhero> heros = new ArrayList<Superhero>();
			Superhero hero = new Superhero();
			Power power = new Power();
			power.setId(1);
			List<Power> powers = new ArrayList<Power>();
			powers.add(power);
			hero.setPowers(powers);
			heros.add(hero);
			when(mockDao.getHeros()).thenReturn(heros);
			assertTrue(service.powerInUse(id));
		}
		
		@Test
		public void testPowerInUseSad() {
			int id = 5;
			List<Superhero> heros = new ArrayList<Superhero>();
			Superhero hero = new Superhero();
			Power power = new Power();
			power.setId(1);
			List<Power> powers = new ArrayList<Power>();
			powers.add(power);
			hero.setPowers(powers);
			heros.add(hero);
			when(mockDao.getHeros()).thenReturn(heros);
			assertFalse(service.powerInUse(id));
		}
		
		@Test
		public void testHeroInUseHappy() {
			int id = 1;
			List<SuperheroTeam> teams = new ArrayList<SuperheroTeam>();
			SuperheroTeam team = new SuperheroTeam();
			List<Superhero> heros = new ArrayList<Superhero>();
			Superhero hero = new Superhero();
			hero.setId(1);
			heros.add(hero);
			team.setTeam(heros);
			teams.add(team);
			when(mockDao.getTeams()).thenReturn(teams);
			assertTrue(service.heroInUse(id));
		}
		
		@Test
		public void testHeroInUseSad() {
			int id = 9;
			List<SuperheroTeam> teams = new ArrayList<SuperheroTeam>();
			SuperheroTeam team = new SuperheroTeam();
			List<Superhero> heros = new ArrayList<Superhero>();
			Superhero hero = new Superhero();
			hero.setId(1);
			heros.add(hero);
			team.setTeam(heros);
			teams.add(team);
			when(mockDao.getTeams()).thenReturn(teams);
			assertFalse(service.heroInUse(id));
		}
		
		
		@Test
		public void testUniqueConstraintPower() {
			Power power1 = new Power();
			power1.setName("name");
			Power power2 = new Power();
			power2.setName("name");
			List<Power> powers = new ArrayList<Power>();
			powers.add(power1); 
			when(mockDao.getPowers()).thenReturn(powers);
			assertTrue(service.uniqueConstraintPower(power2));
		}
		
		
}
