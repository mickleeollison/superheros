package superheroTeams.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class controllers {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "/home.html";
	}
	@RequestMapping(value = "/power", method = RequestMethod.GET)
	public String power() {
		return "/superhero-power.html";
	}
	@RequestMapping(value = "/team", method = RequestMethod.GET)
	public String team() {
		return "/superhero-team.html";
	}
	@RequestMapping(value = "/hero", method = RequestMethod.GET)
	public String hero() {
		return "/superhero-hero.html";
	}
}
